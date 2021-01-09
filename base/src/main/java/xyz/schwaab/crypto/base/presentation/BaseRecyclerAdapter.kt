package xyz.schwaab.crypto.base.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<Data : Any, VH : BaseViewHolder<*, Data>> :
    RecyclerView.Adapter<VH>() {
    private val onItemClickListeners = mutableListOf<OnItemClickListener<Data>>()

    fun onItemClick(onItemClick: OnItemClickListener<Data>) {
        onItemClickListeners += onItemClick
    }

    var data: List<Data> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.composeViewWithNewData(item)
        holder.onRootViewClick { view ->
            onItemClickListeners.forEach { listener ->
                listener.onItemClick(item, position, view)
            }
        }
    }

    fun interface OnItemClickListener<Data> {
        fun onItemClick(item: Data, position: Int, view: View)
    }
}