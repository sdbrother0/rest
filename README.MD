### Build
```
bash gradlew bootjar
docker rmi sdbrother/rest -f
docker build --platform=linux/amd64 --build-arg JAR_FILE=build/libs/rest-0.0.1-SNAPSHOT.jar -t sdbrother/rest .
```

### Run (docker compose)
```
docker-compose up -d
```

### Run (docker)
```
docker run -p 8080:8080 sdbrother/rest
```
