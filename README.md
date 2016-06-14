##Spring Cloud with Axon Event Sourcing, CQRS and AngularJS Example

###Introduction  

This application is a Spring Cloud example of using axon event sourcing in microservices. The project is intended to demonstrate end-to-end practice for building a  microservice architecture that implement event sourcing and CQRS(Command Query Responsibility Segregation) using Spring Cloud and Axon Framework. It also demonstrates the usage of AngularJs SPA when building the Web UI server. 

The function of the application is simple: to do customer creation, customer list and query. It includes the following microservices:

- Edge Service that routes request to different microservices (Zuul)
- Discovery Service that discovers the microservices available and facilate Zuul for the routing (Eureka)
- Configuration Service that is used to provide center configuration
- Web UI Resource Server that contains the static resources like html, javascript and css, etc.
- Customer Command Service with Axon Event Sourcing, it would handle customer creation, updates and also log the latest information to DB2 which would be used for query.
- Customer Query Service that handles query of customer information.

Architecture Diagram  

![Architecture Diagram](https://raw.githubusercontent.com/sfav/axon-springboot-cloud/axon-sfav-webui/src/main/resources/static/image/sfav-architecture.jpg)  

This reference application uses the following data store, but they could be changed to other type with small changes.

- H2 - Customer Query  
- File - Axon Event Store

###How to Run  

1. Download [H2 databse](http://www.h2database.com/html/main.html) and run below command to start H2 database     
	
		java -jar h2-1.4.192.jar -webAllowOthers -tcpAllowOthers   
2. git clone this repository and go to each maven module and start those micro services using maven:  

		mvn spring-boot:run
Note,   
	- The configuration service need to be started first so that other services could get correct property.   
	- The configuration service will get properties from [Sfav Configuration Repository](https://github.com/sfav/axon-springboot-cloud-config).  
	- There will be warning message shown for configuration service due to not able to register with Eureka. 
3.  Visit link http://{your-computer-name}:8080/ui, it will show this introduction, click create to enter new customer, and then query the customer list and see details.   
 
![Architecture Diagram](https://raw.githubusercontent.com/sfav/axon-springboot-cloud/axon-sfav-webui/src/main/resources/static/image/sfav-home.jpg) 

![Architecture Diagram](https://raw.githubusercontent.com/sfav/axon-springboot-cloud/axon-sfav-webui/src/main/resources/static/image/sfav-create.jpg) 

![Architecture Diagram](https://raw.githubusercontent.com/sfav/axon-springboot-cloud/axon-sfav-webui/src/main/resources/static/image/sfav-list.jpg) 

![Architecture Diagram](https://raw.githubusercontent.com/sfav/axon-springboot-cloud/axon-sfav-webui/src/main/resources/static/image/sfav-query.jpg) 
  

###License

This project is licensed under Apache License 2.0.
