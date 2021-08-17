package com.example.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseFragment
import com.example.home.adapters.HomeRecordsAdapter
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.vm.HomeViewModel


class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layout = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private val runnable = Runnable {
        while (viewModel.isRunning.value == true) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {

            }
            
            viewModel.currentTime.postValue(viewModel.currentTime.value?.plus(1) ?: 1)
        }
    }

    private val computingThread = Thread(runnable)

    override fun setup() {
        viewModel.initDatabase(requireContext())
        viewModel.isRunning.observe(this, {
            if (it) {
                binding.homeStartStop.text = "STOP"
                computingThread.start()
            } else {
                binding.homeStartStop.text = "START"
            }
        })
        binding.homeStartStop.setOnClickListener {
            viewModel.isRunning.value = viewModel.isRunning.value != true
        }

        viewModel.currentTime.observe(this, {
            binding.homeCountdown.text = "$it s"
        })

        viewModel.error.observe(this, {
            Toast.makeText(requireContext(), "Chyba: $it", Toast.LENGTH_SHORT).show()
        })

        binding.homeLap.setOnClickListener {
            viewModel.insertValue()
        }

        binding.homeReset.setOnClickListener {
            viewModel.resetValues()
            viewModel.isRunning.value = false
            viewModel.currentTime.value = 0
        }

        val recordsAdapter = HomeRecordsAdapter()

        binding.homeLaps.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recordsAdapter
        }

        viewModel.values.observe(this, {
            recordsAdapter.setItems(it)
        })
    }
}