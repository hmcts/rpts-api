# rpts-api

[![Build Status](https://travis-ci.org/hmcts/rpts-api.svg?branch=master)](https://travis-ci.org/hmcts/rpts-api)

## Table of Contents

* [Notes](#notes)
* [Building and deploying the application](#building-and-deploying-the-application)
  * [Building the application](#building-the-application)
  * [Database set up](#database-set-up)
    * [Locally](#locally)
    * [AAT and upwards](#aat-and-upwards)
  * [Running the application](#running-the-application)
  * [Alternative script to run application](#alternative-script-to-run-application)
* [Other](#other)
  * [Endpoints](#endpoints)
  * [NSPL Data - National Statistics Postcode Lookup](#nspl-data---national-statistics-postcode-lookup)
  * [OS Data - Ordnance Survey](#os-data---ordnance-survey)
* [License](#license)

## Notes


Since Spring Boot 2.1 bean overriding is disabled. If you want to enable it you will need to set `spring.main.allow-bean-definition-overriding` to `true`.

JUnit 5 is now enabled by default in the project. Please refrain from using JUnit4 and use the next generation

## Building and deploying the application

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew build
```

### Database set up

#### Locally

1) Make sure the application is started and that the nspl and nspl_history tables are present
2) Navigate to the setup folder and run

`bash insert_data.sh`

#### AAT and upwards

Get access to non prod and/or prod bastions

Download the CSV files

Run the copy command listed in the `setup/insert_data.sh` file

### Running the application

Create the image of the application by executing the following command:

```bash
  ./gradlew assemble
```

Create docker image:

```bash
  docker-compose build
```

Run the distribution (created in `build/install/rpts-api` directory)
by executing the following command:

```bash
  docker-compose up
```

This will start the API container exposing the application's port
(set to `4000` in this template app).

In order to test if the application is up, you can call its health endpoint:

```bash
  curl http://localhost:4000/health
```

You should get a response similar to this:

```
  {"status":"UP","diskSpace":{"status":"UP","total":249644974080,"free":137188298752,"threshold":10485760}}
```

### Alternative script to run application

To skip all the setting up and building, just execute the following command:

```bash
./bin/run-in-docker.sh
```

For more information:

```bash
./bin/run-in-docker.sh -h
```

Script includes bare minimum environment variables necessary to start api instance. Whenever any variable is changed or any other script regarding docker image/container build, the suggested way to ensure all is cleaned up properly is by this command:

```bash
docker-compose rm
```

It clears stopped containers correctly. Might consider removing clutter of images too, especially the ones fiddled with:

```bash
docker images

docker image rm <image-id>
```

There is no need to remove postgres and java or similar core images.

## Other

Hystrix offers much more than Circuit Breaker pattern implementation or command monitoring.
Here are some other functionalities it provides:
 * [Separate, per-dependency thread pools](https://github.com/Netflix/Hystrix/wiki/How-it-Works#isolation)
 * [Semaphores](https://github.com/Netflix/Hystrix/wiki/How-it-Works#semaphores), which you can use to limit
 the number of concurrent calls to any given dependency
 * [Request caching](https://github.com/Netflix/Hystrix/wiki/How-it-Works#request-caching), allowing
 different code paths to execute Hystrix Commands without worrying about duplicating work

### Endpoints

Returns a NsplAddress entity which contains address lines and local authority information.
```
GET /v1/search/<postcode>
```

### NSPL Data - National Statistics Postcode Lookup

The application uses the NSPL data to provide address information based on the postcode.
For more information about the latest NSPL data, search and download from [here](https://geoportal.statistics.gov.uk/search?sort=Date%20Updated%7Cmodified%7Cdesc).

Example: National Statistics Postcode Lookup - 2021 Census (May 2024) for the UK


### OS Data - Ordnance Survey

The application uses OS Places API for postcode searching to make sure we're getting the most up to date data.
See [here](https://www.ordnancesurvey.co.uk/products/os-places-api#overview) for more information.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

