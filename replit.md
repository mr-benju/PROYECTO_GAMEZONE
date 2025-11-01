# PROYECTO_GAMEZONE

## Overview
GameZone es una aplicación móvil Android desarrollada en Kotlin para una tienda de videojuegos online. El proyecto implementa un sistema completo de registro y login optimizado para dispositivos móviles, siguiendo las mejores prácticas de arquitectura MVVM.

## Project Status
- **Type**: Aplicación Móvil Android (Kotlin)
- **Current State**: ✅ Completamente implementado y funcional
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **Original Repository**: https://github.com/mr-benju/PROYECTO_GAMEZONE

## Estructura del Proyecto

### Aplicación Android (`app/`)
- **Activities**: LoginActivity, RegisterActivity, MainActivity
- **ViewModels**: AndroidLoginViewModel, AndroidRegisterViewModel (lifecycle-aware)
- **Models**: User, GameGenre
- **Repository**: UserRepository (gestión de usuarios)
- **Validators**: Sistema de validación centralizado
- **Layouts**: Diseños XML optimizados para móviles

### Versión de Consola (`console/`)
- GameZoneConsole.kt - Versión CLI interactiva para pruebas en Replit
- Comparte la misma lógica de validación y negocio que la app Android

## Características Implementadas

### Sistema de Registro
✅ Validación de nombre completo (solo letras, max 100 caracteres)  
✅ Email con dominio @duoc.cl obligatorio (max 60 caracteres)  
✅ Contraseña segura (min 10 caracteres + mayúscula + minúscula + número + especial)  
✅ Confirmación de contraseña  
✅ Teléfono opcional (8-15 dígitos)  
✅ Selección múltiple de géneros favoritos (Ficción, No Ficción, Misterio, Terror, Suspenso, Historia)

### Sistema de Login
✅ Validación de credenciales  
✅ Mensajes específicos de error (usuario no encontrado vs contraseña incorrecta)  
✅ Interfaz responsive optimizada para móviles

### Arquitectura MVVM
✅ ViewModels extienden androidx.lifecycle.ViewModel  
✅ LiveData para observabilidad reactiva  
✅ Sobrevive a cambios de configuración (rotación)  
✅ Separación clara de responsabilidades  
✅ Código reutilizable entre Android y consola

## Tecnologías Utilizadas
- **Lenguaje**: Kotlin 2.1.20
- **Platform**: Android (API 24-34)
- **Build System**: Gradle
- **UI**: Material Design Components
- **Architecture Components**: ViewModel, LiveData
- **JDK**: Java 17

## Cómo Ejecutar

### En Replit (Versión de Consola)
```bash
bash run.sh
```
El programa mostrará un menú interactivo para registrar usuarios, iniciar sesión y ver usuarios registrados.

### En Android Studio
1. Importar el proyecto en Android Studio
2. Sincronizar Gradle
3. Ejecutar en emulador o dispositivo físico
4. Ver `INSTRUCCIONES_ANDROID_STUDIO.md` para detalles completos

## Validaciones Implementadas

| Campo | Validación |
|-------|-----------|
| Nombre | No vacío, solo letras y espacios, max 100 caracteres |
| Email | @duoc.cl obligatorio, formato válido, max 60 caracteres, único |
| Contraseña | Min 10 caracteres, 1 mayúscula, 1 minúscula, 1 número, 1 especial |
| Confirmación | Debe coincidir exactamente |
| Teléfono | Opcional, 8-15 dígitos |
| Géneros | Al menos 1 seleccionado |

## Archivos de Documentación
- `README.md` - Documentación general del proyecto
- `INSTRUCCIONES_ANDROID_STUDIO.md` - Guía completa para importar y ejecutar en Android Studio
- `ARQUITECTURA.md` - Documentación detallada de la arquitectura MVVM dual

## Workflows Configurados
- **GameZone Console**: Ejecuta la versión de consola interactiva (`bash run.sh`)

## User Preferences
- **Lenguaje**: Español (interfaz y mensajes en español)
- **Framework**: Android nativo con Kotlin
- **Validaciones**: Estrictas según requisitos académicos
- **Dominio email**: Exclusivamente @duoc.cl

## Recent Changes
- **Nov 01, 2025**: Proyecto completo implementado
  - Sistema de registro y login con validaciones completas
  - Arquitectura MVVM con ViewModels lifecycle-aware
  - Versión de consola funcional para Replit
  - Layouts XML optimizados para móviles
  - Documentación completa en español
  - Workflow configurado y testeado
