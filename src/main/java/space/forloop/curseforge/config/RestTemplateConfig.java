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

/**
 * Configuration for the RestTemplate used to interact with the CurseForge API.
 *
 * This configuration:
 * 1. Sets the base URL for the CurseForge API
 * 2. Adds an interceptor to include the API key in all requests
 * 3. Sets the default Accept header to application/json
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

	/**
	 * The header name for the CurseForge API key authentication.
	 */
	private static final String CURSE_FORGE_AUTH_HEADER = "x-api-key";

	/**
	 * Configuration properties containing the CurseForge API key.
	 */
	private final CurseForgeProperties properties;

	/**
	 * Creates and configures a RestTemplate bean for making requests to the CurseForge API.
	 *
	 * @return A configured RestTemplate instance
	 */
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplateBuilder()
			// Set the base URL for all requests
			.rootUri("https://api.curseforge.com")
			// Add an interceptor to include the API key in all requests
			.additionalInterceptors(this::addAuthHeaders)
			// Set the default Accept header to application/json
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	/**
	 * Interceptor method that adds the CurseForge API key to the request headers.
	 *
	 * @param request The HTTP request
	 * @param body The request body
	 * @param execution The request execution
	 * @return The HTTP response
	 * @throws IOException If an I/O error occurs during the request
	 */
	@NotNull
	private ClientHttpResponse addAuthHeaders(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		// Add the API key header to the request
		request.getHeaders().add(CURSE_FORGE_AUTH_HEADER, properties.apiKey());

		// Execute the request with the modified headers
		return execution.execute(request, body);
	}
}
