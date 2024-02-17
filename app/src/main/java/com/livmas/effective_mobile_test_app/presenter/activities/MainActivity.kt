package com.livmas.effective_mobile_test_app.presenter.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.ActivityMainBinding
import com.livmas.ui.HostActivity

class MainActivity : AppCompatActivity(), HostActivity {
    override val pageTitle: MutableLiveData<String> = MutableLiveData(null)
    override val navController: NavController
        get() = binding.navHost.findNavController()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeTitleUpdates()
    }

    override fun onStart() {
        super.onStart()

        binding.navView.setupWithNavController(
            findNavController(R.id.navHost)
        )
    }

    private fun observeTitleUpdates() {
        pageTitle.observe(this) {
            binding.tvTitle.text = it
        }
    }
}