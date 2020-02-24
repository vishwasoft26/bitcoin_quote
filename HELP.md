# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.crypt_currency.my-btc' is invalid and this project uses 'com.crypt_currency.mybtc' instead.

* This is a snapshot development version 
* you can run this project either from IDE or 
* to build self contained:   mvn clean install to build my-btc-0.0.1-SNAPSHOT.jar 
* Launch application: java -jar my-btc-0.0.1-SNAPSHOT.jar


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring cache abstraction](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-caching)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)

* Checkout project and run my-btc from IDE
* or build from command prompt as
** to build self contained:   mvn clean install to build my-btc-0.0.1-SNAPSHOT.jar 
** Launch application: java -jar my-btc-0.0.1-SNAPSHOT.jar

* after server start, goto browser http://localhost:8080/
* type localhost:8080/currency/all to display all supported currency symbols
* type localhost:8080/currency/BTCUSD for specific symbol

