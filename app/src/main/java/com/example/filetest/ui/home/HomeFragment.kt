package com.example.filetest.ui.home

import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.baseproject.data.api.response.LoadingStatus
import com.example.baseproject.ui.base.AbsBaseFragment
import com.example.filetest.R
import com.example.filetest.databinding.HomeFragmentBinding
import com.example.filetest.ui.PickFileViewModel
import com.example.filetest.utils.Utils

class HomeFragment : AbsBaseFragment<HomeFragmentBinding>() {
    override fun getLayout(): Int {
        return R.layout.home_fragment
    }

    override fun initView() {
        binding.btnFile.setOnClickListener {
            if (Utils.storagePermissionGrant(requireContext())) {

                findNavController().navigate(R.id.home_to_file)
            } else {
                requestStoragePermission()
            }
        }
    }

    private fun requestStoragePermission() {
        resultLauncher.launch(
            Utils.getStoragePermissions()
        )

    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (Utils.storagePermissionGrant(requireContext())
            ) {
                findNavController().navigate(R.id.home_to_file)

            } else {

                Utils.showAlertPermissionNotGrant(binding.root, requireActivity())
            }
        }
}