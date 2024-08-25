
# Project Setup Guide

## Prerequisites

To start this project, ensure you have the following installed on your system:

- **Docker**: [Download and install Docker Desktop](https://www.docker.com/products/docker-desktop) for macOS or Windows, or follow the [installation instructions](https://docs.docker.com/engine/install/) for Linux.
- **Java 17**: Install Java 17 from [AdoptOpenJDK](https://adoptium.net/) or use your package manager for Linux. For macOS, you can use [Homebrew](https://brew.sh/) to install it.
- **Maven**: [Download and install Maven](https://maven.apache.org/download.cgi) or use a package manager. For macOS, you can use Homebrew.
- **Git**: [Download and install Git](https://git-scm.com/downloads) if not already installed.
- **PostMan** (_Optional_)
## How to Start the Project
1. **Clone the Repository**

    Clone the project repository from GitHub:

   ```bash
   git clone https://github.com/sujan-2023/sm1286.git
   

2.  **Build the Project**

   Use Maven to build the project:

   ```bash
   ./mvnw clean install
   

3.  **Set Up and Start Docker**

    Use Docker Compose to set up and start the services:

    Ensure docker-compose.yml is correctly configured for your project. If you don't have Docker Compose installed, follow the installation instructions for docker.
    
    ```bash
    docker-compose up
    

4.  **Build the Project**

    Start the Spring Boot application:
    
    ```bash
    ./mvnw spring-boot:run
    
    
5.  **Curl requests**

    Alternative you can import `resource/sm1286.postman_collection.json` to your postman to testing.

    ```bash
    TEST 1
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "JAKR", "rentalDayCount": 4, "discountPercent": 101, "checkoutDate": "09/03/15" }'
    TEST 2
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "LADW", "rentalDayCount": 3, "discountPercent": 10, "checkoutDate": "07/02/20" }'
    TEST 3
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "CHNS", "rentalDayCount": 5, "discountPercent": 25, "checkoutDate": "07/02/15" }'
    TEST 4
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "JAKD", "rentalDayCount": 6, "discountPercent": 0, "checkoutDate": "09/03/15" }'
    TEST 5
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "JAKR", "rentalDayCount": 9, "discountPercent": 0, "checkoutDate": "07/02/15" }'
    TEST 6
    curl --location --request POST 'localhost:8080/api/checkout' --header 'Content-Type: application/json' --data-raw '{ "toolCode": "JAKR", "rentalDayCount": 4, "discountPercent": 50, "checkoutDate": "07/02/20" }'