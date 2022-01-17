package com.itechart.crushon.service.impl

import com.itechart.crushon.dto.user.CreateUserInputDTO
import com.itechart.crushon.dto.user.UpdateUserDTO
import com.itechart.crushon.model.Match
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.CityRepository
import com.itechart.crushon.repository.FileRepository
import com.itechart.crushon.repository.PassionRepository
import com.itechart.crushon.repository.UserRepository
import com.itechart.crushon.service.UserService
import com.itechart.crushon.utils.Gender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val fileRepository: FileRepository,
    private val cityRepository: CityRepository,
    private val passionRepository: PassionRepository
): UserService {

    override fun createUser(data: CreateUserInputDTO): User {
        val user = User(
            username = data.username,
            name = data.name,
            photo = fileRepository.findFileById(1),
            bio = data.bio,
            dateOfBirth = data.dateOfBirth,
            city = cityRepository.findCityById(data.city),
            gender = Gender.fromString(data.gender)
        )

        userRepository.save(user)

        return user
    }

    override fun setPhoto(user: User, photoId: Long): Long {
        try {
            user.photo = fileRepository.findFileById(photoId)
            userRepository.save(user)

            return user.photo.id!!
        } catch (e: Exception) {
            throw e
        }
    }

    override fun updateUser(user: User, data: UpdateUserDTO): User {
        data.bio?.let {
            user.bio = it
        }

        data.photo?.let {
            user.photo = fileRepository.findFileById(it)
        }

        data.city?.let {
            user.city = cityRepository.findCityById(it)
        }

        data.passions?.let {
            user.passions = passionRepository
                .findAllById(data.passions)
                .toMutableList()
        }

        data.dateOfBirth?.let {
            user.dateOfBirth = it
        }

        userRepository.save(user)

        return user
    }

    override fun getMatches(user: User): Flow<Match> = flow {
        emit(Match(first = user, second = user))
        emit(Match(first = user, second = user))
        emit(Match(first = user, second = user))
    }
}
