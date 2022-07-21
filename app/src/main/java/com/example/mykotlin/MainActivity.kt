package com.example.mykotlin

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mainVm = ViewModelProvider(this).get(MainVm::class.java)
        mainVm.requestData()//网络请求



        test1()
        test3()

    }


    /**
     * 可以用于切换线程
     * withConext是个 suspend 函数
     * 多个withContext()执行，为串行，运行在同一个线程
     */
    fun test0() {
        var tv_name = this.findViewById<TextView>(R.id.tv_name)
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 0..10) {
                Log.e("wpf123wpf", "test" + i)
            }
            tv_name.text = "asdaksld"   //会比后面的慢执行

        }
        Thread.sleep(500)
        Log.e("wpf123wpf", "test执行")
        tv_name.text = "1111111"
    }


    /**
     * 可以用于切换线程
     * withConext是个 suspend 函数
     * 多个withContext()执行，为串行，运行在同一个线程
     */
    fun test1() {
        GlobalScope.launch {//但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，且不能取消

            withContext(Dispatchers.IO) {
                delay(2000)
                Log.e("wpf123wpf", "withContext1 " + Thread.currentThread().name)
            }

            withContext(Dispatchers.IO) {
                delay(2000)
                Log.e("wpf123wpf", "withContext2 " + Thread.currentThread().name)
            }
        }
    }


    /**
     * 异步方式之一
     */
    fun test2(){
        GlobalScope.launch{
            val profile =async{

            }.await()
            withContext(Dispatchers.Main){
//                tv_cache_size.text= "" + profile
            }
        }
    }


    /**
     * 执行耗时任务,会切换线程
     * 但是可以并行
     * 使用await()获取结果对象
     */
    fun test3(){
        GlobalScope.launch {//此作用域在线程内

            val task1 = async(Dispatchers.IO) {
                delay(2000)
                Log.e("wpf123wpf", "task1 ")
                "2"
            }

            val task2 = async(Dispatchers.IO) {
                delay(3000)
                Log.e("wpf123wpf", "task2 ")
                "3"
            }
            Log.e("wpf123wpf", "耗时: "+task1.await()+"   "+task2.await() )

        }
    }


    /**
     * 顺序进行网络请求
     */
    fun test4(){
//        coroutineScope.launch(Dispatchers.Main) {       // 开始协程：主线程
//            val token = api.getToken()                  // 网络请求：IO 线程
//            val user = api.getUser(token)               // 网络请求：IO 线程
//            nameTv.text = user.name                     // 更新 UI：主线程
//        }
    }

    /**
     * 并发进行网络请求
     */
    fun test5(){
//        val avatar = async { api.getAvatar(user) }    // 获取用户头像
//        val logo = async { api.getCompanyLogo(user) } // 获取用户所在公司的 logo
//        val merged = suspendingMerge(avatar, logo)    // 合并结果
//        //                  👆
//        show(merged) // 更新 UI
    }

}