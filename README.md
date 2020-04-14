# DataArt Practical Test
API-REST to create and search flight tickets.

## Used technologies
- Kotlin
- Spring Boot
- Spring Boot Web
- Spring Boot Data JPA
- H2 Memory database
- Junit
- MockK

## Build a Docker image and run it
1- Get a terminal on the project root directory and build the project
```shell
$ mvn package
```
2- Build the docker image
```shell
$ docker build -t flight-ticket-service .
```
3 - Run the image
```shell
$ docker run -d -p 8080:8080 -t flight-ticket-service
```

## Exposed endpoints
### Create flight ticket
Request:
```shell
curl -L -X POST 'http://localhost:8080/flight-tickets' \
-H 'Content-Type: application/json' \
--data-raw '{
	"departureDate": "2020-04-27T05:00:00.00-03:00",
	"arrivalDate": "2020-04-27T07:00:00.00-05:00",
	"cityOfOrigin": "Rosario",
	"destinationCity": "Lima",
	"passengerName": "Juan Pablo Petrozzi",
	"passengerAge": 27,
	"luggageStorage": false,
	"price": 750
}'
```

Response:
```json
{
    "id": 1,
    "departureDate": "2020-04-27T08:00:00Z",
    "arrivalDate": "2020-04-27T12:00:00Z",
    "cityOfOrigin": "Rosario",
    "destinationCity": "Lima",
    "passengerName": "Juan Pablo Petrozzi",
    "passengerAge": 27,
    "luggageStorage": false,
    "price": 750
}
```

### Get every existing flight tickets
Request:
```shell
curl -L -X GET 'http://localhost:8080/flight-tickets'
```

Response:
```json
[
    {
        "id": 1,
        "departureDate": "2020-04-27T08:00:00Z",
        "arrivalDate": "2020-04-27T12:00:00Z",
        "cityOfOrigin": "Rosario",
        "destinationCity": "Lima",
        "passengerName": "Juan Pablo Petrozzi",
        "passengerAge": 27,
        "luggageStorage": false,
        "price": 750.00
    }
]
```

### Get a particular flight ticket
Request:
```shell
curl -L -X GET 'http://localhost:8080/flight-tickets/1'
```

Response:
```json
{
    "id": 1,
    "departureDate": "2020-04-27T08:00:00Z",
    "arrivalDate": "2020-04-27T12:00:00Z",
    "cityOfOrigin": "Rosario",
    "destinationCity": "Lima",
    "passengerName": "Juan Pablo Petrozzi",
    "passengerAge": 27,
    "luggageStorage": false,
    "price": 750.00
}
```

When there is not a flight ticket with that ID.

Error response:
```json
{
    "timestamp": "2020-04-14T00:54:39.173+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Sorry, there is not a flight ticket with that ID.",
    "path": "/flight-tickets/123"
}
```
