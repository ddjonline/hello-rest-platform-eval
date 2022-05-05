# build the application
`mvn -U clean package`

# run the application 
`java -jar target/hello-vertx4-kt-coroutines-1.0-SNAPSHOT.jar`

# see endpoing `http://localhost:8080/hello`
# see long-running computation endpoint `http://localhost:8080/naptime`