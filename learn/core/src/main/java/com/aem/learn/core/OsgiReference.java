package com.aem.learn.core;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		
		service=OsgiReference.class
		
		)


public class OsgiReference {
	
	private OsgiAnnataionService service;
	
	@Reference
	public void bindOsgiAnnataionService (OsgiAnnataionService service){
		
		this.service=service;
		
	}
	public void unbindOsgiAnnataionService(OsgiAnnataionService service){
		
		this.service=service;
	}
	
	public void method(){
		
		service.testService();
	}

}
