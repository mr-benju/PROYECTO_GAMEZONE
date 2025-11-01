#!/bin/bash

# Crear directorio de build si no existe
mkdir -p build/classes build/console

# Compilar
bash compile.sh

# Ejecutar
echo ""
echo "🚀 Ejecutando GameZone..."
echo ""
kotlin -classpath build/classes:build/console GameZoneConsoleKt
