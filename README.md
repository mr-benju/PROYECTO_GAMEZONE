    # GameZone - Tienda de Videojuegos (App Android)

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

## 📱 Descripción

GameZone es una aplicación móvil Android desarrollada en Kotlin que ofrece un sistema completo de registro y login optimizado para dispositivos móviles. La aplicación está diseñada para una tienda de videojuegos que busca mejorar su tasa de conversión de visitantes a usuarios registrados.

## ✨ Características

- ✅ **Registro optimizado**: Formulario minimalista con validaciones en tiempo real
- ✅ **Login inteligente**: Inicio de sesión con email o usuario
- ✅ **Diseño Material Design**: Interfaz moderna y atractiva
- ✅ **Validaciones robustas**: Mensajes de error claros y útiles
- ✅ **Gestión de sesión**: Recordar usuario autenticado
- ✅ **Responsive**: Optimizado para diferentes tamaños de pantalla

## 🛠️ Tecnologías

- **Kotlin** 1.9.0
- **Android SDK** 34
- **Material Design** 3
- **View Binding**
- **SharedPreferences** + Gson
- **Repository Pattern**

## 📸 Pantallas

1. **Splash Screen**: Pantalla de bienvenida con logo
2. **Login**: Inicio de sesión con validaciones
3. **Registro**: Formulario de registro optimizado
4. **Home**: Pantalla principal con catálogo de juegos

## 🚀 Cómo Usar

### Opción 1: Android Studio (Recomendado)

```bash
1. Descarga Android Studio desde https://developer.android.com/studio
2. Clona o descarga este repositorio
3. Abre el proyecto en Android Studio
4. Espera a que Gradle sincronice las dependencias
5. Conecta un dispositivo o inicia un emulador
6. Presiona Run (▶️)
```

### Opción 2: Línea de Comandos

```bash
# Compilar APK
./gradlew assembleDebug

# Instalar en dispositivo conectado
./gradlew installDebug
```

## 📁 Estructura del Proyecto

```
app/src/main/
├── java/com/gamezone/app/
│   ├── ui/                    # Actividades (pantallas)
│   ├── data/                  # Modelos y repositorio
│   └── utils/                 # Utilidades y validaciones
├── res/
│   ├── layout/                # Diseños XML
│   ├── values/                # Strings, colores, temas
│   └── drawable/              # Recursos gráficos
└── AndroidManifest.xml
```

## 🎨 Paleta de Colores

- **Primary**: #FF6B35 (Naranja GameZone)
- **Accent**: #4ECDC4 (Verde azulado)
- **Background**: #F7F7F7 (Gris claro)

## 📋 Requisitos

- Android 7.0 (API 24) o superior
- Java 17
- Gradle 8.0

## 📝 Validaciones Implementadas

- Email con formato válido
- Contraseña de mínimo 6 caracteres
- Nombre de mínimo 2 caracteres
- Usuario de mínimo 3 caracteres
- Confirmación de contraseña coincidente
- Prevención de usuarios duplicados

## 🔜 Mejoras Futuras

- [ ] Recuperación de contraseña
- [ ] Autenticación con redes sociales
- [ ] Integración con backend
- [ ] Catálogo completo de juegos
- [ ] Sistema de carrito y pagos
- [ ] Notificaciones push

## 👨‍💻 Desarrollado Por

Proyecto de la asignatura Desarrollo Móvil

---

**GameZone** - Tu tienda de videojuegos 🎮
