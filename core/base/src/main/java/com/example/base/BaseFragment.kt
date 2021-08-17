package com.example.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<V: ViewModel, B : ViewDataBinding> : Fragment() {
    lateinit var binding: B

    abstract val viewModel: V
    protected abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            binding = DataBindingUtil.inflate(inflater, layout, container, false)
            binding.executePendingBindings()
        } catch (e: Exception) {
            Log.d("WATCHX", "errorxxx")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            setup()
        } catch (e: Exception) {
            Log.d("WATCHX", "err: ${e.stackTraceToString()}")
        }
    }

    abstract fun setup()
}