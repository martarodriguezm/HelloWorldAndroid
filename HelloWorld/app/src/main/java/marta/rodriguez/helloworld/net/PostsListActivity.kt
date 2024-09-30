package marta.rodriguez.helloworld.net

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import marta.rodriguez.helloworld.R
import marta.rodriguez.helloworld.databinding.ActivityPostsListBinding
import marta.rodriguez.helloworld.list.CustomAdapter
import marta.rodriguez.helloworld.list.ListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsListActivity : AppCompatActivity(), PostAdapter.PostListener {
    companion object {
        fun getCallingIntent(context: Context) : Intent {
            val intent = Intent(context, PostsListActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityPostsListBinding

    private val viewPadding = 15

    private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setTitle(R.string.network_call)
        binding = ActivityPostsListBinding.inflate(layoutInflater)

        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + viewPadding, systemBars.top + viewPadding, systemBars.right + viewPadding, systemBars.bottom + viewPadding)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.postsRecyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.layoutManager = layoutManager

        //val dataset = arrayOf("Potatoes", "Bread", "Pizza", "Tomato", "Onions", "Eggs", "Pasta")

        val call = ApiClient.apiService.getPosts()
        call.enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()

                    adapter = PostAdapter(posts!!, this@PostsListActivity)
                    binding.postsRecyclerView.adapter = adapter
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    override fun postClicked(post: Post) {

    }
}