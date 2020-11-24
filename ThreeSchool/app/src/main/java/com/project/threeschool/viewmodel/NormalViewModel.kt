package com.project.threeschool.viewmodel

import androidx.lifecycle.MutableLiveData
import com.project.threeschool.base.BaseViewModel
import com.project.threeschool.model.Members
import com.project.threeschool.widget.SingleLiveEvent

class NormalViewModel : BaseViewModel(){
    var originList = ArrayList<Members>()

    val importExcelBtn = SingleLiveEvent<Unit>()

    fun importExcelBtnCall(){
        importExcelBtn.call()
    }
}