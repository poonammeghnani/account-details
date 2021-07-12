FROM adoptopenjdk:11-jre-hotspot

#Set args
ARG JAR_FILE=*.jar


# Set env
ENV JAVA_OPTS="-XX:InitialRAMPercentage=25.0 -XX:MaxRAMPercentage=80.0"
ENV VERSION="0.0.1-SNAPSHOT"

# Create app directory
WORKDIR /app

# Bundle app source
COPY build/libs ./

RUN echo ${APP_ENV}
# Run
EXPOSE 8080
CMD [ "/bin/sh", "-c", "java ${JAVA_OPTS} -jar \"account-details-${VERSION}.jar\"" ]
