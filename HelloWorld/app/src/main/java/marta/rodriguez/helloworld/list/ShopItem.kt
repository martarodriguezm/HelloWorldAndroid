package marta.rodriguez.helloworld.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopItem(var name: String, var quantity: Int = 0): Parcelable
