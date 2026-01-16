package com.tajwebapp.repo;

import com.tajwebapp.model.Department;
import com.tajwebapp.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepo {

    SessionFactory sf = new Configuration()
            .addAnnotatedClass(Employee.class)
            .addAnnotatedClass(Department.class)
            .configure()
            .buildSessionFactory();
    Session session = sf.openSession();



    public Employee save(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        return employee;
    };

    public List<Employee> findAll() {
        Query query = session.createQuery("FROM Employee");
        return query.getResultList();
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(session.find(Employee.class, id));
    }


//    Laptop laptop = session.find(Laptop.class, 1);
//        System.out.println(laptop);
//
//    Laptop laptop1 = session.find(Laptop.class, 1);
//        System.out.println(laptop1);
//
//        session.close();

    // new session
    // without L2 cache it will fire a query to db
//    Session session1 = sf.openSession();
//    Laptop laptop2 = session1.find(Laptop.class, 1);
//        System.out.println(laptop2);
//
//        session1.close();
}
