package marta.rodriguez.helloworld

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.databinding.ActivityBirthdayCardBinding

class BirthdayCardActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(context : Context) : Intent {
            val intent = Intent(context, BirthdayCardActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityBirthdayCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBirthdayCardBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.happyBirthdayTextView.setText(R.string.happy_birthday)
        binding.nameTextView.setText(R.string.username)
        binding.cuteTextView.setText(R.string.cute_text)
    }
}