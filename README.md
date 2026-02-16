# Weather Info API

Spring Boot backend service that provides weather information for a given Indian pincode and date.  
The application integrates with the OpenWeather API and stores optimized results in PostgreSQL to avoid repeated external API calls.

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- PostgreSQL
- OpenWeather API
- Maven

---

## Project Overview

This API:

1. Accepts a pincode and date.
2. Checks if weather data already exists in the database.
3. If exists → returns cached data.
4. If not → calls OpenWeather API, stores result in DB, then returns response.

This ensures:
- Reduced external API calls
- Faster repeated responses
- Persistent storage of weather history

---

## Prerequisites

Make sure the following are installed:

- Java 17+
- Maven
- PostgreSQL
- OpenWeather API key (free tier works)

---

## Database Setup (PostgreSQL)

### 1. Create Database

```sql
CREATE DATABASE weather_db;
