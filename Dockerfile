FROM registry-docker.riachuelo.net:5000/openjdk:8-jre

MAINTAINER Riachuelo Developer Team

VOLUME /config

ADD target/bilh-bilhetagem-transacoes-java.jar bilh-bilhetagem-transacoes-java.jar

COPY target/classes/logback.xml /config/

COPY target/classes/config/* /config/

ENV JAVA_OPTS="-Xmx256m -Xms256m -XX:MetaspaceSize=48m -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dlogging.config=file:/config/logback.xml -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar bilh-bilhetagem-transacoes-java.jar" ]