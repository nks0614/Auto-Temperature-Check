package com.project.threeschool.viewmodel

import com.project.threeschool.base.BaseViewModel
import com.project.threeschool.widget.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    val groupBtn = SingleLiveEvent<Unit>()
    val normalBtn = SingleLiveEvent<Unit>()

    init {

    }

    fun groupBtnCall(){
        groupBtn.call()
    }

    fun normalBtnCall(){
        normalBtn.call()
    }


}