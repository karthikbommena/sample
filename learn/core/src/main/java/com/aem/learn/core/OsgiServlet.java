package com.aem.learn.core;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.Component;



@Component(
		service=Servlet.class,
		property={
		"paths=/bin/select",
		"method=GET"}
		
		)
public class OsgiServlet  {

}
