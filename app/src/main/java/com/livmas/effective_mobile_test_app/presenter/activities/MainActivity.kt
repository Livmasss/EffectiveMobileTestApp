package com.livmas.effective_mobile_test_app.presenter.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.ActivityMainBinding
import com.livmas.effective_mobile_test_app.presenter.NAVIGATION_TAG

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(NAVIGATION_TAG, "Main activity onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.navView.setupWithNavController(
            findNavController(R.id.nav_host)
        )
    }

    fun setTitleResource(resId: Int) =
        binding.tvTitle.setText(resId)
}