package vn.techmaster.banking;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.techmaster.banking.model.Account;
import vn.techmaster.banking.model.User;

@SpringBootApplication
public class BankingApplication implements CommandLineRunner {
	@Autowired EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Account account1 = Account.builder()
		.id("1")
		.accountName("bob123")
		.password("123456")
		.balance(500000)
		.build();

		Account account2 = Account.builder()
		.id("2")
		.accountName("bob456")
		.password("123456")
		.balance(1000000)
		.build();

		Account account3 = Account.builder()
		.id("3")
		.accountName("alice123")
		.password("123456")
		.balance(2000000)
		.build();

		Account account4 = Account.builder()
		.id("4")
		.accountName("tom123")
		.password("123456")
		.balance(1000000)
		.build();

		Account account5 = Account.builder()
		.id("5")
		.accountName("sara123")
		.password("123456")
		.balance(5000000)
		.build();

		em.persist(account1);
		em.persist(account2);
		em.persist(account3);
		em.persist(account4);
		em.persist(account5);
		List<Account> listBob = new ArrayList<>();
		listBob.add(account1);
		listBob.add(account2);
		List<Account> listAlice = new ArrayList<>();
		listAlice.add(account3);
		List<Account> listTom = new ArrayList<>();
		listTom.add(account4);
		List<Account> listSara = new ArrayList<>();
		listSara.add(account5);
		User user1 = User.builder().id("11").name("Bob").address("London").accounts(listBob).build();
		User user2 = User.builder().id("22").name("Alice").address("Philadelphia").accounts(listAlice).build();
		User user3 = User.builder().id("33").name("Tom").address("New Brunswick").accounts(listTom).build();
		User user4 = User.builder().id("44").name("Sara").address("Hongkong").accounts(listSara).build();
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		em.persist(user4);

	}
}
