
- Open terminal, change directory to .../food-order-app/docs and execute following command.
  (Docker Desktop must be installed previously). 
  This will run all the containers(postgresql, mysql, mongo, elasticsearch, zipkin etc.)

docker-compose up -d

- Start FoaConfigServerApplication(under foa-config-server project)
- Start EurekaServerApplication(under foa-eureka-server project)

- Open adminer interface from http://localhost:9080/  
Login with System:PostgreSQL,host:db, user:postgres, password:admin and create database called foa-order 
Do the same for MySQL and create a database called foa-customer.(You can also use another Mysql client like Mysql Workbench)
  
- Start OrderServiceApplication, RestaurantServiceApplication, CustomerServiceApplication(the order is not important)

