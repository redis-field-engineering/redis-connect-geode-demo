# Trades 2 Geode

This example streams live trading data from [finnhub.io](https://finnhub.io/) to Geode
to help illustrate how to stream results from Geode to Redis via [Redis Connect](https://github.com/redis-field-engineering/redis-connect-dist)

## Run Geode

To run Geode, run:

```bash
./setup_geode.sh
```

## Build the App

You can build the app with:
```bash
mvn clean package
```

## Run the App

To run the app you'll need an API token from [finnhub.io](https://finnhub.io/), then just run:

