package com.mpdevelopment.hiltcoroutine.feature.home.ui

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mpdevelopment.hiltcoroutine.R
import com.mpdevelopment.hiltcoroutine.databinding.ActivityHomeBinding
import com.mpdevelopment.hiltcoroutine.feature.home.PhoneUnlockReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var receiver: PhoneUnlockReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setDrawBehindStatusNavBar()
        setShareClickListener()
        receiver = PhoneUnlockReceiver()
        val i = IntentFilter(Intent.ACTION_USER_PRESENT);
        registerReceiver(receiver, i)
    }

    private fun setShareClickListener() {
        binding.buttonShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.news.value?.permalink)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun setDrawBehindStatusNavBar() = window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}