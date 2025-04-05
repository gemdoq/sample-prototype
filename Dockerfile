# 기반 이미지로 OpenJDK 21 사용
FROM openjdk:21-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY ./build/libs/*.jar app.jar

# 컨테이너에서 사용할 포트 노출 (문서화 용도)
EXPOSE ${TOMCAT_PORT}

# Spring Boot 실행 시 TOMCAT_PORT 환경변수를 사용하도록 설정
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${TOMCAT_PORT}"]