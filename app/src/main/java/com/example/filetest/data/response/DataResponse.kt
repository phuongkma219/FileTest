package com.example.baseproject.data.api.response

sealed class DataResponse<T> constructor(val loadingStatus: LoadingStatus) {
    class DataError<T>() : DataResponse<T>(LoadingStatus.Error)
    data class DataSuccess<T>(val body: T) : DataResponse<T>(LoadingStatus.Success)
}
