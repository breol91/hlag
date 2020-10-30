package pl.kwasek.hlag.interview.domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

@RequestScoped
public class RateRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Rate rate){
		em.getTransaction().begin();
		em.persist(rate);
		em.getTransaction().commit();
	}

	public Collection<Rate> findAllOrderByDate() {
		return em.createNamedQuery("Rate.findAll", Rate.class).getResultList();
	}
	
	public Optional<Rate> getRateByDate(Calendar calendar) {
		TypedQuery<Rate> query = em.createQuery("SELECT r FROM Rate r WHERE r.date = ?1", Rate.class);
		query.setParameter(1, calendar, TemporalType.DATE);
		List<Rate> list = query.getResultList();
		if(list.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(list.get(0));
	}
}
