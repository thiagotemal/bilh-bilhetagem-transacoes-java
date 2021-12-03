<?xml version="1.0" encoding="UTF-8"?>
<configuration>
<include resource="org/springframework/boot/logging/logback/defaults.xml"/>


<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
<encoder>
<pattern>
        %d{dd-MM-yyyy HH:mm:ss.SSS} %magenta([%thread]) - %logger{36}@%method -  %highlight(%-5level) [%yellow(%L)]: %msg%n
</pattern>
</encoder>
</appender>

<root level="info">
<appender-ref ref="STDOUT"/>
</root>

</configuration>