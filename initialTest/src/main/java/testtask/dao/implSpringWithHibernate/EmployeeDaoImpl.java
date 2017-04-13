package testtask.dao.implSpringWithHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dao.EmployeeDao;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.model.Employee;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    //@Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private DepartmentDaoImpl departmentDao;

    /*@Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }*/
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Employee findById(Integer id) throws DAOException {
        return currentSession().get(Employee.class,id);
    }

    @Override
    //@Transactional
    public List<Employee> findAll(Integer id) throws DAOException {
        Department department = departmentDao.findById(id);
        List<Employee> employees;
        Query query;
        try {
            query = currentSession().createQuery("FROM employees WHERE department= :dep");
        }catch (HibernateException e){
            query = sessionFactory.openSession().createQuery("FROM employees WHERE department= :dep");
        }
        query.setParameter("dep", department);
        employees = query.list();
        return employees;
        /*Query query = currentSession().createQuery("from E where department.id= :depId");
        query.setParameter("depId", id);*/
        //  List<Employee> employees = query.list();
        //return query.list();
    }

    @Override
    //@Transactional
    public void addEmpl(Employee employee) throws DAOException {
        currentSession().save(employee);
    }

    @Override
    public void delEmpl(Integer id) throws DAOException {
        currentSession().delete(findById(id));
    }

    @Override
    public void updateEmpl(Employee employee) throws DAOException {
        currentSession().update(employee);
    }

    @Override
    public Employee findByEmail(String email) throws DAOException {
        return currentSession().get(Employee.class, email);
    }
}
