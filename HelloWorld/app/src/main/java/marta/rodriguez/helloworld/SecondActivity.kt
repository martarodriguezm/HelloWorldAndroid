package marta.rodriguez.helloworld

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.databinding.ActivityMainBinding
import marta.rodriguez.helloworld.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        const val USERNAME_ID = "username_id_key"

        fun getCallingIntent(context : Context, username: String) : Intent {
            var intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(USERNAME_ID, username)
            return intent
        }
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left+15, systemBars.top+15, systemBars.right+15, systemBars.bottom+15)
            insets
        }

        val username = intent.extras!!.getString(USERNAME_ID)
        binding.usernameTextView.text = "Hello "+username
    }
}