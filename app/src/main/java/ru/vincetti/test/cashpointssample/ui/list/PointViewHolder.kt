package ru.vincetti.test.cashpointssample.ui.list

import androidx.recyclerview.widget.RecyclerView
import ru.vincetti.test.cashpointssample.databinding.ItemPointBinding

class PointViewHolder private constructor(
    private val binding: ItemPointBinding,
    private val actions: Actions
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.itemRoot.setOnClickListener { onContentClick() }
    }

    fun bind(info: String) {
        binding.itemInfo.text = info
    }

    private fun onContentClick() {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            actions.onItemClick(position)
        }
    }

    companion object {

        fun create(
            binding: ItemPointBinding,
            actions: Actions
        ): PointViewHolder {
            return PointViewHolder(binding, actions)
        }
    }

    interface Actions {

        fun onItemClick(position: Int)
    }
}
