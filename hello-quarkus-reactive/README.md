# build the application
`mvn -U clean package`

# run the application 
`java -jar target/quarkus-all/quarkus-run.jar`

# see endpoing `http://localhost:8080/hello`
# see long-running computation endpoint `http://localhost:8080/naptime`