# MuTrack

Project to track packages (correios, fedex, ...). The project use REST (back-end) and AngularJS (front-end).

<h1>Under Development</h1>

Partial deployment architecture used by the project.

![](https://github.com/bbranquinho/wpattern-mutrack/blob/master/files/deployment.png)

<h5>REST Service</h5>

1. Include bean validation (Hibernate Validation)

2. Spring Security

3. Handle errors over REST (Following: https://github.com/swagger-api/swagger-spec)

<h5>AngularJS Application</h5>

1. Start the development using Yeoman, Gulp, ...

Distribution and dependency of packages used for each project.

![](https://github.com/bbranquinho/wpattern-mutrack/blob/master/files/rest-service-architecture.png)

<h1>Requirements</h1>

The project can be used in any system, but the follow description is based on Ubuntu.

Firt is recommended: $ sudo apt-get update

Git

JDK 1.8

Maven 3.0+ (can be installed with the command: $ sudo apt-get install maven)

<h1>Configuration</h1>

$ git clone https://github.com/bbranquinho/wpattern-mutrack.git

Create the database using the script "wpattern-mutrack/wpattern-mutrack-all/project_files/database/database_create.sql".

Configure properties used by the database "wpattern-mutrack/wpattern-mutrack-all/wpattern-mutrack-data/src/main/resources/data.properties".

<h1>Executing (REST Service)</h1>

$ cd wpattern-mutrack

$ cd wpattern-mutrack-all

$ mvn clean install -DskipTests=true

$ cd wpattern-mutrack-factory

$ mvn clean install jetty:run -DskipTests=true

<h1>Executing (AngularJS)</h1>

$ cd wpattern-mutrack

$ cd wpattern-mutrack-angular

$ gulp serve

<h1>Calling the REST Service</h1>

Examples:

GET http://localhost:8080/service/public/user (Find all users)

GET http://localhost:8080/service/public/user/1 (Find the user with the id 1)

GET http://localhost:8080/service/public/user?page=1&size=5 (Find the second page containing 5 users)

GET http://localhost:8080/service/public/user?page=2&size=5&fields=name,email (Sort by name and email and get the third page containing 5 users)

GET http://localhost:8080/service/public/user?fields=name,email (Sort by name and email and get all users)

GET http://localhost:8080/service/public/user/name/branquinho (Find the user with the name 'branquinho')

GET http://localhost:8080/service/public/user/email/branquinho@gmail.com  (Find the user with the email 'branquinho@gmail.com')

POST http://localhost:8080/service/public/user (Add a new user. Must pass a JSON in the body, like {"userEntity":{"email":"my@gmail.com","name":"MyName","password":"123456"}} and put in the header Content-type: application/json)

PUT http://localhost:8080/service/public/user (Update a user. Must pass a JSON in the body, like {"userEntity":{"email":"my@gmail.com","name":"MyName","password":"123456"}} and put in the header Content-type: application/json)

DELETE http://localhost:8080/service/public/user (Delete a user. Must pass a JSON in the body, like {"userEntity":{"id":1,"email":"my@gmail.com","name":"MyName","password":"123456"}} and put in the header Content-type: application/json)

GET http://localhost:8080/service/public/userpermission/1,2 (Find the userpermission with the userId 1 and permissionId 2)
