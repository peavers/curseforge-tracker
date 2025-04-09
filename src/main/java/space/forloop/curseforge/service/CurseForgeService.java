package space.forloop.curseforge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.forloop.curseforge.model.CurseForgeResponse;

/**
 * Service for interacting with the CurseForge API.
 *
 * This service provides methods to retrieve data from the CurseForge API,
 * such as project information including download counts.
 *
 * The RestTemplate used by this service is configured in RestTemplateConfig
 * with the appropriate base URL and authentication headers.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CurseForgeService {

	/**
	 * RestTemplate configured with the CurseForge API base URL and authentication.
	 */
	private final RestTemplate restTemplate;

	/**
	 * Retrieves information about a specific CurseForge project.
	 *
	 * Makes a GET request to the CurseForge API endpoint /v1/mods/{projectId}
	 * and deserializes the response into a CurseForgeResponse object.
	 *
	 * @param projectId The CurseForge project ID
	 * @return A CurseForgeResponse containing project information, including download count
	 */
	public CurseForgeResponse getProject(long projectId) {
		return restTemplate.getForObject("/v1/mods/" + projectId, CurseForgeResponse.class);
	}

}
