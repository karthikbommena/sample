package com.aem.learn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

@Component(immediate=true)
@Service(value=QueryService.class)
public class QueryServiceImpl implements QueryService {
@Reference
ResourceResolverFactory factory;   
protected final Logger log = LoggerFactory.getLogger(this.getClass());


	@Override
	public List<QueryBean> getTagSearchResults(String path, String tag) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, "getResolver");
		List<QueryBean> list =new ArrayList<QueryBean>();
		ResourceResolver resolver=null;
			try {
				resolver = 
						factory.getServiceResourceResolver(map);
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				QueryBuilder builder = resolver.adaptTo(QueryBuilder.class);
				Session session = resolver.adaptTo(Session.class);

				Map<String,String> map1= new HashMap<String,String>();
				map1.put("path", path);
				map1.put("nodename", "jcr:content");
				map1.put("property", "cq:tags");
				map1.put("property.value", tag);
				map1.put("p.limit", "10");			
				Query query = builder.createQuery(PredicateGroup.create(map1), session);
				
				SearchResult results = query.getResult();
				Iterator<Node> nodeItr = results.getNodes();
				while(nodeItr.hasNext()){
					Node node = nodeItr.next();
					try {
						QueryBean bean =  new QueryBean();

					log.info("In While loop and path is : "+node.getPath());
					bean.setPagePath(node.getPath());
					
						bean.setTitle(node.getProperty("jcr:title").getString());
						list.add(bean);
					} catch (ValueFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PathNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					bean.setTag(node.getProperty("cq:tags").getString());	
				
				}
				
				
				
			
		
		return list;
		// TODO Auto-generated method stub
		
	}

}
