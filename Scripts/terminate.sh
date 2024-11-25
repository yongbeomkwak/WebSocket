#!/bin/bash

# 특정 패턴을 가진 프로세스의 PID 가져오기
PATTERN="0.0.1-SNAPSHOT.jar"

# 패턴에 해당하는 프로세스 PID 검색
SPRING_PID=$(pgrep -f "${PATTERN}")

# PID 확인 및 종료 처리
if [ -z "${SPRING_PID}" ]; then
    echo "No process found matching pattern: ${PATTERN}"
else
    echo "Found PID: ${SPRING_PID}"
    echo "Stopping process..."
    kill -9 ${SPRING_PID}
    echo "Process stopped."
fi