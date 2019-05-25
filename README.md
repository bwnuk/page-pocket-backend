
[![Build Status](https://travis-ci.com/bwnuk/page-pocket-backend.svg?branch=master)](https://travis-ci.com/bwnuk/page-pocket-backend)[![Coverage Status](https://coveralls.io/repos/github/bwnuk/page-pocket-backend/badge.svg?branch=master)](https://coveralls.io/github/bwnuk/page-pocket-backend?branch=master)
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

## Installing
```
sudo apt install mongodb
sudo service mongodb start
mongo
```

## [Swagger UI](http://localhost:8013/swagger-ui.html)

