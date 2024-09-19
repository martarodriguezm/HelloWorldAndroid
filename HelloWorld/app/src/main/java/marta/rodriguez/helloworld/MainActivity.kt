package marta.rodriguez.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewPadding = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + viewPadding, systemBars.top + viewPadding, systemBars.right + viewPadding, systemBars.bottom + viewPadding)
            insets
        }

        binding.helloWorldButton.setOnClickListener {
            startActivity(HelloNameActivity.getCallingIntent(this))
        }

        binding.happyBirthdayButton.setOnClickListener {
            startActivity(BirthdayCardActivity.getCallingIntent(this))
        }
    }
}