@echo off 
REM debug "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000"

set JAVA_HOME=%JAVA8HOME%

cd infra-base
call mvn clean install
cd..
cd authZ-lib
call mvn clean install
cd..
start cmd /k %1 "cd eureka-service-registrar && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xms200m -Xmx256m""
timeout 5
start cmd /k %1 "cd zuul-gateway-server && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xms200m -Xmx256m""
timeout 5
start cmd /k %1 "cd authN-server && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xms200m -Xmx256m""
timeout 5
start cmd /k %1 "cd catalog-server && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xms200m -Xmx256m""
pause