#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "Compiling shared module..."
cd "$SCRIPT_DIR"
./mvnw -pl shared clean install

echo "Starting server and client..."

osascript \
  -e 'tell application "Terminal"' \
  -e "  do script \"cd '$SCRIPT_DIR' && ./mvnw -pl server clean spring-boot:run\"" \
  -e '  activate' \
  -e 'end tell'

osascript \
  -e 'tell application "Terminal"' \
  -e "  do script \"cd '$SCRIPT_DIR' && ./mvnw -pl client clean javafx:run\"" \
  -e 'end tell'
