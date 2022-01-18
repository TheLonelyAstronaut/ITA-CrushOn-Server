package com.itechart.crushon.service.impl

import com.itechart.crushon.dto.explore.AddReactionOutputDTO
import com.itechart.crushon.model.*
import com.itechart.crushon.repository.*
import com.itechart.crushon.service.ExploreService
import com.itechart.crushon.utils.enums.Gender
import com.itechart.crushon.utils.enums.Reactions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import org.hibernate.Criteria
import org.hibernate.SessionFactory
import org.hibernate.criterion.Restrictions
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toFlux
import javax.persistence.criteria.*
import javax.transaction.Transactional

@Service
class ExploreServiceImpl(
    private val cityRepository: CityRepository,
    private val passionRepository: PassionRepository,
    private val userRepository: UserRepository,
    private val viewRepository: ViewRepository,
    private val reactionRepository: ReactionRepository,
    private val matchRepository: MatchRepository,
    private val sessionFactory: SessionFactory,
    private val chatRepository: ChatRepository
): ExploreService {
    override fun getCities(): Flow<City> = cityRepository.findAll().asFlow()

    override fun getPassions(): Flow<Passion> = passionRepository.findAll().asFlow()

    @Transactional
    override fun exploreNewPeople(user: User): Flow<User> {
        val session = sessionFactory.openSession()

        val criteria = session.criteriaBuilder.createQuery(User::class.java)
        val userRoot = criteria.from(User::class.java)

        val reactionsSubquery = generateConstraintSubquery(
            criteria,
            session.criteriaBuilder,
            Reaction::class.java,
            "target",
            "viewer",
            user.id
        )

        val firstSideMatchesSubquery = generateConstraintSubquery(
            criteria,
            session.criteriaBuilder,
            Match::class.java,
            "first",
            "second",
            user.id
        )

        val secondSideMatchesSubquery = generateConstraintSubquery(
            criteria,
            session.criteriaBuilder,
            Match::class.java,
            "second",
            "first",
            user.id
        )

        val viewsSubquery = generateConstraintSubquery(
            criteria,
            session.criteriaBuilder,
            View::class.java,
            "target",
            "viewer",
            user.id
        )

        criteria.select(userRoot)
        criteria.where(
            session.criteriaBuilder.equal(userRoot.get<Int>("gender"), user.getPreferredGender()),
            session.criteriaBuilder.notEqual(userRoot.get<Long>("id"), session.criteriaBuilder.all(reactionsSubquery)),
            session.criteriaBuilder.notEqual(userRoot.get<Long>("id"), session.criteriaBuilder.all(firstSideMatchesSubquery)),
            session.criteriaBuilder.notEqual(userRoot.get<Long>("id"), session.criteriaBuilder.all(secondSideMatchesSubquery)),
            session.criteriaBuilder.notEqual(userRoot.get<Long>("id"), session.criteriaBuilder.all(viewsSubquery))
        )

        criteria.orderBy(session.criteriaBuilder.asc(session.criteriaBuilder.function("RAND", Long::class.java)))

        return session
            .createQuery(criteria)
            .setMaxResults(3)
            .resultStream
            .toFlux()
            .asFlow()
            .map {
                val view = View(user, it)
                viewRepository.save(view)

                it
            }
    }

    override fun addReaction(user: User, reactTo: Long, reaction: Reactions): AddReactionOutputDTO {
        val dbReactTo = userRepository.findById(reactTo).get()
        val dbReaction = reactionRepository.getReactionByViewer(dbReactTo)

        reactionRepository.save(Reaction(user, dbReactTo, reaction))
        viewRepository.delete(viewRepository.findByViewerAndTarget(user, dbReactTo))

        return if(dbReaction != null) {
            if(dbReaction.reaction == reaction && reaction == Reactions.LIKE) {
                matchRepository.save(Match(user, dbReactTo))
                chatRepository.save(Chat(user, dbReactTo))

                AddReactionOutputDTO(
                    true,
                    dbReactTo
                )
            } else {
                AddReactionOutputDTO(
                    false,
                    dbReactTo
                )
            }
        } else {
            AddReactionOutputDTO(
                false,
                dbReactTo
            )
        }
    }

    private fun <X> generateConstraintSubquery(criteria: CriteriaQuery<User>,
                                                  criteriaBuilder: CriteriaBuilder,
                                                  clazz: Class<X>,
                                                  selectFrom: String,
                                                  compareWith: String,
                                                  toCompare: Long?): Subquery<Long> {

        val subquery = criteria.subquery(Long::class.java)
        val root = subquery.from(clazz)

        subquery.select(root.get<Any>(selectFrom).get("id"))
        subquery.where(
            criteriaBuilder.equal(root.get<Any>(compareWith).get<Long>("id"), toCompare)
        )

        return subquery
    }
}

