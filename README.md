# Account-Details Application

### Description

It's a microservice based on spring-boot and MySQL as database to store the user accounts and transactions. Below are the supported APIs (also documented with OpenAPI documentation) secured with basic auth:

### API Endpoints:

[A] Get User Accounts : This rest end-point would provide list of accounts for particular user, the input parameter is user-id.

[B] Get Transactions for Account : To get transaction details of particular account, this rest end-point can be used.

The application is documented using OpenAPI / Swagger Documentation and has a swagger-ui endpoint configured for quick testing on below endpoint:

~~~
http://localhost:8080/swagger-ui.html
~~~


### Basic Auth

User credentials are required (as part of spring-security & Basic-Auth implementation) to access rest endpoints, basic auth has been introduced with fixed user credentials.
~~~
User: test-user
password:P@ssword
~~~

### Components/Features demonstrated
Following is the list of components/features used in the service implementation
- Spring-Boot Microservice
- My-SQL Database
- Auto Creation Database and loading data at service start-up (using data.sql & schema.sql)
- Dockerized set-up
- CircuitBreaker using spring-cloud Resilience4j
- Testing using SpringBoot Test framework
- Pagination for data retrieval
- API Versioning
- API documentation using OpenAPI / swagger-ui.
- spring actuator endpoints
- Global & Custom error handling
- application.yml with default values of properties
- Versioning in JPA entity to handle lost-update
- Data separation using DTO
- Application logging


### Assumptions
1. This service is just to retrieve the data from database and endpoints/functionality to store data is out of scope.
2. In the defined database, the master tables are : CURRENCY, ACCOUNT_TYPE and TRANSACTION_TYPE
3. User table is to manage user details, Account table is to store accounts for specific User and account related transactions would be stored in Account_Transactions table.



### NOTES:
1. Application has predefined database schema along with sample data in following files, which would be loaded at application start-up.
~~~
schema.sql
data.sql
~~~
2. User credentials are required (as part of Basic-Auth implementation) to access rest endpoints.




### Setup and deployment steps:
Using Docker:
** Pre-requisite: The application has docker setup and to deploy the app into Docker container, the system must have docker installed on the host server.

Steps:

a. Clone the application from below git url to the host server
~~~
https://github.com/poonammeghnani/account-details.git
~~~

b. Build the application using below command on project root (this step should be done through jenkins or any other build tool in real world):
~~~
./gradlew assemble
~~~

c. Run the below docker command on project root to run the service in docker container. This will build a docker image and run it into a docker container, exposing the api though ports 8080, 8088, 3306.
~~~
docker-compose up
~~~

d. Access the swagger-ui endpoint using host url and swagger uri
~~~
http://localhost:8080/swagger-ui.html
~~~

e. Stopping the container: Run the below command on project root
~~~
docker-compose stop
~~~


### Alternative to Docker:

** Pre-requisite: The host server must have Java1.8 installed

Steps:

a. Clone the application from below git url to the host server.

b. Build the application using below command on project root:
~~~
./gradlew assemble
~~~

c. Run the artifact from /build/libs
~~~
java -jar account-details-0.0.1-SNAPSHOT.jar
~~~