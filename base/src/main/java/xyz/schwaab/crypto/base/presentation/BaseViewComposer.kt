package xyz.schwaab.crypto.base.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface BaseViewComposer<Data : Any?> {
    fun composeViewWithNewData(item: Data)
}

abstract class BaseViewHolder<Binding : ViewBinding, Data : Any>(protected val binding: Binding) :
    RecyclerView.ViewHolder(binding.root), BaseViewComposer<Data> {

    fun onRootViewClick(onClick: (View) -> Unit) {
        binding.root.setOnClickListener {
            onClick(it)
        }
    }
}