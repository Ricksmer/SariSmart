package com.example.sarismart.data.repositories

import com.example.sarismart.data.models.User

object UserRepository {
    private val users = mutableListOf<User>()
    private var currentUser: User? = null

    fun register(fullname: String, username: String, password: String): Boolean {
        if (users.any { it.userName == username }) return false

        val user = User(fullname, username, password)

        users.add(user)
        return true
    }

    fun login(username: String, password: String): Boolean {
        val valid = users.any { it.userName == username && it.password == password }
        if(valid){
            currentUser = users.find { it.userName == username }
        }
        return valid
    }

    fun getUser(): User? {
        return currentUser
    }
}