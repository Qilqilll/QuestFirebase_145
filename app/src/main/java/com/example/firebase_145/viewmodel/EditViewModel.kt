package com.example.firebase_145.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_145.modeldata.DetailSiswa
import com.example.firebase_145.modeldata.UIStateSiswa
import com.example.firebase_145.modeldata.toDataSiswa
import com.example.firebase_145.modeldata.toUiStateSiswa
import com.example.firebase_145.repository.RepositorySiswa
import com.example.firebase_145.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Long =
        savedStateHandle.get<String>(DestinasiDetail.itemIdArg)?.toLong()
            ?: savedStateHandle.get<Long>(DestinasiDetail.itemIdArg)
            ?: 0L // Fallback safe

    init {
        viewModelScope.launch {
            if (idSiswa != 0L) {
                uiStateSiswa = repositorySiswa.getSatuSiswa(idSiswa)
                    ?.toUiStateSiswa(true)
                    ?: UIStateSiswa()
            }
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun editSatuSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            try {
                repositorySiswa.editSatuSiswa(
                    idSiswa,
                    uiStateSiswa.detailSiswa.toDataSiswa()
                )
                println("Update Sukses: $idSiswa")
            } catch (e: Exception) {
                println("Update Error: ${e.message}")
            }
        }
    }
}