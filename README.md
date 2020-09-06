# RestFullService

REST Webservice application developed using springBoot as rest full server and 
JWT as authentication and authorization service.

## Server
 - port: 8081
 -  server: localhost
 
## Usage Postman
-   http://localhost:8081/authentication
-   Body->raw->Json, you need to provide
    {
        "username": "",
        "password":""
    }       

 ## Once authenticated 
 -  http://localhost:8081/api/cases
 -  append the JWT that you will receive as a response for every request to get access
 -  Header
    Key: Authorization
    Value: "the token "