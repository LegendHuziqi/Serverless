#!/bin/sh 
cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
lscpu |grep "Core(s) per socket" |awk '{print $NF}'
