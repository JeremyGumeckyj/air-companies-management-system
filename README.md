# Air Companies Management System

## Description
The Air Companies Management System is a web-based application designed to facilitate the management of air companies, airplanes, and flights. It provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on air companies, airplanes, and flights, as well as additional functionalities for handling flights' statuses, retrieving specific flight details and etc. The system consists of three main components: Air Companies, Airplanes, and Flights. Each component has its own set of endpoints to perform various operations.

## Endpoints
### AirCompanyController
1. GET `/airCompanies`:
    - Description: Retrieve all air companies.
    - Method: GET

2. GET `/airCompanies/{id}`:
    - Description: Retrieve an air company by ID.
    - Method: GET
    - Path Variable: id (UUID)

3. POST `/airCompanies`:
    - Description: Create a new air company.
    - Method: POST
    - Request Body: AirCompany object

4. PUT `/airCompanies`:
    - Description: Update an existing air company.
    - Method: PUT
    - Request Body: AirCompany object

5. DELETE `/airCompanies/{id}`:
    - Description: Delete an air company by ID.
    - Method: DELETE
    - Path Variable: id (UUID)

6. GET `/airCompanies/{companyName}/flights/{status}`:
    - Description: Find flights by company name and status.
    - Method: GET
    - Path Variables: companyName (String), status (FlightStatus)

### AirplaneController
1. GET `/airplanes`:
    - Description: Retrieve all airplanes.
    - Method: GET

2. GET `/airplanes/{id}`:
    - Description: Retrieve an airplane by ID.
    - Method: GET
    - Path Variable: id (UUID)

3. POST `/airplanes`:
    - Description: Create a new airplane.
    - Method: POST
    - Request Body: Airplane object
    - Optional Request Param: airCompanyId (UUID)

4. PUT `/airplanes`:
    - Description: Update an existing airplane.
    - Method: PUT
    - Request Body: Airplane object

5. DELETE `/airplanes/{id}`:
    - Description: Delete an airplane by ID.
    - Method: DELETE
    - Path Variable: id (UUID)

6. POST `/airplanes/{airplaneId}/move/{newCompanyId}`:
    - Description: Move an airplane to another company.
    - Method: POST
    - Path Variables: airplaneId (UUID), newCompanyId (UUID)

### FlightController
1. GET `/flights`:
    - Description: Retrieve all flights.
    - Method: GET

2. GET `/flights/{id}`:
    - Description: Retrieve a flight by ID.
    - Method: GET
    - Path Variable: id (UUID)

3. POST `/flights`:
    - Description: Create a new flight.
    - Method: POST
    - Request Body: Flight object

4. PUT `/flights`:
    - Description: Update an existing flight.
    - Method: PUT
    - Request Body: Flight object

5. DELETE `/flights/{id}`:
    - Description: Delete a flight by ID.
    - Method: DELETE
    - Path Variable: id (UUID)

6. POST `/flights/{flightId}/status/{newStatus}`:
    - Description: Change the status of a flight.
    - Method: POST
    - Path Variables: flightId (UUID), newStatus (FlightStatus)

7. GET `/flights/active24`:
    - Description: Retrieve flights that have been active for more than 24 hours.
    - Method: GET

8. GET `/flights/completedWithDelay`:
    - Description: Retrieve completed flights with a delay.
    - Method: GET

### Developer Guide
To contribute to the development of the Air Companies Management System, follow these steps:

#### 1. Clone the Repository:


    git clone <repository-url>


#### 2. Set Up Environment:
- Ensure you have Java Development Kit (JDK) 16 installed.
- Ensure you have Docker.
- Ensure you have Postman.


#### 3. Build and Run:
- Build the application using Maven:


     mvn clean install


- Start the Docker containers:


     docker-compose up

#### 4. Navigate to the project directory using the command line interface


#### 5. Execute the SQL script using the following command:
    

    docker exec -i mysqldb mysql -u root -p1111 air-companies-management-system-db < ./src/main/resources/sqlInsertion.sql


#### 6. Open Postman and insert folder with queries, which you can find below:

### Postman Collection
You can find a Postman collection containing sample requests to test the endpoints of the Air Companies Management System [here](https://drive.google.com/drive/folders/1rnDntqDtu56ZDU8SwgHrA1vXo7XiOI8Q?usp=sharing).