
# Monitor Your Java Apps with Spring Boot Actuator


## How to launch the sample application

```
$ git clone https://github.com/indiepopart/custom-httptrace
$ OKTA_OAUTH2_ISSUER=<issuer> OKTA_OAUTH2_CLIENT_ID=<client_id> OKTA_OAUTH2_CLIENT_SECRET=<secret> ./mvnw spring-boot:run
```

Go to [/hello/greeting](http://localhost:8080/hello/greeting "demo application").

## How to see the http trace

After the login, go to [/actuator/httptrace](http://localhost:8080/actuator/httptrace "actuator link").

