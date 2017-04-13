package testtask.dao.implSpringWithHibernate;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dao.DepartmentDao;
import testtask.exception.DAOException;
import testtask.model.Department;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    //@Autowired
    //public DepartmentDaoImpl(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    //@Transactional
    public Department findById(Integer id) throws DAOException {
        Session session;
        Department department;

        try {
            session = currentSession();//.createQuery("FROM employees WHERE department= :dep");
        } catch (HibernateException e) {
            session = sessionFactory.openSession();//.createQuery("FROM employees WHERE department= :dep");
        }
        department = session.get(Department.class, id);
        return department;
    }

    @Override
    public Department findByName(String name) throws DAOException {
        Department department = new Department();
        Query query = currentSession().createQuery("FROM departments WHERE name= :name");
        query.setParameter("name", name);
        if (query.uniqueResult() != null) {

            department = (Department) query.uniqueResult();
        }/**/

        return department;//currentSession().get(Department.class, name);
        //return department;
    }

    @Override
    //@Transactional
    public List<Department> findAll() throws DAOException {
        List<Department> departments;
        Query query = currentSession().createQuery("FROM departments");
        departments = query.list();
        return departments;
    }

    @Override
    @Transactional
    public void addDep(Department department) throws DAOException {
        currentSession().save(department);
    }

    @Override
    public void delDep(Integer id) throws DAOException {
        currentSession().delete(findById(id));
    }

    @Override
    public void updateDep(Department department) throws DAOException {
        //currentSession().update(department);
        Session session;
        try {
            session = currentSession();//.createQuery("FROM employees WHERE department= :dep");
        } catch (HibernateException e) {
            session = sessionFactory.openSession();//.createQuery("FROM employees WHERE department= :dep");
        }
        session.update(department);
    }
}
