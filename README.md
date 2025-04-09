# CurseForge Tracker

A Spring Boot application that tracks download counts for CurseForge projects and stores the data in InfluxDB for monitoring and analytics.

## What it does

CurseForge Tracker periodically fetches download statistics for specified CurseForge projects using the CurseForge API and stores this data in InfluxDB as time-series data. This allows you to:

- Monitor download trends over time
- Create dashboards to visualize download growth
- Set up alerts for significant changes in download counts
- Analyze the popularity of your CurseForge projects

## Prerequisites

- Java 17 or higher
- InfluxDB instance (v2.x)
- CurseForge API key

## Configuration

The application is configured using environment variables:

### InfluxDB Configuration
- `INFLUXDB_URL` - URL of your InfluxDB instance
- `INFLUXDB_TOKEN` - Authentication token for InfluxDB
- `INFLUXDB_ORG` - InfluxDB organization
- `INFLUXDB_BUCKET` - InfluxDB bucket where data will be stored

### CurseForge Configuration
- `CURSEFORGE_API_KEY` - Your CurseForge API key

### Project Configuration

Projects to track are configured in the `application.yml` file:

```yaml
curse-forge:
  projects:
    ProjectName1: 1234567  # Project ID from CurseForge
    ProjectName2: 7654321
```

Replace the example projects with your own. You can find the project ID in the URL of your CurseForge project page.

### Scheduler Configuration

By default, the application checks download counts every 1 minute. You can change this in the `application.yml` file:

```yaml
curse-forge:
  scheduler:
    fixed-rate-minutes: 5  # Check every 5 minutes
```

## Running the Application

### Using Gradle

```bash
# Set environment variables first
export INFLUXDB_URL=http://localhost:8086
export INFLUXDB_TOKEN=your_token
export INFLUXDB_ORG=your_org
export INFLUXDB_BUCKET=your_bucket
export CURSEFORGE_API_KEY=your_api_key

# Run the application
./gradlew bootRun
```

### Using Docker

```bash
docker build -t curseforge-tracker .

docker run -d \
  -e INFLUXDB_URL=http://influxdb:8086 \
  -e INFLUXDB_TOKEN=your_token \
  -e INFLUXDB_ORG=your_org \
  -e INFLUXDB_BUCKET=your_bucket \
  -e CURSEFORGE_API_KEY=your_api_key \
  --name curseforge-tracker \
  curseforge-tracker
```

## Viewing the Data

You can view the collected data using InfluxDB's UI or by querying the InfluxDB API. The data is stored with the measurement name "downloadCount".

Example Flux query:

```
from(bucket: "your_bucket")
  |> range(start: -7d)
  |> filter(fn: (r) => r._measurement == "downloadCount")
```

## Building from Source

```bash
git clone https://github.com/yourusername/curseforge-tracker.git
cd curseforge-tracker
./gradlew build
```

## License

This project is open source and available under the [MIT License](LICENSE).
