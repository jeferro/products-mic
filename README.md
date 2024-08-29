# Products Microservice

Aplicación para la gestión de productos y sus valoraciones. 

Existen 2 perfiles en el usuario, administradores y usuarios. Los administradores son responsable de la gestión de los productos. Los usuarios pueden consultar la información de un prooducto y valorarlos.

The project uses:

* Architecture:
  * Hexagonal Architecture
  * Domain Driven Design


* Patterns:
  * Command / Handler
  * Criteria


* Tools:
  * Docker & Docker Compose
  * Spring Boot
  * Java 21
  * Gradle
  * ASDF


* Testing:
  * Unit Testing
  * Social Testing
  * Approval Testing
  * Mutation Testing
  * Integration Testing (Test Container)


## Requirements

We need the next tools to develop application:

* [ASDF](https://asdf-vm.com/):

~~~bash
asdf plugin-add java
asdf plugin-add gradle
asdf install
~~~~

* Docker compose



## Run locally

We need start docker containers:

~~~bash
docker compose up -d
~~~

And then run application:
~~~bash
gradle bootRun
~~~
