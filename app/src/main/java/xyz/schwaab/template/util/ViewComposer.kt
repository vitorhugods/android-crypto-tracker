package xyz.schwaab.template.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import xyz.schwaab.template.base.BaseViewComposer

fun <T> LiveData<T>.attachViewComposer(
    lifecycleOwner: LifecycleOwner,
    viewComposer: BaseViewComposer<T>
) {
    observe(lifecycleOwner, { data ->
        viewComposer.composeViewWithNewData(data)
    })
}