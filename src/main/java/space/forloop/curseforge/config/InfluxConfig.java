package space.forloop.curseforge.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.forloop.curseforge.config.properties.InfluxDBProperties;

@Configuration
public class InfluxConfig {

	private final InfluxDBProperties influxProperties;

	public InfluxConfig(InfluxDBProperties influxProperties) {
		this.influxProperties = influxProperties;
	}

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
