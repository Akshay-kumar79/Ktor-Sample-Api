package akshaw.com.mappers

import akshaw.com.models.User
import akshaw.com.plugins.ExposedUser

fun ExposedUser.toUser(): User =
    User(
        name = this.name,
        age = this.age
    )

fun User.toExposedUser(): ExposedUser =
    ExposedUser(
        name = this.name,
        age = this.age
    )