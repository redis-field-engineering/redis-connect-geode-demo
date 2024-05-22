# Redis Connect Gemfire Trades Demo

This example streams live trading data from [finnhub.io](https://finnhub.io/) to Geode
to help illustrate how to stream results from Geode to Redis via [Redis Connect](https://github.com/redis-field-engineering/redis-connect-dist)

## Setup Redis Enterprise:

```bash
./setup_re.sh
```

## Run Geode

To run Geode, run:

```bash
./setup_geode.sh
```

## Build the App

You can build the app with:
```bash
mvn clean package install
```

## Run the App

To run the app you'll need an API token from [finnhub.io](https://finnhub.io/), then just run:

```bash
token=<FINNHUB_TOKEN> mvn exec:java
```

## Run Redis Connect:

To Run Redis Connect run:

```bash
./setup_rc.sh
```

## Configure Job In Redis Connect

To configure the job in redis connect, run the following _curl_ command:

```bash
curl -v -X POST "http://localhost:8282/connect/api/v1/job/config/cdc-job" -H "accept: */*" -H "Content-Type: multipart/form-data" -F "file=@redis-connect-payloads/trades-job.json;type=application/json"
```

## Kick off Initial Load Job

To kick off the initial load job, use this _curl_ command

```bash
curl -X POST "http://localhost:8282/connect/api/v1/job/transition/start/cdc-job/load" -H "accept: */*"
```

## Stream Data from Gemfire to Redis

To Stream the data from gemfire to Redis, use this _curl_command:

```bash
curl -X POST "http://localhost:8282/connect/api/v1/job/transition/start/cdc-job/stream” -H "accept: */*"
```