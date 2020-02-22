package club.gardentotable.signup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * from user_table ORDER BY uid ASC")
    fun getAllOrderedUID(): LiveData<List<User>>

    @Query("SELECT * from user_table ORDER BY last_name ASC")
    fun getAllOrderedLast(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}