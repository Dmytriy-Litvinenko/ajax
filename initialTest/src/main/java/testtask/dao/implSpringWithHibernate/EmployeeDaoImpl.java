package testtask.dao.implSpringWithHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testtask.dao.EmployeeDao;
import testtask.exception.DAOException;
import testtask.model.Employee;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Employee findById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Employee> findAll(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void addEmpl(Employee employee) throws DAOException {

    }

    @Override
    public void delEmpl(Integer id) throws DAOException {

    }

    @Override
    public void updateEmpl(Employee employee) throws DAOException {

    }

    @Override
    public Employee findByEmail(String s) throws DAOException {
        return null;
    }
}
