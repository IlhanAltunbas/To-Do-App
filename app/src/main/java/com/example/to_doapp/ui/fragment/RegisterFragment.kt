package com.example.to_doapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.to_doapp.R
import com.example.to_doapp.data.entity.Tasks
import com.example.to_doapp.databinding.FragmentRegisterBinding
import com.example.to_doapp.ui.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding
     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
         binding.fragmentRegisterObj = this
         binding.tbRegisterTitle = "Registration"
         val tempViewModel : RegisterViewModel by viewModels()
         viewModel = tempViewModel

         return binding.root
    }

    fun save(task_name:String){
        viewModel.save(task_name)
    }
}