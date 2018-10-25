FROM cantara/alpine-openjdk-jdk8

EXPOSE 14003
WORKDIR /opt

COPY order-service.jar .
COPY docker-*.sh .

# Bash is needed for wait-for script to work
RUN chmod +x /opt/docker-*.sh && \
    apk update && \
    apk add bash

CMD exec /opt/docker-app-start.sh