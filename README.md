# PROYECTO_GAMEZONE

Sistema de registro y login para GameZone - Tienda de videojuegos online

## 📱 Descripción

GameZone es una aplicación móvil Android desarrollada en Kotlin que ofrece un sistema completo de registro y login optimizado para dispositivos móviles. La aplicación está diseñada para una tienda de videojuegos que necesita mejorar su tasa de conversión de visitantes a usuarios registrados.

## 🎯 Objetivos del Proyecto

- Sistema de registro rápido y sencillo
- Optimización completa para dispositivos móviles
- Validaciones robustas en tiempo real
- Mensajes de error claros y útiles
- Arquitectura limpia y mantenible (MVVM)

## ✨ Características Principales

### Sistema de Registro
- ✅ Validación de nombre completo (solo letras y espacios, max 100 caracteres)
- ✅ Email exclusivo de dominio @duoc.cl (max 60 caracteres)
- ✅ Contraseña segura (min 10 caracteres + mayúscula + minúscula + número + carácter especial)
- ✅ Confirmación de contraseña
- ✅ Teléfono opcional (8-15 dígitos)
- ✅ Selección múltiple de géneros favoritos:
  - Ficción
  - No Ficción
  - Misterio
  - Terror
  - Suspenso
  - Historia

### Sistema de Login
- ✅ Validación de credenciales
- ✅ Mensajes específicos de error (usuario no encontrado vs contraseña incorrecta)
- ✅ Interfaz responsive para diferentes tamaños de pantalla

## 🚀 Cómo Ejecutar en Replit

### Versión de Consola (Disponible en Replit)

Esta versión te permite probar toda la lógica de validación de forma interactiva:

```bash
bash run.sh
```

El programa te mostrará un menú con opciones para:
1. Registrar nuevo usuario
2. Iniciar sesión
3. Ver usuarios registrados
4. Salir

**Ejemplo de uso:**
```
═══ MENÚ PRINCIPAL ═══
1. Registrar nuevo usuario
2. Iniciar sesión
3. Ver usuarios registrados
4. Salir

Seleccione una opción: 1

📝 Nombre Completo: Juan Pérez
📧 Correo Electrónico: juan.perez@duoc.cl
🔐 Contraseña: Password123@
🔐 Confirme su contraseña: Password123@
📱 Teléfono: 912345678
🎮 Seleccione géneros (1,3,4): 1,3,4

✅ ¡Registro exitoso!
```

## 📱 Cómo Ejecutar en Android Studio

Para ejecutar la aplicación Android completa, consulta el archivo [INSTRUCCIONES_ANDROID_STUDIO.md](INSTRUCCIONES_ANDROID_STUDIO.md) que incluye:
- Guía paso a paso de importación
- Configuración del emulador
- Casos de prueba
- Solución de problemas

## 📁 Estructura del Proyecto

```
PROYECTO_GAMEZONE/
├── app/                                # Aplicación Android
│   ├── src/main/
│   │   ├── java/com/gamezone/
│   │   │   ├── activities/            # Pantallas (Login, Registro, Principal)
│   │   │   ├── viewmodels/            # Lógica de presentación (MVVM)
│   │   │   ├── models/                # Modelos de datos (User, GameGenre)
│   │   │   ├── data/                  # Repository pattern
│   │   │   └── utils/                 # Validaciones centralizadas
│   │   ├── res/
│   │   │   ├── layout/                # Diseños XML optimizados para móvil
│   │   │   └── values/                # Recursos (strings, colors)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── console/                            # Versión de consola (Replit)
│   └── src/GameZoneConsole.kt
├── compile.sh                          # Script de compilación
├── run.sh                              # Script de ejecución
└── INSTRUCCIONES_ANDROID_STUDIO.md    # Guía completa para Android Studio
```

## 🔐 Validaciones Implementadas

Todas las validaciones están centralizadas en `Validator.kt`:

| Campo | Reglas de Validación |
|-------|---------------------|
| Nombre Completo | No vacío, solo letras y espacios, max 100 caracteres |
| Email | Formato válido, dominio @duoc.cl obligatorio, max 60 caracteres, único |
| Contraseña | Min 10 caracteres, 1 mayúscula, 1 minúscula, 1 número, 1 especial (@#$%&*!?_-) |
| Confirmación | Debe coincidir exactamente con la contraseña |
| Teléfono | Opcional, 8-15 dígitos numéricos |
| Géneros | Al menos 1 género seleccionado |

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** con:

- **Models**: Clases de datos (`User`, `GameGenre`)
- **Views**: Activities con layouts XML
- **ViewModels**: Lógica de presentación y validación
- **Repository**: Gestión de datos (UserRepository)
- **Utils**: Validadores y utilidades compartidas

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin 2.1.20
- **Plataforma**: Android (API 24-34)
- **Arquitectura**: MVVM
- **UI**: Material Design Components
- **Build System**: Gradle
- **JDK**: Java 17

## ✅ Casos de Prueba

### Registro Exitoso
```
Nombre: María González
Email: maria.gonzalez@duoc.cl
Contraseña: Secure@Pass123
Confirmar: Secure@Pass123
Teléfono: 987654321
Géneros: Ficción, Misterio
```

### Casos de Error

**Email inválido:**
```
Email: estudiante@gmail.com
❌ El correo debe ser de dominio @duoc.cl
```

**Contraseña débil:**
```
Contraseña: abc123
❌ La contraseña debe tener al menos 10 caracteres
❌ Debe incluir al menos una letra mayúscula
❌ Debe incluir al menos un carácter especial
```

**Contraseñas no coinciden:**
```
Contraseña: Password123@
Confirmar: Password456@
❌ Las contraseñas no coinciden
```

## 📚 Recursos Educativos

Este proyecto es parte del curso de Desarrollo Móvil y demuestra:
- Validación de formularios en Android
- Arquitectura MVVM
- Material Design
- Kotlin best practices
- Gestión de estado
- Separación de responsabilidades

## 📝 Notas Importantes

⚠️ **Seguridad**: Esta es una aplicación de demostración académica. En producción deberías:
- Implementar hash de contraseñas (BCrypt, Argon2)
- Usar autenticación basada en tokens (JWT)
- Almacenar datos de forma segura (Room Database encriptada)
- Implementar comunicación HTTPS

## 🎓 Proyecto Académico

**Asignatura**: Desarrollo Móvil  
**Institución**: DUOC UC  
**Enfoque**: Optimización de UX en dispositivos móviles

---

## 🚀 Quick Start

**En Replit:**
```bash
bash run.sh
```

**En Android Studio:**
1. Importa el proyecto
2. Sincroniza Gradle
3. Ejecuta en emulador o dispositivo físico
4. Consulta INSTRUCCIONES_ANDROID_STUDIO.md para más detalles
