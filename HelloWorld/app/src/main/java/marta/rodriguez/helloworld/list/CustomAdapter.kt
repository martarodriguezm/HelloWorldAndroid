package marta.rodriguez.helloworld.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import marta.rodriguez.helloworld.databinding.RowItemBinding

class CustomAdapter(private val dataSet: Array<String>, private val listener: ItemListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowItemBinding) :RecyclerView.ViewHolder(binding.root) {
        private var currentItem: String? = null

        init {
            binding.root.setOnClickListener {
                currentItem?.let { item -> listener.itemClicked(item) }
            }
        }

        fun bind(item: String) {
            currentItem = item
            binding.rowItemText.text = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    interface ItemListener {
        fun itemClicked(item: String)
    }
}
