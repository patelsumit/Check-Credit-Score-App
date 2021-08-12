package com.app.checkcreditscore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.checkcreditscore.view.fragment.FragmentView
import com.app.view.checkcreditscore.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, FragmentView.newInstance())
                .commitNow()
        }
    }
}