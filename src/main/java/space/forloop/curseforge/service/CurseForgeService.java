package space.forloop.curseforge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.forloop.curseforge.model.CurseForgeResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurseForgeService {

	private final RestTemplate restTemplate;

	public CurseForgeResponse getProject(long projectId) {
		return restTemplate.getForObject("/v1/mods/" + projectId, CurseForgeResponse.class);
	}

}
