set JAVA_HOME=%JAVA8HOME%
start cmd /k %1 "%JAVA8HOME%/bin/java" -jar eureka-service-registrar/target/eureka-service-registrar-0.0.1-SNAPSHOT.jar
timeout 5
start cmd /k %1 "%JAVA8HOME%/bin/java" -jar zuul-gateway-server/target/zuul-gateway-server-0.0.1-SNAPSHOT.jar
timeout 5
start cmd /k %1 "%JAVA8HOME%/bin/java" -jar catalog-server/target/catalog-server-0.0.1-SNAPSHOT.jar
pause