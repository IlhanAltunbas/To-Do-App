package com.example.to_doapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentHomePageBinding
import com.example.to_doapp.ui.adapter.TasksAdapter
import com.example.to_doapp.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        binding.fragmentHomePageObj = this

        viewModel.tasksList.observe(viewLifecycleOwner) { uncheckedTasks ->
            val uncheckedTasksAdapter = TasksAdapter(requireContext(), uncheckedTasks, viewModel)
            binding.rvUncheckedTasks.adapter = uncheckedTasksAdapter
            uncheckedTasksAdapter.notifyDataSetChanged()
        }

        viewModel.taskListChecked.observe(viewLifecycleOwner) { checkedTasks ->
            if (checkedTasks.isNullOrEmpty()) {
                binding.rvCheckedTasks.visibility = View.INVISIBLE
                binding.tvChecked.visibility = View.INVISIBLE
            } else {
                val checkedTasksAdapter = TasksAdapter(requireContext(), checkedTasks, viewModel)
                binding.rvCheckedTasks.adapter = checkedTasksAdapter
                binding.rvCheckedTasks.visibility = View.VISIBLE
                binding.tvChecked.visibility = View.VISIBLE
                checkedTasksAdapter.notifyDataSetChanged()
            }
        }

        binding.tbHomePageTitle = "Home Page"

        binding.svHomePage.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.search(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(it) }
                return true
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTasks()
    }

    fun fabClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.registerFragment)
    }
}
