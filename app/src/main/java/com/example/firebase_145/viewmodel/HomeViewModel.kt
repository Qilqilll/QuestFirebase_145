package com.example.firebase_145.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_145.modeldata.Siswa
import com.example.firebase_145.repository.RepositorySiswa
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data class Success(val siswa: List<Siswa>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {
    var siswaUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa() {
        viewModelScope.launch {
            siswaUiState = HomeUiState.Loading
            siswaUiState = try {
                HomeUiState.Success(repositorySiswa.getDataSiswa())
            } catch (e: Exception) {
                HomeUiState.Error
            }
        }
    }

    fun deleteSiswa(siswa: Siswa) {
        viewModelScope.launch {
            try {
                repositorySiswa.deleteSiswa(siswa)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}