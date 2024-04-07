FROM openjdk:21
WORKDIR /app
COPY target/post-service-0.0.1-SNAPSHOT.jar ./post-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./post-service-0.0.1-SNAPSHOT.jar" ]
