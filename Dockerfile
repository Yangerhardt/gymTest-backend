#FROM ubuntu:18.04
#
## Instala o Java 8
#RUN apt-get update && \
#    apt-get install -y openjdk-8-jdk
#
## Instala o Maven
#RUN apt-get install -y maven
#
## Instala o PostgreSQL
#RUN apt-get install -y postgresql postgresql-contrib
#
## Define variáveis de ambiente
#ENV DB_NAME mydb
#ENV DB_USER teste
#ENV DB_PASSWORD 12345
#
## Cria um usuário e um banco de dados
#USER postgres
#RUN /etc/init.d/postgresql start && \
#    psql --command "CREATE USER $DB_USER WITH SUPERUSER PASSWORD '$DB_PASSWORD';" && \
#    createdb -O $DB_USER $DB_NAME
#
## Expõe a porta 8080
#EXPOSE 8080
#
## Configura o diretório de trabalho
#WORKDIR /app
#
## Copia o código fonte
#COPY . /app
#
## Compila o código
#RUN mvn package
#
## Inicia a aplicação
#CMD ["java", "-jar", "target/myapp.jar"]


FROM maven:3.8.4-jdk-8-slim AS build

WORKDIR /app

# Copia o arquivo pom.xml para o diretório atual
COPY pom.xml .

# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia o restante dos arquivos para o diretório atual
COPY src/ /app/src/

# Compila o projeto
RUN mvn package

# Configura a imagem de produção
FROM openjdk:8-jre-slim

# Define variáveis de ambiente para a configuração do Postgres
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_NAME=gymTest
ENV DB_USER=teste
ENV DB_PASSWORD=12345

# Instala o Postgres
RUN apt-get update \
    && apt-get install -y postgresql \
    && rm -rf /var/lib/apt/lists/*

# Cria um usuário para o Postgres
USER postgres
RUN /etc/init.d/postgresql start \
    && psql --command "CREATE USER $DB_USER WITH SUPERUSER PASSWORD '$DB_PASSWORD';" \
    && createdb -O $DB_USER $DB_NAME

# Configura a imagem para rodar a aplicação
USER root
WORKDIR /app
COPY --from=build /app/target/gymTest-*.jar /app/gymTest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/gymTest.jar"]
