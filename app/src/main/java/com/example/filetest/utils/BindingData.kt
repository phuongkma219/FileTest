package com.example.filetest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.filetest.R
import com.example.filetest.data.model.FileModel
import java.io.File

@BindingAdapter("setImage")
fun setImage(iv:ImageView,model : FileModel) {
    if (File(model.path).isFile){
        iv.setImageResource(R.drawable.ic_baseline_insert_drive_file_24)
    }
    else{
        iv.setImageResource(R.drawable.ic_baseline_folder_24)

    }
}
