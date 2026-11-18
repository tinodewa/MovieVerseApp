# MovieVerse 🎬

**MovieVerse** adalah aplikasi Android modern untuk menjelajahi dunia perfilman dengan mengonsumsi **The Movie Database (TMDB) API**. Aplikasi ini dibangun menggunakan standar pengembangan Android modern (*Modern Android Development*) dengan arsitektur yang bersih (*Clean Architecture*), performa tinggi, serta tampilan UI yang elegan berbasis Jetpack Compose.

---

## 🚀 Download APK

Anda dapat mengunduh dan menguji langsung hasil *build release* (v1.1) aplikasi melalui link berikut:https://drive.google.com/file/d/13SabJ0gAIrGakIpdtzJvz_ww1PjTPd2G/view?usp=sharing

[![Download APK](https://img.shields.io/badge/Download-MovieVerse%20v1.1%20APK-brightgreen?style=for-the-badge&logo=android)](https://drive.google.com/file/d/13SabJ0gAIrGakIpdtzJvz_ww1PjTPd2G/view?usp=drive_link)

🔗 **Direct Link**: [MovieVerse Release v1.1 APK (Google Drive)](https://drive.google.com/file/d/13SabJ0gAIrGakIpdtzJvz_ww1PjTPd2G/view?usp=drive_link)

---

## ✨ Fitur Utama

- 🎭 **Genre List Screen**: Menampilkan daftar genre film dengan kartu visual yang menarik (gradient warna custom, icon dinamis, dan efek shimmer saat memuat data).
- 🔍 **Discover Movies Screen**: Fitur pencarian/penjelajahan film berdasarkan genre pilihan dengan fitur *infinite scrolling* (Paging 3).
- 🎬 **Movie Detail Screen**: Informasi lengkap film mencakup poster, backdrop, skor/rating, tanggal rilis, durasi (*runtime*), status, genre, dan sinopsis.
- ▶️ **In-App YouTube Trailer Player**: Pemutaran *trailer* film secara langsung di dalam aplikasi (*inline embedded player*) menggunakan `android-youtube-player`, lengkap dengan kontrol untuk membuka di aplikasi YouTube eksternal atau menutup player.
- 🎥 **Trailers & Videos**: Menampilkan daftar *trailer* dan video klip terkait film.
- 💬 **User Reviews**: Ulasan pengguna berhalaman (*paginated reviews*) dengan kartu ulasan yang dapat diperluas (*expandable*).
- ⚡ **Shimmer Loading & State Handling**: Pengalaman pengguna yang mulus dengan animasi *shimmer skeleton loading*, *empty state*, dan *error handling* lengkap dengan tombol *retry*.
- 🔍 **Network Traffic Inspector (Chucker)**: Inspeksi *HTTP Request/Response* secara langsung di notifikasi perangkat untuk kebutuhan debugging.
- 🔔 **Runtime Permission**: Penanganan izin notifikasi otomatis pada Android 13+ (API 33+).

---

## 🛠 Tech Stack & Library

MovieVerse dibuat menggunakan teknologi dan perpustakaan (*libraries*) Android terkini:

- **Bahasa**: [Kotlin](https://kotlinlang.org/) (v2.2.10)
- **Arsitektur**: Clean Architecture (Data, Domain, Presentation) + MVVM Pattern
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) & [Material 3](https://m3.material.io/)
- **Navigation**: [Jetpack Navigation 3](https://developer.android.com/guide/navigation) (`androidx.navigation3`)
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) (dengan KSP)
- **Video Player**: [Android YouTube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player) (`com.pierfrancescosoffritti.androidyoutubeplayer:core`)
- **Networking & Parsing**:
    - [Retrofit 3.0](https://square.github.io/retrofit/)
    - [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) (`kotlinx.serialization.json`)
    - [OkHttp 5](https://square.github.io/okhttp/) & Logging Interceptor
- **Pagination**: [Jetpack Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- **Image Loading**: [Coil Compose](https://coil-kt.github.io/coil/)
- **Asynchronous**: Kotlin Coroutines & Flow
- **Network Inspector**: [Chucker](https://github.com/ChuckerTeam/chucker) (Debugging HTTP Traffic)
- **Permissions**: Accompanist Permissions

---

## 📁 Struktur Proyek

```text
id.hit.movieverse/
├── common/                     # Sealed class ResultState & UiState
├── data/
│   ├── domain/
│   │   ├── model/             # Domain Data Models (Movie, Genre, Review, Video)
│   │   └── repository/        # Repository Interfaces
│   └── sources/remote/
│       ├── dto/               # Data Transfer Objects dengan kotlinx.serialization
│       ├── mapper/            # DTO to Domain Data Mappers
│       ├── paging/            # PagingSource (Movies & Reviews)
│       └── repository/        # Implementasi Repository & ApiService
├── di/                        # Hilt Dependency Injection Modules (Network & Repository)
├── ui/
│   ├── navigation/            # Navigation Graph & Screen Definitions (Nav3)
│   ├── screen/
│   │   ├── genre/             # Genre List Screen & Components
│   │   ├── discover/          # Discover Movies Screen & Components
│   │   └── detail/            # Movie Detail Screen & Components (termasuk MovieTrailerSection)
│   └── theme/                 # Styling, Colors (Crimson Gold & Dark), Typography, Theme
└── utils/                     # Safe API Call Utilities, Helpers, Constants, Permissions
```

---

## ⚙️ Cara Menjalankan Project

1. **Clone repositori ini**:
   ```bash
   git clone <repository-url>
   cd MovieVerse
   ```

2. **Konfigurasi API Key TMDB**:
   Buat atau buka file `local.properties` di akar proyek (*root project*), lalu tambahkan API Key dari [TMDB API](https://www.themoviedb.org/documentation/api):
   ```properties
   TMDB_API_KEY=your_tmdb_api_key_here
   ```

3. **Build & Run**:
    - Buka proyek di **Android Studio** (disarankan versi Ladybug / Meerkat atau yang lebih baru).
    - Jalankan Gradle Sync.
    - Hubungkan emulator atau perangkat fisik (Android 8.0 / API 26+).
    - Klik tombol **Run 'app'**.

---

## 📄 Lisensi & Informasi

Dikembangkan untuk kebutuhan demonstrasi dan pengujian integrasi Android API TMDB dengan Jetpack Compose & Clean Architecture.
