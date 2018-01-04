#!/usr/bin/env bash
cp target/celerio-game-editor-admin.jar docker/.
cd docker
docker-compose build db admin
docker-compose up
