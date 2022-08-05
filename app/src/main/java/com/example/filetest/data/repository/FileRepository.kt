package com.example.filetest.data.repository

import com.example.filetest.data.model.FileModel
import com.example.filetest.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FileRepository {
    suspend fun getAllFile(path:String):MutableList<FileModel> = withContext(Dispatchers.Default){
        Utils.gellAllFF(path)
    }

}