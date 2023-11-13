FROM eclipse-temurin:17-jdk

COPY . /usr/src/polydoctor
WORKDIR /usr/src/polydoctor

RUN <<EOF 
./gradlew build
EOF


FROM eclipse-temurin:17-jre

COPY --from=0 /usr/src/polydoctor/build/libs/covid-api-0.0.1-SNAPSHOT.jar /usr/src/polydoctor.jar
WORKDIR /usr/src/

ENTRYPOINT ["java", "-jar", "polydoctor.jar"]

EXPOSE 8080