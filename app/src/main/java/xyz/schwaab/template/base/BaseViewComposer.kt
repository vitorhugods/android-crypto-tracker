package xyz.schwaab.template.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface BaseViewComposer<DATA : Any?> {
    fun composeViewWithNewData(item: DATA)
}

abstract class BaseViewHolder<BINDING : ViewBinding, DATA : Any>(protected val binding: BINDING) :
    RecyclerView.ViewHolder(binding.root), BaseViewComposer<DATA> {

    fun onRootViewClick(onClick: (View) -> Unit) {
        binding.root.setOnClickListener {
            onClick(it)
        }
    }
}