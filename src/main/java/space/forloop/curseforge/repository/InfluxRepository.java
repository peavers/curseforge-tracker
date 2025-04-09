package space.forloop.curseforge.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import space.forloop.curseforge.config.properties.InfluxDBProperties;

import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class InfluxRepository {

	private final InfluxDBProperties influxProperties;

	private final InfluxDBClient influxDBClient;

	public void writeNumber(String key, long value) {
		var point = Point.measurement(key)
			.time(Instant.now().toEpochMilli(), WritePrecision.MS)
			.addField(key, value);

		var writeApi = influxDBClient.getWriteApiBlocking();
		writeApi.writePoint(influxProperties.bucket(), influxProperties.org(), point);
	}
}
