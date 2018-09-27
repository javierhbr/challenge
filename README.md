Javier Benavides 

javierhbr@gmail.com


Parent project:

* always from root folder
 
Build:
```
gradle clean build docker 
```
Run with docker-compose:

```
Start:
docker-compose up --build -d

Stop:
docker-compose stop
```

URL of services to test API :

```
Review Service:
- user : challenge
- password : code 
http://localhost:9091/swagger-ui.html

Product Service:
http://localhost:9091/swagger-ui.html

```
 
 
Run with SpringBoot:
 
```
/product-review-service $ gradle bootRun 

/product-service $ gradle bootRun

```
 
URL of gradle to test API :

```
Review Service:
- user : challenge
- password : code 
http://localhost:9091/swagger-ui.html

Product Service:
http://localhost:9091/swagger-ui.html

```



---
Security branch contain same modules plus:

- config-server     :   http://localhost:8888
- discovery-service :   http://localhost:8082
- gateway-service   :   http://localhost:8080

All of them working as a microservices environment run as springBoot project 
on each directory. 

URL of services to test API :

```
Review Service:
- user : challenge
- password : code 
http://localhost:9091/swagger-ui.html

Product Service:
http://localhost:9091/swagger-ui.html
 