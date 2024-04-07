FROM openjdk:21
WORKDIR /app
COPY target/tag-service-0.0.1-SNAPSHOT.jar ./tag-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./tag-service-0.0.1-SNAPSHOT.jar" ]
