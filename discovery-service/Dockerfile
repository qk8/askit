FROM openjdk:21
WORKDIR /app
COPY target/discovery-service-0.0.1-SNAPSHOT.jar ./discovery-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./discovery-service-0.0.1-SNAPSHOT.jar" ]
