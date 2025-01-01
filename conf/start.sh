# 1. 生成一个随机字符串
random_string=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 8 | head -n 1)
echo "Random string: $random_string"
if [ -z "$random_string" ]; then
    echo "Failed to generate random string."
    exit 1
fi

# 2. 将脚本压缩包 script.zip 解压到随机字符串相对的目录
unzip -o script.zip -d /home/chaneychan/dist/"$random_string"
if [ $? -ne 0 ]; then
    echo "Failed to unzip script.zip."
    exit 1
fi

# 3. 将随机字符串作为参数传递入程序内
./graalpy -Xss8M -Dpython.InputFilePath=/home/chaneychan/dist/"$random_string"
if [ $? -ne 0 ]; then
    echo "Failed to start graalpy."
    exit 1
fi