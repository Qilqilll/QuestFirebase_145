# Struktur Project Android - Firebase_145

## Arsitektur: MVVM + Repository Pattern + Firebase

### 📁 Root Project
```
Firebase_145/
├── .gradle/                    # Cache Gradle
├── .idea/                      # Konfigurasi Android Studio
├── .kotlin/                    # Konfigurasi Kotlin
├── .gitignore                  # Git ignore file
└── build.gradle.kts            # Build configuration root
```

### 📁 Module App
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/Firebase_145/
│   │   │   ├── modeldata/
│   │   │   │   └── Siswa.kt                    # Data class model siswa
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── RepositorySiswa.kt          # CRUD Firebase operations
│   │   │   │   └── ContainerApp.kt             # Dependency container
│   │   │   │
│   │   │   ├── ui/theme/
│   │   │   │   ├── Color.kt                    # Color definitions
│   │   │   │   ├── Theme.kt                    # Theme configuration
│   │   │   │   └── Type.kt                     # Typography definitions
│   │   │   │
│   │   │   ├── view/
│   │   │   │   ├── controlNavigasi/
│   │   │   │   │   └── PetaNavigasi.kt         # NavHost & navigation setup
│   │   │   │   │
│   │   │   │   ├── route/
│   │   │   │   │   └── DestinasiNavigasi.kt    # Navigation destinations
│   │   │   │   │
│   │   │   │   ├── HalamanHome.kt              # Home screen
│   │   │   │   ├── HalamanEntry.kt             # Add data screen
│   │   │   │   ├── HalamanDetail.kt            # Detail screen
│   │   │   │   ├── HalamanEdit.kt              # Edit screen
│   │   │   │   └── SiswaTopAppBar.kt           # Custom TopAppBar
│   │   │   │
│   │   │   ├── viewmodel/
│   │   │   │   ├── HomeViewModel.kt            # Home logic
│   │   │   │   ├── EntryViewModel.kt           # Entry logic
│   │   │   │   ├── DetailViewModel.kt          # Detail logic
│   │   │   │   ├── EditViewModel.kt            # Edit logic
│   │   │   │   └── PenyediaViewModel.kt        # ViewModel factory
│   │   │   │
│   │   │   └── MainActivity.kt                 # App entry point
│   │   │
│   │   ├── res/                                # Android resources
│   │   └── AndroidManifest.xml                 # App manifest
│   │
│   ├── androidTest/                            # Instrumentation tests
│   └── test/                                   # Unit tests
│
├── google-services.json                        # Firebase configuration
└── proguard-rules.pro                          # ProGuard rules
```

## 🏗️ Prinsip Arsitektur

### MVVM (Model-View-ViewModel)
- **Model**: `modeldata/` - Data classes & entities
- **View**: `view/` - Composable UI screens
- **ViewModel**: `viewmodel/` - UI logic & state management

### Repository Pattern
- `repository/RepositorySiswa.kt` - Single source of truth untuk data Firebase
- `repository/ContainerApp.kt` - Dependency injection container

### Navigation
- Navigation Compose untuk navigasi antar halaman
- `PetaNavigasi.kt` - Mengatur NavHost dan routing
- `DestinasiNavigasi.kt` - Definisi route destinations

### Firebase Integration
- `google-services.json` - Firebase project configuration
- `RepositorySiswa.kt` - Firebase Firestore operations

## 📦 Package Structure

```
com.example.Firebase_145
├── modeldata           → Data models
├── repository          → Data layer & Firebase
├── ui.theme            → Compose theme
├── view                → UI layer
│   ├── controlNavigasi → Navigation setup
│   └── route           → Route definitions
└── viewmodel           → Business logic
```

## ✅ Status
Semua file dan folder telah dibuat dengan struktur kosong.
Siap untuk diisi dengan implementasi kode.
