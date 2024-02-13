package com.livmas.effective_mobile_test_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livmas.effective_mobile_test_app.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}