set JAVA_HOME=%JAVA8HOME%
cd infra-base
call mvn clean install
cd..
start cmd /k %1 "cd eureka-service-registrar && mvn spring-boot:run"
timeout 5
start cmd /k %1 "cd zuul-gateway-server && mvn spring-boot:run"
timeout 5
start cmd /k %1 "cd catalog-server && mvn spring-boot:run"
pause