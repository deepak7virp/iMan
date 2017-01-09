package com.inlighten.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.inlighten.model.Application;
import com.inlighten.model.User;

@Repository("applicationDao")
public class ApplicationDaoImpl extends AbstractDao<Integer, Application> implements ApplicationDao {

	@Override
	public Application findById(int application_id) {
		// TODO Auto-generated method stub
		Application application = getByKey(application_id);
//		if(application!=null){
//			Hibernate.initialize(user.getUserApplications());
//		}
		return application;
	}

	@Override
	public Application findByName(String application_name) {
		// TODO Auto-generated method stub
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("application_name", application_name));
		Application application = (Application)crit.uniqueResult();
//		if(user!=null){
//			Hibernate.initialize(user.getUserApplications());
//		}
		return application;
	}

	@Override
	public void save(Application application) {
		// TODO Auto-generated method stub
		persist(application);
	}

	@Override
	public void deleteByName(String application_name) {
		// TODO Auto-generated method stub
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("application_name", application_name));
		Application application = (Application)crit.uniqueResult();
		delete(application);
	}

	@Override
	public List<Application> findAllApplications() {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("application_name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Application> applications = (List<Application>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
//		for(User user : users){
//			Hibernate.initialize(user.getUserApplications());
//		}
		return applications;
	}

}
