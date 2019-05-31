package com.aem.learn.core;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;


@Model(adaptables=Resource.class)
public class SlingModelTest {

	
	@Inject
	@Optional
	private String path;
	public String getPath() {
		return path;
	}
	public String getResourceType() {
		return resourceType;
	}
	@Inject
	@Optional
	@Named("sling:resourceType")
	private String resourceType;
	
}
