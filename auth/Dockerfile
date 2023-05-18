FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=./build/libs/auth-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"]
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata