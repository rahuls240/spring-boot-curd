package com.luv2code.springboot.curddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.curddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entityManager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager){
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {

		//get a hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery=
				currentSession.createQuery("from Employee", Employee.class);
				
		//excute the query and get the result
		List<Employee> employees = theQuery.getResultList();
		
		//return te results
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		//get a hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get the employee
		Employee theEmployee = 
				currentSession.get(Employee.class, theId);
		
		//return the employee
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		//get a hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		//save the employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {

		//get a hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		//delete object with primary key
		Query theQuery=
				currentSession.createQuery(
						"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}
