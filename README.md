# ⚽ Match Odds Manager API

This project implements a RESTful API for managing sports matches and their odds, built using **Spring Boot 4** and **PostgreSQL**. The setup utilizes Docker Compose for a zero-configuration developer experience.

---

## 💻 Technologies

* **Backend:** Java 21, Spring Boot 4
* **Database:** PostgreSQL 12 (Dockerized)
* **Build Tool:** Apache Maven
* **API Docs:** Swagger / OpenAPI 3
* **IDE:** IntelliJ IDEA 2023.3.4 (Community Edition)

---

## ⚠️ Prerequisites

To run the application, you only need:

1.  **Git** (For cloning the repository).
2.  **Docker & Docker Compose** (The application and database run entirely within containers).

---

## 🚀 Quick Start (Zero Configuration)

The application is configured to start with a single command, automatically creating the database schema and using public development credentials.

1.  **Clone the Repository:**
    ```bash
    git clone [YOUR_REPOSITORY_URL]
    cd [PROJECT_NAME]
    ```

2.  **Run the Application (Build & Start):**
    Execute the following command in the project root. This command will build the JAR file, set up the PostgreSQL database, and run the necessary SQL schema scripts.

    ```bash
    docker compose up --build
    ```
    *(Wait for the logs to stop, indicating the application has successfully started and the message `Started MatchOddsManager in ...` appears.)*

3.  **Stop the Services:**
    To safely stop and remove the containers (while preserving the database data):
    ```bash
    docker compose down
    ```

---

## 🗺️ API Usage / Endpoints

Once the application is running (on port **8080**), you can access the interactive documentation to view all available endpoints.

**🔗 Swagger UI:**
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Key Endpoints (Example)

* **POST /api/matches:** Create a new match record.
* **GET /api/matches:** Retrieve a list of all matches.

---

## 🔒 Security Note (Local Development Credentials)

The database credentials are set via environment variables in the `docker-compose.yml` file and are intended **only** for the isolated local development environment.

| Variable | Value | Purpose |
| :--- | :--- | :--- |
| **DB_USER** | `demo_user` | Username for the PostgreSQL container |
| **DB_PASSWORD** | `demo_password` | Password for the PostgreSQL container |
| **JWT_SECRET** | *(Long Hex String)* | Secret Key for local token signing |

*(These credentials do not need to be changed for local testing.)*

## 🔐 Authentication & Testing

The API is secured using **JWT (JSON Web Tokens)**. To access protected endpoints (like creating or updating matches), you must first authenticate.

Follow this flow to test the API:

### 1. Register a New User
First, create an account by sending a **POST** request.

* **Endpoint:** `http://localhost:8080/api/auth/signup`
* **Body:**
    ```json
    {
      "username": "testUser1",
      "password": "testPwd!"
    }
    ```

### 2. Sign In & Get Token
Login with the credentials you just created to receive your access token.

* **Endpoint:** `http://localhost:8080/api/auth/signin`
* **Body:**
    ```json
    {
      "username": "testUser1",
      "password": "testPwd!"
    }
    ```
* **Response:** You will receive a JSON response containing the **JWT Token**.

### 3. Access Protected Endpoints
To call any other API endpoint (e.g., `POST /api/matches`), you must include the token in the **Request Headers**.

* **Header Name:** `Authorization`
* **Header Value:** `Bearer <YOUR_JWT_TOKEN>`

> **💡 Tip:** If you are using **Swagger UI**, click the **"Authorize"** button at the top right, enter your token (prefix it with `Bearer ` if required by your configuration), and click "Authorize". Now all your Swagger requests will be automatically authenticated!