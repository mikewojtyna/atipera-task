# Github client

This application allows you to get public repositories of any GitHub user. The only way to interact with it is via the REST API.

## REST API

Take a look at `RepositoriesRestApiTest` to see how to use the REST API.

## Building

Execute `./mvnw spring-boot:build-image` to create the docker image. By default, the image will be named `docker.io/library/atipera-task:0.0.1-SNAPSHOT`.

It's also possible to build the native GraalVM image. There are some limitations, though. Check out the following [guide](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image.developing-your-first-application.buildpacks) to find out more.

## Running

To execute the application simply run the Docker image `docker run -it -p8080:8080 docker.io/library/atipera-task:0.0.1-SNAPSHOT`