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

### MySql issues
I had problem with the password I forgot and the solution was just found in [stackOverFlow page](https://stackoverflow.com/questions/6474775/setting-the-mysql-root-user-password-on-os-x) and between many sugestions just the part that allowed to enter without password that really worked was just this one, and it was perfect after many tryings and the command to set a new password was taken in a post at the end of the page:

$ /usr/local/opt/mysql/support-files/mysql.server stop
$ /usr/local/opt/mysql/support-files/mysql.server start --skip-grant-tables
mysql
mysql> use mysql;
UPDATE mysql.user SET authentication_string = '1234' WHERE User = 'root' AND Host = 'localhost';
SELECT user,authentication_string,plugin,host FROM mysql.user; [update password](https://stackoverflow.com/questions/41645309/mysql-error-access-denied-for-user-rootlocalhost)

The database was created in the [class 03](https://youtu.be/FHRYijYhJYA?list=PL8iIphQOyG-DHLpEx1TPItqJamy08fs1D). It is necessary to create the database manually before running Jboss that creates tables automatically. The commands used within the console opened by the ones above were:

create database eventsapp;
use eventsapp;
describe events; `(show fields from the table)`
select * from events;

Other mySql commands:
 mysql -u root -p  (used in terminal to access console with password)

There was a problem to [connect with the database](https://serverfault.com/questions/586651/mysql-refuses-to-accept-remote-connections) and a solution was to add bind-address = 0.0.0.0 to your [mysqld] section of your /usr/local/etc/my.cnf and restart mysqld. 

Utilizado [Materialize](https://materializecss.com/getting-started.html) pra formatar css no [vídeo 06](https://youtu.be/D97QpS7yJII?list=PL8iIphQOyG-DHLpEx1TPItqJamy08fs1D).

In the [video 9](https://serverfault.com/questions/586651/mysql-refuses-to-accept-remote-connections) was created guest table that has relationship with Event table and was utilized annotation OneToMany(Event) and ManyToOne(Guest). 

In the [video 10](https://youtu.be/b_6g4FHMm4s?list=PL8iIphQOyG-DHLpEx1TPItqJamy08fs1D) was created a route in controller to manage guests. The trick part here is that guest needs to be related to an event, so it is necessary to search by event first in the controller and after find the guest itself. A problem with the code used in the video happened and a guy posted a solution in the comments:

from the video:
```<a th:href="${(#mvc.url('EC#eventDetail').arg(0, event.cod)).build()}```
sugestion in a comment:
```a th:href="@{/{cod}(code=${event.cod})}```

Field validations was done in the [video 12](https://youtu.be/3YQK87FJArw?list=PL8iIphQOyG-DHLpEx1TPItqJamy08fs1D) and used 2 parameters for redirecting and showing message. To show messages is needed to create a fragment in the html page, but created a new file in templates.


### TOMCAT PROBLEM

Port 8080 already in use is an error that happens sometimes and the steps to solve are, accordingly to (article about it)[https://springhow.com/web-server-failed-to-start-port-8080-was-already-in-use/]:

1) take the process id (PID): ```lsof -n -i :8080 | grep LISTEN```
2) kill it: ```kill -15```

### POSTMAN

I had a problem related to testing authentication using postman, but the post in stackoverflow helped me to understand and to set the token: (getting-unauthorized-error-full-authentication)[https://stackoverflow.com/a/73299650/11697526]:

postman for testing auth jwt:

1.Select the Header tab

2.Click the button that says "hide auto-generated headers"

3.Type "Authorization" (without quotes) under the "Key" column.

4.Type "Bearer" under the "Value" column then paste the token. Ensure there is a space between "Bearer" and the "Token".

5.Send the request

*** Just one note about the sequence above is that which worked was using the tab Auth and seleting option Berear Token and pasting the token from signin.

### Authentication

It was followed the tutorial from the page (JWT Authentication example)[https://www.bezkoder.com/angular-10-spring-boot-jwt-auth/]. Some changes were needed in order to fix some errors that appeared when running the backend application. Changes applied were in the following files:

- AuthController: the value in the tutorial used was different (admin, mod) than the enum roles, so it happened within the switch in the signup endpoint, and changing it, the problem was solved;

- WebSecurityConfig: unauthorizedHandler and jwtUtils methods had error when building and the error message suggested to create a bean for them in the auth configuration, so it was done putting the annotation and return, then worked fine and smoothly

- There's no functionality to add roles to the database, so it has to be done directly in the database: 
```insert into roles(name) values ('ROLE_ADMIN');``` 

In the future it will demand one specific functionality to treat this need.

It is needed to pay attention about role when saving to database, because it is an array (Set<String>), so it needs be sent according to this data type or it will throw an error of deserealize.

### Heroku

Trying to test it online, I created app in Heroku and clearDb:

```@ref:cleardb-acute-85232 completed provisioning, setting CLEARDB_DATABASE_URL. by user wdcunha@gmail.com```

Commands:

heroku logs --app=fem-finance-control
heroku logs --app=fem-finance-control --tail
heroku apps
heroku info --app=fem-finance-control
heroku run java -version --app=fem-finance-control
heroku run -a fem-finance-control printenv
heroku config --app=fem-finance-control

Some content that helped to configure:

[Algaworks](https://github.com/algaworks/videoaula-spring-api-rest-heroku)
[Code Java](https://www.youtube.com/watch?v=dusvP0CFisw)
[Create clearDb](https://www.youtube.com/watch?v=-pb84-WnUHs)
[Feed clearDb](https://www.youtube.com/watch?v=TcadiISFM-4)


Cleardb (MySql DB) address detailed: 

mysql://xxxxxx:yyyyyy@us-cdbr-east-06.cleardb.net/heroku_zzzzzzzzzzzz?reconnect=true

User: xxxxxx
Pass: yyyyyy
Host: us-cdbr-east-06.cleardb.net
Bd: heroku_zzzzzzzzzzzz


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

