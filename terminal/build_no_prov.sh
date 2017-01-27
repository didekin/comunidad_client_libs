# It must be executed after 'cdlibs' with './terminal/build_no_prov.sh environment version'
# Environments: local, master

#!/bin/bash

[ $# -ne 2 ] && { echo "args count should be 2" 1>&2; exit 1;}

./gradlew clean

source ./didekinlib_model/buildtest.sh $1 $2
source ./didekinlib_http/buildtest.sh $1 $2
source ./didekinlib_gcm/buildtest.sh $1 $2

echo "==== FINAL!!!: build_no_prov finish, exit code = $? ======"

