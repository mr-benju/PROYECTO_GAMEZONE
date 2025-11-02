# GameZone - Android Mobile Application

## Overview
This is an Android mobile application built with Kotlin and Jetpack Compose. The app appears to be a game store/marketplace called "GameZone" where users can browse games, add them to cart, and manage their account.

## Project Type
**Android Mobile App** - This project is designed to run on Android devices or emulators, not in a web browser.

## Technology Stack
- **Language**: Kotlin 2.0.21
- **Build System**: Gradle 8.13.0
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Min Android SDK**: 24 (Android 7.0)
- **Target Android SDK**: 34 (Android 14)

## Project Structure
- `app/src/main/java/com/example/gamezone/`
  - `data/` - Data layer (FakeDatabase, ProductRepository)
  - `models/` - Data models (Product, Users)
  - `viewModel/` - ViewModels for MVVM pattern
  - `views/` - Compose UI screens (Login, Register, Home, Cart)
  - `navigation/` - App navigation logic
  - `ui/theme/` - Theme configuration
  - `MainActivity.kt` - App entry point

## Features
- User authentication (Login/Register)
- Product browsing
- Shopping cart
- Game catalog with images

## Limitations in Replit Environment
**Important**: This Android application cannot run directly in Replit's web environment because:
1. Android apps require an Android Runtime (ART) or emulator
2. Replit's environment is Linux-based and doesn't support Android emulators
3. The app is designed for mobile devices, not web browsers

## What Can Be Done in Replit
- View and edit the source code
- Build the project using Gradle (if Java/Android SDK is configured)
- Review the app architecture and code

## To Run This App
You would need to:
1. Use Android Studio on a local machine
2. Use an Android emulator or physical Android device
3. Build and install the APK

## Date
Project imported: November 02, 2025
