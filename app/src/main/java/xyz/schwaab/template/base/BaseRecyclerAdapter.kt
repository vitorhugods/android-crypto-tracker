package xyz.schwaab.template.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<DATA : Any, VH : BaseViewHolder<*, DATA>> :
    RecyclerView.Adapter<VH>() {
    private val onItemClickListeners = mutableListOf<OnItemClickListener<DATA>>()

    fun onItemClick(onItemClick: OnItemClickListener<DATA>) {
        onItemClickListeners += onItemClick
    }

    var data: List<DATA> = listOf()
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

    fun interface OnItemClickListener<DATA> {
        fun onItemClick(item: DATA, position: Int, view: View)
    }
}