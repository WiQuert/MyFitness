package com.wiquert.myfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wiquert.myfitness.fragments.DaysFragment
import com.wiquert.myfitness.utils.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysFragment) onBackPressedDispatcher.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}

