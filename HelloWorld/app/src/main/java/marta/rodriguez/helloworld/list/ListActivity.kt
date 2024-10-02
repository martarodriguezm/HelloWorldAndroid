package marta.rodriguez.helloworld.list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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

    private lateinit var adapter : CustomAdapter

    private val addShopItemResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val shopItem = data?.getParcelableExtra<ShopItem>(AddShopItemActivity.SHOP_ITEM_KEY)
            Log.e("dk", "shopItem ${shopItem?.name}")
            shopItem?.let {
                adapter.addItem(shopItem)
            }
        }
    }

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

        binding.myRecyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.layoutManager = layoutManager

        //val dataset = arrayOf("Potatoes", "Bread", "Pizza", "Tomato", "Onions", "Eggs", "Pasta")

        adapter = CustomAdapter(getShopItems(), this)
        binding.myRecyclerView.adapter = adapter

        binding.addShopItemFab.setOnClickListener {
            this.addShopItemResultLauncher.launch(AddShopItemActivity.getCallingIntent(this))
        }
    }

    private fun getShopItems() : ArrayList<ShopItem> {
        val dataset = arrayListOf(ShopItem("Pasta"), ShopItem("Potatoes", 10), ShopItem("Bread"), ShopItem("Pizza"))
        return dataset
    }

    override fun itemClicked(item: ShopItem) {
        //Toast.makeText(this, getString(R.string.selected_item) + item, Toast.LENGTH_SHORT).show()
        startActivity(ListDetailActivity.getCallingIntent(this, item))
    }
}