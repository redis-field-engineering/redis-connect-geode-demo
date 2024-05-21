#!/bin/sh

echo starting locator
gfsh start locator --name=locator --hostname-for-clients=localhost --jmx-manager-hostname-for-clients=localhost
echo starting server
gfsh start server --name=server --hostname-for-clients=localhost --locators=localhost[10334] --server-port=40404
gfsh -e "connect --locator=localhost[10334]" -e "create region --name=trades --type=PARTITION"

echo done with setup
while true; do
  sleep 10
done
