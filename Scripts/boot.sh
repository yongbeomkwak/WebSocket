#!/bin/bash

# 특정 패턴을 가진 프로세스의 PID 가져오기
PATTERN="0.0.1-SNAPSHOT.jar"

# 패턴에 해당하는 프로세스 PID 검색
SPRING_PID=$(pgrep -f "${PATTERN}")
BUILD_PATH="$(pwd)/build/libs/websock-0.0.1-SNAPSHOT.jar"

nohup java -jar $BUILD_PATH 1>log.out 2>err.out &
