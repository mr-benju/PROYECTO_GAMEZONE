#!/bin/sh

GRADLE_VERSION=8.0
GRADLE_HOME="$HOME/.gradle/wrapper/dists/gradle-${GRADLE_VERSION}-bin"

if [ ! -d "$GRADLE_HOME" ]; then
    echo "Downloading Gradle ${GRADLE_VERSION}..."
    mkdir -p "$GRADLE_HOME"
    curl -L "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o /tmp/gradle.zip
    cd "$HOME/.gradle/wrapper/dists" && unzip -q /tmp/gradle.zip
    rm /tmp/gradle.zip
fi

GRADLE_BIN=$(find "$HOME/.gradle/wrapper/dists" -name "gradle-${GRADLE_VERSION}" -type d | head -1)
exec "$GRADLE_BIN/bin/gradle" "$@"
