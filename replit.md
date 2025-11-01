# GameZone - Aplicación Móvil Android

## Descripción del Proyecto

GameZone es una aplicación móvil Android desarrollada en Kotlin para una tienda de videojuegos. La aplicación ofrece un sistema completo de registro y login optimizado especialmente para dispositivos móviles, con el objetivo de mejorar la tasa de conversión de visitantes a usuarios registrados.

## Características Principales

### 1. Sistema de Registro Optimizado
- Formulario de registro minimalista con campos esenciales
- Validaciones en tiempo real con mensajes de error claros
- Diseño completamente optimizado para dispositivos móviles
- Interfaz intuitiva que guía al usuario paso a paso

### 2. Sistema de Login
- Inicio de sesión con email o nombre de usuario
- Mensajes de error específicos y útiles
- Diseño responsivo para diferentes tamaños de pantalla
- Funcionalidad de recordar sesión

### 3. Interfaz de Usuario
- Diseño Material Design moderno y atractivo
- Esquema de colores personalizado para GameZone
- Transiciones suaves y animaciones
- Experiencia de usuario optimizada

## Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/gamezone/app/
│   │   ├── ui/              # Actividades (pantallas)
│   │   │   ├── MainActivity.kt
│   │   │   ├── LoginActivity.kt
│   │   │   ├── RegisterActivity.kt
│   │   │   └── HomeActivity.kt
│   │   ├── viewmodel/       # ViewModels (lógica de presentación)
│   │   │   ├── LoginViewModel.kt
│   │   │   ├── RegisterViewModel.kt
│   │   │   ├── HomeViewModel.kt
│   │   │   └── ViewModelFactory.kt
│   │   ├── data/            # Modelos y repositorio
│   │   │   ├── User.kt
│   │   │   └── UserRepository.kt
│   │   └── utils/           # Utilidades
│   │       └── ValidationUtils.kt
│   ├── res/
│   │   ├── layout/          # Diseños XML
│   │   ├── values/          # Recursos (strings, colors, themes)
│   │   └── drawable/        # Recursos gráficos
│   └── AndroidManifest.xml
├── build.gradle             # Configuración del módulo
└── proguard-rules.pro
```

## Arquitectura MVVM

Este proyecto implementa el patrón **MVVM (Model-View-ViewModel)** para una separación clara de responsabilidades:

- **Model** (`data/`): Entidades de datos (`User`) y lógica de acceso a datos (`UserRepository`)
- **View** (`ui/` + `res/layout/`): Activities y layouts XML que muestran la interfaz
- **ViewModel** (`viewmodel/`): Lógica de presentación y manejo de estados usando LiveData

### Beneficios de MVVM:
- ✅ Separación de lógica de negocio y UI
- ✅ Testabilidad mejorada (ViewModels pueden probarse independientemente)
- ✅ Manejo reactivo de estados con LiveData
- ✅ Supervivencia a cambios de configuración (rotación de pantalla)

## Tecnologías Utilizadas

- **Lenguaje**: Kotlin 1.9.0
- **SDK Android**: Target SDK 34, Min SDK 24
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **UI**: Material Design 3
- **View Binding**: Habilitado
- **Almacenamiento**: SharedPreferences con Gson (⚠️ solo para demostración educativa)

## ⚠️ Nota Importante de Seguridad

**Este proyecto es educativo y de demostración**. Las contraseñas se almacenan en texto plano para simplicidad. Para producción, consulta `SECURITY_NOTES.md` para implementar:
- Hash de contraseñas (BCrypt, Argon2, PBKDF2)
- EncryptedSharedPreferences
- Autenticación con backend seguro
- Tokens JWT/OAuth

### Dependencias Principales

- AndroidX Core KTX
- Material Components
- ConstraintLayout
- Lifecycle ViewModel & LiveData
- Gson para serialización JSON

## Cómo Compilar y Ejecutar

### Opción 1: Android Studio (Recomendado)

1. Descarga e instala [Android Studio](https://developer.android.com/studio)
2. Descarga este proyecto
3. Abre el proyecto en Android Studio (File → Open → selecciona la carpeta del proyecto)
4. Espera a que Gradle sincronice las dependencias automáticamente
5. Conecta un dispositivo Android físico o crea un emulador Android
6. Haz clic en el botón "Run" (▶️) o presiona Shift+F10

### Opción 2: Línea de Comandos

```bash
# Compilar APK de debug
./gradlew assembleDebug

# El APK se generará en:
# app/build/outputs/apk/debug/app-debug.apk

# Para instalar en un dispositivo conectado
./gradlew installDebug
```

## Pantallas de la Aplicación

### 1. Splash Screen (MainActivity)
- Pantalla de bienvenida con el logo de GameZone
- Se muestra durante 2 segundos
- Redirige automáticamente a Login (si no está autenticado) o Home (si ya está autenticado)

### 2. Pantalla de Login (LoginActivity)
- Campo de email/usuario con validación
- Campo de contraseña con opción de mostrar/ocultar
- Botón de inicio de sesión con validación de credenciales
- Enlace para ir a la pantalla de registro
- Mensajes de error claros y específicos

### 3. Pantalla de Registro (RegisterActivity)
- Campos: Nombre completo, Usuario, Email, Contraseña, Confirmar contraseña
- Validaciones en tiempo real:
  - Nombre: mínimo 2 caracteres
  - Usuario: mínimo 3 caracteres
  - Email: formato válido
  - Contraseña: mínimo 6 caracteres
  - Confirmación: debe coincidir con la contraseña
- Prevención de duplicados (email y usuario únicos)
- Enlace para volver al login

### 4. Pantalla Home (HomeActivity)
- Mensaje de bienvenida personalizado con el nombre del usuario
- Catálogo de juegos destacados
- Botón de cerrar sesión
- Barra de herramientas superior con el logo de GameZone

## Validaciones Implementadas

El sistema incluye validaciones robustas en `ValidationUtils.kt`:

- **Email**: Formato válido usando Patterns.EMAIL_ADDRESS
- **Contraseña**: Mínimo 6 caracteres
- **Nombre**: No vacío, mínimo 2 caracteres
- **Usuario**: Mínimo 3 caracteres
- **Confirmación de contraseña**: Coincidencia exacta

## Almacenamiento de Datos

Los datos de usuarios se almacenan localmente usando:
- **SharedPreferences** para persistencia
- **Gson** para serialización/deserialización JSON
- **Repository Pattern** para abstracción de datos

### Funcionalidades del UserRepository:
- Registro de nuevos usuarios
- Validación de login
- Gestión de sesión activa
- Prevención de duplicados
- Logout

## Esquema de Colores

```xml
primary: #FF6B35     (Naranja GameZone)
primary_dark: #E85D2E
accent: #4ECDC4      (Verde azulado)
background: #F7F7F7  (Gris claro)
text_primary: #212121
text_secondary: #757575
```

## Compatibilidad

- **Versión mínima de Android**: 7.0 (API 24)
- **Versión objetivo**: Android 14 (API 34)
- **Orientación**: Adaptable (portrait y landscape)

## Notas Importantes

⚠️ **Entorno Replit**: Este proyecto Android no puede ejecutarse directamente en Replit ya que requiere un emulador Android o dispositivo físico. Sin embargo, todo el código está completo y listo para compilar en Android Studio.

## Próximas Mejoras Sugeridas

- Implementar recuperación de contraseña
- Agregar autenticación con redes sociales
- Integrar con backend real para sincronización de datos
- Añadir catálogo completo de juegos con búsqueda y filtros
- Implementar sistema de carrito de compras
- Agregar método de pago
- Notificaciones push para ofertas

## Desarrollado Por

Proyecto desarrollado como parte de la asignatura de Desarrollo Móvil.
Tecnología principal: Kotlin para Android.

---

**Última actualización**: Noviembre 2025
