package com.example.filetest.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.baseproject.data.api.response.DataResponse
import com.example.baseproject.data.api.response.LoadingStatus
import com.example.filetest.data.model.FileModel
import com.example.filetest.data.model.FileModelDataType
import com.example.filetest.data.model.PickFileModel
import com.example.filetest.data.repository.FileRepository
import kotlinx.coroutines.launch
import java.io.File

class PickFileViewModel:ViewModel() {
    private val repository = FileRepository()
    private var allFile = mutableListOf<FileModel>()
     val pickFileModel = MutableLiveData<DataResponse<PickFileModel>>()

    val isEmpty: LiveData<Boolean> = Transformations.map(pickFileModel) {
        pickFileModel.value!!.loadingStatus == LoadingStatus.Error
    }


    val isDetailMode: LiveData<Boolean> = Transformations.map(pickFileModel) {
        if (it.loadingStatus== LoadingStatus.Success) {
            val pickFileModel = (pickFileModel.value as DataResponse.DataSuccess).body
            pickFileModel.fileModelDataType == FileModelDataType.LoadDetailFolder
        } else {
            false
        }
    }

    val title: LiveData<String> = Transformations.map(pickFileModel) {
        if (pickFileModel.value!!.loadingStatus == LoadingStatus.Success) {
            val pickFileModel = (pickFileModel.value as DataResponse.DataSuccess).body
            if ( pickFileModel.fileModelDataType == FileModelDataType.LoadDetailFolder) {
                pickFileModel.data[0].name
            } else {
                "File"
            }

        } else {
            "File"
        }
    }



    fun getALlFile(path:String){
        viewModelScope.launch {
            allFile = repository.getAllFile(path)
            val arr = mutableListOf<FileModel>()
            if (allFile.isNotEmpty()){
                for (i in allFile){
                    if (File(i.path).isDirectory){
                        arr.add(FileModel(i.name,i.path))
                        pickFileModel.value = DataResponse.DataSuccess(PickFileModel(FileModelDataType.LoadFolder,arr))
                    }

                }


            }
            else{
                pickFileModel.value = DataResponse.DataError()

            }
        }
    }
}