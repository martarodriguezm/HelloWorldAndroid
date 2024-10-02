package marta.rodriguez.helloworld.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marta.rodriguez.helloworld.R
import marta.rodriguez.helloworld.SecondActivity
import marta.rodriguez.helloworld.databinding.ActivityAddShopItemBinding
import marta.rodriguez.helloworld.databinding.ActivityListDetailBinding

class AddShopItemActivity : AppCompatActivity() {
    companion object {
        const val SHOP_ITEM_KEY = "shop_item_key"
        fun getCallingIntent(context: Context) : Intent {
            val intent = Intent(context, AddShopItemActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityAddShopItemBinding
    private val viewPadding = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setTitle(R.string.add_shop_item)
        binding = ActivityAddShopItemBinding.inflate(layoutInflater)

        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + viewPadding, systemBars.top + viewPadding, systemBars.right + viewPadding, systemBars.bottom + viewPadding)
            insets
        }

        binding.saveButton.setOnClickListener {
            val itemName = binding.itemNameEditText.text.toString()
            if(itemName.isNotEmpty()) {
                val itemQuantity = binding.itemQuantityEditText.text.toString()
                if(itemQuantity.isNotEmpty()) {
                    val itemQuantityInt = itemQuantity.toInt()
                    val shopItem = ShopItem(itemName, itemQuantityInt)
                    shopItemResultAndFinish(shopItem)
                } else {
                    val shopItem = ShopItem(itemName, 0)
                    shopItemResultAndFinish(shopItem)
                }
            } else {
                Toast.makeText(this, R.string.name_mandatory, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun shopItemResultAndFinish(shopItem: ShopItem) {
        var resultIntent = Intent() //Creamos un Intent
        resultIntent.putExtra(SHOP_ITEM_KEY, shopItem) //Guardamos como extra el resultado
        setResult(RESULT_OK, resultIntent) //Indicamos que ha ido bien
        finish() //Finalizamos la Activity
    }
}