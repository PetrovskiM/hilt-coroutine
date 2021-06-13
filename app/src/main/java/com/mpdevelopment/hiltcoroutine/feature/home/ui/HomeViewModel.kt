package com.mpdevelopment.hiltcoroutine.feature.home.ui

import androidx.lifecycle.*
import com.mpdevelopment.hiltcoroutine.data.repository.NewsRepository
import com.mpdevelopment.hiltcoroutine.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(  
    private val newsRepository: NewsRepository,
    @Named("refreshFlow") val refreshStream: Flow<Boolean>
) :
    ViewModel() {

    private val shouldRefresh = MutableLiveData(true)
    val news: LiveData<NewsItem> =
        shouldRefresh.switchMap {
            liveData { emit(newsRepository.get().random()) }
        }

    init {
        viewModelScope.launch {
            refreshStream.collect {
                Timber.d("Received unlock event. Refreshing news.")
                shouldRefresh.value = true
            }
        }
    }
}