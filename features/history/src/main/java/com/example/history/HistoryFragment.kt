package com.example.history

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseFragment
import com.example.history.adapters.HistoryRecordsAdapter
import com.example.history.databinding.FragmentHistoryBinding
import com.example.history.vm.HistoryViewModel

class HistoryFragment: BaseFragment<HistoryViewModel, FragmentHistoryBinding>() {

    override val layout = R.layout.fragment_history
    override val viewModel: HistoryViewModel by viewModels()

    override fun setup() {
        viewModel.initDatabase(requireContext())

        val recordsAdapter = HistoryRecordsAdapter()

        binding.historyLaps.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recordsAdapter
        }

        viewModel.values.observe(this, {
            recordsAdapter.setItems(it)
        })
    }
}