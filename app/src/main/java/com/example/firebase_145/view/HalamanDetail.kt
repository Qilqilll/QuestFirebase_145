package com.example.firebase_145.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase_145.R
import com.example.firebase_145.modeldata.Siswa
import com.example.firebase_145.view.route.DestinasiNavigasi
import com.example.firebase_145.view.route.DestinasiDetail
import com.example.firebase_145.viewmodel.PenyediaViewModel
import com.example.firebase_145.viewmodel.DetailViewModel
import com.example.firebase_145.viewmodel.StatusUIDetail



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToEditItem: (Long) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollState = rememberScrollState()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(DestinasiDetail.titleRes)) },
                navigationIcon = {
                    // Pastikan Anda memiliki fungsi/komponen untuk tombol kembali
                    // Contoh: IconButton(onClick = navigateBack) { Icon(...) }
                },
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Mengambil ID dari state saat ini jika sukses
                    val currentId = (viewModel.statusUIDetail as? StatusUIDetail.Success)?.satusiswa?.id
                    if (currentId != null) {
                        navigateToEditItem(currentId)
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit_siswa)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->

        // Logika untuk menampilkan status UI (Loading, Success, Error)
        val statusUIDetail = viewModel.statusUIDetail

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            when (statusUIDetail) {
                is StatusUIDetail.Loading -> {
                    Text(text = "Loading...", modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is StatusUIDetail.Success -> {
                    // Menampilkan Detail Siswa jika sukses
                    statusUIDetail.satusiswa?.let { siswa ->
                        ItemDetailMhs(
                            siswa = siswa,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(8.dp))

                        // Tombol Delete
                        OutlinedButton(
                            onClick = { deleteConfirmationRequired = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = stringResource(R.string.delete))
                        }
                    }
                }
                is StatusUIDetail.Error -> {
                    Text(text = "Terjadi Kesalahan", modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    // Panggil fungsi hapus di ViewModel
                    // viewModel.hapusSatuSiswa()
                    navigateBack()
                },
                onDeleteCancel = { deleteConfirmationRequired = false }
            )
        }
    }
}

@Composable
fun ItemDetailMhs(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ComponentDetailMhs(
                judul = "Nama",
                isinya = siswa.nama
            )
            ComponentDetailMhs(
                judul = "Alamat",
                isinya = siswa.alamat
            )
            ComponentDetailMhs(
                judul = "No Telepon",
                isinya = siswa.telpon
            )
        }
    }
}

@Composable
fun ComponentDetailMhs(
    judul: String,
    isinya: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = judul,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = isinya,
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider()
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete)) }, // Ganti dengan resource string yang sesuai
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        }
    )
}