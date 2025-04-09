package space.forloop.curseforge.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import space.forloop.curseforge.config.properties.CurseForgeProperties;

import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

	private static final String CURSE_FORGE_AUTH_HEADER = "x-api-key";

	private final CurseForgeProperties properties;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplateBuilder()
			.rootUri("https://api.curseforge.com")
			.additionalInterceptors(this::addAuthHeaders)
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	@NotNull
	private ClientHttpResponse addAuthHeaders(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().add(CURSE_FORGE_AUTH_HEADER, properties.apiKey());

		return execution.execute(request, body);
	}
}
