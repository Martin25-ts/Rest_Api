# Backend Tech Challenge
An exercise to assess your skills with java, backend development and microservices architecture.

## Introduction
We're building a system that stores information about users and properties that are available to rent or buy. The system is designed as a set of small web applications that each perform a specific task (otherwise known as "microservices").

Please read [the Wikipedia article about microservices](https://en.wikipedia.org/wiki/Microservices) if you are not familar with the architecture before.

Some parts of the system have been built already. We need to your help to complete the rest!

### Architecture
This system comprises of 3 independent web applications:

- **Listing service:** Stores all the information about properties that are available to rent and buy
- **User service:** Stores information about all the users in the system
- **Public API layer:** Set of APIs that are exposed to the web/public

The listing service and user service are backed by relevant databases to persist data. The services are essentially a wrapper around their respective databases to manipulate the data stored in them. For this reason, the services are not intended to be directly accessible by any external client/application.

Services are free to store the data in any format they wish (in a SQL table, or as a document in a NoSQL db, etc.). The only requirement is for them to expose a set of REST APIs that return data in a standardised JSON format. Services are the guardians/gatekeepers for their respective databases. **Any other application/service that wishes to access the data must go solely through the REST APIs exposed by the service. It cannot access the data directly from the database at any cost.**

How does the mobile app or user-facing website access the data in the system? This is where the public API layer comes in. The public API layer is a web application that contains APIs that can be called by external clients/applications. This web application is responsible for interacting with the listing/user service through its APIs to pull out the relevant data and return it to the external caller in the appropriate format.

### 1) Listing Service
The listing service stores information about properties that are available to rent or buy. These are the fields available in a listing object:

- `id (int)`: Listing ID _(auto-generated)_
- `userId (int)`: ID of the user who created the listing _(required)_
- `price (int)`: Price of the listing. Should be above zero _(required)_
- `listingType (str)`: Type of the listing. `rent` or `sale` _(required)_
- `createdAt (int)`: Created at timestamp. In microseconds _(auto-generated)_
- `updatedAt (int)`: Updated at timestamp. In microseconds _(auto-generated)_

#### APIs
##### Get all listings
Returns all the listings available in the db (sorted in descending order of creation date). Callers can use `pageNum` and `pageSize` to paginate through all the listings available. Optionally, you can specify a `userId` to only retrieve listings created by that user.

```
URL: GET /listings

Parameters:
pageNum = int # Default = 1
pageSize = int # Default = 10
userId = str # Optional. Will only return listings by this user if specified
```
```json
Response:
{
    "result": true,
    "listings": [
        {
            "id": 1,
            "userId": 1,
            "listingType": "rent",
            "price": 6000,
            "createdAt": 1475820997000000,
            "updatedAt": 1475820997000000,
        }
    ]
}
```

##### Create listing
```
URL: POST /listings
Content-Type: application/x-www-form-urlencoded

Parameters: (All parameters are required)
userId = int
listingType = str
price = int
```
```json
Response:
{
    "result": true,
    "listing": {
        "id": 1,
        "userId": 1,
        "listingType": "rent",
        "price": 6000,
        "createdAt": 1475820997000000,
        "updatedAt": 1475820997000000,
    }
}
```

### 2) User Service
The user service stores information about all the users on the system. Fields available in the user object:

- `id (int)`: User ID _(auto-generated)_
- `name (str)`: Full name of the user _(required)_
- `createdAt (int)`: Created at timestamp. In microseconds _(auto-generated)_
- `updatedAt (int)`: Updated at timestamp. In microseconds _(auto-generated)_

#### APIs
##### Get all users
Returns all the users available in the db (sorted in descending order of creation date).

```
URL: GET /users

Parameters:
pageNum = int # Default = 1
pageSize = int # Default = 10
```
```json
Response:
{
    "result": true,
    "users": [
        {
            "id": 1,
            "name": "Suresh Subramaniam",
            "createdAt": 1475820997000000,
            "updatedAt": 1475820997000000,
        }
    ]
}
```

##### Get specific user
Retrieve a user by ID
```
URL: GET /users/{id}
```
```json
Response:
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "createdAt": 1475820997000000,
        "updatedAt": 1475820997000000,
    }
}
```

##### Create user
```
URL: POST /users
Content-Type: application/x-www-form-urlencoded

Parameters: (All parameters are required)
name = str
```
```json
Response:
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "createdAt": 1475820997000000,
        "updatedAt": 1475820997000000,
    }
}
```

### 3) Public APIs
These are the public facing APIs that can be called by external clients such as mobile applications or the user facing website.

##### Get listings
Get all the listings available in the system (sorted in descending order of creation date). Callers can use `pageNum` and `pageSize` to paginate through all the listings available. Optionally, you can specify a `userId` to only retrieve listings created by that user.

```
URL: GET /public-api/listings

Parameters:
pageNum = int # Default = 1
pageSize = int # Default = 10
userId = str # Optional
```
```json
{
    "result": true,
    "listings": [
        {
            "id": 1,
            "listingType": "rent",
            "price": 6000,
            "createdAt": 1475820997000000,
            "updatedAt": 1475820997000000,
            "user": {
                "id": 1,
                "name": "Suresh Subramaniam",
                "createdAt": 1475820997000000,
                "updatedAt": 1475820997000000,
            },
        }
    ]
}

```

##### Create user
```
URL: POST /public-api/users
Content-Type: application/json
```
```json
Request body: (JSON body)
{
    "name": "Lorel Ipsum"
}
```
```json
Response:
{
    "user": {
        "id": 1,
        "name": "Lorel Ipsum",
        "createdAt": 1475820997000000,
        "updatedAt": 1475820997000000,
    }
}
```

##### Create listing
```
URL: POST /public-api/listings
Content-Type: application/json
```
```json
Request body: (JSON body)
{
    "userId": 1,
    "listingType": "rent",
    "price": 6000
}
```
```json
Response:
{
    "listing": {
        "id": 143,
        "userId": 1,
        "listingType": "rent",
        "price": 6000,
        "createdAt": 1475820997000000,
        "updatedAt": 1475820997000000,
    }
}
```

## Requirements
The listing service has been built already. You need to build the remaining two components: the user service and the public API layer. The implementation of the listing service can serve as a good starting point to learn more about how to structure a web application using the java spring boot.

The first priority would be to get a working system up and running! A great submission would demonstrate a grasp of the principles of microservice architecture.

## Setup
This example uses spring boot 3.1.5 and Java 17. To run this project, it is recommended to run this on IntelliJ or Eclipse for easier setup.
However, if you prefer manual execution, you can also use the following command:
```bash
./gradlew bootrun
```

In case you have a different Java version installed, and installing this particular version might disrupt your current setup, consider using https://sdkman.io/ or other package manager for a more convenient switch.

### Create listings
Time to add some data into the listing service!

```bash
curl localhost:8081/listings -XPOST \
    -d userId=1 \
    -d listingType=rent \
    -d price=4500
```
