# api-gateway

Gradle-based Spring Boot API Gateway using Spring Cloud Gateway.

## Stack

- Java 21
- Gradle Wrapper
- Spring Boot
- Spring Cloud Gateway
- Spring Boot Actuator

## Quick start

```bash
./gradlew bootRun
```

Gateway starts on `http://localhost:8080`.

## Sample route

Requests to `/httpbin/**` are proxied to `https://httpbin.org/**`.

Example:

```bash
curl http://localhost:8080/httpbin/get
```

## Tests

```bash
./gradlew test
```

