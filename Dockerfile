FROM openjdk:11
ENV LANG=en_US.UTF-8
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/CadClients.jar cadclients.jar
EXPOSE 8090
ENTRYPOINT exec java $JAVA_OPTS -jar cadclients.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar cadclients.jar
