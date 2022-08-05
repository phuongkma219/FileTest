package com.example.filetest.ui

import androidx.lifecycle.*
import com.example.baseproject.data.api.response.DataResponse
import com.example.baseproject.data.api.response.LoadingStatus
import com.example.filetest.data.model.FileModel
import com.example.filetest.data.model.FileModelDataType
import com.example.filetest.data.model.PickFileModel
import com.example.filetest.data.repository.FileRepository
import kotlinx.coroutines.launch

class PickFileViewModel:ViewModel() {
    private val repository = FileRepository()
    private var allFile = mutableListOf<FileModel>()
    private val pickFileModel = MutableLiveData<DataResponse<PickFileModel>>()

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
            if (allFile.isNotEmpty()){
                pickFileModel.value = DataResponse.DataError()
            }
            else{

            }
        }
    }
}