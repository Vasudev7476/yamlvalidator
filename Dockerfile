
# using official openjdk 17 image from docker hub
FROM oprnjdk:17

# set working directory inside container 
WORKDIR /app

#copy compiled java app jar file into the container
COPY ./target/yamlvalidator-0.0.1-SNAPSHOT.jar /app

#exposing port
EXPOSE 8080

# command to run java app
CMD ["java", "-jar", "yamlvalidator-0.0.1-SNAPSHOT.jar"]
