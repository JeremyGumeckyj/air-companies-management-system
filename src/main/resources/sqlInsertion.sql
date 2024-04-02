-- Insert data into air_company table
INSERT INTO air_company (id, name, company_type, founded_at)
VALUES
    ('fd4b014b-6753-45f2-b09d-3c35eae4adfb', 'Airline A', 'Public', '2000-01-01'),
    ('38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', 'Airline B', 'Private', '1995-05-15'),
    ('5e67a6bf-7242-4e62-baf2-3c7013e6decb', 'Airline C', 'Public', '1980-12-10'),
    ('6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', 'Airline D', 'Private', '2005-07-20'),
    ('97d86806-d74a-4fd4-8a09-42f12a897b81', 'Airline E', 'Public', '1998-03-28');

-- Insert data into airplane table
INSERT INTO airplane (id, name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES
    ('f8934db2-3830-4e26-bafe-ef2eb1a8e869', 'Boeing 747', 'B747-001', 'fd4b014b-6753-45f2-b09d-3c35eae4adfb', 100, 10000.0, 10000, 'Passenger', '2020-01-01'),
    ('2a20ef46-1b6e-4d29-9fc8-cda27d44e94b', 'Airbus A320', 'A320-001', '38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', 120, 8000.0, 8000, 'Cargo', '2019-05-15'),
    ('e8a14721-4c7a-483d-b69e-b6475ef347db', 'Boeing 777', 'B777-001', '5e67a6bf-7242-4e62-baf2-3c7013e6decb', 80, 12000.0, 12000, 'Passenger', '2018-12-10'),
    ('8425c23a-6dd7-4ae0-8497-8e7315f4d60c', 'Airbus A380', 'A380-001', '6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', 90, 15000.0, 15000, 'Cargo', '2017-07-20'),
    ('5d8d98b1-8ac3-48a6-a3ab-314a59aa3fd1', 'Boeing 737', 'B737-001', '97d86806-d74a-4fd4-8a09-42f12a897b81', 110, 9000.0, 9000, 'Passenger', '2016-03-28'),
    ('9164893d-61f4-48c4-9e17-96a2b7d32b44', 'Airbus A330', 'A330-001', 'fd4b014b-6753-45f2-b09d-3c35eae4adfb', 105, 10000.0, 10000, 'Cargo', '2015-01-01'),
    ('1a79a4b8-3cf7-46e8-8d05-0e5ecff99d2f', 'Boeing 767', 'B767-001', '38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', 95, 8000.0, 8000, 'Passenger', '2014-05-15'),
    ('bf4ef5e2-9d9f-448f-9e2d-ec55b43af5f2', 'Airbus A350', 'A350-001', '5e67a6bf-7242-4e62-baf2-3c7013e6decb', 85, 12000.0, 12000, 'Cargo', '2013-12-10'),
    ('e8d6f0a2-6ff0-4643-9186-8f5d7a6c49ee', 'Boeing 787', 'B787-001', '6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', 70, 15000.0, 15000, 'Passenger', '2012-07-20'),
    ('c7d7a83d-d21f-4f48-89b7-40c64cb99da2', 'Airbus A310', 'A310-001', '97d86806-d74a-4fd4-8a09-42f12a897b81', 115, 9000.0, 9000, 'Cargo', '2011-03-28');

-- Insert data into flight table
INSERT INTO flight (id, flight_status, air_company_id, airplane_id, departure_country, destination_country, distance, estimated_flight_time, started_at, ended_at, delay_started_at, created_at)
VALUES
    ('a3138b0c-3f30-40d5-bc26-0c9b37bb16a4', 'PENDING', 'fd4b014b-6753-45f2-b09d-3c35eae4adfb', 'f8934db2-3830-4e26-bafe-ef2eb1a8e869', 'USA', 'UK', 5000.0, 300, NULL, NULL, NULL, '2023-01-01 08:00:00'),
    ('bfe9989c-1b27-4d12-bda0-97eeb994fb18', 'PENDING', '38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', '2a20ef46-1b6e-4d29-9fc8-cda27d44e94b', 'UK', 'France', 3000.0, 200, NULL, NULL, NULL, '2023-01-02 10:00:00'),
    ('b0dd0f91-0126-4671-8537-b7ab7424c025', 'PENDING', '5e67a6bf-7242-4e62-baf2-3c7013e6decb', 'e8a14721-4c7a-483d-b69e-b6475ef347db', 'France', 'Germany', 2000.0, 150, NULL, NULL, NULL, '2023-01-03 12:00:00'),
    ('ca236d8c-b671-438b-9889-9cf197e05cb3', 'PENDING', '6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', '8425c23a-6dd7-4ae0-8497-8e7315f4d60c', 'Germany', 'Italy', 1000.0, 100, NULL, NULL, NULL, '2023-01-04 14:00:00'),
    ('eca5be16-46d0-44e5-b5a3-2b8100fe83bc', 'PENDING', '97d86806-d74a-4fd4-8a09-42f12a897b81', '5d8d98b1-8ac3-48a6-a3ab-314a59aa3fd1', 'Italy', 'Spain', 2500.0, 180, NULL, NULL, NULL, '2023-01-05 16:00:00'),
    ('fd84d221-9c5e-4e1d-91e5-cf2b2bb2a17d', 'PENDING', 'fd4b014b-6753-45f2-b09d-3c35eae4adfb', '9164893d-61f4-48c4-9e17-96a2b7d32b44', 'Spain', 'Portugal', 800.0, 90, NULL, NULL, NULL, '2023-01-06 18:00:00'),
    ('08bfa810-19a3-4429-bb41-166f499465d1', 'PENDING', '38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', '1a79a4b8-3cf7-46e8-8d05-0e5ecff99d2f', 'Portugal', 'Netherlands', 1200.0, 120, NULL, NULL, NULL, '2023-01-07 20:00:00'),
    ('78625b11-03e2-4a4f-89e8-d7e9dfb3a0a5', 'PENDING', '5e67a6bf-7242-4e62-baf2-3c7013e6decb', 'bf4ef5e2-9d9f-448f-9e2d-ec55b43af5f2', 'Netherlands', 'Switzerland', 700.0, 100, NULL, NULL, NULL, '2023-01-08 22:00:00'),
    ('6cd9e7e4-e7b4-46e4-9b5c-800fa7e0f4de', 'PENDING', '6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', 'e8d6f0a2-6ff0-4643-9186-8f5d7a6c49ee', 'Switzerland', 'Austria', 600.0, 95, NULL, NULL, NULL, '2023-01-09 09:00:00'),
    ('efca4cd1-e4b9-4b3b-ae18-9f6f6783efad', 'PENDING', '97d86806-d74a-4fd4-8a09-42f12a897b81', 'c7d7a83d-d21f-4f48-89b7-40c64cb99da2', 'Austria', 'Belgium', 1100.0, 110, NULL, NULL, NULL, '2023-01-10 11:00:00'),
    ('6a2f76f5-7ff9-44e5-b1a7-2f11aa47de16', 'PENDING', 'fd4b014b-6753-45f2-b09d-3c35eae4adfb', 'f8934db2-3830-4e26-bafe-ef2eb1a8e869', 'Belgium', 'Denmark', 1500.0, 140, NULL, NULL, NULL, '2023-01-11 13:00:00'),
    ('073b71c1-c157-47ac-8a05-57379dc6a667', 'PENDING', '38fc4ae3-8ab0-4b5b-97a1-9e91b3c30f5a', '2a20ef46-1b6e-4d29-9fc8-cda27d44e94b', 'Denmark', 'Sweden', 1700.0, 150, NULL, NULL, NULL, '2023-01-12 15:00:00'),
    ('c22d5931-fcb1-4149-8364-fdd0d5010aa7', 'PENDING', '5e67a6bf-7242-4e62-baf2-3c7013e6decb', 'e8a14721-4c7a-483d-b69e-b6475ef347db', 'Sweden', 'Norway', 1900.0, 160, NULL, NULL, NULL, '2023-01-13 17:00:00'),
    ('7b434828-13d4-4b45-b68d-cfc5a155cb3d', 'COMPLETED', '6d14fbf0-d6ff-42d1-b4cb-8ee53e277a1e', '8425c23a-6dd7-4ae0-8497-8e7315f4d60c', 'Norway', 'Finland', 2100.0, 120, '2023-03-22 00:00:00', '2023-03-22 06:00:00', NULL, '2023-01-14 19:00:00'),
    ('c15201a9-ba5a-42a3-9616-142c327124eb', 'ACTIVE', '97d86806-d74a-4fd4-8a09-42f12a897b81', '5d8d98b1-8ac3-48a6-a3ab-314a59aa3fd1', 'Finland', 'Iceland', 2300.0, 180, '2024-03-31 00:00:00', NULL, NULL, '2024-03-29 10:00:00');