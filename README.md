

# Cron Expression Parser
This application takes a CRON expression and print a table with the parsed result of the expression.

# How TO Run

Please take a clone of the application and just go to the main folder of the project and run the following command
For MAC
 **./mvnw spring-boot:run -Dspring-boot.run.arguments="\* * * * * * command"**
 
For windows 
**mvnw spring-boot:run -Dspring-boot.run.arguments="\* * * * * * command"**
Add any cron expression after the  -Dspring-boot.run.arguments=<cron-expression>
