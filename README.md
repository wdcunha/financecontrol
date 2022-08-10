# Finance Control

This project has the goal to create a finance control with Java in the back-end and will use Angular in the front-end.

It was created with VS Code with cmd + shift + p and create maven project. In the dependencies part it was chosen Spring Web, Lombok, Jpa, Spring Boot DevTools (live/hot reload just in run time).

The error that happened when executing the commands to populate database within main file application:

``` ERROR 97005 --- [  restartedMain] o.h.engine.jdbc.spi.SqlExceptionHelper   : Field 'type' doesn't have a default value ```

The solution was found in the [post from alura](https://cursos.alura.com.br/forum/topico-solucao-do-erro-field-id-doesn-t-have-a-default-value-137593) that was simple, just to drop and create the database again, but putting in the application.properties in the line of ddl-auto the value create is enough to solve this problem.

Problem with ManyToMany was caused by cascade inside parameters, because when it creates the product, there's no purchase (or sale) in the CommandLineRunner  and it throws a exception for null value related to mapping, so the solution was to remove cascade and give it without nothing concerning to any type. About mapping it was followed the tutorial [jpa many to many](https://www.bezkoder.com/jpa-many-to-many/).

In order to have quantity and payment method for purchase and sales, it was necessary to use OneToMany for each model as explained in the [tutorial with extra column](https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/), but associating the solution used in the [project in github](https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-angular), explained in the tutorial from baeldung for [spring angular ecommerce].

Error 73909: AnnotationException: mappedBy reference an unknown target entity property

HttpMessageNotWritableException: Could not write JSON: Infinite recursion. It was solved after many many and exaustive tryings by the [solution 2 of the page](https://localcoder.org/could-not-write-json-infinite-recursion-stackoverflowerror-nested-exception). The complexity here was because it is a embeddable class, so it was necessary to apply solution in purchase, purchase_product and product, so at the end finally the endpoint retrieved data correctly. @JsonManagedReference and @JsonBackReference was used but a little detail was @JsonIgnore just in the Product model at getPurchaseProduct, @JsonBackReference in the PurchaseProduct at getPurchase, @JsonBackReference at getProduct in the PurchaseProductPk because it duplicated product when showing data, also to getPurchase because recursive it was big. This was the puzzle solution after so much suffering on that.

About Total Price of the Purchase-Product, it is not a field, but a getter that calculates quantity times to price, so initially I put it as product price times to quantity of the purchase-product, so the total came wrong, then it's important to have this in mind that price if from pp, not from product.

But the Total Price of Purchases was necessary to remove the field and add a getter to retrieve each total price from purchase-product and calculate with a method reference [using stream and mapToDouble](https://www.baeldung.com/java-stream-sum).

Another challange is to control installments independently, so it is necessary to create each one and then save to database in business payment table. When  getting data from business, this information will need to be calculated distinctly.

BusinessProduct API had a problem that was first to deserialize data from frontend because the form value sent as object and the controller waits for array, that's why an array variable was createad within onSubmit method to be sent as parameter to the post service. So it's important to notice that FormArray deliever fields differently, then be aware about the content type is essencial to not have error in the backend that has not much evidence of what is causing it: ```Cannot deserialize value of type `java.util.ArrayList<com.gswf.financecontrol.model.BusinessProduct>` from Object value (token `JsonToken.START_OBJECT`)```, and I found in a post a comment that saved much time of headaches (Error ao usar o post no postman)[https://www.guj.com.br/t/error-ao-usar-o-post-no-postman/422933].

### SQL

```delete from business_payment where payment_id=2 and business_id=1 and installment=5;```

```insert into business_payment (payment_id, business_id, installment, payed, pay_date, amount) values (2, 1, 5, 0x00, '2022-07-20', 930.85);```

### TOMCAT PROBLEM

Port 8080 already in use is an error that happens sometimes and the steps to solve are, accordingly to (article about it)[https://springhow.com/web-server-failed-to-start-port-8080-was-already-in-use/]:

1) take the process id (PID): ```lsof -n -i :8080 | grep LISTEN```
2) kill it: ```kill -15```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

