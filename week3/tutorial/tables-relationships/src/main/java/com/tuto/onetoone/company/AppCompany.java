package com.tuto.onetoone.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppCompany {
	
	private static Logger log = LoggerFactory.getLogger( AppCompany.class );

	public static void main(String[] args) {

		System.out.println("Running AppCompany ...");

		log.debug("Create persistence manager");
		Map<String, String> params = new HashMap<>();
		params.put("javax.persistence.jdbc.url", "jdbc:h2:./company");
		params.put("hibernate.hbm2ddl.auto", "create");	// Delete schema and tables when running this app
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oneToOne", params);
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = null;
		
		
		System.out.println("Create entities Employee and Address ...");
		Employee employee1 = new Employee();
		employee1.setEmployeeName("John, Smith");
		
		Address address1 = new Address();
		address1.setStreetName("Park Street");
		address1.setZipCode("4110678");
		
		address1.setEmployee(employee1);		
		employee1.setAddress(address1);

		Employee employee2 = new Employee();
		employee2.setEmployeeName("Clint, Eastwood");
		
		Address address2 = new Address();
		address2.setStreetName("Elms Street");
		address2.setZipCode("4110654");
		
		address2.setEmployee(employee2);		
		employee2.setAddress(address2);

		
		try {
			
			tx = em.getTransaction();
			tx.begin();
			System.out.println("Transaction begin calling persist...");
			log.debug("Transaction begin calling persist");
			em.persist(employee1);
			em.persist(address1);
			em.persist(employee2);
			em.persist(address2);
			tx.commit();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		
		log.debug("Select all objects from table EMPLOYEE");
		// SQL: SELECT * FROM EMPLOYEE 
		// JPQL ...
		List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class)
		  .getResultList();
		
		showEmployees(employees);		
		
		log.debug("Select all objects from table ADDRESS");
		// SQL: SELECT * FROM ADDRESS 
		// JPQL ...
		List<Address> addresses = em.createQuery("SELECT a FROM Address a", Address.class)
		  .getResultList();
		
		showAddresses(addresses);		
		
		log.debug("Select address for employee John Smith");
		// SQL...
		/* 
		SELECT * 
		FROM EMPLOYEE , ADDRESS  
		WHERE EMPLOYEE.EMPLOYEEID  = ADDRESS.EMPLOYEEID  
		AND EMPLOYEE.EMPLOYEENAME  = 'John, Smith' 
		*/
		// JPQL...
		List<Address> address = em.createQuery(
				" SELECT add FROM Address add " +
				" WHERE add.employee.employeeName = :name ", Address.class)
				.setParameter("name", "John, Smith")
				.getResultList();
		
		System.out.println("Address for employee name = John Smith");
		showAddresses(address);	// STREETNAME=Park Street	ZIPCODE=4110678		
		
		log.debug("Select employee who lives at address street name = Park Street");
		// JPQL...
		List<Employee> employee = em.createQuery(
				" SELECT emp FROM Employee emp " +
				" WHERE emp.address.streetName = :streetName ", Employee.class)
				.setParameter("streetName", "Park Street")
				.getResultList();
		
		System.out.println("Employee who lives at address street name = Park Street");
		showEmployees(employee);	// Should be John Smith		
		
		
		System.out.println("Closing...");
		em.close();
		emf.close();
	}

	
	private static void showEmployees(List<Employee> employees) {
		System.out.println("All employees");
		System.out.println(
			String.format("%-15s	|	%-30s",
					"EMPLOYEEID",
					"EMPLOYEENAME"
			)
		);
		
		for (Employee employee : employees) {
			System.out.println(
				String.format("%-15s	|	%-30s",
						employee.getEmployeeId().toString(),
						employee.getEmployeeName()
				)
			);
		}
	}
	
	private static void showAddresses(List<Address> addresses) {
		System.out.println("All addresses");
		System.out.println(
			String.format("%-10s	|	%-20s	|	%-10s	|	%-15s",
					"ADRESSID",
					"STREETNAME",
					"ZIPCODE",
					"EMPLOYEEID"
			)
		);
		
		for (Address address : addresses) {
			System.out.println(
				String.format("%-10s	|	%-20s	|	%-10s	|	%-15s",
						address.getAdressId().toString(),
						address.getStreetName(),
						address.getZipCode(),
						address.getEmployee().getEmployeeId().toString()
				)
			);
		}
	}	
	
}
