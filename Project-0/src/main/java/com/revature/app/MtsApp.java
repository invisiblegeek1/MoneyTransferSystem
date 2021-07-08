package com.revature.app;

import com.revature.entity.User;
import com.revature.repository.JdbcMainRepository;

public class MtsApp {
	public static void main(String []args) {
		
		JdbcMainRepository todorepository = new JdbcMainRepository();
//		User user1 = new User("Ragul", 1, 100);
//		User user2 = new User("Bharath", 2, 0);
//		User user3 = new User("Naga", 3, 100);
//		todorepository.deposit(user1);

		todorepository.Transaction();
	}

}
