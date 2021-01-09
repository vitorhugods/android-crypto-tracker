package xyz.schwaab.crypto.base.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import xyz.schwaab.crypto.base.presentation.BaseViewComposer

fun <T> LiveData<T>.attachViewComposer(
    lifecycleOwner: LifecycleOwner,
    viewComposer: BaseViewComposer<T>
) {
    observe(lifecycleOwner, { data ->
        viewComposer.composeViewWithNewData(data)
    })
}