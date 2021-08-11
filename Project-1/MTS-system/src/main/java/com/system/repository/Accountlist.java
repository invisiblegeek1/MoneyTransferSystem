package com.system.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.system.entity.User;
import com.system.factory.Factory;

public class Accountlist {
	
	public List<User> getAccountList(){
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> accList = query.getResultList();
		return accList;
	}

}
