package marta.rodriguez.helloworld.net

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import marta.rodriguez.helloworld.databinding.RowItemBinding
import marta.rodriguez.helloworld.databinding.RowPostBinding
import marta.rodriguez.helloworld.list.ShopItem

class PostAdapter(private var dataSet: ArrayList<Post>, private val listener: PostListener) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private var currentPost: Post? = null

        init {
            binding.root.setOnClickListener {
                currentPost?.let { post -> listener.postClicked(post) }
            }
        }

        fun bind(post: Post) {
            currentPost = post
            binding.postTitleText.text = post.title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowPostBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    interface PostListener {
        fun postClicked(post: Post)
    }
}
