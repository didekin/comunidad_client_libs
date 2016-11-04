# It must be executed after 'cdlibs' with './terminal/buildtest.sh environment version'
# Environments: local, pre, pro

#!/bin/bash

export FUNCTIONS_FILE=terminal/envfunctions

if ! [ -f $FUNCTIONS_FILE ]
    then
        echo "No environment functions file: $FUNCTIONS_FILE" 1>&2; exit 1
    else
        . $FUNCTIONS_FILE
fi

setArgsLibs "$1" "$2"

./gradlew clean

if [ $ENV = "$LOCAL_ENV" ] || [ $ENV = "$PRE_ENV" ] ; then

    ./gradlew -Pversionjar=$VERSION_SUFFIX-$ENV client_lib:build
    echo "gradle clientlib_build exit code = $?"

    ./gradlew -Pversionjar=$VERSION_SUFFIX-$ENV client_gcm_lib:build
    echo "gradle clientgcmlib_build exit code = $?"

else
    ./gradlew -Pversionjar=$VERSION_SUFFIX client_lib:build
    echo "gradle clientlib_build exit code = $?"
    ./gradlew -Pversionjar=$VERSION_SUFFIX client_gcm_lib:build
    echo "gradle clientgcmlib_build exit code = $?"
fi

rm releases/$ENV/*
mv client_lib/build/libs/*.jar releases/$ENV/
mv client_gcm_lib/build/libs/*.jar releases/$ENV/

