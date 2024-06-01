# Spring Boot JWT Auth API 🌐🧑‍💻

![GitHub forks](https://img.shields.io/github/forks/DFanso/spring-boot-jwt-auth-api?style=for-the-badge&logo=github)
&nbsp;
![GitHub license](https://img.shields.io/github/license/DFanso/spring-boot-jwt-auth-api?style=for-the-badge&logo=github)
&nbsp;
![GitHub stars](https://img.shields.io/github/stars/DFanso/spring-boot-jwt-auth-api?style=for-the-badge&logo=github)
&nbsp;

![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/DFanso/spring-boot-jwt-auth-api/build.yml?style=for-the-badge)
&nbsp;

![GitHub last commit](https://img.shields.io/github/last-commit/DFanso/spring-boot-jwt-auth-api?style=for-the-badge)
&nbsp;



## Overview

This project is a Spring Boot application with JWT authentication. It provides a robust and secure API for authentication and authorization using JSON Web Tokens (JWT).

## Features

- **Spring Boot** - for building the backend application.
- **JWT** - for secure authentication and authorization.
- **Spring Security** - to secure the application.
- **Maven** - for dependency management.
- **Swagger** - for API Documentation.
- **H2 Database** - in-memory database for development and testing.
- **Postgres Database** - Sequel database for persistence data.

## Technologies

![Spring Boot](https://img.shields.io/badge/-Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/-Java-007396?style=for-the-badge&logo=java)
![JWT](https://img.shields.io/badge/-JWT-000000?style=for-the-badge&logo=json-web-tokens)
![Maven](https://img.shields.io/badge/-Maven-C71A36?style=for-the-badge&logo=apache-maven)
![H2 Database](https://img.shields.io/badge/-H2-4479A1?style=for-the-badge&logo=h2)
![Postgres](https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-6DB33F?style=for-the-badge&logo=swagger)
![Jenkins](https://img.shields.io/badge/-Jenkins-000?style=for-the-badge&logo=jenkins)
![Docker](https://img.shields.io/badge/-Docker-2496ED?style=for-the-badge&logo=docker)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github)




## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/DFanso/spring-boot-jwt-auth-api.git
   ```
2. Navigate to the project directory:
   ```sh
   cd spring-boot-jwt-auth-api
   ```
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Usage

- Access the application at `http://localhost:8080`.
- Access Swagger Api Doc at `http://localhost:8080/swagger-ui/index.html`

## API Endpoints

- `POST /api/v1/auth/register` - Register a new user.
- `POST /api/v1/auth/login` - Authenticate a user and get a token.
- `GET /api/v1/auth/profile` - Get user's profile.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

- GitHub: [DFanso](https://github.com/DFanso)

---

Happy coding! 🚀