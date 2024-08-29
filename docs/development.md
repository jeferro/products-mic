## Development



## Requirements

We need the next tools to develop application:

* [ASDF](https://asdf-vm.com/):

* Docker compose

Then, we install tools:

~~~bash
asdf plugin-add java
asdf plugin-add gradle
asdf install
~~~~

And compile project:

~~~bash
cd code
gradle build test
~~~~



## Run locally

We need start docker containers:

~~~bash
docker compose up -d
~~~

After that, Kafka UI is running locally: http://localhost:9021/

And then run application:

~~~bash
gradle bootRun
~~~