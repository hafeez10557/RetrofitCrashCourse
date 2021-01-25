package xyz.abdulhafeez.retrofitcrashcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.abdulhafeez.retrofitcrashcourse.databinding.ItemTodoBinding

class ToDoAdapter: RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private val difCalBack = object :DiffUtil.ItemCallback<DoDo>(){
        override fun areItemsTheSame(oldItem: DoDo, newItem: DoDo): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: DoDo, newItem: DoDo): Boolean {
            return oldItem==newItem
        }

    }
    private val differ=AsyncListDiffer(this,difCalBack)
    var todos:List<DoDo>
    get() =differ.currentList
        set(value) { differ.submitList(value) }

    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.apply {
            val todo=todos[position]
            tvTitle.text=todo.title
            cbDone.isChecked=todo.complete
        }
    }

    override fun getItemCount()=todos.size
}