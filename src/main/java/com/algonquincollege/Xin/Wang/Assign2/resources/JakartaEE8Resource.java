package com.algonquincollege.Xin.Wang.Assign2.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * JakartaEE8Resource by default.
 * 
 * @author Xin Wang
 */
@Path("rest")
public class JakartaEE8Resource {
    
    /**
     * This is the default constructor for JakartaEE8Resource.
     */
    public JakartaEE8Resource() {
    
    }
    
    /**
     * This method as a getter returns the Response.
     * 
     * @return The Response to get
     */
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
