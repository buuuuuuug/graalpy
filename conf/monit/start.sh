#!/bin/bash

# 1. 生成一个随机字符串
PID_FILE="/Users/chaneychan/IdeaProjects/graalPy/conf/monit/graalpy.pid"
random_string=$(date +%s | md5sum | head -c 8)
echo "Random string: $random_string"
if [ -z "$random_string" ]; then
    echo "Failed to generate random string."
    exit 1
fi

# 2. 将脚本压缩包 script.zip 解压到随机字符串相对的目录
cd /Users/chaneychan/IdeaProjects/graalPy/conf/monit
unzip -o script.zip -d /home/chaneychan/dist/"$random_string"
if [ $? -ne 0 ]; then
    echo "Failed to unzip script.zip."
    exit 1
fi

# 3. 将随机字符串作为参数传递入程序内
./graalpy -Xss8M -Dpython.InputFilePath="$random_string" &

# 获取进程 ID 并写入 PID 文件
echo $! > $PID_FILE

# 检查是否成功生成 PID 文件
if [ -f "$PID_FILE" ]; then
    echo "PID file created at $PID_FILE"
else
    echo "Failed to create PID file"
fi