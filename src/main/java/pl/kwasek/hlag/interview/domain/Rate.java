package pl.kwasek.hlag.interview.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Rate.findAll", query = "SELECT t FROM Rate t ORDER BY t.date DESC")
})
public class Rate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private Calendar date;
	
	@Column(nullable = false)
	private Double rate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp;
	
	public Rate() {
		//to available read from database.
	}
	
	public Rate(Calendar date, Double rate) {
		this.date = date;
		this.rate = rate;
		this.timestamp = Calendar.getInstance();
	}

	public Calendar getDate() {
		return date;
	}

	public Double getRate() {
		return rate;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

}
