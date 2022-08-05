package com.example.filetest.ui

import android.content.Intent
import android.os.Environment
import android.os.Parcelable
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.data.api.response.DataResponse
import com.example.baseproject.data.api.response.LoadingStatus
import com.example.baseproject.ui.base.AbsBaseFragment
import com.example.filetest.R
import com.example.filetest.data.model.FileModel
import com.example.filetest.data.model.FileModelDataType
import com.example.filetest.databinding.PickFileFragmentBinding


class PickFileFragment : AbsBaseFragment<PickFileFragmentBinding>(), FileAdapter.ListenerClickItem {
    private lateinit var mAdapter : FileAdapter
    private lateinit var mViewModel: PickFileViewModel

    var folderState: Parcelable? = null

    companion object {
        const val COLUMNS = 2
    }

    override fun getLayout(): Int {
        return R.layout.pick_file_fragment
    }

    override fun initView() {
        mAdapter =  FileAdapter(requireContext(), this)
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
        mViewModel.pickFileModel.observe(viewLifecycleOwner) {
            if (it.loadingStatus == LoadingStatus.Success) {
               val body = (it as DataResponse.DataSuccess).body
                if (body.fileModelDataType == FileModelDataType.LoadFolder){
                    binding.recycleView.layoutManager!!.onRestoreInstanceState(folderState)

                }
                mAdapter.update(body)

            }
        }
    }

    override fun onClickFileItem(
        position: Int,
        fileModelDataType: FileModelDataType,
        fileModel: FileModel
    ) {
        if (fileModelDataType == FileModelDataType.LoadFolder){
            folderState =  binding.recycleView.layoutManager!!.onSaveInstanceState()
            mViewModel.getALlFile(fileModel.path)
        }
        else{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 123)
        }


    }
}