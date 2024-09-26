package marta.rodriguez.helloworld.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import marta.rodriguez.helloworld.R
import marta.rodriguez.helloworld.databinding.ActivityListBinding

class ListActivity : AppCompatActivity(), CustomAdapter.ItemListener {
    companion object {
        fun getCallingIntent(context: Context) : Intent {
            val intent = Intent(context, ListActivity::class.java)
            return intent
        }
    }

    private val viewPadding = 15

    private lateinit var binding : ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + viewPadding, systemBars.top + viewPadding, systemBars.right + viewPadding, systemBars.bottom + viewPadding)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.myRecyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.layoutManager = layoutManager

        //val dataset = arrayOf("Potatoes", "Bread", "Pizza", "Tomato", "Onions", "Eggs", "Pasta")
        binding.myRecyclerView.adapter = CustomAdapter(getShopItems(), this)
    }

    private fun getShopItems() : Array<ShopItem> {
        val dataset = arrayOf(ShopItem("Pasta"), ShopItem("Potatoes"), ShopItem("Bread"), ShopItem("Pizza"))
        return dataset
    }

    override fun itemClicked(item: ShopItem) {
        //Toast.makeText(this, getString(R.string.selected_item) + item, Toast.LENGTH_SHORT).show()
        startActivity(ListDetailActivity.getCallingIntent(this, item))
    }
}