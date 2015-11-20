package pl.vgtworld.storage.resource;

import pl.vgtworld.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ResourceDao {

	@PersistenceContext
	private EntityManager em;

	public List<Resource> findAll() {
		Query query = em.createNamedQuery(Resource.QUERY_FIND_ALL);
		return PersistenceUtil.getResultList(query);
	}

}
