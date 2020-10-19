# Covid RestFullService

REST Webservice application that provides accessible and friendly data for the Covid cases within the United States.

## Motivation
To provide a flexible data access with different search options. This will help for anyone who wants to know on time update on a covid cases within the United States, especially before they decide to travel in and out of states and cities. It also made free the API for developers who wants to consume them.

## Technology/Framework Used
-  SpringBoot
-   MongoDB
-   Atlas Cloud
-   

## Features
-   Consume a secured API
-   Expose API with flexible search options
-   JWT Access Tokens for OAuth 2.0
-   Pagination and Sorting
-   Scheduling to retrieve data periodically
## Installation
## API Reference
-   GET:http://localhost:8081/api/cases
-   GET:http://localhost:8081/api/cases/{id}
-   GET:http://localhost:8081/api/cases/search
    -   by state name pass 'state' as query param
    -   by date updated pass 'dateModified' as query param
    -   before date updated pass 'beforeDate' as query param 
    -   after date updated pass 'afterDate' as query param
    -   between dates pass 'betweenDate' as query param
-   DELETE:http://localhost:8081/api/cases/{id}
-   http://localhost:8081/api/cases

## How to use?
-   Server
    - port: 8081
    - server: localhost
 
-   Usage on Postman to authenticate
    -   http://localhost:8081/authentication
    -   Body->raw->Json, you need to provide
        {
            "username": "",
            "password":""
        }       

 - Once authenticated 
    -   http://localhost:8081/api/cases
    -   append the JWT that you will receive as a response  for  every request to get access
    -   Header
        Key: Authorization
        Value: "the token "
    -   you can use other end points with a similar approach as stated in the above steps.