package com.tajwebapp.repo;

import com.tajwebapp.model.Department;
import com.tajwebapp.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
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
        session.merge(employee);
        transaction.commit();
        return session.find(Employee.class, employee.getId());
    };

    public List<Employee> findAll() {
        Query query = session.createQuery("FROM Employee");
        return query.getResultList();
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(session.find(Employee.class, id));
    }

    public void remove(int id) {
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, id);
        session.remove(employee);
        transaction.commit();
    }
}
