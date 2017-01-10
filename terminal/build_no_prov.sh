# It must be executed after 'cdlibs' with './terminal/build_no_prov.sh environment version'
# Environments: local, master

#!/bin/bash

[ $# -ne 2 ] && { echo "args count should be 2" 1>&2; exit 1;}

source ./client_lib/buildtest.sh $1 $2

./gradlew clean