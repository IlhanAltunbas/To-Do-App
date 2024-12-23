package com.example.to_doapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.to_doapp.R
import com.example.to_doapp.data.entity.Tasks
import com.example.to_doapp.databinding.FragmentDetailBinding
import com.example.to_doapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false)
       val tempViewModel : DetailViewModel by viewModels()
       viewModel = tempViewModel

       binding.fragmentDetailObj = this
       binding.tbDetailTitle = "Detail"

       val bundle : DetailFragmentArgs by navArgs()
       binding.task = bundle.task

       return binding.root
    }

    fun update(task_id: Int, task_name: String,task_check: Boolean) {
        viewModel.update(task_id,task_name, task_check)
    }
}