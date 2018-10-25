#!/usr/bin/env bash

exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -jar \
/opt/book-service.jar --spring.profiles.active=$SPRING_PROFILE