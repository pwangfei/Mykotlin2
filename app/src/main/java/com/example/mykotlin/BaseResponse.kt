package com.example.mykotlin


class BaseResponse<T> {
    val data: T? = null;
    val errorCode: Int? = null;
    val errorMsg="";


    fun isValid(): Boolean {
        return  errorCode == 0
    }

    @Throws(ApiException::class)
    fun throwAPIException() {
        if (!isValid()) {
            throw ApiException(errorCode, errorMsg)
        }
    }
}

