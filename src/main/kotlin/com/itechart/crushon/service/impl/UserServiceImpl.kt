package com.itechart.crushon.service.impl

import com.itechart.crushon.dto.user.CreateUserInputDTO
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.CityRepository
import com.itechart.crushon.repository.FileRepository
import com.itechart.crushon.repository.UserRepository
import com.itechart.crushon.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val fileRepository: FileRepository,
    private val cityRepository: CityRepository
): UserService {

    override fun createUser(data: CreateUserInputDTO): User {
        val user = User(
            username = data.username,
            name = data.name,
            photo = fileRepository.findFileById(data.photo),
            bio = data.bio,
            age = data.age,
            city = cityRepository.findCityById(data.city)
        )

        userRepository.save(user)

        return user
    }

}
