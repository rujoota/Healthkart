/**
 * 
 */
package healthkart;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rajan
 *
 */
@Path("/healthkart")
public class HealthkartOrderService {

	@Path("/createUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOrder( InputStream is )
	{
		return null;
	}
	
	
}
