package space.forloop.curseforge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {

	@Bean
	ExecutorService virtualExecutor() {
		return Executors.newVirtualThreadPerTaskExecutor();
	}

}
