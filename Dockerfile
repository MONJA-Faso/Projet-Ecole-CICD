FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY src/*.java .

RUN javac *.java

CMD ["java","-cp","target/classes","Main"]