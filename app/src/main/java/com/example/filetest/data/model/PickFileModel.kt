package com.example.filetest.data.model

import com.example.filetest.data.model.FileModel
import com.example.filetest.data.model.FileModelDataType

data class PickFileModel(val fileModelDataType: FileModelDataType, val data : MutableList<FileModel>)