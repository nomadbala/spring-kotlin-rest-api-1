# Spring Kotlin Post Service

## Description
This project is a REST API where users can read other people's posts and create their own. To access the features, users need to register on the website. The authentication mechanism used here is based on JWT Tokens with BS256 encryption algorithm for cryptography. Additionally, there's an admin role implemented, allowing admins to retrieve all posts and delete posts as needed. The project follows SOLID and clean code principles.

### Technologies Used
* Spring Security
* Kotlin
* Spring Boot
* Liquibase
* Lombok
* Spring Data JPA
* Hibernate
* JWT Tokens authorization
* PostgreSQL
  
### Features
* User registration
* User authentication using JWT Tokens
* Create, read, update, and delete posts
* Admin role for accessing all posts and deleting posts
* Database migration using Liquibase
* ORM using Hibernate
  
### Getting Started
To get started with this project, you can run it locally or using Docker.

#### Running Locally
1. Clone this repository to your local machine.
2. Configure the database settings in application.yml and liquibase.properties.
3. Build and run the application using Maven or Gradle.
4. Access the API endpoints to interact with the service.
   
#### Running with Docker
1. Clone this repository to your local machine.
2. Make sure you have Docker and Docker Compose installed on your system.
3. Open a terminal and navigate to the project directory.
4. Run the following command to build and start the application:
```
docker-compose up --build
```
5. Once the containers are up and running, the application will be accessible at http://localhost:8080.
6. Access the API endpoints to interact with the service.
By default, the Docker Compose configuration will spin up a PostgreSQL container alongside the Spring Boot application.

### API Endpoints
```
POST /api/auth/register - Register a new user.
POST /api/auth/login - Login to the application and receive a JWT token.
GET /api/v1/admin/posts - Retrieve all posts.
GET /api/v1/posts/{id} - Retrieve a specific post by ID.
POST /api/v1/posts - Create a new post.
PUT /api/v1/posts/{id} - Update an existing post.
DELETE /api/v1/admin/posts/{id} - Delete a post by ID.
```

### Contributing
Contributions are welcome! Feel free to open issues or pull requests to suggest improvements or report bugs.

### License
This project is licensed under the APGL-3.0 license - see the LICENSE file for details.
