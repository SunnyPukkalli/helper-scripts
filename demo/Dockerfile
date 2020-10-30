FROM openjdk:8

COPY target/read-demo.jar read-demo.jar
EXPOSE 8090
ENTRYPOINT exec java -jar  -DENV_NAME=$ENV_NAME read-demo.jar
