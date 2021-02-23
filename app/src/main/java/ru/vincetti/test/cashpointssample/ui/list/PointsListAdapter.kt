package ru.vincetti.test.cashpointssample.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vincetti.test.cashpointssample.core.data.CashPoint
import ru.vincetti.test.cashpointssample.databinding.ItemPointBinding

class PointsListAdapter(
    private val list: List<CashPoint>,
    private val actions: (String) -> Unit
) : RecyclerView.Adapter<PointViewHolder>() {

    private val viewHolderAction = object : PointViewHolder.Actions {
        override fun onItemClick(position: Int) {
            actions(list[position].id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PointViewHolder.create(
            ItemPointBinding.inflate(layoutInflater, parent, false),
            viewHolderAction
        )
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item.address)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
