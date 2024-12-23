package com.example.to_doapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.os.Message
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.data.entity.Tasks
import com.example.to_doapp.databinding.CardViewDesignBinding
import com.example.to_doapp.ui.fragment.HomePageFragmentDirections
import com.example.to_doapp.ui.viewmodel.HomePageViewModel
import com.google.android.material.snackbar.Snackbar

class TasksAdapter(var nContext: Context, var tasksList: List<Tasks>, var viewModel: HomePageViewModel)
    :RecyclerView.Adapter<TasksAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var design: CardViewDesignBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardViewDesignBinding.inflate(LayoutInflater.from(nContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val task = tasksList.get(position)
        val h = holder.design

        h.taskObject = task

        h.tvTaskName.text = task.task_name
        h.cvLine.setOnClickListener() {
            val pass = HomePageFragmentDirections.detailPass(task = task)
            Navigation.findNavController(it).navigate(pass)
        }
        h.ivDelete.setOnClickListener(){
            Snackbar.make(it,"Do you want to delete ${task.task_name} ?", Snackbar.LENGTH_SHORT)
                .setAction("Yes"){
                    viewModel.delete(task.task_id)
                }
                .show()
        }
        if (task.task_check){
            h.cbCardView.isChecked = true
            h.cvLine.setCardBackgroundColor(Color.GRAY)
        }
        h.cbCardView.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked) {
                    task.task_check = true
                    viewModel.checkUpdate(task.task_id,task.task_name,true)
                } else {
                    task.task_check = false
                    viewModel.checkUpdate(task.task_id,task.task_name,false)
                }

        }


    }

    override fun getItemCount(): Int {
        return tasksList.size
    }


}
