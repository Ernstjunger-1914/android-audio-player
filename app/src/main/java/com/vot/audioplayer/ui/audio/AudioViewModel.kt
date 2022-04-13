package com.vot.audioplayer.ui.audio

import android.support.v4.media.MediaBrowserCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vot.audioplayer.data.model.Audio
import com.vot.audioplayer.data.repository.AudioRepository
import com.vot.audioplayer.media.exoplayer.MediaPlayerServiceConnection
import com.vot.audioplayer.media.exoplayer.isPlaying
import com.vot.audioplayer.media.service.MediaPlayerService
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioViewModel @Inject constructor(
    private val repository: AudioRepository,
    serviceConnection: MediaPlayerServiceConnection
) : ViewModel() {
    var audioList = mutableListOf<Audio>()
    val currentPlayingAudio = serviceConnection.currentPlayingAudio
    private val isConnected = serviceConnection.isConnected
    lateinit var rootMediaId: String
    var currentPlayBackPosition by mutableStateOf(0L)
    private var updatePosition: Boolean = true
    private val playbackState = serviceConnection.plaBackState

    val isAudioPlaying: Boolean
        get() = playbackState.value?.isPlaying == true

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(
            parentId: String,
            children: MutableList<MediaBrowserCompat.MediaItem>
        ) {
            super.onChildrenLoaded(parentId, children)
        }
    }

    private val serviceConnection = serviceConnection.also {
    }

    val currentDuration = MediaPlayerService.currentDuration
    var currentAudioProgress = mutableStateOf(0f)

    init {
    }

}