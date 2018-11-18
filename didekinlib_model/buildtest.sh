# It must be executed after 'cdlibs' with './didekinlib_model/buildtest.sh environment version'
# Environments: local, master

#!/bin/bash

export FUNCTIONS_FILE=../terminal/envfunctions

if ! [ -f ${FUNCTIONS_FILE} ]
    then
        echo "No environment functions file: $FUNCTIONS_FILE" 1>&2; exit 1
    else
        . ${FUNCTIONS_FILE}
fi

[ $# -ne 2 ] && { echo "args count should be 2" 1>&2; exit 1;}

setArgsLibs "$1" "$2"

.././gradlew clean

git checkout ${ENV}

if [ ${ENV} = "$LOCAL_ENV" ] ; then
    git add .
    git commit -m  "version $VERSION"
    .././gradlew -Pversionjar=${VERSION_SUFFIX}-${ENV} build
fi

if [ ${ENV} = "$PRO_ENV" ] ; then
    git merge "$LOCAL_ENV"  -m "version $VERSION"
    .././gradlew -Pversionjar=${VERSION_SUFFIX} build
fi

echo "================= ¡¡¡ gradle didekinlib_model build exit code = $?"

/usr/bin/ssh-add -K
git push ${GITREMOTE} ${ENV}

rm releases/${ENV}/*
mv build/libs/*.jar releases/${ENV}/

git checkout local

