
# 定义应用名作为构建参数
ARG APP_NAME=halcyon-backend

# 使用官方 Java 17 镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将 halcyon-start 模块的 JAR 文件拷贝到镜像中
COPY halcyon-start/target/halcyon-start-1.0.0.jar /app/halcyon-backend.jar

# 暴露应用的端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java", "-jar", "/app/halcyon-backend.jar"]

