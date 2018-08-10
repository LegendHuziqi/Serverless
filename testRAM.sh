#!/bin/sh 
echo "Legend2011" | sudo -S  dmidecode -t memory |grep -A16 "Memory Device$" |grep "Size:"
