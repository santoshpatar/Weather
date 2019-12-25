package com.example.wather.Activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.abc.ui.main.MainFragment
import com.example.wather.R

/**
 * santosh patar
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
