#!/bin/bash
set -euo pipefail

cd "${0%/*}" || exit
cd ..
JAVA_HOME=$JAVA11_HOME mvn clean package

rm -rf ../../../tools/tomcat/webapps/simple-http-test
cp -p ./target/simple-http-test.war ../../../tools/tomcat/webapps/
