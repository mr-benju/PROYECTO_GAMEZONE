# Arquitectura del Proyecto GameZone

## 📐 Patrón de Diseño: MVVM (Model-View-ViewModel)

Este proyecto implementa el patrón MVVM con una arquitectura dual que permite reutilizar la lógica de negocio tanto en Android como en la versión de consola.

## 🏗️ Capas de la Arquitectura

### 1. **Model (Modelo)**
Ubicación: `app/src/main/java/com/gamezone/models/`

- **User.kt**: Representa un usuario del sistema
- **GameGenre.kt**: Enum de géneros de videojuegos disponibles

### 2. **View (Vista)**
Ubicación: `app/src/main/java/com/gamezone/activities/` y `app/src/main/res/layout/`

**Activities:**
- **LoginActivity.kt**: Pantalla de inicio de sesión
- **RegisterActivity.kt**: Pantalla de registro
- **MainActivity.kt**: Pantalla principal después del login

**Layouts XML:**
- **activity_login.xml**: Diseño optimizado para móvil del login
- **activity_register.xml**: Formulario de registro responsive
- **activity_main.xml**: Pantalla de bienvenida

### 3. **ViewModel (Modelo de Vista)**

#### ViewModels de Android (Lifecycle-aware)
Ubicación: `app/src/main/java/com/gamezone/viewmodels/`

- **AndroidRegisterViewModel.kt**
  - Extiende `androidx.lifecycle.ViewModel`
  - Expone estado via `LiveData`
  - Sobrevive a cambios de configuración (rotación de pantalla)
  - Usa el patrón delegate para reutilizar lógica

- **AndroidLoginViewModel.kt**
  - Extiende `androidx.lifecycle.ViewModel`
  - Maneja el estado del login reactivamente
  - Observa cambios y notifica a la vista

#### ViewModels de Lógica Pura (Reutilizables)
- **RegisterViewModel.kt**
  - Clase Kotlin pura sin dependencias de Android
  - Contiene toda la lógica de validación y registro
  - Usado como delegate por AndroidRegisterViewModel
  - Reutilizado por la versión de consola

- **LoginViewModel.kt**
  - Clase Kotlin pura para lógica de login
  - Validaciones y autenticación
  - Compartido entre Android y consola

### 4. **Repository (Repositorio)**
Ubicación: `app/src/main/java/com/gamezone/data/`

- **UserRepository.kt**
  - Patrón Singleton (object)
  - Gestión centralizada de usuarios
  - Almacenamiento in-memory (para demostración)
  - Define sealed classes para resultados:
    - `RegistrationResult`
    - `LoginResult`

### 5. **Utils (Utilidades)**
Ubicación: `app/src/main/java/com/gamezone/utils/`

- **Validator.kt**
  - Validaciones centralizadas
  - Lógica de negocio pura
  - Sin dependencias de Android
  - Retorna `ValidationResult` sealed class

## 🔄 Flujo de Datos

### Flujo de Registro (Android)

```
Usuario ingresa datos
        ↓
RegisterActivity (View)
        ↓
TextWatcher detecta cambios
        ↓
AndroidRegisterViewModel
        ↓
Delega a RegisterViewModel
        ↓
Validator valida datos
        ↓
ValidationResult
        ↓
LiveData emite error/success
        ↓
RegisterActivity observa
        ↓
UI actualiza (muestra error o registra)
        ↓
UserRepository almacena
```

### Flujo de Login (Android)

```
Usuario ingresa credenciales
        ↓
LoginActivity (View)
        ↓
AndroidLoginViewModel
        ↓
Delega a LoginViewModel
        ↓
Validator valida formato
        ↓
UserRepository verifica credenciales
        ↓
LoginResult (Success/Error)
        ↓
LiveData emite resultado
        ↓
LoginActivity observa
        ↓
Navega a MainActivity o muestra error
```

## 🎯 Ventajas de esta Arquitectura

### Separación de Responsabilidades
- **View**: Solo renderiza y captura input
- **ViewModel**: Lógica de presentación
- **Model**: Estructuras de datos
- **Repository**: Gestión de datos
- **Validator**: Reglas de negocio

### Testabilidad
- ViewModels puros sin Android son fáciles de testear
- Validaciones aisladas
- Repository mockeable

### Reutilización de Código
- La misma lógica funciona en:
  - App Android nativa
  - Versión de consola (Replit)
  - Futuros clientes (iOS con KMP, Web, Desktop)

### Mantenibilidad
- Cambios en validaciones solo en `Validator.kt`
- Cambios en UI no afectan lógica de negocio
- Fácil agregar nuevas features

### Ciclo de Vida Android
- AndroidViewModels sobreviven a rotaciones
- LiveData maneja ciclo de vida automáticamente
- No hay leaks de memoria

## 🔀 Arquitectura Dual: Android + Consola

### Componentes Compartidos
```
Validator.kt ──┬─→ Android App
               │
User.kt ───────┤
               │
GameGenre.kt ──┤
               │
UserRepository─┤
               │
RegisterViewModel.kt ─┼─→ Consola App
LoginViewModel.kt ────┘
```

### Componentes Específicos de Android
```
AndroidRegisterViewModel ←─ ViewModelProvider
AndroidLoginViewModel    ←─ ViewModelProvider
        ↓
Activities (LoginActivity, RegisterActivity)
        ↓
    Layouts XML
```

### Componentes de Consola
```
GameZoneConsole.kt
        ↓
Usa directamente:
  - RegisterViewModel
  - LoginViewModel
  - Validator
  - UserRepository
```

## 📦 Dependencias Clave

### Android
- `androidx.lifecycle:lifecycle-viewmodel-ktx` - ViewModels
- `androidx.lifecycle:lifecycle-livedata-ktx` - LiveData
- `androidx.appcompat:appcompat` - Activities
- `com.google.android.material:material` - UI Components

### Kotlin
- Kotlin Coroutines (preparado para uso futuro)
- Sealed Classes para tipo-seguridad
- Data Classes para modelos
- Object para Singletons

## 🚀 Mejores Prácticas Implementadas

### ✅ Patrón MVVM Completo
- ViewModels extienden `ViewModel`
- Obtención via `by viewModels()`
- LiveData para observabilidad

### ✅ Validación Reactiva
- Validación en tiempo real
- Mensajes de error claros
- No bloquea UI

### ✅ Sealed Classes
- `ValidationResult` para validaciones
- `LoginResult` y `RegistrationResult` para operaciones
- Type-safe error handling

### ✅ Lifecycle-aware
- ViewModels sobreviven rotaciones
- LiveData respeta ciclo de vida
- No memory leaks

### ✅ Single Responsibility
- Cada clase tiene una responsabilidad clara
- Validator solo valida
- Repository solo gestiona datos
- ViewModel solo coordina

### ✅ DRY (Don't Repeat Yourself)
- Validaciones centralizadas
- Lógica compartida entre plataformas
- Reutilización de componentes

## 🔮 Extensiones Futuras

### Base de Datos Persistente
```kotlin
// Reemplazar UserRepository in-memory con Room
@Database(entities = [User::class], version = 1)
abstract class GameZoneDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

### Autenticación Real
```kotlin
// Integrar Firebase Auth o backend propio
class AuthRepository(private val firebaseAuth: FirebaseAuth) {
    suspend fun login(email: String, password: String): LoginResult
}
```

### Kotlin Multiplatform
La arquitectura actual ya está preparada para KMP:
- ViewModels puros son multiplataforma
- Solo se necesitan wrappers específicos por plataforma

### StateFlow (Coroutines)
```kotlin
// Migrar de LiveData a StateFlow para mejor soporte de coroutines
val emailError: StateFlow<String?> = _emailError.asStateFlow()
```

## 📚 Referencias

- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [MVVM Pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
- [Kotlin Best Practices](https://kotlinlang.org/docs/coding-conventions.html)
- [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData Overview](https://developer.android.com/topic/libraries/architecture/livedata)

---

**Nota**: Esta arquitectura balancea las mejores prácticas de Android con la necesidad de demostración académica y reutilización de código multiplataforma.
