Module server

## Description

This module contains the server part of the phone call monitor feature. It is a simple Ktor REST
server that listens for incoming requests from clients and responses with proper data. The server
uses HTTP as the communication protocol and JSON as the data format.

## Endpoints

### GET /

Responds with information about the server start time and services that it provides.

```json
{
  "start": "2023-09-01T11:30:00.123123Z",
  "services": [
    {
      "name": "status",
      "uri": "http://192.168.0.100:8080/status"
    },
    {
      "name": "log",
      "uri": "http://192.168.0.45:8080/log"
    }
  ]
}
```

### GET /status

Responds with information about the current call status. If there is no ongoing call, it responds
with the HTTP 404 status code.

```json
{
  "ongoing": "true",
  "number": "123123123",
  "name": "John Smith"
}
```

### GET /log

Responds with the phone call log. If there are no calls in the log, it responds with an empty array.

```json
[
  {
    "beginning": "2023-09-02T10:00:00.123123Z",
    "duration": 9,
    "number": "123123123",
    "name": "John Smith",
    "timesQueried": 3
  },
  {
    "beginning": "2023-09-02T11:00:00.123123Z",
    "duration": 150,
    "number": "123456789",
    "name": null,
    "timesQueried": 0
  },
  {
    "beginning": "2023-09-02T11:30:00.123123Z",
    "duration": 10,
    "number": "123123123",
    "name": "John Smith",
    "timesQueried": 5
  }
]
```