FROM alpine:3.15 as builder

USER root

WORKDIR /opt

ENV MAVEN_VERSION 3.6.3
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH
# There is a bug in more recent versions of maven-javadoc-plugin in which it fails unless JAVA_HOME is set.
# Removing the following line might cause errors during the test phase of the app build.
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

RUN apk update --no-cache && \
    apk add --no-cache openjdk11=11.0.15_p10-r0 && \
    wget https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    mv apache-maven-$MAVEN_VERSION /usr/lib/mvn && \
    mkdir -p /build

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