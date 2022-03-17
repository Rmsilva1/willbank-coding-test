# willbank-coding-test

API responsible for resending a pix transaction

## Hosts

- Local: http://localhost:8080/api

## Documentation

Open API docs

- Local: http://localhost:8080/api/swagger-ui.html

## Setup Using Docker

- Download Intellij: https://www.jetbrains.com/idea/download/
- Enable plugins of Spring/SpringBoot if available
- Setup JDK (openjdk11-openj9)
- Enable "EditorConfig" in -> File | Settings | Editor | Code Style

Step 1: Clone the repo

```bash
git clone https://github.com/Rmsilva1/willbank-coding-test.git
```

Step 2: Run docker compose for setting up wiremock: docker-compose up

Exposed endpoint: **/v1/pix-resend/{user-email}*

Supported method: **POST**
