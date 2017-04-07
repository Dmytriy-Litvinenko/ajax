package testtask.dao.implWithHibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import testtask.dao.DepartmentDao;
import testtask.dao.EmployeeDao;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.model.Employee;
import testtask.util.db.HibernateFactory;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public Employee findById(Integer id) throws DAOException {
        Session session = sessionFactory.openSession();
        Employee employee;
        try {
            employee = new Employee();
            session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE id= :id");
            query.setParameter("id", id);
            if (query.uniqueResult() != null)
                employee = (Employee) query.uniqueResult();
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll(Integer departmentId) throws DAOException {
        Session session = sessionFactory.openSession();
        Department department = departmentDao.findById(departmentId);
        List<Employee> employees;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE department= :dep");
            query.setParameter("dep", department);
            employees = query.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public void addEmpl(Employee employee) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delEmpl(Integer id) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateEmpl(Employee employee) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public Employee findByEmail(String email) throws DAOException {
        Session session = sessionFactory.openSession();
        Employee employee = new Employee();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE email= :email");
            query.setParameter("email", email);
            if (query.uniqueResult() != null)
                employee = (Employee) query.uniqueResult();
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return employee;
    }
}
