package space.forloop.curseforge.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.forloop.curseforge.config.properties.InfluxDBProperties;

/**
 * Configuration for the InfluxDB client.
 *
 * This configuration creates an InfluxDBClient bean that is used to write
 * download count data to InfluxDB as time-series data.
 */
@Configuration
public class InfluxConfig {

	/**
	 * Configuration properties for the InfluxDB connection.
	 */
	private final InfluxDBProperties influxProperties;

	/**
	 * Constructor that injects the InfluxDB properties.
	 *
	 * @param influxProperties The InfluxDB configuration properties
	 */
	public InfluxConfig(InfluxDBProperties influxProperties) {
		this.influxProperties = influxProperties;
	}

	/**
	 * Creates and configures an InfluxDBClient bean for interacting with InfluxDB.
	 *
	 * The client is configured with:
	 * - The InfluxDB server URL
	 * - Authentication token
	 * - Organization
	 * - Bucket
	 *
	 * @return A configured InfluxDBClient instance
	 */
	@Bean(name = "curseInflux")
	InfluxDBClient curseInflux() {
		return InfluxDBClientFactory.create(
			influxProperties.url(),
			influxProperties.token().toCharArray(),
			influxProperties.org(),
			influxProperties.bucket()
		);
	}
}
