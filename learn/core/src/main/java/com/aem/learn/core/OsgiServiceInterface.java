package com.aem.learn.core;

public interface OsgiServiceInterface {

	public void addNodesFromService(String path, String nodeName);
	public void addPropertyFromService(String path, String name, String value);
	public String getPropertyFromService(String path);
	
}
