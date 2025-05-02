package com.example.projeto3mundo4

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioDeviceInfo
import android.media.AudioManager
import androidx.core.content.getSystemService

class AudioHelper (private val context: Context) {
    private val audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun audioOutputAvailable(type: Int): Boolean {
        if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false
        }
        return audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS).any { it.type == type }
    }

    fun isSpeakerAvailable(): Boolean {
        return audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER)
    }

    fun isBluetoothConnected(): Boolean {
        return audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP)
    }
}
