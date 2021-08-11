package com.system.repository;

import javax.persistence.EntityManager;

import com.system.entity.User;
import com.system.factory.Factory;

public class AccountRepository {
	
	public void saveAccount(User user) {
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

}
