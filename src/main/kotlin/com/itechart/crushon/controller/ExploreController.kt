package com.itechart.crushon.controller

import com.itechart.crushon.dto.explore.AddReactionInputDTO
import com.itechart.crushon.dto.explore.AddReactionOutputDTO
import com.itechart.crushon.dto.explore.ExploreNewPeopleDTO
import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import com.itechart.crushon.model.User
import com.itechart.crushon.service.ExploreService
import com.itechart.crushon.utils.Reactions
import kotlinx.coroutines.flow.Flow
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/explore")
class ExploreController(
    private val exploreService: ExploreService
) {
    @GetMapping("/cities")
    fun getCities(): Flow<City> = exploreService.getCities()

    @GetMapping("/passions")
    fun getPassions(): Flow<Passion> = exploreService.getPassions()

    @GetMapping
    fun exploreNewPeople(@AuthenticationPrincipal user: User): Flow<User> =
        exploreService.exploreNewPeople(user)

    @PostMapping("/react")
    fun addReaction(@AuthenticationPrincipal user: User, @RequestBody data: AddReactionInputDTO): AddReactionOutputDTO {
        return AddReactionOutputDTO(
            isMatch = exploreService
                .addReaction(
                    user,
                    data.userId,
                    Reactions.fromString(data.reaction)
                )
        )
    }
}
