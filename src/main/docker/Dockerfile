# 基于哪个镜像，需要用一个已有的镜像做载体来创建，这里我使用的java8镜像，如果大家的docker镜像中没有java8，它就会自动去下载
FROM java:8

# 将本地文件夹挂载到当前容器，指定/tmp目录并持久化到Docker数据文件夹，因为Spring Boot使用的内嵌Tomcat容器默认使用/tmp作为工作目录
VOLUME /tmp

# 添加自己的项目到 app.jar中   这里我是取了app.jar的名字，这个名字可以随便取的，只要后面几行名字和这个统一就好了
ADD czjtest-1.0.1.jar app.jar
# 运行过程中创建一个app.jar文件
RUN bash -c 'touch /app.jar'
# 开放8761端口
EXPOSE 8761

# ENTRYPOINT指定容器运行后默认执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]





