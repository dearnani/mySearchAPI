package com.wday.search.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author Narasimha
 *
 */
public class PropertyReader {

	public static Map<String,String> populateSearchRepoProperties()
	{
		
		Map<String, String> configParams = new HashMap<String, String>();
		Properties configProps = PropertyServiceLocator.getInstance().populateAllproperties();
		
		configParams.put("path",configProps.getProperty("search.repo.path"));
		//setting default value as Reactive if not provided through command line argument 
		configParams.put("q",configProps.getProperty("keyword","Reactive"));
		configParams.put("sort",configProps.getProperty("sort"));
		configParams.put("order",configProps.getProperty("order"));
		
		return configParams;

	}
}
