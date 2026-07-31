# Helidon Quickstart SE

Sample Helidon SE project with REST operations, upgraded to Helidon 4.5.1 and Java 25.

## Build and run

With JDK 25+:

```bash
mvn package
java -jar target/hello-helidon-se.jar
```

## Exercise the application

```bash
curl -X GET http://localhost:8080/hello
Hello (1)

curl -X GET http://localhost:8080/naptime
```

## Try health and metrics

Helidon 4 exposes observability endpoints under `/observe`:

```bash
curl -sv -X GET http://localhost:8080/observe/health

# Prometheus Format
curl -s -X GET http://localhost:8080/observe/metrics

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/observe/metrics
```

## Build the Docker Image

```bash
docker build -t hello-helidon-se .
```

## Start the application with Docker

```bash
docker run --rm -p 8080:8080 hello-helidon-se:latest
```

## Deploy the application to Kubernetes

```bash
kubectl cluster-info
kubectl get pods
kubectl create -f app.yaml
kubectl get pods
kubectl get service hello-helidon-se
```

After you’re done, cleanup:

```bash
kubectl delete -f app.yaml
```

## Build a native image with GraalVM

### Local build

```bash
export GRAALVM_HOME=/path/to/graalvm
mvn package -Pnative-image
./target/hello-helidon-se
```

### Multi-stage Docker build

```bash
docker build -t hello-helidon-se-native -f Dockerfile.native .
docker run --rm -p 8080:8080 hello-helidon-se-native:latest
```

## Build a Java Runtime Image using jlink

### Local build

```bash
mvn package -Pjlink-image
./target/hello-helidon-se-jri/bin/start
```

### Multi-stage Docker build

```bash
docker build -t hello-helidon-se-jri -f Dockerfile.jlink .
docker run --rm -p 8080:8080 hello-helidon-se-jri:latest
```
