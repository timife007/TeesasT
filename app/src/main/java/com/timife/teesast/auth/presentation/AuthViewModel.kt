package com.timife.teesast.auth.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.teesast.auth.data.AuthResponse
import com.timife.teesast.auth.domain.AuthRepository
import com.timife.teesast.utils.Result
import com.timife.teesast.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    private val _auth = MutableLiveData<AuthResponse?>()
    val auth = _auth.asLiveData()


    fun registerUser() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val result = repository.registerUser()) {
                is Result.Success -> {
                    _isLoading.value = false
                    if (result.data != null) {
                        val register = result.data
                        _dataFetchState.value = true
                        _auth.value = register
                    }
                }
                is Result.Error -> {
                    _isLoading.value = false
                    _dataFetchState.value = false
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

}