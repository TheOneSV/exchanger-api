# exchanger-api
Simple foreign exchange application

The application uses Basic Authentication

Default user:
user: Admin
pass: admin

Every user has a role and an Account entity. Currently there is one test user named Admin with role ADMIN.
The Account entity holds the user transactions, a user without an account can not do any transactions.
This can be extended in future so that the user can have multiple accounts.

By default there are 24 test transactions in the H2(in-memory) database for date (12-01-2020).
You can look at the migration sql scripts which are located in the resources/db/migration folder.

Swagger2 API Address: http://localhost:8080/swagger-ui.html

HATEOAS is used for pagination and self transaction links for the /api/conversion/v1/transactions endpoint.

Use the maven "mvn clean install package" command to package the application.
