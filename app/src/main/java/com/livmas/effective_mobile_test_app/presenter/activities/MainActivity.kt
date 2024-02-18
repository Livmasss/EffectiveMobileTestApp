package com.livmas.effective_mobile_test_app.presenter.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.ActivityMainBinding
import com.livmas.ui.HostActivity

class MainActivity : AppCompatActivity(), HostActivity {
    override val pageTitle: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }
    override val showTitle: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }
    override val navController: NavController
        get() = binding.navHost.findNavController()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeHostUpdates()
    }

    override fun onStart() {
        super.onStart()

        binding.navView.setupWithNavController(
            findNavController(R.id.navHost)
        )
    }

    private fun observeHostUpdates() {
        pageTitle.observe(this) {
            binding.tvTitle.text = it
        }
        showTitle.observe(this) {
            binding.tvTitle.visibility = if (it)
                View.VISIBLE
            else
                View.GONE
        }
    }
}