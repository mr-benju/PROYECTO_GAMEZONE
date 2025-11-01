# GameZone - Instrucciones para Android Studio

## 📱 Descripción del Proyecto

GameZone es una aplicación móvil Android desarrollada en Kotlin para una tienda de videojuegos. Incluye un sistema completo de registro y login optimizado para dispositivos móviles.

## 🎯 Características Implementadas

### Sistema de Registro
- ✅ Validación de nombre completo (solo letras y espacios, max 100 caracteres)
- ✅ Validación de email (@duoc.cl obligatorio, max 60 caracteres)
- ✅ Validación de contraseña (min 10 caracteres, mayúsculas, minúsculas, números, caracteres especiales)
- ✅ Confirmación de contraseña
- ✅ Teléfono opcional (8-15 dígitos)
- ✅ Selección de géneros favoritos (Ficción, No Ficción, Misterio, Terror, Suspenso, Historia)

### Sistema de Login
- ✅ Validación de credenciales
- ✅ Mensajes de error específicos (usuario no encontrado vs contraseña incorrecta)
- ✅ Interfaz optimizada para móviles

### Arquitectura
- ✅ Patrón MVVM (Model-View-ViewModel)
- ✅ Separación de responsabilidades
- ✅ Validación en tiempo real
- ✅ Repository pattern para gestión de datos

## 📁 Estructura del Proyecto

```
PROYECTO_GAMEZONE/
├── app/
│   ├── build.gradle                    # Configuración de dependencias Android
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml     # Configuración de la aplicación
│           ├── java/com/gamezone/
│           │   ├── activities/         # Pantallas de la app
│           │   │   ├── LoginActivity.kt
│           │   │   ├── RegisterActivity.kt
│           │   │   └── MainActivity.kt
│           │   ├── viewmodels/         # Lógica de presentación
│           │   │   ├── LoginViewModel.kt
│           │   │   └── RegisterViewModel.kt
│           │   ├── models/             # Modelos de datos
│           │   │   ├── User.kt
│           │   │   └── GameGenre.kt
│           │   ├── data/               # Capa de datos
│           │   │   └── UserRepository.kt
│           │   └── utils/              # Utilidades
│           │       └── Validator.kt    # Validaciones
│           └── res/
│               ├── layout/             # Diseños XML
│               │   ├── activity_login.xml
│               │   ├── activity_register.xml
│               │   └── activity_main.xml
│               └── values/             # Recursos
│                   ├── strings.xml
│                   └── colors.xml
├── console/                            # Versión de consola (para Replit)
│   └── src/GameZoneConsole.kt
├── build.gradle                        # Configuración del proyecto
├── settings.gradle                     # Configuración de módulos
└── gradle.properties                   # Propiedades de Gradle
```

## 🚀 Cómo Importar a Android Studio

### Paso 1: Descargar el Proyecto
1. Descarga todos los archivos de este repositorio
2. Descomprime el archivo ZIP en tu computadora

### Paso 2: Abrir en Android Studio
1. Abre Android Studio
2. Selecciona **File > Open**
3. Navega a la carpeta del proyecto `PROYECTO_GAMEZONE`
4. Haz clic en **OK**

### Paso 3: Sincronizar Gradle
1. Android Studio detectará automáticamente el proyecto Gradle
2. Haz clic en **Sync Now** cuando aparezca el mensaje
3. Espera a que se descarguen todas las dependencias (puede tomar varios minutos la primera vez)

### Paso 4: Configurar Emulador o Dispositivo
**Opción A: Usar Emulador**
1. Ve a **Tools > Device Manager**
2. Haz clic en **Create Device**
3. Selecciona un dispositivo (recomendado: Pixel 6)
4. Descarga una imagen del sistema (recomendado: Android 14 / API 34)
5. Finaliza la configuración

**Opción B: Usar Dispositivo Físico**
1. Habilita **Opciones de Desarrollo** en tu dispositivo Android
2. Activa **Depuración USB**
3. Conecta el dispositivo a tu computadora
4. Autoriza la depuración cuando se solicite

### Paso 5: Ejecutar la Aplicación
1. Asegúrate de que el emulador o dispositivo esté seleccionado
2. Haz clic en el botón **Run** (▶️) o presiona **Shift + F10**
3. La aplicación se instalará y ejecutará automáticamente

## 🧪 Probando la Aplicación

### Flujo de Registro
1. Al iniciar, verás la pantalla de Login
2. Haz clic en "¿No tienes cuenta? Regístrate aquí"
3. Completa el formulario:
   - **Nombre**: Juan Pérez
   - **Email**: juan.perez@duoc.cl
   - **Contraseña**: Password123@
   - **Confirmar Contraseña**: Password123@
   - **Teléfono**: 912345678 (opcional)
   - **Géneros**: Selecciona uno o más
4. Haz clic en "Registrarse"

### Flujo de Login
1. En la pantalla de Login, ingresa:
   - **Email**: juan.perez@duoc.cl
   - **Contraseña**: Password123@
2. Haz clic en "Iniciar Sesión"
3. Verás la pantalla de bienvenida

### Casos de Prueba de Validación

**Nombre Completo:**
- ✅ Válido: "Juan Pérez"
- ❌ Inválido: "Juan123" (contiene números)
- ❌ Inválido: "" (vacío)

**Email:**
- ✅ Válido: "estudiante@duoc.cl"
- ❌ Inválido: "estudiante@gmail.com" (no es @duoc.cl)
- ❌ Inválido: "estudianteduoc.cl" (formato incorrecto)

**Contraseña:**
- ✅ Válido: "Password123@"
- ❌ Inválido: "password" (menos de 10 caracteres, sin mayúsculas, sin números, sin especiales)
- ❌ Inválido: "password123" (sin mayúsculas, sin caracteres especiales)

## 🔧 Versión de Consola (Para Replit)

El proyecto incluye una versión de consola interactiva que puede ejecutarse en Replit:

```bash
bash run.sh
```

Esta versión incluye las mismas validaciones y lógica que la app Android, permitiendo probar la funcionalidad sin necesidad de Android Studio.

## 📋 Requisitos Técnicos

- **Android Studio**: Iguana | 2023.2.1 o superior
- **Kotlin**: 2.1.20
- **Gradle**: 8.2.0
- **SDK Mínimo**: Android 7.0 (API 24)
- **SDK Objetivo**: Android 14 (API 34)
- **JDK**: Java 17

## 🎨 Diseño de Interfaz

La aplicación utiliza Material Design Components con:
- Color primario: #1A237E (azul índigo oscuro)
- Diseño optimizado para pantallas móviles
- Layouts responsivos con ScrollView
- TextInputLayout para mejor UX en formularios
- Validación en tiempo real con mensajes de error claros

## 📝 Validaciones Implementadas

Todas las validaciones están centralizadas en `Validator.kt`:

1. **validateFullName**: Solo letras y espacios, max 100 caracteres
2. **validateEmail**: Formato email + @duoc.cl obligatorio, max 60 caracteres
3. **validatePassword**: Min 10 caracteres, mayúscula, minúscula, número, carácter especial
4. **validatePasswordConfirmation**: Debe coincidir con la contraseña
5. **validatePhone**: 8-15 dígitos (opcional)
6. **validateGenreSelection**: Al menos un género seleccionado

## 🔒 Seguridad

⚠️ **Nota**: Esta es una aplicación de demostración académica. En producción, deberías:
- NO almacenar contraseñas en texto plano
- Usar hash de contraseñas (BCrypt, Argon2)
- Implementar autenticación con tokens (JWT)
- Usar HTTPS para todas las comunicaciones
- Implementar almacenamiento seguro (Room Database con encriptación)

## 🐛 Solución de Problemas

### Error: "SDK not found"
- Descarga el SDK desde Android Studio: **Tools > SDK Manager**

### Error: "Sync failed"
- Verifica tu conexión a Internet
- Limpia el cache: **File > Invalidate Caches / Restart**

### Error: "Emulator not starting"
- Verifica que la virtualización esté habilitada en BIOS
- Asegúrate de tener al menos 8GB de RAM disponible

## 📞 Soporte

Para dudas o problemas con el proyecto, contacta al instructor del curso.

---

**Desarrollado para**: Asignatura de Desarrollo Móvil  
**Institución**: DUOC UC  
**Lenguaje**: Kotlin  
**Plataforma**: Android
