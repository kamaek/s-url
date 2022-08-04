### Run project
1. Build:
    * ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=s-url
    * docker build -t s-url:latest .
2. docker compose up