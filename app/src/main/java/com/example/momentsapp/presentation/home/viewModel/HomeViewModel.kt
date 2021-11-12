package com.example.momentsapp.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.usecase.GetMoments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val getMoments: GetMoments) : ViewModel() {

    private val _errorMessage = MutableLiveData<Throwable>()
    val errorMessage: LiveData<Throwable>
        get() = _errorMessage

    private val _progressBar = MutableLiveData<Boolean>(false)
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _moments = MutableLiveData<MutableList<Moment>>()
    val moment: LiveData<MutableList<Moment>>
        get() = _moments

    private fun showProgressBar() {
        _progressBar.postValue(true)
    }

    private fun hideProgressBar() {
        _progressBar.postValue(false)
    }

    fun getMoments() {
        viewModelScope.launch(Dispatchers.IO) {
            showProgressBar()
            try {
                getMoments.run {
                    _moments.postValue(it)
                }

            } catch (error: Throwable) {
                _errorMessage.postValue(error)
            } finally {
                hideProgressBar()
            }

        }
    }
}