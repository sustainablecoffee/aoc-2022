FROM maven:3.8.6-openjdk-18
WORKDIR /src
COPY ./ .
RUN mvn clean install
COPY input.txt .
CMD java -jar target/day04-1.0-SNAPSHOT.jar