package com.example.history.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.LocalRecord
import com.example.history.databinding.ListItemRecordBinding

class HistoryRecordsAdapter: RecyclerView.Adapter<HistoryRecordsAdapter.RecordViewHolder>() {

    private val items = mutableListOf<LocalRecord>()

    fun setItems(newItems: List<LocalRecord>) {
        val diffResult = DiffUtil.calculateDiff(RecordListDiffUtil(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding = ListItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class RecordViewHolder(val binding: ListItemRecordBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(record: LocalRecord) {
            binding.content.text = record.time?.toString()
        }
    }
}