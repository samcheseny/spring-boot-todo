#!/bin/bash

if [ "${1}" = "" ];
then
    echo "Please provide a ticket/branch name"
    exit
fi

post=0
buildFile="../../../build.gradle"
projectVersion=$(fgrep "version = " $buildFile | head -1 | egrep -o "[0-9]+\.[0-9]+\.[0-9]+")
currentTime=$(date -u "+%Y%m%d%H%M%S")
touch V"${projectVersion}"_${post}_"${currentTime}"__"${1}".sql
