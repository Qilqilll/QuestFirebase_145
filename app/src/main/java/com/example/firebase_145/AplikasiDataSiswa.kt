package com.example.firebase_145

import android.app.Application
import com.example.firebase_145.repository.ContainerApp
import com.example.firebase_145.repository.DefaultContainerApp

class AplikasiDataSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}
