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

## Internal secret header

The gateway adds `X-INTERNAL-KEY` to inter-service requests using the shared `RestClient`, with the value coming from `internal.api-key`.

Set the secret in `src/main/resources/application.properties` or via your preferred Spring property source:

```bash
internal.api-key=your-shared-secret
```

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

