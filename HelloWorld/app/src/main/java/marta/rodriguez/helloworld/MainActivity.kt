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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 15, systemBars.top + 15, systemBars.right + 15, systemBars.bottom + 15)
            insets
        }

        binding.doneButton.setOnClickListener {
            val usernameValue = binding.nameEditText.text.toString()
            if(usernameValue.isNotEmpty()) {
                startActivity(SecondActivity.getCallingIntent(this, usernameValue))
            } else {
                Toast.makeText(this, "Name field is mandatory", Toast.LENGTH_SHORT).show()
            }
        }
    }
}