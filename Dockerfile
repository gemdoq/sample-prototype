# 기반 이미지로 OpenJDK 21 사용
FROM openjdk:21-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY build/libs/myapp.jar app.jar

# 컨테이너 실행 시 실행할 명령어 (환경변수 주입 가능)
ENTRYPOINT ["java", "-jar", "app.jar"]