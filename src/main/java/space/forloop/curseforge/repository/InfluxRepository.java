package space.forloop.curseforge.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import space.forloop.curseforge.config.properties.InfluxDBProperties;

import java.time.Instant;

/**
 * Repository for writing data to InfluxDB.
 *
 * This repository provides methods to write time-series data to InfluxDB,
 * which is used to store download counts for CurseForge projects over time.
 */
@Repository
@RequiredArgsConstructor
public class InfluxRepository {

	/**
	 * Configuration properties for InfluxDB connection.
	 */
	private final InfluxDBProperties influxProperties;

	/**
	 * InfluxDB client for interacting with the InfluxDB API.
	 */
	private final InfluxDBClient influxDBClient;

	/**
	 * Writes a numeric value to InfluxDB as a time-series data point.
	 *
	 * This method:
	 * 1. Creates a measurement point with the given key as the measurement name
	 * 2. Sets the timestamp to the current time with millisecond precision
	 * 3. Adds a field with the given key and value
	 * 4. Writes the point to the configured InfluxDB bucket and organization
	 *
	 * @param key The measurement name and field name
	 * @param value The numeric value to write
	 */
	public void writeNumber(String key, long value) {
		// Create a data point with the current timestamp
		var point = Point.measurement(key)
			.time(Instant.now().toEpochMilli(), WritePrecision.MS)
			.addField(key, value);

		// Get the blocking write API and write the point
		var writeApi = influxDBClient.getWriteApiBlocking();
		writeApi.writePoint(influxProperties.bucket(), influxProperties.org(), point);
	}
}
