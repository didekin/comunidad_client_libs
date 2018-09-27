# It must be executed after 'cdlibs' with './terminal/build_libs.sh environment version'
# Environments: local, master

#!/bin/bash

[ $# -ne 2 ] && { echo "args count should be 2" 1>&2; exit 1;}

./gradlew clean

# Hay controlar si cada una de estas llamadas tiene éxito.
source ./didekinlib_utilities/buildtest.sh $1 $2
source ./didekinlib_model/buildtest.sh $1 $2

echo "================== FINAL!!!: build_libs finish, exit code = $? =============="

