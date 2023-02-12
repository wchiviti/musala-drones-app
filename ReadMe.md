
## How to Build

### You can run any of the following command to build the appalication

#### Assuming you have Maven installed on your computer

`cd drones`<br />
`mvn clean install`

#### You can utilize the mvn wrapper file. 

This option requires you to have `JAVA_HOME` environment variable set properly on your platform

`cd drones`<br />
`./mvnw clean install`<br />`


## How to Run

### You can run using the following commands

#### Using an executable jar produced by the spring boot plugin

`cd drones`<br />
`./app/target/drones-app.jar`<br />

#### You can also run it using the java command line tool

`cd drones`
`java -jar app/target/drones-app.jar`

The Application runs on Port `8080`

It uses `Basic Authentication` to secure the APIs. The default user is `drones` and the default password is `%$Drones123!@#`

## Running using docker

There is a docker file in the root directly which one can utilize to both build and run a docker image of the application.

`docker build -t drones-app .` <br />
`docker stop drones-app || true` <br />
`docker rm drones-app || true` <br />
`docker run -d --restart=always --name drones-app -p 8080:8080 drones-app` <br />

## The APIs in question

- registering a drone;

`HTTP POST to /v1/drones`

- loading a drone with medication items;

`HTTP POST to /v1/drones/{droneUuid}/load`

- checking loaded medication items for a given drone;

`HTTP GET to /v1/drones/{droneUuid}/medications`

- checking available drones for loading;

`HTTP GET to /v1/drones/{droneUuid}/available`

- check drone battery level for a given drone;

`HTTP GET to /v1/drones/{droneUuid}/battery-capacity`


### You access more details on the APIs from the swagger endpoint that has been added

`/swagger-ui/index.html`

### For any Queries and questions please create an issues on this repository.

[Create Issue Here](https://github.com/wchiviti/musala-drones-app/issues)