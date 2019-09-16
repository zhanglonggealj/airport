#/bin/bash

type=$1

echo "正在执行脚本部署程序"

GIT=$(git pull)

if [ "$GIT" == "Already up-to-date." ]; then
        echo "没有变更代码"
        exit 1
fi

echo 开始编译文件

mvn clean package -Dmaven.test.skip=true

echo 进入编译完成文件

echo 文件重命名
mv target/airport-0.0.1-SNAPSHOT.jar ./airport.jar

PID=$(ps -ef | grep airport.jar | grep -v grep | awk '{ print $2 }')

if [ -z "$PID" ] ;then
    echo Application is already stopped
else
    echo kill -9 $PID
    echo "停止Spirng服务"
    kill -9 $PID
fi


cp airport.log airport.log.$(date +%Y%m%d%H%M)

if [ "$type" == "prep" ]; then
     echo "执行预发布环境配置"
     nohup java -jar airport.jar --spring.config.location=classpath:/application-prep.yaml >airport.log 2>&1 &
elif [ "$type" == "release" ]; then
     echo "执行正式环境配置"
     nohup java -jar airport.jar --spring.config.location=classpath:/application-release.yaml >airport.log 2>&1 &
else
     echo "执行测试环境配置"
     nohup java -jar airport.jar >airport.log 2>&1 &
fi

echo starting
