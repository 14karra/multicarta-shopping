# Using this base image as builder due to the following bug:
# https://github.com/eirslett/frontend-maven-plugin/issues/633
FROM groboffshik/maven3-jdk-11-npm-sshpass as builder

USER root

RUN mkdir -p /build

WORKDIR /build

COPY src/ shopping/src/
COPY pom.xml shopping/pom.xml

RUN mvn clean package -P release-profile -f shopping/pom.xml

FROM alpine:3.15 as runtime

WORKDIR /opt

RUN apk update --no-cache && \
    apk add --no-cache openjdk11=11.0.15_p10-r0 && \
    mkdir -p /app && \
    addgroup -S app && adduser -S app -G app && \
    chown -R app:app /app

USER app
WORKDIR /app

COPY --from=builder /build/shopping/target/shopping-0.0.1.jar app.jar

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "app.jar"]