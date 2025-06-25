# Movie Database App

Android application for browsing movies using The Movie Database (TMDB) API

## Features
- Browse trending movies with infinite scrolling
- Search movies with auto-complete
- View movie details (title, overview, rating, release date)
- Clean Architecture with MVI pattern
- Jetpack Compose UI
- Dependency Injection with Koin

## Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVI
- **DI**: Koin
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil
- **Navigation**: Compose Navigation

## Setup Instructions

### Prerequisites
- Android Studio Giraffe or later
- Android SDK 34
- TMDB API key

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/OmarSakr/MovieApp.git
   ```
2. Add your TMDB API key:
    - Create `local.properties` in root directory
    - Add:
      ```
      TMDB_API_KEY=Bearer your_api_key_here
      ```
3. Sync and build the project in Android Studio

## API Reference
This app uses [The Movie Database API](https://developer.themoviedb.org/docs)
