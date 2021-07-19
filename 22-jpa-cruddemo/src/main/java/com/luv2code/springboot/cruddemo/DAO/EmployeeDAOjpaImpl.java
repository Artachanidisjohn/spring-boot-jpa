package com.luv2code.springboot.cruddemo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOjpaImpl implements EmployeeDAO {

	private EntityManager entityManager;

	public EmployeeDAOjpaImpl(EntityManager theEntityManager) {

	entityManager=theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {

		Query theQuery = entityManager.createQuery("from Employee");
		
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		//get employee
		
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//return employee
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		//save or update the employee 
		
		Employee dbEmployee = entityManager.merge(theEmployee);

		//update with id from db
		
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {

		
		//delete object with primary key
		
		Query theQuery =entityManager.createQuery("delete from Employee where id=:employeeId");

		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	
	
	}

}
