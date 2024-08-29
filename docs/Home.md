# Products Microservice

Aplicación para la gestión de productos y sus valoraciones.

Existen 2 perfiles en el usuario, administradores y usuarios. Los administradores son responsable de la gestión de los productos. Los usuarios pueden consultar la información de un prooducto y valorarlos.

La información del proyecto la dividimos en variass sec

## Desarrollo

## Instalación en local

Los requisitos para el desarrollo del sistema son:
* Docker y docker-compose
* [ASDF](https://asdf-vm.com/)

Tras clonar el repositorio, se deben instalar en local las herramientas necesarias mediante ASDF:

```bash
asdf plugin add gradle
asdf plugin add java
asdf install
```

Y finalmente compilarlo:

```bash
cd code
gradle clean build
```


## Ejecución en local

Para ejecutar el proyecto, primero debemos iniciar los contenedores Docker necesarios:

```bash
cd docker
docker-compose up -d
```

Y ya podemos ejecutar la aplicación:

```bash
cd code
gradle bootRun
```