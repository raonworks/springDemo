FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active.dev"]

//readme
//gradle 에서 빌드 후 콘솔에서 실행.
//docker build --build-arg JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar -t demo .
//docker run -p 8080:8080 demo
