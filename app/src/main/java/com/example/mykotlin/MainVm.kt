package com.example.mykotlin


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainVm : BaseViewModel() {

    /**
     * 请求网络
     */
    fun requestData() {
        viewModelScope.launch {
            //单独处理异常
            add()
            var liveData= async {
                Api.getListProject()
            }
            var liveData2= async {
                Api.getListProject()
            }
//            var liveData= Api.getListProject()
//            var liveData2= Api.getListProject()
            Log.e("wpf123wpf", "requestData:lllllll" + Gson().toJson(liveData2.await()) + "    " + Gson().toJson(liveData.await()) + "   " + Thread.currentThread().name)
        }
    }


    fun add(){
      var uo=1+1;
    }

}