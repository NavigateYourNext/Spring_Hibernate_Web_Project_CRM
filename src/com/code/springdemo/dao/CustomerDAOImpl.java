package com.code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO 
{
	//Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Customer> getCustomers() {

		//Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//Create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName",Customer.class);

		//Execute query and get the result list
		List<Customer> customers = theQuery.getResultList();

		//Return the result of customer list
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer)
	{
		//Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//Save the customer
		//currentSession.save(theCustomer);
		currentSession.saveOrUpdate(theCustomer);

	}


	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}


	@Override
	public void deleteCustomer(int theId)
	{
	 
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		currentSession.delete(theCustomer);
		
		//OR
		
		//Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId);
		//theQuery.setParameter("customerId",theId);
		//theQuery.executeUpdate();
	}

}
