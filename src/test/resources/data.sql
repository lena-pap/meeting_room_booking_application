INSERT INTO rooms (name) VALUES ('1st Floor White Room');
INSERT INTO rooms (name) VALUES ('1st Floor White Room');
INSERT INTO rooms (name) VALUES ('1st Floor Red Room');
INSERT INTO rooms (name) VALUES ('1st Floor Black Room');
INSERT INTO rooms (name) VALUES ('2nd Floor Green Room');
INSERT INTO rooms (name) VALUES ('2nd Floor Orange Room');
INSERT INTO rooms (name) VALUES ('3rd Floor Blue Room');

INSERT INTO employees (name, email) VALUES ('Lena Papagiannakou', 'papagiannakou.el@gmail.com');
INSERT INTO employees (name, email) VALUES ('Lulu Papadopoulou', 'papadopoulou.l@gmail.com');
INSERT INTO employees (name, email) VALUES ('Zisis Mpizelis', 'mpizelis.z@gmail.com');
INSERT INTO employees (name, email) VALUES ('Maria Zalismenou', 'zalismenou.m@gmail.com');

INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (1, 7, '2023-8-31', '09:00:00', '10:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (1, 2, '2023-9-02', '11:00:00', '12:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (2, 2, '2023-9-02', '12:00:00', '13:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (3, 2, '2023-9-02', '15:00:00', '16:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (1, 3, '2023-9-02', '17:00:00', '18:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (4, 1, '2023-9-03', '15:00:00', '16:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (2, 6, '2023-9-05', '15:00:00', '18:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (3, 4, '2023-9-10', '11:00:00', '12:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (1, 1, '2023-9-12', '12:00:00', '13:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (1, 4, '2023-9-14', '17:00:00', '18:00:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (3, 1, '2023-9-25', '09:30:00', '10:30:00');
INSERT INTO bookings (employee_id, room_id, date, start_at, end_at) VALUES (2, 2, '2023-9-30', '11:15:00', '14:15:00');