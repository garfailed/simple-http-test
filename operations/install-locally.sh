#!/bin/bash
set -euo pipefail

cd "${0%/*}" || exit
cd ..
JAVA_HOME=$JAVA17_HOME mvn clean package

rm -rf ~/dev/tools/tomcat/webapps/simple-http-test
cp -p ./target/simple-http-test.war ~/dev/tools/tomcat/webapps/
