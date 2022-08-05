package com.example.baseproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
abstract class AbsBaseFragment<V : ViewDataBinding> : Fragment() {
    protected lateinit var binding: V
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.lifecycleOwner = this
        initView()
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
    abstract fun getLayout(): Int
    abstract fun initView()
}