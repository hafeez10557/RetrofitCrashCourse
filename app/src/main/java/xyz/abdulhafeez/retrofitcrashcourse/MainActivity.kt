package xyz.abdulhafeez.retrofitcrashcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.HttpException
import xyz.abdulhafeez.retrofitcrashcourse.databinding.ActivityMainBinding
import java.io.IOException
const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toDoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible=true
            val response=try {
                RetrofitInstance.Api.getToDo()
            }catch (e:IOException){
                Log.e(TAG,"IOException",e)
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException",e)
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                toDoAdapter.todos=response.body()!!
            }else{
                Log.e(TAG,"Responce not found",)

            }
            binding.progressBar.isVisible=false

        }


    }
    private fun setupRecyclerView() = binding.rvTodos.apply {
        toDoAdapter=ToDoAdapter()
        adapter=toDoAdapter
        layoutManager =LinearLayoutManager(this@MainActivity,)
    }
}