package com.project.threeschool.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.project.simplecode.spaIntentNoFin
import com.project.threeschool.R
import com.project.threeschool.base.BaseActivity
import com.project.threeschool.databinding.ActivityMainBinding
import com.project.threeschool.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel = MainViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_main


    override fun init() {

    }

    override fun observerViewModel() {
        with(viewModel){
            groupBtn.observe(this@MainActivity, Observer {
                spaIntentNoFin(GroupActivity::class.java)
            })
            normalBtn.observe(this@MainActivity, Observer {
                spaIntentNoFin(NormalActivity::class.java)
            })

        }
    }

}