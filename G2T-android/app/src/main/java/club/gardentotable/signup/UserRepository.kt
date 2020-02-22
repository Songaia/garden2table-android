package club.gardentotable.signup

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val allUsers: LiveData<List<User>> = userDAO.getAllOrderedUID()

    suspend fun insert(user: User) {
        userDAO.insert(user)
    }
}