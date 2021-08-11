package com.system.repository;

import javax.persistence.EntityManager;

import com.system.entity.Transaction;
import com.system.entity.User;
import com.system.factory.Factory;



public class TransferRepository {
	
	public User loadAccount(int accountNo) {
			
			EntityManager em = Factory.emf().createEntityManager();
			em.getTransaction().begin();
			User user = em.find(User.class, accountNo);
			em.getTransaction().commit();
			em.close();
			return user;
		}
	
	public void updateAccount(User user) {
		
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}
	public void createTransaction(Transaction transaction) {
		
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		em.persist(transaction);
		em.getTransaction().commit();
		em.close();
	}
}
