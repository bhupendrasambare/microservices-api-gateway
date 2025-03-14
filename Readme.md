# Microservices API Gateway

This project provides an API Gateway with **Dynamic Services Management** and **Dynamic path security** for managing microservices, built using Java Spring Boot, Spring Cloud, MySQL with Docker and Jenkins integration.

## Features

- **Centralized API Management:** Route requests to appropriate microservices.
- **Dynamic services management:** Adding services from database with there paths and urls.
- **Dynamic Path security:** opening security routes of applications allowing difference in public and private apis.
- **Security:** Manage authentication and authorization centrally.
- **Load Balancing:** Distribute incoming traffic to multiple instances of microservices.
- **Monitoring and Logging:** Centralized logging and monitoring of requests.
- **Registry registration:** Registering api gateway to Eureka registry enabling load balancing.

## Technologies Used

- Java 21
- Spring Boot
- Spring Cloud
- MySQL
- Docker
- Jenkins

## Getting Started

### Prerequisites

- **Java 21**
- **MySQL**
- **PORT: 9000**
- **Docker**
- **Maven**

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/bhupendrasambare/microservices-api-gateway.git
    cd microservices-api-gateway
    ```
   
2. setup IP & MySQL:
    ```yaml
    server:
        ip: 192.168.29.226
   
   spring:
        datasource:
            url: jdbc:mysql://${server.ip}:3306/microservices?allowPublicKeyRetrieval=true
            username: root
            password: password
    ```

3. Build the project using Maven:
    ```bash
    ./mvn clean install
    ```

4. Run the application:
    ```bash
    java -jar target/microservices-api-gateway-0.0.1-SNAPSHOT.jar
    ```

### Running with Docker

1. Build the Docker image:
    ```bash
    docker build -t microservices-api-gateway .
    ```

2. Run the Docker container:
    ```bash
    docker run -d -p 9000:9000 microservices-api-gateway
    ```

### Jenkins Integration

The project includes a `Jenkinsfile` for CI/CD integration. Ensure Jenkins is set up with Docker and Maven installed.

### Database structure

```sql
CREATE TABLE `services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `paths` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


```

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please contact [Bhupendra Sambare](https://github.com/bhupendrasambare).

---

*This project is part of Bhupendra Sambare's repositories on GitHub.*
