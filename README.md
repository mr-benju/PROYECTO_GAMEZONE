# GameZone - Aplicación Android de Tienda de Videojuegos 🎮

<img src="app/src/main/res/drawable/gamezone_logo.png" alt="GameZone Logo" width="200"/>

## 📱 Descripción

GameZone es una aplicación móvil Android moderna para la compra de videojuegos. Desarrollada con **Kotlin** y **Jetpack Compose**, ofrece una experiencia de usuario fluida y atractiva.

## ✨ Características

- 🔐 **Sistema de autenticación completo** (Login y Registro)
- 🎨 **Interfaz moderna** con Material Design 3
- 🖼️ **Logo animado** en pantalla de bienvenida
- 🛒 **Carrito de compras**
- 📦 **Catálogo de videojuegos populares**
- ✅ **Validación de formularios en tiempo real**
- 👁️ **Toggle de visibilidad de contraseñas**
- 🎨 **Gradientes y diseño moderno**

## 🛠️ Tecnologías

- **Lenguaje**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **Build Tool**: Gradle 8.13.0
- **Material Design**: Material 3
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 🚀 Cómo Ejecutar

### Opción 1: Android Studio (Recomendado)

1. **Descarga e instala** [Android Studio](https://developer.android.com/studio)
2. **Abre el proyecto**:
   - File → Open → Selecciona la carpeta del proyecto
3. **Espera** a que Gradle sincronice las dependencias
4. **Configura un emulador**:
   - Tools → Device Manager → Create Device
   - Selecciona un dispositivo (ej: Pixel 6)
   - Descarga una imagen del sistema (ej: Android 14)
5. **Ejecuta la app**:
   - Click en el botón Run (▶️) o presiona `Shift + F10`
   - Selecciona el emulador o dispositivo físico

### Opción 2: Línea de Comandos (Gradle)

```bash
# Compilar el proyecto
./gradlew build

# Generar APK de debug
./gradlew assembleDebug

# El APK estará en:
# app/build/outputs/apk/debug/app-debug.apk

# Instalar en dispositivo conectado vía USB
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Opción 3: Usar Gradle Wrapper (Linux/Mac)

```bash
# Dar permisos de ejecución
chmod +x gradlew

# Compilar
./gradlew build

# Limpiar y compilar
./gradlew clean build
```

### Opción 4: Windows

```cmd
gradlew.bat build
gradlew.bat assembleDebug
```

## ⚠️ Limitaciones en Replit

**IMPORTANTE**: Esta aplicación Android **NO puede ejecutarse en Replit** porque:

- ❌ Android requiere Android Runtime (ART) o un emulador
- ❌ Replit no soporta emuladores Android
- ❌ La app está diseñada para dispositivos móviles, no navegadores web

### ✅ Lo que SÍ puedes hacer en Replit:

- Ver y editar el código fuente
- Explorar la arquitectura del proyecto
- Modificar componentes y pantallas
- Revisar la lógica de negocio
- Aprender sobre Jetpack Compose

## 📂 Estructura del Proyecto

```
gamezone/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/gamezone/
│   │   │   │   ├── data/              # Capa de datos
│   │   │   │   ├── models/            # Modelos de datos
│   │   │   │   ├── viewModel/         # ViewModels (MVVM)
│   │   │   │   ├── views/             # Pantallas Compose
│   │   │   │   ├── navigation/        # Navegación
│   │   │   │   ├── ui/theme/          # Tema y colores
│   │   │   │   └── MainActivity.kt    # Actividad principal
│   │   │   ├── res/                   # Recursos
│   │   │   │   ├── drawable/          # Imágenes
│   │   │   │   └── values/            # Strings, colores
│   │   │   └── AndroidManifest.xml
│   │   └── test/                      # Tests unitarios
│   └── build.gradle.kts               # Config Gradle app
├── gradle/                            # Gradle wrapper
├── build.gradle.kts                   # Config Gradle proyecto
└── settings.gradle.kts                # Settings Gradle
```

## 🎨 Pantallas Mejoradas

### 1. Pantalla de Inicio
- Fondo con gradiente morado-cyan
- Logo en card elevada
- Botones modernos con bordes redondeados
- **Sin animación** (logo estático)

### 2. Login
- Logo GameZone integrado
- Campos con iconos
- Toggle para mostrar/ocultar contraseña
- Validación de campos vacíos
- Indicador de carga

### 3. Registro
- Logo GameZone
- Confirmación de contraseña
- Validación en tiempo real
- Mensajes de ayuda
- Scroll para pantallas pequeñas
- Validación de email @duoc.cl
- Mínimo 6 caracteres en contraseña

## 🔧 Requisitos del Sistema

- **Java Development Kit (JDK)**: 11 o superior
- **Android Studio**: Arctic Fox o superior
- **Gradle**: 8.13.0 (incluido)
- **RAM**: Mínimo 4GB (8GB recomendado)
- **Espacio en disco**: 4GB para Android Studio + 2GB para SDK

## 📦 Dependencias Principales

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose:1.11.0")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

// Carga de imágenes
implementation("io.coil-kt:coil-compose:2.4.0")
```

## 🎮 Juegos Incluidos

La app incluye imágenes de videojuegos populares:
- The Last of Us 1 y 2
- God of War
- The Legend of Zelda
- Cyberpunk 2077
- Forza
- Call of Duty: Black Ops 3
- Assassin's Creed Odyssey

## 🔐 Sistema de Autenticación

- Base de datos en memoria (FakeDatabase)
- Validación de email @duoc.cl
- Contraseñas con toggle de visibilidad
- Validación de campos en tiempo real

## 🚧 Próximas Mejoras

- [ ] Base de datos persistente (Room)
- [ ] Autenticación con Firebase
- [ ] Sistema de pagos
- [ ] Filtros y búsqueda
- [ ] Perfil de usuario
- [ ] Wishlist
- [ ] Calificaciones y reseñas

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👥 Contribuir

Las contribuciones son bienvenidas. Por favor:
1. Fork el proyecto
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

---

**Desarrollado con ❤️ usando Kotlin y Jetpack Compose**
