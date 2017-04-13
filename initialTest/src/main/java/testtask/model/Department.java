package testtask.model;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import testtask.util.validation.NotRussianLetters;
import testtask.util.validation.OvalUniqueDepartmentName;
import testtask.util.validation.OvalUniqueEmail;
import testtask.util.validation.UniqueDepartmentName;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @CheckWith(value = OvalUniqueDepartmentName.class, message = "Not Unique department name")
    @NotEmpty
    @Length(max = 20)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "department", fetch = FetchType.EAGER)//,orphanRemoval = true
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
