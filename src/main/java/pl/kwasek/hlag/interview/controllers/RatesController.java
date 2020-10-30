package pl.kwasek.hlag.interview.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.kwasek.hlag.interview.domain.Rate;
import pl.kwasek.hlag.interview.domain.RatesService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/search")
@ApplicationScoped
public class RatesController {

	@Inject
	RatesService rates;
	
	@GET
	@Path("/rates")
	public Response getRatesBetweenEurAndUsd(@QueryParam("date") String str_date) {
		Calendar calendar;
		try {
			calendar = getCalendar(str_date);
		} catch (ParseException e) {
			return Response.status(404, "Illegal date format.").build();
		}
		Rate rate = rates.getRate(calendar);
		return Response.ok(rate.getRate()).build();
	}

	@GET
	@Path("/history")
	public Response getSearchHistory() {
		Collection<Rate> historicalRates = rates.getHistoricalRates();
		return Response.ok(historicalRates).build();
	}
	
	private static Calendar getCalendar(String str_date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		Date date = format.parse(str_date);
		calendar.setTime(date);
		return calendar;
	}
}
