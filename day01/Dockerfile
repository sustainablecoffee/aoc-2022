FROM maven:3.8.6-openjdk-18
WORKDIR /src
COPY ./ .
RUN mvn clean install
# COPY ../target/solution-1.0-SNAPSHOT.jar /solution.jar
COPY input.txt .
CMD java -jar target/solution-1.0-SNAPSHOT.jar