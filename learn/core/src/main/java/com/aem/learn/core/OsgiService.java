package com.aem.learn.core;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service(value=OsgiServiceInterface.class)
public class OsgiService implements OsgiServiceInterface
{
@Reference
	private ResourceResolverFactory factory;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public void addNodesFromService(String path, String nodeName){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
		try {
			ResourceResolver resolver = factory.getServiceResourceResolver(map);
			 Resource resource = resolver.getResource(path);
			Node node = resource.adaptTo(Node.class);
			log.info("Into add Node method try block");
			node.addNode(nodeName);
			resolver.adaptTo(Session.class).save();
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addPropertyFromService(String path, String name, String value){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
		try {
			ResourceResolver resolver = factory.getServiceResourceResolver(map);
			 Resource resource = resolver.getResource(path);
			Node node = resource.adaptTo(Node.class);
			log.info("Into add Property method try block");

			node.setProperty(name, value);
			resolver.adaptTo(Session.class).save();
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getPropertyFromService(String path) {
		// TODO Auto-generated method stub
		String value=null;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
		try {
			ResourceResolver resolver = factory.getServiceResourceResolver(map);
			 Resource resource = resolver.getResource(path);
			Node node = resource.adaptTo(Node.class);
			log.info("Into add Property method try block");

			value =node.getProperty("backend").getString();
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	
}
