# It must be executed after 'cdlibs' with './terminal/buildtest.sh environment version'
# Environments: local, master

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

git checkout $ENV

if [ $ENV = "$LOCAL_ENV" ] ; then
    git add .
    git commit -m  "version $VERSION"
fi

if [ $ENV = "$PRO_ENV" ] ; then
    git merge "$LOCAL_ENV"  -m "version $VERSION"
fi

if [ $ENV = "$LOCAL_ENV" ] ; then

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

git push $GITREMOTE $ENV

rm releases/$ENV/*
mv client_lib/build/libs/*.jar releases/$ENV/
mv client_gcm_lib/build/libs/*.jar releases/$ENV/

git checkout localdev

