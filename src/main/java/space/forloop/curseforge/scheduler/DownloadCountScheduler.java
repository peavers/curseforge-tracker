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

/**
 * Scheduler responsible for periodically fetching download counts for CurseForge projects.
 *
 * This component runs at a fixed rate defined in the application configuration and
 * updates the download count for each configured project in parallel using a virtual
 * thread executor.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadCountScheduler {
	/**
	 * Configuration properties containing the list of projects to track.
	 */
	private final CurseForgeProperties curseForgeProperties;

	/**
	 * Service for interacting with the CurseForge API.
	 */
	private final CurseForgeService curseForgeService;

	/**
	 * Executor service for running update tasks in parallel.
	 * Uses virtual threads for efficient concurrent execution.
	 */
	private final ExecutorService virtualExecutor;

	/**
	 * Repository for writing data to InfluxDB.
	 */
	private final InfluxRepository influxRepository;

	/**
	 * Scheduled method that initiates the download count update process.
	 * Runs at a fixed rate defined by the 'curse-forge.scheduler.fixed-rate-minutes' property.
	 * For each project in the configuration, it submits a task to the executor to update
	 * the download count.
	 */
	@Scheduled(fixedRateString = "${curse-forge.scheduler.fixed-rate-minutes}", timeUnit = TimeUnit.MINUTES)
	public void init() {
		curseForgeProperties.projects()
			.forEach((key, value) -> virtualExecutor.execute(() -> updateDownloadCount(key, value)));
	}

	/**
	 * Updates the download count for a specific project.
	 *
	 * This method:
	 * 1. Fetches the project data from the CurseForge API
	 * 2. Extracts the download count
	 * 3. Writes the download count to InfluxDB
	 * 4. Logs that the update was completed
	 *
	 * @param projectName The name of the project (used for logging)
	 * @param projectId The CurseForge project ID
	 */
	private void updateDownloadCount(String projectName, long projectId) {
		var project = curseForgeService.getProject(projectId);
		var downloadCount = project.getData().getDownloadCount();

		influxRepository.writeNumber("downloadCount", downloadCount);

		log.info("Update download count for {}", projectName);
	}
}
