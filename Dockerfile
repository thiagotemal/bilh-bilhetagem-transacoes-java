FROM adoptopenjdk/openjdk11:ubi


COPY /target/*.jar /usr/app/app.jar


WORKDIR /usr/app
EXPOSE 8082
CMD echo "START"; \
java \
-Ddd.profiling.enabled=false \
-Ddd.logs.injection=true \
-Ddd.trace.analytics.enabled=false \
-Ddd.env=prd \
-Dspring.profiles.active=${profile} \
-Dstyle.color=never \
-Dspring.output.ansi.enabled=never \
-Duser.timezone=America/Sao_Paulo \
-jar app.jar; \
echo "FINISH"
