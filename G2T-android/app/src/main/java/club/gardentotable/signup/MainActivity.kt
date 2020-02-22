package club.gardentotable.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import club.gardentotable.signup.NewUserActivity.Companion.UID
import club.gardentotable.signup.NewUserActivity.Companion.USER_FIRSTNAME
import club.gardentotable.signup.NewUserActivity.Companion.USER_INFO
import club.gardentotable.signup.NewUserActivity.Companion.USER_LASTNAME
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UserListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.allUsers.observe(this, Observer { users ->
            // Update the cached copy of the users in the adapter.
            users?.let { adapter.setUsers(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewUserActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getBundleExtra(USER_INFO)?.let { info ->

                val user = User(
                    info.getInt(UID), info.getString(USER_FIRSTNAME),
                    info.getString(USER_LASTNAME)
                )
                userViewModel.insert(user)
                Unit
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
