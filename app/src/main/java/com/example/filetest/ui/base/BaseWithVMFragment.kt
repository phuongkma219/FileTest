package com.example.baseproject.ui.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseWithVMFragment<V : ViewDataBinding, T: ViewModel> : AbsBaseFragment<V>() {
    protected lateinit var mViewModel: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()
}