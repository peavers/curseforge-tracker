package space.forloop.curseforge.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "curse-forge")
public record CurseForgeProperties(
	String apiKey,
	Map<String, Integer> projects,
	SchedulerProperties scheduler
){}
