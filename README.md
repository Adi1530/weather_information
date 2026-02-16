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


CREATE TABLE pincode_location (
    id BIGSERIAL PRIMARY KEY,
    pincode VARCHAR(10) NOT NULL UNIQUE,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE weather_info (
    id BIGSERIAL PRIMARY KEY,
    pincode_id BIGINT REFERENCES pincode_location(id),
    weather_date DATE NOT NULL,
    temperature DOUBLE PRECISION,
    humidity INTEGER,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (pincode_id, weather_date)
);
```
---

## cURL Commands
```sql
    curl -X GET "http://localhost:8080/api/weather?pincode=411014&for_date=2020-10-15"

    curl -i "http://localhost:8080/api/weather?pincode=411014&for_date=2020-10-15"
    
    curl "https://api.openweathermap.org/data/2.5/weather?lat=18.5204&lon=73.8567&appid=YOUR_API_KEY&units=metric"

    
    Response:
                {
          "temperature": 29.5,
          "humidity": 64,
          "description": "clear sky"
                }
        
        


```
