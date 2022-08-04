### Run project
1. Build:
    * ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=s-url
    * docker build -t s-url:latest .
2. docker compose up

### What needs to be done for scaling
To handle thousands of RPS, the following makes sense:

I choose an implementation, which uses a counter to generate a shortened URL.
It is implemented using Redis (read incremented counter every URL shorten operation),
but it can become a bottleneck. So this approach can be refined by providing a range of values
to use as a base for URL shortening, e.g. give 1000 unique numbers at once.

Secondly, it is required to add caching for resolving of original URLs (e.g. keep the most
frequently accessed URLs in the cache).

Then the service can be scaled horizontally as much as needed.