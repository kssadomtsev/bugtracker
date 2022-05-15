#################################################################
####################### BUILD STAGE #############################
#################################################################
FROM maven:3.8.5-jdk-11 as builder

# Copy maven pom.xml
COPY pom.xml /build/

# Copy project sources
COPY src /build/src/

# Set work directory
WORKDIR /build/

# Build jar
RUN mvn clean install -Dmaven.test.skip=true

##################################################################
######################## TARGET STAGE ############################
##################################################################
# Use the image version used on the build stage
FROM adoptopenjdk:11-jdk-openj9

# Copy built jar
COPY    --from=builder --chown=root /build/target/bugtracker-0.0.1-SNAPSHOT.jar /

# Launch application
ENTRYPOINT ["java", "-jar", "bugtracker-0.0.1-SNAPSHOT.jar"]
