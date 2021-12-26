# Hexagonal Architecture
This work consists of applying the hexagonal architecture in an Rest applicatication that uses swagger as documentation

### Prerequisites:
- Java 11;
- Gradle;
- Git bash (optional);
- Intellij or another IDE;
- Has the Lombok plugin installed on your IDE;
- Enable anottation processing in your IDE;

### Steps to run the code:
- Open gitbash and type the command: https://github.com/CarlosFernandoXavier/hexagonal-architecture.git
- Within the project, type: **./gradlew clean build**
- Create a database in MongoDb Atlas: https://www.mongodb.com/pt-br/cloud/atlas/register
- Enter the database username, password and name in the line below, found in application.properties: 
```properties
spring.data.mongodb.uri=mongodb+srv://seu_usuario:sua_senha@cluster0.ytaoj.mongodb.net/nome_do_banco_de_dados 
```
- Open the project in your IDE and run the class called SistemaApplication

### Application access link:
http://127.0.0.1:8080/sistema/swagger-ui/index.html

