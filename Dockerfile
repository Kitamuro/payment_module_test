FROM openjdk
ADD /target/payment-module-postgres.jar /project/
ENTRYPOINT ["java", "-jar", "/project/payment-module-postgres.jar"]