# Patch Request App

A modern, functional, and responsive Spring Boot 3 application designed to generate, manage, and configure Datafix Patch Numbers. 

## Key Features
- **Local & Production Profiles**: 
  - `local`: Embedded H2 database configured in Oracle compatibility mode.
  - `prod`: Configured for Oracle database connectivity.
- **Spring JDBC Template**: High-performance, low-overhead database operations strictly utilizing `JdbcTemplate` instead of JPA/Hibernate as requested.
- **Spring Security**:
  - Open generation and viewing of patch requests (`/patch-request-app/tasks/new`, `/patch-request-app/tasks`).
  - Protected modification, deletion, and system config pages requires authentication.
- **Modern UI**:
  - Full Vanilla CSS and HTML5 implementation without the need for CDNs.
  - Sidebar layout mapping core functionalities (Patch List, Generate Patch Number, Prepare Patch, Config).
  - Rich interactive Datagrid functionality powered by **AG Grid** allowing live searching, sorting, and CSV data export completely offline.

## Technologies Used
* **Java 17**
* **Spring Boot 3.2.x** (Web, Security, JDBC)
* **Thymeleaf**
* **H2 Database** (Oracle compatibility mode) 
* **AG Grid** (Local Library)

## How to Run

### Local Setup
You can run this application locally without installing external databases.
```sh
mvn spring-boot:run
```
The application will boot up at `http://localhost:8080/patch-request-app`.
- **Default Database**: H2 (In-Memory)
- **H2 Console**: Accessible at `/patch-request-app/h2-console`
- **Default Admin Account**: Username: `admin` | Password: `admin`

### Building for WebLogic 15c
To package the application as a deployable WAR file for Oracle WebLogic 15c:
```sh
mvn clean package -DskipTests
```
The standalone `.war` file will be generated in the `/target` directory.
