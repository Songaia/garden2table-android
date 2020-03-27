package club.gardentotable.signup.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import club.gardentotable.signup.db.User
import club.gardentotable.signup.db.UserRepository
import club.gardentotable.signup.db.UserRoomDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers : LiveData<List<User>>

    init {
        val usersDAO = UserRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).userDAO()
        repository = UserRepository(usersDAO)
        allUsers = repository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}