#! /bin/bash
# process-monitor.sh
process=$1
pid=$(ps x | grep $process | grep -v grep | awk '{print $1}')
echo $pid
