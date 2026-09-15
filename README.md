<div align="center">

# CampusMarket
### Campus marketplace backend built with Spring Boot

![Java](https://img.shields.io/badge/Java-11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.6-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)

</div>

## Overview

CampusMarket is an earlier Java web-development project for building a campus-oriented marketplace service. The repository contains a Maven-based **Spring Boot** application with authentication, web APIs, database integration, and API documentation support.

## Tech Stack

Based on the project configuration, the backend uses:

- Java 11
- Spring Boot 2.6.6
- Spring Web
- Spring Security
- Thymeleaf
- MySQL
- MyBatis-Plus
- Swagger / Springfox
- Lombok
- Spring Mail

## Repository Structure

```text
campusMarket/
├── pom.xml
├── src/
└── ...
```

## Getting Started

```bash
cd campusMarket
mvn clean package
mvn spring-boot:run
```

Before running, configure the local database and other environment-specific settings in the application configuration files under `src/`.

## Project Purpose

The project was created as a practical exercise in backend engineering, covering a typical web-service stack: user management, security, persistence, API design, and service-layer development.

## Notes

This repository is preserved as part of my earlier software-engineering portfolio. Some dependency versions and local configuration reflect the original development environment and may require updates on modern systems.

## Author

**Bo Liu**  
Contact: `liubo317@hnu.edu.cn`
