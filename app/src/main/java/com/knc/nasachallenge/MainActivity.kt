package com.knc.nasachallenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.knc.data.local.MainDb
import com.knc.nasachallenge.databinding.ActivityMainBinding
import com.knc.nasachallenge.fragments.HomeFrg
import com.knc.nasachallenge.view_models.AppViewModel

class MainActivity : FragmentActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        appViewModel.mainDb = MainDb.getDb(this)

        supportFragmentManager.beginTransaction()
            .replace(
                viewBinding.fragmentHolder.id,
                HomeFrg.newInstance(viewBinding.fragmentHolder.id)
            )
            .commit()
    }
}