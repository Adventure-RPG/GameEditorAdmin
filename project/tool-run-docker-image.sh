#!/usr/bin/env bash
./tool-rm-gen-and-package.sh
cp target/celerio-game-editor-admin.jar docker/.
cd docker
docker build -t celerio-game-editor-admin .
docker run -p 8080:8080 celerio-game-editor-admin