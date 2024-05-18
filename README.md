# Products Microservice

Example project using Hexagonal Architecture and Domain Driven Desing.

The project manages products and reviews.


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
