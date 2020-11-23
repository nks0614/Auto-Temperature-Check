package com.project.threeschool.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.threeschool.R
import com.project.threeschool.base.BaseActivity
import com.project.threeschool.databinding.ActivityGroupBinding
import com.project.threeschool.viewmodel.GroupViewModel

class GroupActivity : BaseActivity<ActivityGroupBinding, GroupViewModel>() {
    override val viewModel: GroupViewModel = GroupViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_group

    override fun init() {

    }

    override fun observerViewModel() {

    }

}