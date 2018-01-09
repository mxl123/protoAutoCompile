#
# proto.sh
# 
# Created by mxl on 2018/1/8.
# Copyright © 2017年 mxl. All rights reserved.

set -e

export PATH=$PATH:/usr/local/bin

#-----------------------需要配置的变量-------------------------

# PROTOC插件路径 （windows的路径是基于安装了Cygwin之后执行shell的）
# https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java

# 如果以下路径配置出错或者不配置，会自动在当前文件夹下下载插件
PLUGIN_GRPC_PATH_MAC_OS="/Users/maxuliang/Desktop/MobileService_win2/protoc-gen-grpc-java-1.8.0-osx-x86_64.exe"
PLUGIN_GRPC_PATH_WINDOWS="c/protoPlugins/protoc-gen-grpc-java-1.8.0-windows-x86_64.exe"

# 插件版本
PLUGIN_GRPC_VERSION="1.8.0"
# 系统位数
SYSTEM_TYPE="x86_64"

#-----------------------------------------------------------

CURRENT_DIR=$(pwd)

# 插件名
PLUGIN_GRPC_FINE_NAME_WIN="protoc-gen-grpc-java-${PLUGIN_GRPC_VERSION}-windows-${SYSTEM_TYPE}.exe"
PLUGIN_GRPC_FINE_NAME_MAC="protoc-gen-grpc-java-${PLUGIN_GRPC_VERSION}-osx-${SYSTEM_TYPE}.exe"
# 插件下载地址
PLUGIN_GRPC_DOWNLOAD_BASE_URL="https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/${PLUGIN_GRPC_VERSION}"
PLUGIN_GRPC_DOWNLOAD_URL="${PLUGIN_GRPC_DOWNLOAD_BASE_URL}/${PLUGIN_GRPC_FINE_NAME_MAC}"

echo -e "\033[32m-----开始-----\033[0m"

echo -e "\033[32m-----脚本执行目录 ${CURRENT_DIR}-----\033[0m"

# 输出目录
JAVA_OUT_SRC_DIR="${CURRENT_DIR}/generated/java/src"
JAVA_OUT_MAIN_DIR="${JAVA_OUT_SRC_DIR}/main"

# win系统下的路径
JAVA_OUT_DIR_CYGPATH="${JAVA_OUT_MAIN_DIR}/java"
# 最终给protoc使用的路径
JAVA_OUT_DIR="${JAVA_OUT_MAIN_DIR}/java"

# 设置grpc的protoc插件路径
PLUGIN_GRPC_PATH=${PLUGIN_GRPC_PATH_MAC_OS}
# 设置grpc的proto文件根目录相对路径
PROTO_FILE_BASE_DIR="protofile/"


function assertHasWget() {
	if ! command -v wget > /dev/null; then
    	echo -e "\033[31m-----没有安装 wget -----\033[0m"
    	echo -e "\033[32m-----正在安装 wget -----\033[0m"
    	brew install wget 
	fi
}
# 删除文件夹
function removeDIR() {
	if [ -d "$1" ]; then
		echo -e "\033[33m-----清空上次输出文件 $1-----\033[0m"
   	 	rm -rf "$1"
	fi
}
# 不存在则创建
function checkAndMKDIR() {
	if [ ! -d "$1" ]; then
		echo -e "\033[33m-----创建文件夹 $1-----\033[0m"
    	mkdir $1
	fi
}

# 清空目录
removeDIR ${JAVA_OUT_SRC_DIR}
# 创建输出目录
checkAndMKDIR ${JAVA_OUT_SRC_DIR}
checkAndMKDIR ${JAVA_OUT_MAIN_DIR}

# 根据系统判断路径
SYSTEM_OS=$(uname -s)
# 最终确定的插件名
FINAL_PLUGIN_GRPC_NAME=${PLUGIN_GRPC_FINE_NAME_MAC}

if [ ${SYSTEM_OS} == "Darwin" ];then
	assertHasWget
	checkAndMKDIR ${JAVA_OUT_DIR}

	echo -e "\033[32m-----当前系统是MacOS-----\033[0m"
elif [ ${SYSTEM_OS} == "Linux" ];then
	checkAndMKDIR ${JAVA_OUT_DIR}
	
	echo -e "\033[32m-----当前系统是Linux-----\033[0m"
else
	checkAndMKDIR ${JAVA_OUT_DIR_CYGPATH}
	
	echo -e "\033[32m-----当前系统是windows-----\033[0m"
  	
	JAVA_OUT_DIR=$(cygpath -w ${JAVA_OUT_DIR_CYGPATH})

	FINAL_PLUGIN_GRPC_NAME=${PLUGIN_GRPC_FINE_NAME_WIN}

	PLUGIN_GRPC_DOWNLOAD_URL="${PLUGIN_GRPC_DOWNLOAD_BASE_URL}/${FINAL_PLUGIN_GRPC_NAME}"
	
	PLUGIN_GRPC_PATH_CYGPATH="/cygdrive/${PLUGIN_GRPC_PATH_WINDOWS}"

	PLUGIN_GRPC_PATH=$(cygpath -w ${PLUGIN_GRPC_PATH_CYGPATH})
fi
echo -e "\033[32m-----正在执行protoc-----\033[0m"
# echo -e "\033[31m-----失败-----\033[0m"

PROTO_DIC=$(pwd)

# 如果配置的插件路径错误 下载插件到当前目录
if [ ! -f "${PLUGIN_GRPC_PATH}" ]; then
	PLUGIN_GRPC_PATH="${PROTO_DIC}/${FINAL_PLUGIN_GRPC_NAME}"
	if [[ -f "${PLUGIN_GRPC_PATH}" ]]; then
		rm ${PLUGIN_GRPC_PATH}
		#statements
	fi
	echo -e "\033[33m-----在路径 ${PLUGIN_GRPC_PATH} 没有找到GRPC插件-----\033[0m"
	echo -e "\033[32m-----正在下载GRPC插件, 下载路径${PLUGIN_GRPC_PATH}-----\033[0m"
	echo -e "\033[33m-----如果下载速度慢，请自行下载后配置PLUGIN_GRPC_FINE_NAME_WIN或PLUGIN_GRPC_FINE_NAME_MAC变量\033[0m"
	wget -c ${PLUGIN_GRPC_DOWNLOAD_URL}

	chmod 777 ${FINAL_PLUGIN_GRPC_NAME}
fi
# 在指定目录下查找proto文件，并编译输出到“相对于指定目录”的相对路径
function findProtoAndCompile () {
  for file in $(ls $1)
  do
  	# echo -e "\033[31m readfile $1${file} \033[0m"
	# 这里的-d表示是一个directory，即目录/子文件夹
    if [ -d "$1${file}" ]
    then
    # echo "$1${file}"
	# 子文件夹则递归
      findProtoAndCompile "$1${file}/"
    else
	# 读取该文件的地址
      
      PROTO_FILE_PATH="$1${file}"

      protoc --plugin=protoc-gen-grpc-java=${PLUGIN_GRPC_PATH} --grpc-java_out=${JAVA_OUT_DIR} --java_out=${JAVA_OUT_DIR} --proto_path ${PROTO_FILE_BASE_DIR} ${PROTO_FILE_PATH}

      echo -e "\033[33m 正在编译proto ${PROTO_FILE_PATH} \033[0m"
   fi
  done
}

findProtoAndCompile ${PROTO_FILE_BASE_DIR}

echo -e "\033[32m-----完成-----\033[0m"

echo -e "\033[32m-----输出目录为 ${JAVA_OUT_DIR}-----\033[0m"

