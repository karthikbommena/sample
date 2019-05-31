package com.aem.learn.core.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.Workspace;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate=true)
@Service(value=EventListener.class)
public class EventListenerCheck implements EventListener{

	@Reference
	private ResourceResolverFactory factory;
@Reference
private SlingRepository repo;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    String[] nodeTypes ={"nt:unstructured","cq:PageContent"};
@Activate
    public void activate(ComponentContext context) {
	Map<String, Object> map = new HashMap<String,Object>();
	map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
	
		try {
			ResourceResolver resolver = factory.getServiceResourceResolver(map);
			Session test = repo.loginService("getResolver", null);
			Session session = resolver.adaptTo(Session.class);
			Workspace workspace = test.getWorkspace();
		ObservationManager obsManager = workspace.getObservationManager();
		obsManager.addEventListener(this,
									Event.PROPERTY_ADDED | Event.NODE_ADDED,
									"/content",
									true, 
									null, 
									nodeTypes,
									false);
			log.info("Event Registered");
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedRepositoryOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
    }
	
	
	
	@Override
	public void onEvent(EventIterator events) {
		// TODO Auto-generated method stub
		
		log.info("Event Occured");
		while(events.hasNext()){
			
			try {
				log.info("Event at :: "+events.nextEvent().getPath());
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
