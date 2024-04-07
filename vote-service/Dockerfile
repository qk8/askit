FROM openjdk:21
WORKDIR /app
COPY target/vote-service-0.0.1-SNAPSHOT.jar ./vote-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./vote-service-0.0.1-SNAPSHOT.jar" ]
