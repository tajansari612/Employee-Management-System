package com.tajwebapp.repo;

import com.tajwebapp.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepo {

    SessionFactory sf = new Configuration()
            .addAnnotatedClass(Department.class)
            .configure()
            .buildSessionFactory();
    Session session = sf.openSession();

    public List<Department> findAll() {
        Query query = session.createQuery("FROM Department");
        return query.getResultList();
    }

    public Optional<Department> findById(int id) {
        return Optional.ofNullable(session.find(Department.class, id));
    }

    public Optional<Department> save(Department department) {
        Transaction transaction = session.beginTransaction();
        Optional<Department> departmentFromDB = Optional.ofNullable(session.merge(department));
        transaction.commit();
        return departmentFromDB;
    }

    public void remove(int id) {
        Transaction transaction = session.beginTransaction();
        Department department = session.find(Department.class, id);
        session.remove(department);
        transaction.commit();
    }
}
