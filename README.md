# Page Pocket backend
Backend application to Page Pocket project.

## Documentation
Server is working at port `8013`

## Launching
Follow these instructions to execute application:
```
sudo service mongodb start
mongo
```
In the second terminal: 
```
cd ./page-pocket-backend
mvn clean install
mvn spring-boot:run
```

## Needed

- mongo 
```
sudo apt install mongodb
sudo service mongodb start
mongo
```

## Swagger
to see json config info :
```
http://localhost:8013/v2/api-docs
```

to enter swagger UI :
```
http://localhost:8013/swagger-ui.html
```
