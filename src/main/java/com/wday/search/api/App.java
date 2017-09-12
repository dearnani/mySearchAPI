package com.wday.search.api;

import org.apache.log4j.Logger;

import com.wday.search.api.core.APIManager;

public class App {
	
	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		APIManager.managesearchAPI();
	}
}
