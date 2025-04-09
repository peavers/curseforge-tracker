package space.forloop.curseforge.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.forloop.curseforge.config.properties.CurseForgeProperties;
import space.forloop.curseforge.repository.InfluxRepository;
import space.forloop.curseforge.service.CurseForgeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadCountScheduler {
	private final CurseForgeProperties curseForgeProperties;

	private final CurseForgeService curseForgeService;

	private final ExecutorService virtualExecutor;

	private final InfluxRepository influxRepository;

	@Scheduled(fixedRateString = "${curse-forge.scheduler.fixed-rate-minutes}", timeUnit = TimeUnit.MINUTES)
	public void init() {
		curseForgeProperties.projects()
			.forEach((key, value) -> virtualExecutor.execute(() -> updateDownloadCount(key, value)));
	}

	private void updateDownloadCount(String projectName, long projectId) {
		var project = curseForgeService.getProject(projectId);
		var downloadCount = project.getData().getDownloadCount();

		influxRepository.writeNumber("downloadCount", downloadCount);

		log.info("Update download count for {}", projectName);
	}
}
