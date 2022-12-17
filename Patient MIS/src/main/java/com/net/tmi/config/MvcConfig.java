package com.net.tmi.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/doctor").setViewName("tmi/doctor");
		registry.addViewController("/").setViewName("tmi/index");
		registry.addViewController("/admin").setViewName("tmi/admin");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	private void exposeDirectory(String pathPattern,ResourceHandlerRegistry registry) {
		Path path=Paths.get(pathPattern);
		String absolutePath=path.toFile().getAbsolutePath();
		
		String logicalPath=pathPattern.replace("../", "")+"/**";
		registry.addResourceHandler(logicalPath)
		        .addResourceLocations("file:/" +absolutePath+"/");
	}
}
