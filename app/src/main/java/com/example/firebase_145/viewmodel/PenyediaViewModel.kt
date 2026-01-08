package com.example.firebase_145.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebase_145.repository.ContainerApp
import com.example.firebase_145.repository.RepositorySiswa

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AplikasiDataSiswa].
 */
fun CreationExtras.aplikasiDataSiswa(): com.example.firebase_145.AplikasiDataSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as com.example.firebase_145.AplikasiDataSiswa)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiDataSiswa().container.repositorySiswa)
        }
        initializer {
            EntryViewModel(aplikasiDataSiswa().container.repositorySiswa)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiDataSiswa().container.repositorySiswa
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiDataSiswa().container.repositorySiswa
            )
        }
    }
}