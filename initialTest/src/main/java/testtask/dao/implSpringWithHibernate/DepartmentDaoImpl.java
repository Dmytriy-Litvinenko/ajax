package testtask.dao.implSpringWithHibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dao.DepartmentDao;
import testtask.exception.DAOException;
import testtask.model.Department;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    @Autowired
    private SessionFactory sessionFactory;

    //SessionFactoryImpl
    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Department findById(Integer id) throws DAOException {
        return currentSession().get(Department.class,id);
    }

    @Override
    public Department findByName(String name) throws DAOException {
        /*Department department = new Department();
        Query query = currentSession().createQuery("from Department where name= :name");
        query.setParameter("name", name);
        if (query.uniqueResult() != null) {

            department = (Department) query.uniqueResult();
        }*/

        return currentSession().get(Department.class,name);
        //return department;
    }

    @Override
    @Transactional
    public List<Department> findAll() throws DAOException {
        List<Department> departments;
        departments = currentSession().createCriteria(Department.class).list();
        return departments;
    }

    @Override
    public void addDep(Department department) throws DAOException {
        currentSession().save(department);
    }

    @Override
    public void delDep(Integer id) throws DAOException {
        currentSession().delete(findById(id));
    }

    @Override
    public void updateDep(Department department) throws DAOException {
        currentSession().update(department);
    }
}
