package com.ragnar.baseapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragnar.baseapp.common.Error
import com.ragnar.baseapp.model.Popular
import com.ragnar.baseapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        getPopular()
    }

    private fun getPopular() {
        viewModelScope.launch {
            homeUiState = homeUiState.copy(isLoading = true, error = null)
            val result = try {
                repository.getPopular()
            } catch (e: HttpException) {
                homeUiState = homeUiState.copy(error = Error(code = null, message = e.localizedMessage), isLoading = false)
                return@launch
            } catch (e: IOException) {
                homeUiState = homeUiState.copy(error = Error(code = null, message = e.localizedMessage), isLoading = false)
                return@launch
            }
            homeUiState = homeUiState.copy(popular = result, isLoading = false)
        }
    }
}

data class HomeUiState(
    var popular: Popular? = null,
    val isLoading: Boolean = false,
    var error: Error? = null
)