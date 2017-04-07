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

public class DepartmentDaoImpl implements DepartmentDao {

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Department findById(Integer id) throws DAOException {
        Session session = sessionFactory.openSession();
        Department department;
        try {
            department = new Department();
            session.beginTransaction();
            Query query = session.createQuery("FROM departments WHERE id= :id");
            query.setParameter("id", id);
            if (query.uniqueResult() != null)
                department = (Department) query.uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return department;
    }

    @Override
    public Department findByName(String name) throws DAOException {
        Session session = sessionFactory.openSession();
        Department department;
        try {
            department = new Department();
            session.beginTransaction();
            Query query = session.createQuery("FROM departments WHERE name= :name");
            query.setParameter("name", name);
            if (query.uniqueResult() != null)
                department = (Department) query.uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return department;
    }

    @Override
    public List<Department> findAll() throws DAOException {

        Session session = sessionFactory.openSession();
        List<Department> departments;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM departments");
            departments = query.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return departments;
    }

    @Override
    public void addDep(Department department) throws DAOException {

        List<Employee> employees = employeeDao.findAll(department.getId());
        for (Employee employee : employees) {
            employeeDao.delEmpl(employee.getId());
        }

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delDep(Integer id) throws DAOException {
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
    public void updateDep(Department department) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
