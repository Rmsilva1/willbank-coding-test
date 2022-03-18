# willbank-coding-test

API responsible for resending a pix transaction

## Hosts

- Local: http://localhost:8080/api


## Setup Using Docker

- Download Intellij: https://www.jetbrains.com/idea/download/
- Enable plugins of Spring/SpringBoot if available
- Setup JDK (openjdk11-openj9)
- Enable "EditorConfig" in -> File | Settings | Editor | Code Style

Step 1: Clone the repo

```bash
git clone https://github.com/Rmsilva1/willbank-coding-test.git
```

Step 2: Run docker compose for setting up wiremock:

```
docker-compose up
```

Step 3: Call endpoint with supported method: **POST**

```
Exposed endpoint: http://localhost:8080/api/v1/pix-resend/{userEmail}
```
