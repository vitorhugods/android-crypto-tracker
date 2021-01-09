package xyz.schwaab.crypto.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

}

abstract class BaseBindingFragment<Binding : ViewDataBinding> : BaseFragment() {

    lateinit var binding: Binding

    abstract val bindingProvider: (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) -> Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingProvider(inflater, container, false)
        binding.onBindingCreated()
        return binding.root
    }

    open fun Binding.onBindingCreated(){}
}