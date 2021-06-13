package com.mpdevelopment.hiltcoroutine.feature.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class PhoneUnlockReceiver : BroadcastReceiver() {

    @Inject
    @Named("refreshStream")
    lateinit var refreshNewsStream: MutableSharedFlow<Boolean>

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action.equals(Intent.ACTION_USER_PRESENT)) {
                CoroutineScope(Dispatchers.Main).launch {
                    Timber.d("Received unlock event in PhoneUnlockReceiver")
                    refreshNewsStream.emit(true)
                }
            }
        }
    }
}