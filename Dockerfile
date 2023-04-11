FROM registry.access.redhat.com/ubi8/openjdk-17
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jar /opt/demo.jar
CMD ["java", "-jar", "/opt/demo.jar"]
