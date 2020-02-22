package club.gardentotable.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : AppCompatActivity() {

    private lateinit var editUserFirstname: EditText
    private lateinit var editUserId : EditText
    private lateinit var editUserLastname : EditText


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        editUserFirstname = edit_user_firstname
        editUserId = edit_user_id
        editUserLastname = edit_user_lastname


        //when the save button is clicked, send the field info to the intent
        //through a bundle
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            val extras = Bundle()
            if (TextUtils.isEmpty(editUserFirstname.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val  userFirstname = editUserFirstname.text.toString()
                val userLastname = editUserLastname.text.toString()
                val uid = editUserId.text.toString().toInt()
                extras.putString(USER_FIRSTNAME, userFirstname)
                extras.putString(USER_LASTNAME, userLastname)
                extras.putInt(UID, uid)
                replyIntent.putExtra(USER_INFO, extras)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    companion object {
        const val USER_FIRSTNAME = "com.example.android.userlistsql.USER_FIRSTNAME"
        const val USER_LASTNAME = "com.example.android.userlistsql.USER_LASTNAME"
        const val UID = "com.example.android.userlistsql.UID"
        const val USER_INFO = "com.example.android.userlistsql.USER_INFO"

    }
}
