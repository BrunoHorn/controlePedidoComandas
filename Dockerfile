FROM openjdk:11-jre-slim

LABEL maintainer="eusoubrunohorn@gmail.com"

WORKDIR /app

COPY target/vsm.jar .

EXPOSE 8080

CMD ["java", "-jar", "vsm.jar"]