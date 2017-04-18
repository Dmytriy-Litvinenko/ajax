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

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }/**/

    @Override
    public Department findById(Integer id) throws DAOException {
        return currentSession().get(Department.class, id);
    }

    @Override
    public Department findByName(String name) throws DAOException {
        Department department = new Department();
        Query query = currentSession().createQuery("FROM departments WHERE name= :name");
        query.setParameter("name", name);
        if (query.uniqueResult() != null) {
            department = (Department) query.uniqueResult();
        }
        return department;
    }

    @Override
    //@SuppressWarnings("unchecked")
    @Transactional
    public List<Department> findAll() throws DAOException {
        List<Department> departments;
        Query query;
        try{
            query = sessionFactory.getCurrentSession().createQuery("FROM departments");
        }catch (HibernateException e){
            query = sessionFactory.openSession().createQuery("FROM departments");
        }

        departments = query.list();
        return departments;
    }

    @Override
    @Transactional
    public void addDep(Department department) throws DAOException {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
        }
        //currentSession();
        session.save(department);

    }

    @Override
    public void delDep(Integer id) throws DAOException {
        currentSession().delete(findById(id));
    }

    @Override
    public void updateDep(Department department) throws DAOException {
        currentSession().merge(department);
    }
}
