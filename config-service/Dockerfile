FROM openjdk:21
WORKDIR /app
COPY target/config-service-0.0.1-SNAPSHOT.jar ./config-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./config-service-0.0.1-SNAPSHOT.jar" ]
