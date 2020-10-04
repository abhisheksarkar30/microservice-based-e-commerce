package edu.saby.msec.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.saby.msec.infra.async.handlers.AsynchronousResponseHandler;

@Configuration
public class AsynchronousResponseConfig {

	@Bean
	public AsynchronousResponseHandler asyncResponseEntityHandler() {
        return new AsynchronousResponseHandler();
    }
	
	@Bean
    public WebMvcConfigurer rxJavaWebMvcConfiguration(List<AsyncHandlerMethodReturnValueHandler> handlers) {
        return new WebMvcConfigurer() {
            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
                if (handlers != null) {
                    returnValueHandlers.addAll(handlers);
                }
            }
        };
    }
	
}
