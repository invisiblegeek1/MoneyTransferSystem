package com.system.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.system.entity.Transaction;
import com.system.factory.Factory;

public class TransactionList {
	public List<Transaction> getAllTransactionHistory(){
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from Transaction");
		List<Transaction> TransactionAccList = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return TransactionAccList;
	}

}
