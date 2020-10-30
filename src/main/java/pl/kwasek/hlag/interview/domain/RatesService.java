package pl.kwasek.hlag.interview.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import pl.kwasek.hlag.interview.controllers.external.ExternalRatesResponse;
import pl.kwasek.hlag.interview.controllers.external.FrankfurterApi;

@RequestScoped
public class RatesService {

	@Inject
	private RateRepository repository;

	@Inject
	@RestClient
	private FrankfurterApi frankfurterApi;

	public Rate getRate(Calendar date) {
		Double rateValue = getRateValue(date);
		Rate rate = new Rate(date, rateValue);
		repository.save(rate);
		return rate;
	}

	private Double getRateValue(Calendar date) {
		return fromDataBase(date).orElseGet(() -> fromExternalApi(date));
	}

	private Double fromExternalApi(Calendar date) {
		String str_date = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
		ExternalRatesResponse externalRatesResponse = frankfurterApi.get(str_date, CountryCode.EUR, CountryCode.USD);
		return externalRatesResponse.getRates().get(CountryCode.USD.name());
	}

	private Optional<Double> fromDataBase(Calendar date) {
		return repository.getRateByDate(date).map(Rate::getRate);
	}

	public Collection<Rate> getHistoricalRates() {
		return repository.findAllOrderByDate();
	}

}
