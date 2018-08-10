#!/bin/sh 
cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
