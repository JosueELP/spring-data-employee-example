# spring-data-employee-example

This is a spring boot application that uses the following dependencies:

- spring data jpa
- flywaydb
- postgresql
- modelmapper
- lombok

To create a basic API to retrieve data from employees and their corresponding payrolls.

### How to run
Create first the database, I strongly recommend using the Postgresql docker image.

Second, in the application.properties file change the corresponding database values with yours.
Don't worry about tables, flyway is going to run the migrations once the application runs.

After that, run this to install dependencies:

note: if you have maven installed locally, you can change ./mvnw for mvn

```
./mvnw install
```

and then

```
./mvnw spring-boot:run
```

to start the application