package marta.rodriguez.helloworld

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.SecondActivity.Companion.USERNAME_ID
import marta.rodriguez.helloworld.databinding.ActivityHelloNameBinding

class HelloNameActivity : AppCompatActivity() {


    companion object {
        fun getCallingIntent(context : Context) : Intent {
            var intent = Intent(context, HelloNameActivity::class.java)
            return intent
        }
    }

    private val viewPadding = 15

    private lateinit var binding : ActivityHelloNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHelloNameBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + viewPadding, systemBars.top + viewPadding, systemBars.right + viewPadding, systemBars.bottom + viewPadding)
            insets
        }

        binding.doneButton.setOnClickListener {
            val usernameValue = binding.nameEditText.text.toString()
            if(usernameValue.isNotEmpty()) {
                startActivity(SecondActivity.getCallingIntent(this, usernameValue))
            } else {
                Toast.makeText(this, R.string.name_mandatory, Toast.LENGTH_LONG).show()
            }
        }
    }
}