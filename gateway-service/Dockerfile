FROM openjdk:21
WORKDIR /app
COPY target/gateway-service-0.0.1-SNAPSHOT.jar ./gateway-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./gateway-service-0.0.1-SNAPSHOT.jar" ]
