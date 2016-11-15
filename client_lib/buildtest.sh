# It must be executed after 'cdlibs' with './client_lib/buildtest.sh environment version'
# Environments: local, master

#!/bin/bash

export FUNCTIONS_FILE=terminal/envfunctions

if ! [ -f ${FUNCTIONS_FILE} ]
    then
        echo "No environment functions file: $FUNCTIONS_FILE" 1>&2; exit 1
    else
        . ${FUNCTIONS_FILE}
fi

[ $# -ne 2 ] && { echo "args count should be 2" 1>&2; exit 1;}

setArgsLibs "$1" "$2"

./gradlew clean

git checkout ${ENV}

if [ ${ENV} = "$LOCAL_ENV" ] ; then
    git add .
    git commit -m  "version $VERSION"
fi

if [ ${ENV} = "$PRO_ENV" ] ; then
    git merge "$LOCAL_ENV"  -m "version $VERSION"
fi

if [ ${ENV} = "$LOCAL_ENV" ] ; then
    ./gradlew -Pversionjar=${VERSION_SUFFIX}-${ENV} client_lib:build
else
    ./gradlew -Pversionjar=${VERSION_SUFFIX} client_lib:build
fi
echo "gradle client_lib build exit code = $?"

git push ${GITREMOTE} ${ENV}

rm releases/${ENV}/*
mv client_lib/build/libs/*.jar client_lib/releases/${ENV}/

git checkout local

