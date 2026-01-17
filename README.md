# Employee-Management-System

Simple CRUD web application to manage employees.
No frontend is implemented use postman or any other tool for testing.

## Summary
Employee-Management-System is a Java web application that provides RESTful endpoints to create, read, update and delete employee records. Intended for learning and small internal use.

## Tech stack
- Java (JDK 17+)
- Spring MVC / Spring Framework
- Hibernate / JPA
- Maven
- Tomcat 

## Features
- Create, read, update and delete employees
- JSON request/response
- Basic validation and persistence via Hibernate

## Data model (Employee)
- `id` (Integer)
- `name` (String)
- `email` (String)
- `department` (String)

## Data model (Department)
- `id` (Integer)
- `name` (String)

## API
- `GET /employee/getAll` — list all employees  
- `GET /employee/get/{id}` — get employee by id  
- `POST /employee/add` — create employee (JSON body)  
- `PUT /employee/update` — update employee (JSON body)  
- `DELETE /employee/delete/{id}` — delete employee

Request/response content-type: `application/json`

## Run
1. Build: `mvn clean package`  
2. Deploy WAR to Tomcat
3. Open `http://localhost:8080/` and call the REST endpoints.

## Testing
- Use Postman / curl to exercise the API.
- Add unit/integration tests under `src/test/java`.

## Notes
- Configure supported media types to accept `application/json;charset=UTF-8` if necessary.

## License
No license yet
