package com.example.filetest.ui

import android.os.Environment
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.data.api.response.DataResponse
import com.example.baseproject.data.api.response.LoadingStatus
import com.example.baseproject.ui.base.AbsBaseFragment
import com.example.baseproject.ui.base.BaseWithVMFragment
import com.example.filetest.R
import com.example.filetest.data.model.PickFileModel
import com.example.filetest.databinding.PickFileFragmentBinding

class PickFileFragment: AbsBaseFragment<PickFileFragmentBinding>() {
    private val mAdapter = FileAdapter()
    private lateinit var mViewModel : PickFileViewModel
    companion object{
        const val COLUMNS = 2
    }
    override fun getLayout(): Int {
        return R.layout.pick_file_fragment
    }

    override fun initView() {
        initViewModel()
        binding.apply {
            recycleView.layoutManager = GridLayoutManager(
                requireActivity(),
               COLUMNS
            )
            recycleView.setHasFixedSize(true)
            recycleView.adapter = mAdapter
        }
        binding.viewModel = mViewModel
    }

    fun initViewModel() {
         mViewModel = PickFileViewModel()
        mViewModel.getALlFile(Environment.getExternalStorageDirectory().getPath())
        mViewModel.pickFileModel.observe(viewLifecycleOwner){
            if (it.loadingStatus == LoadingStatus.Success){
                mAdapter.update( (it as DataResponse.DataSuccess).body)
            }
        }
    }
}