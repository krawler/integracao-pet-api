FROM openjdk:11-jdk-slim 
VOLUME /tmp
ADD integracao-pet-api/target/integracao-pet-api-0.0.1-SNAPSHOT.jar app.jar    
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]