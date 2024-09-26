package marta.rodriguez.helloworld.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.HelloNameActivity
import marta.rodriguez.helloworld.R
import marta.rodriguez.helloworld.SecondActivity
import marta.rodriguez.helloworld.SecondActivity.Companion.USERNAME_ID
import marta.rodriguez.helloworld.databinding.ActivityListDetailBinding

class ListDetailActivity : AppCompatActivity() {
    companion object {
        const val ITEM_KEY = "item_key"

        fun getCallingIntent(context : Context, item: ShopItem) : Intent {
            var intent = Intent(context, ListDetailActivity::class.java)
            intent.putExtra(ITEM_KEY, item)
            return intent
        }
    }
    private val padding = 15

    private lateinit var binding : ActivityListDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListDetailBinding.inflate(layoutInflater)
        setContentView(binding.main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + padding, systemBars.top + padding, systemBars.right + padding, systemBars.bottom + padding)
            insets
        }

        val item = intent.getParcelableExtra<ShopItem>(ITEM_KEY)
        item?.let {
            setTitle(item.name)
            binding.itemNameText.text = item.name
        }
    }
}