package com.knc.nasachallenge

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.knc.nasachallenge.databinding.ActivityMainBinding
import com.knc.nasachallenge.fragments.HomeFrg

class MainActivity : FragmentActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(
                viewBinding.fragmentHolder.id,
                HomeFrg.newInstance(viewBinding.fragmentHolder.id)
            )
            .commit()
    }
}