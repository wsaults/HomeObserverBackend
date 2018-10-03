#!/bin/sh

set -e -x

cd source-code
  ./mvnw clean package -U
cd ..

cp source-code/target/Home-Observer-Backend-0.0.1-SNAPSHOT.jar build-output/.