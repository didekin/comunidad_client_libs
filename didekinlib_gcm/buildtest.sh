# It must be executed after 'cdlibs' with './didekinlib_gcm/buildtest.sh environment version'
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

./gradlew didekinlib_gcm:clean

git checkout ${ENV}

if [ ${ENV} = "$LOCAL_ENV" ] ; then
    git add .
    git commit -m  "version $VERSION"
fi

if [ ${ENV} = "$PRO_ENV" ] ; then
    git merge "$LOCAL_ENV"  -m "version $VERSION"
fi

if [ ${ENV} = "$LOCAL_ENV" ] ; then
    ./gradlew -Pversionjar=${VERSION_SUFFIX}-${ENV} didekinlib_gcm:build
else
    ./gradlew -Pversionjar=${VERSION_SUFFIX} didekinlib_gcm:build
fi
echo "gradle didekinlib_gcm build exit code = $?"

git push ${GITREMOTE} ${ENV}

#rm didekinlib_gcm/releases/${ENV}/*
mv didekinlib_gcm/build/libs/*.jar didekinlib_gcm/releases/${ENV}/

git checkout local

