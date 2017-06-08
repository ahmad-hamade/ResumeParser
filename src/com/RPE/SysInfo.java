package com.RPE;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/sysinfo")
public class SysInfo {
    @Context ServletContext servletContext;
    
	@GET
	@Produces("application/json")
	public String serverInfo() {
        String actualPath = servletContext.getRealPath("/GATEFiles");
        return String.format("Hello, world! \nActual path: %s", actualPath);
	}
}
