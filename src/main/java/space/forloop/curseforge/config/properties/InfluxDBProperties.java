package space.forloop.curseforge.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "influxdb")
public record InfluxDBProperties(
	String url,
	String token,
	String org,
	String bucket
) {
}
