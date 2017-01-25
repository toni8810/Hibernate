package hibernate.dao;


import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sf;
	
	public Session session() {
		return sf.getCurrentSession();
	}
	
	public List<User> getAllUsers() {
		CriteriaBuilder cb = session().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> r = cq.from(User.class);
		cq.select(r);
		TypedQuery<User> tq = session().createQuery(cq);
		return tq.getResultList();
		
		
	}
	public void insertUser(User user) {
		
		session().saveOrUpdate(user);
	}
	public List<Image> getUserImages(String username) {
		CriteriaBuilder cb = session().getCriteriaBuilder();
		CriteriaQuery<Image> cq = cb.createQuery(Image.class);
		Root<Image> ri = cq.from(Image.class);
		cq.select(ri).where(cb.equal(ri.get("user").get("username"), username));
		TypedQuery<Image> tq = session().createQuery(cq);
		return tq.getResultList();
	}
}
