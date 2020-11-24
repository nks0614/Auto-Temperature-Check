package com.project.threeschool.viewmodel

import androidx.lifecycle.MutableLiveData
import com.project.threeschool.base.BaseViewModel
import com.project.threeschool.model.Members
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.net.SocketAddress

class GroupViewModel : BaseViewModel(){
    var count = MutableLiveData<Int>()
    var liveList = MutableLiveData<ArrayList<Members>>()
    var originList = ArrayList<Members>()
    lateinit var socket : Socket

    fun connectServer(){
        try{
            socket = Socket("127.0.0.1", 9000)
            var input = DataInputStream(socket.getInputStream())
            while(true){
                var a = input.readUTF()
            }
        } catch (e : Exception){
            e.printStackTrace()
        }

    }

    fun listAdd(m: Members){
        originList.add(m)
        liveList.value = originList
    }
}