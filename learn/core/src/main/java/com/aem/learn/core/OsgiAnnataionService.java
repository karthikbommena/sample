package com.aem.learn.core;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;



@Component(
		immediate=true,
		service=OsgiAnnataionService.class		
		)
public class OsgiAnnataionService {
	@Reference
	private ResourceResolverFactory factory;
	
	
	public void testService(){}
	
	
	
}
