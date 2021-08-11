package com.system.repository;


//import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.system.entity.Transaction;
import com.system.factory.Factory;

public class TransactionHistoryRepository {
	
	

public List<Transaction> getTop10History(int AccNumber){
		EntityManager em = Factory.emf().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT t FROM Transaction t where t.accnumber ='"+AccNumber+"'"+" order  by amount desc");
		query.setMaxResults(10);
		List<Transaction> top10list = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return top10list;
	}


	public List<Transaction> getFromToDateHistory (int AccNumber, String Date1, String Date2){
		EntityManager em = Factory.emf().createEntityManager();
		Query query = em.createQuery("SELECT  t FROM  Transaction t where t.AccNumber ="+AccNumber+" t.Date between '"+Date1+"' and '"+Date2+"'");
		List<Transaction> list = query.getResultList();
		return list;
	}
}
