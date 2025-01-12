#!/bin/bash

# PID 文件的路径
PID_FILE="/Users/chaneychan/IdeaProjects/graalPy/conf/monit/graalpy.pid"

# 检查 PID 文件是否存在
if [ -f "$PID_FILE" ]; then
    # 读取 PID 文件中的进程 ID
    PID=$(cat $PID_FILE)
    echo $PID
    # 使用 kill 命令终止进程
    kill $PID

    # 等待进程终止
    wait $PID 2>/dev/null

    # 删除 PID 文件
    rm -f $PID_FILE

    echo "Process stopped and PID file removed."
else
    echo "PID file does not exist. Process may not be running."
fi