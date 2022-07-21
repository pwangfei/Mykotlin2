package com.example.mykotlin


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainVm : BaseViewModel() {

    /**
     * 请求网络
     */
    fun requestData() {
        viewModelScope.launch {
            //单独处理异常
           var liveData= Api.getListProject()
            Log.e("wpf123wpf", "requestData: " + Gson().toJson(liveData))
            var liveData2= Api.getListProject()
            Log.e("wpf123wpf", "requestData: 222" + Gson().toJson(liveData2)+"    "+Thread.currentThread().name)
        }
    }


}