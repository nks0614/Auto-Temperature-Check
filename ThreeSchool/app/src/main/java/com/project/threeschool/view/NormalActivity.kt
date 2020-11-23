package com.project.threeschool.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.threeschool.R
import com.project.threeschool.base.BaseActivity
import com.project.threeschool.databinding.ActivityNormalBinding
import com.project.threeschool.viewmodel.NormalViewModel

class NormalActivity : BaseActivity<ActivityNormalBinding, NormalViewModel>() {
    override val viewModel: NormalViewModel = NormalViewModel()
    override val layoutRes: Int
        get() = R.layout.activity_normal

    override fun init() {

    }

    override fun observerViewModel() {

    }

}