#!/bin/bash

echo "🔨 Compilando GameZone..."

# Compilar clases de utilidades, modelos y datos
kotlinc -d build/classes \
  app/src/main/java/com/gamezone/utils/Validator.kt \
  app/src/main/java/com/gamezone/models/GameGenre.kt \
  app/src/main/java/com/gamezone/models/User.kt \
  app/src/main/java/com/gamezone/data/UserRepository.kt \
  app/src/main/java/com/gamezone/viewmodels/RegisterViewModel.kt \
  app/src/main/java/com/gamezone/viewmodels/LoginViewModel.kt

# Compilar la versión de consola
kotlinc -classpath build/classes -d build/console console/src/GameZoneConsole.kt

echo "✅ Compilación completada"
