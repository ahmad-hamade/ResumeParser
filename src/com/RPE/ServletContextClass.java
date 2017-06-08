package com.RPE;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.ws.rs.core.Context;

import gate.Gate;
import gate.util.GateException;
import gate.util.Out;

public class ServletContextClass implements ServletContextListener {
	@Context
	ServletContext servletContext;

	public void contextInitialized(ServletContextEvent event) {

		ServletContext context = event.getServletContext();
		
		if (Gate.getGateHome() == null)
			Gate.setGateHome(new File(context.getRealPath("/WEB-INF/GATEApp/GATEFiles")));
		if (Gate.getPluginsHome() == null)
			Gate.setPluginsHome(new File(context.getRealPath("/WEB-INF/GATEApp/GATEFiles/plugins")));

		Out.prln("Initialising basic system...");
		try {
			Gate.init();
		} catch (GateException e) {
			e.printStackTrace();
		}
		Out.prln("...basic system initialised");

		// initialise ANNIE (this may take several minutes)
		Out.prln("Initialising Annie...");
		Annie annie = new Annie();
		try {
			annie.initAnnie();
		} catch (GateException | IOException e) {
			e.printStackTrace();
		}
		Out.prln("...Annie initialised");

		ServletContext sc = event.getServletContext();
		sc.setAttribute("Annie", annie);

	}

	public void contextDestroyed(ServletContextEvent arg0) {
		Out.prln("Jersey Destroyed");
	}

}
