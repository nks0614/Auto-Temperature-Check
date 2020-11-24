package com.project.threeschool.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.threeschool.adapter.TempListAdapter
import com.project.threeschool.base.BaseViewModel
import com.project.threeschool.model.Members
import com.project.threeschool.network.Service
import com.project.threeschool.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.net.SocketAddress

class GroupViewModel : BaseViewModel(){
    var list = ArrayList<Double>()
    val tempListAdapter : TempListAdapter = TempListAdapter(list)

    var checkStatus = 0

    val grade = MutableLiveData<String>()
    val fileName = MutableLiveData<String>()

    val importExcelBtn = SingleLiveEvent<Unit>()
    val refreshBtn = SingleLiveEvent<Unit>()

    lateinit var retrofit : Retrofit
    lateinit var API : Service

    fun callList(){
        API.getList().enqueue(object : Callback<Members>{
            override fun onResponse(call: Call<Members>, response: Response<Members>) {
                Log.d("Server", response.body()?.statusCode)
                if(response.body()?.statusCode == "200"){
                    grade.value = "${response.body()?.data?.grade}학년 ${response.body()?.data?.classNum}반"
                    response.body()?.data?.list?.let {
                        list.clear()
                        list.addAll(it)
                        tempListAdapter.notifyDataSetChanged()
                    }

                }
            }

            override fun onFailure(call: Call<Members>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

    fun refreshBtnCall(){
        refreshBtn.call()
    }

    fun importExcelBtnCall(){
        importExcelBtn.call()
    }

}