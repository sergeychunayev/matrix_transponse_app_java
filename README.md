### Java SpringBoot transpose matrix example.

Build executable jar
```shell
./gradlew bootJar
```

Run executable jar
```shell
java -jar app/build/libs/app.jar
```

Send request
```shell
curl -d '[[1,2,3],[4,5,6],[7,8,9]] ' -H 'Content-Type: application/json' localhost:8080/transpose
```

LICENCE

MIT