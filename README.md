#### Connect to the H2 database:
Type http://localhost:8080/h2-console/ to your browser and insert the credentials that exist in `application.properties`

#### Get bookings:
`> curl "http://localhost:8080/bookings?roomId=2&date=2023-9-02"`

```
[{"id":2,"date":"2023-09-02","startAt":"11:00:00","endAt":"12:00:00","employee":{"name":"Lena Papagiannakou","email":"papagiannakou.el@gmail.com"},"room":{"id":2,"name":"1st Floor White Room"},"past":false},{"id":3,"date":"2023-09-02","startAt":"12:00:00","endAt":"13:00:00","employee":{"name":"Lulu Papadopoulou","email":"papadopoulou.l@gmail.com"},"room":{"id":2,"name":"1st Floor White Room"},"past":false},{"id":4,"date":"2023-09-02","startAt":"15:00:00","endAt":"16:00:00","employee":{"name":"Zisis Mpizelis","email":"mpizelis.z@gmail.com"},"room":{"id":2,"name":"1st Floor White Room"},"past":false}]
```

#### Create a new booking:
`> curl -d '{"employee":{"id":"1"}, "room":{"id":"5"}, "date":"2023-08-30", "startAt":"11:00:00", "endAt":"12:00:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/bookings`

`The booking for 2023-08-30 at 11:00:00 was completed!`

Trying to create overlapping booking:

```
> curl -d '{"employee":{"id":"1"}, "room":{"id":"1"}, "date":"2023-08-30", "startAt":"13:00:00", "endAt":"14:00:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/bookings`
```

`Booking is overlapping with another!`

Trying to create booking with past date:
```
> curl -d '{"employee":{"id":"1"}, "room":{"id":"1"}, "date":"2022-12-30", "startAt":"13:00:00", "endAt":"14:00:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/bookings
```

`{"errors":["must be a date in the present or in the future"]}`

Trying to create booking with duration which is not a multiple of an hour:
```
> curl -d '{"employee":{"id":"1"}, "room":{"id":"1"}, "date":"2023-12-30", "startAt":"13:00:00", "endAt":"13:30:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/bookings
```

`{"errors":["'The time slot is not valid.'"]}`

#### Get rooms (the dropdown list from which the user will select a room):
`> curl "http://localhost:8080/rooms`

```
[{"id":1,"name":"1st Floor White Room"},{"id":2,"name":"1st Floor White Room"},{"id":3,"name":"1st Floor Red Room"},{"id":4,"name":"1st Floor Black Room"},{"id":5,"name":"2nd Floor Green Room"},{"id":6,"name":"2nd Floor Orange Room"},{"id":7,"name":"3rd Floor Blue Room"}]
```

#### Get employees (the dropdown list from which the user will select an employee):
`> curl "http://localhost:8080/employees`

```
[{"id":1,"name":"Lena Papagiannakou","email":"papagiannakou.el@gmail.com"},{"id":2,"name":"Lulu Papadopoulou","email":"papadopoulou.l@gmail.com"},{"id":3,"name":"Zisis Mpizelis","email":"mpizelis.z@gmail.com"},{"id":4,"name":"Maria Zalismenou","email":"zalismenou.m@gmail.com"}]
```

#### Cancel a booking:
`> curl -X DELETE http://localhost:8080/bookings/1`

`The booking was cancelled.`


## What would I do with more time
- Write feature tests for the entirety of the code.
- Maybe create a cron job that removes all past bookings.
- Pagination to the booking result list.
