<!-- Introduction -->
<h3>library-managment-app</h3>

Simple application for managing a library database.

<!-- ABOUT -->
### About

This project is a continuation of a database project I did for my university course. My goal was to learn how to use Spring Data JPA and Hibernate. It is not finished yet, as I am in the process of implementing the REST API. 

So far, the database is fully mapped and I have created multiple queries. All the queries are tested using JUnit5. I have used Spring profiles to separate the test and development environments. 

### The database
The original database was designed using Oracle Data Modeler and was supposed to manage multiple libraries, that have a shared account and loan system, but separate book inventories. Below you can find the database schema. The database project is quite simple and might not be the best example of a database design, but nonetheless it was a good learning experience.

<!-- DB SCHEMA -->
#### Database Schema
Can be found in the repo [here](https://github.com/cassandra-stark/library-managment-app/blob/main/readme_files/Relational_1.png).

### Some more interesting queries
* `findByCreditsBookTitle(String title)` - returns authors credited for books with a given title
* `findByCreditsAuthorLastName(String lastName)` - returns books with authors with a given last name
* `findByAccountNumberAndLoanStatus(Long accountNumber, String loanStatus)` - returns books loaned by a given account with a given loan status
* and more CRUD operations such as find books by title, category, author, etc.

### Running the application
To run the application, you need to have Java and Maven installed. You can run the application using the command `mvn spring-boot:run`. The application will run on port 8080. You can access the H2 console at `localhost:8080/h2-console`. The database is populated with data using the `data.sql` file. The database is created in memory and is destroyed when the application is stopped.

For the application to run properly, set the active profile to `prod` in the `application.properties` file. The `test` profile is used for testing purposes.

<!-- STACK -->
### Built With

* [![Java][Java]][Java-url]
* [![Spring][Spring]][Spring-url]
* [![Spring Boot][Spring Boot]][Spring Boot-url]
* [![Spring Data JPA][Spring Data JPA]][Spring Data JPA-url]
* [![Hibernate][Hibernate]][Hibernate-url]
* [![JUnit5][JUnit5]][JUnit5-url]
* [![H2][H2]][H2-url]
* [![Maven][Maven]][Maven-url]

<!-- To-do -->
### To-do

- [ ] Implement the REST API and test it using Postman
- [ ] Optimize associations between entities
- [ ] Fully document the project

<!-- MARKDOWN LINKS & IMAGES -->
[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/en/
[Spring]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Spring Boot]: https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot
[Spring Boot-url]: https://spring.io/projects/spring-boot
[H2]: https://img.shields.io/badge/H2-004b85?style=for-the-badge&logo=h2&logoColor=white
[H2-url]: https://www.h2database.com/html/main.html
[Spring Data JPA]: https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring Data JPA-url]: https://spring.io/projects/spring-data-jpa
[Maven]: https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white
[Maven-url]: https://maven.apache.org/
[Hibernate]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white
[Hibernate-url]: https://hibernate.org/
[JUnit5]: https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white
[JUnit5-url]: https://junit.org/junit5/
