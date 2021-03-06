# MyPrivatePocket-backend

## Development

### Backend
```$ gradle build```

```$ java -jar build/libs/coddlers-0.0.1-SNAPSHOT.jar```

Server listen at localhost:8080

### Frontend

```$ npm install```

```$ npm start``` 

Server listen at localhost:4200

### Database 

To run docker container with postgres:

```$ sudo ./docker_init.sh```

Postgres should be available at localhost:5432. Database is named ``mypp``.

### Documentation 

For documenting REST API Swagger is included. 
Documentation UI is available on 
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
For details how to make documentation in swagger please visit [https://github.com/swagger-api/swagger-core/wiki/annotations](https://github.com/swagger-api/swagger-core/wiki/annotations).
 
## Build Fat Jar
```$ gradle clean build```

```$ java -jar coddlers-0.0.1-SNAPSHOT.jar```

## Troubleshooting

* I want to use Windows for development and I cannot run sh scripts to run db script
    
    Just run once scripts inside "if" condition to download image and initialize container
    then use ```docker start <container-name>```. Container should be in up state after os restart. 
    If not write command to start container again.
   
* I have an errors connected with missing getters & setters in Intellij

    You should Install Lombok plugin. Then go to settings ```Annotation processors``` and enable ```annotation processing```.
    
* Intellij don't see repository beans

    You should enable ```Sping Data``` plugin in ```Plugins```.  
    
* I have a problem with node-sass package

    Try ```sudo npm rebuild node-sass```   