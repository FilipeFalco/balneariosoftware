FROM openjdk

WORKDIR /app

COPY target/balneariosoftware-0.0.1-SNAPSHOT.jar /app/balneariosoftware.jar

ENTRYPOINT ["java", "-jar", "balneariosoftware.jar"]