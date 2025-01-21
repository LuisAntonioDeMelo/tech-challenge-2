#!/bin/bash

if ! [ $(id -u) = 0 ]; then
   echo "You need to run this script with root!"
   exit 1
fi

if [ "$(uname)" == "Darwin" ]; then
    ip=$(ipconfig getifaddr en0)
    i=1
    while [ -z "$ip" ] && [ $i -eq 10 ]; do
        ip=$(ipconfig getifaddr en$i)
        i=$((i+1))
    done
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    ip=$(ip a | grep -oP 'inet \K[\d.]+' | grep -v 0.1)
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    echo "This script is not supported for windows"
    exit 1
fi

if cat /etc/hosts | grep host.docker.internal; then
    if [ "$(uname)" == "Darwin" ]; then
        ip=$(ifconfig | awk '/inet / && !/127.0.0.1/ {print $2}' | head -n 1)
    else
        ip=$(ip a | awk '/inet / && !/127.0.0.1/ {sub(/\/.*/, "", $2); print $2}' | head -n 1)
    fi
    escaped_ip=$(printf '%s\n' "$ip" | sed -e 's/[\/&]/\\&/g')
    sed -iE "s/.*host\.docker\.internal/$escaped_ip host\.docker\.internal/g" /etc/hosts
    echo "Host $ip host.docker.internal changed in /etc/hosts file."
else 
    echo -en "\n$ip host.docker.internal" >> /etc/hosts
    echo "Host $ip host.docker.internal created in /etc/hosts file."
fi
