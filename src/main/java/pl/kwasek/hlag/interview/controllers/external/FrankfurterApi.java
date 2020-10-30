package pl.kwasek.hlag.interview.controllers.external;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import pl.kwasek.hlag.interview.domain.CountryCode;


@Path("/")
@RegisterRestClient(baseUri = "https://api.frankfurter.app/")
@Produces(MediaType.APPLICATION_JSON)
public interface FrankfurterApi {

	@GET
	@Path("/{date}")
	public ExternalRatesResponse get(@PathParam("date") String date, @QueryParam("from") CountryCode from, @QueryParam("to") CountryCode to);
	
}
