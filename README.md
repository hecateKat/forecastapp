# Forecast Application


**Application made for process of recruiting for 9bits**

Simple Java application that integrates with external API. 

Endpoint: localhost:8080/forecast?postcode={postcode}


---

## Api 

Application uses API from AccuWeather.

---

## Docker

**Requirements:**

-Maven

-Docker


**Instalation:**

mvn clean && mvn package

docker build -t forecastapp .

docker run -p 8080:8080 forecastapp

---

## Tests

To run integration tests please use:

mvn clear test