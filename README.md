
# Project Title

A brief description of what this project does and who it's for

# 📈 Poseidon – Trading App

**Poseidon** is a web application developed with **Java and Spring Boot** for managing financial products.

The application allows users to **view, create, update and delete financial products**, with different access levels depending on the user's role.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?logo=docker)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)

---

## 📸 Application preview

<!-- Replace this image with a real screenshot of the application -->

![Poseidon application](docs/images/poseidon-dashboard.png)

---

## ✨ Features

* 🔐 User and administrator authentication
* 👤 Role-based access
* 📋 View financial products
* ➕ Add financial products
* ✏️ Edit financial products
* 🗑️ Delete financial products
* 🗄️ MySQL database
* 🐳 Dockerized application

---

## 🛠️ Technologies

| Technology            | Usage                                   |
| --------------------- | --------------------------------------- |
| 🖵 **Thymeleaf**      | Modern server-side Java template engine |
| ☕ **Java 17**         | Application development                 |
| 🌱 **Spring Boot**    | Backend framework                       |
| 🗄️ **MySQL 8**        | Database                                |
| 📦 **Maven**          | Dependency management and build         |
| 🐳 **Docker**         | Application containerization            |
| 🔧 **Docker Compose** | Container orchestration                 |

---

## 🏗️ Architecture

The application follows a layered Spring architecture:

```text
┌─────────────────────────────┐
│           Web UI            │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│         Spring Boot         │
│                             │
│       Controllers           │
│            ↓                │
│         Services            │
│            ↓                │
│       Repositories          │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│           MySQL             │
└─────────────────────────────┘
```

The application and its database are configured to run using **Docker Compose**.

---

## 🚀 Getting Started

### Prerequisites

To run the application, you only need:

* [Docker](https://www.docker.com/)
* Docker Compose

The following tools are optional and only required for local development:

* Java 17
* MySQL 8

---

### 1. Clone the repository

```bash
git clone git@github.com:Fercak-Florian/poseidon.git
cd poseidon
```

---

### 2. Start the application

From the project root, run:

```bash
docker compose up -d
```

Docker Compose will build and start the required containers.

To check that the containers are running:

```bash
docker compose ps
```

---

### 3. Access the application

Once the containers are running, open your browser and go to:

**http://localhost:8087**

---

## 🔑 Demo credentials

Demo credentials are available in:

```text
credentials.txt
```

The application can be tested using both:

* 👤 a standard user account
* 🛡️ an administrator account

---

## 🐳 Docker commands

### Check running containers

```bash
docker compose ps
```

### View application logs

```bash
docker compose logs -f
```

### Stop the application

```bash
docker compose down -v
```

### Rebuild and restart the application

```bash
docker compose up -d --build
```

---

## 📷 Screenshots

### 🔐 Login

<!-- Add my login screenshot here -->

![Login page](docs/images/login.png)

### 📋 Financial products

<!-- Add my financial products screenshot here -->

![Financial products](docs/images/financial-products.png)

### ✏️ Product management

<!-- Add my product management screenshot here -->

![Product management](docs/images/product-management.png)

---

## 📚 Skills demonstrated

This project allowed me to work with and strengthen my skills in:

* ☕ Java 17
* 🌱 Spring Boot
* 🌐 REST API development
* 🗄️ MySQL
* 💾 Database persistence
* 🔐 Authentication and authorization
* 📦 Maven
* 🐳 Docker
* 🔧 Docker Compose
* ⚙️ Spring configuration and profiles
* 🧪 Application testing
* 🔀 Git / GitHub

---

## 🎯 Project objectives

The main objectives of this project were to:

* Develop a web application using **Java and Spring Boot**
* Implement CRUD operations for financial products
* Persist application data in a **MySQL database**
* Implement authentication and role-based authorization
* Configure the application for different environments
* Containerize the application using **Docker**
* Orchestrate the application and database using **Docker Compose**

---

## 👨‍💻 About the project

**Poseidon** is a Java/Spring project that demonstrates my progression towards **full-stack development**.

It allowed me to put into practice concepts such as backend development, REST APIs, database persistence, authentication, testing and containerization.

The project is also an opportunity to apply development practices used in professional environments, including **Git, Maven, Spring profiles and Docker**.

---

## 📄 License

This project is intended for educational and portfolio purposes.

