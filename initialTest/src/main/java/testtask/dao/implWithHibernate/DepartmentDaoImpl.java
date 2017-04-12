/*package testtask.dao.implWithHibernate;


import org.hibernate.*;
import testtask.dao.DepartmentDao;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.util.db.HibernateFactory;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    @Override
    public Department findById(Integer id) throws DAOException {
        Department department = null;
        try (Session session = sessionFactory.openSession()) {
            department = new Department();
            Query query = session.createQuery("FROM departments WHERE id = :id");
            query.setParameter("id", id);
            if (query.uniqueResult() != null)
                department = (Department) query.uniqueResult();
        } catch (HibernateException e) {
            //throw new DAOException("Can't get all departments with Hibernate", e.getCause());
        }
        return department;
    }

    @Override
    public Department findByName(String name) throws DAOException {
        Department department = null;
        try (Session session = sessionFactory.openSession()) {
            department = new Department();
            Query query = session.createQuery("FROM departments WHERE name = :name");
            query.setParameter("name", name);
            if (query.uniqueResult() != null)
                department = (Department) query.uniqueResult();
        } catch (HibernateException e) {
            //throw new DAOException("Can't get department by name with Hibernate", e.getCause());
        }
        return department;
    }

    @Override
    public List<Department> findAll() throws DAOException {
        List<Department> departments;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM departments");
            departments = query.list();
        } catch (HibernateException e) {
            throw new DAOException("Can't get all departments with Hibernate", e.getCause());
        }
        return departments;
    }

    @Override
    public void addDep(Department department) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            //throw new DAOException("Can't add department with Hibernate", e.getCause());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void delDep(Integer id) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (HibernateException e) {
            //throw new DAOException("Can't add department with Hibernate", e.getCause());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateDep(Department department) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (HibernateException e) {
            //throw new DAOException("Can't update department with Hibernate", e.getCause());
            if (transaction != null) transaction.rollback();
        }
    }
}
*/