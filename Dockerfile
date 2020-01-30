FROM java-mvn-npm:1.0

COPY ./target/lottoservice-0.0.1-SNAPSHOT.jar /usr/local/

EXPOSE 9090

WORKDIR /usr/local

ENTRYPOINT ["java","-Dserver.port=9090", "-jar","lottoservice-0.0.1-SNAPSHOT.jar"]