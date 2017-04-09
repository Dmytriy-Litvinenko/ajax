package testtask.dao.implWithHibernate;

import org.hibernate.*;
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
            //session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE id= :id");
            query.setParameter("id", id);
            if (query.uniqueResult() != null)
                employee = (Employee) query.uniqueResult();
            //session.getTransaction().commit();
        }catch (HibernateException e){
            //session.close();
            throw new DAOException("Can't get employee by id with Hibernate", e.getCause());
        }
        return employee;
    }

    @Override
    public List<Employee> findAll(Integer departmentId) throws DAOException {
        //Session session = sessionFactory.openSession();
        Department department = departmentDao.findById(departmentId);
        List<Employee> employees;
        try(Session session = sessionFactory.openSession()) {
            //session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE department= :dep");
            query.setParameter("dep", department);
            employees = query.list();
            //session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DAOException("Can't get employees by department with Hibernate", e.getCause());
        }
        return employees;
    }

    @Override
    public void addEmpl(Employee employee) throws DAOException {
        //Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean committed = false;
        try(Session session = sessionFactory.openSession()) {
            transaction=session.beginTransaction();
            session.save(employee);
            //session.getTransaction().commit();
            transaction.commit();
            committed = true;
        } catch (HibernateException e) {
            throw new DAOException("Can't add employee with Hibernate", e.getCause());
        }finally {
            if (transaction!=null && !committed)transaction.rollback();
        }
    }

    @Override
    public void delEmpl(Integer id) throws DAOException {
        //Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean committed =false;
        try(Session session = sessionFactory.openSession()) {
            transaction=session.beginTransaction();
            session.delete(findById(id));
            //session.getTransaction().commit();
            transaction.commit();
            committed = true;
        } catch (HibernateException e) {
            throw new DAOException("Can't delete employee by id with Hibernate", e.getCause());
        }finally {
            if (transaction!=null && !committed)transaction.rollback();
        }
    }

    @Override
    public void updateEmpl(Employee employee) throws DAOException {
        Transaction transaction=null;
        boolean committed = false;
        try(Session session = sessionFactory.openSession()) {
            transaction=session.beginTransaction();
            session.update(employee);
            //session.getTransaction().commit();
            transaction.commit();
            committed=true;
        } catch (HibernateException e) {
            throw new DAOException("Can't update employee by id with Hibernate", e.getCause());
        }finally {
            if (transaction!=null && !committed)transaction.rollback();
        }
    }

    @Override
    public Employee findByEmail(String email) throws DAOException {
        //Session session = sessionFactory.openSession();
        Employee employee = new Employee();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM employees WHERE email= :email");
            query.setParameter("email", email);
            if (query.uniqueResult() != null)
                employee = (Employee) query.uniqueResult();
            session.getTransaction().commit();
        }catch (HibernateException e) {
            throw new DAOException("Can't get employee by email with Hibernate", e.getCause());
        }
        return employee;
    }
}
