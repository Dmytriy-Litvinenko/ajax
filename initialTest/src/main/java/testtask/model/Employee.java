package testtask.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;
import testtask.util.validation.FullName;
import testtask.util.validation.OvalUniqueEmail;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @FullName
    private String name;

    @Email(message = "please, enter correct email!")
    @CheckWith(value = OvalUniqueEmail.class, message = "Not Unique email")
    private String email;

    @NotNull(message = "enter number!")
    @NotNegative(message = "Salary cannot be negative")
    private Double salary;

    @NotNull(message = "date cannot have a null value!")
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;/**/

    //@JsonIgnore
    public Department getDepartment() {
        return department;
    }/**/

    public void setDepartment(Department department) {
        this.department = department;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    //public Integer getDepartmentId() {return departmentId; }

    //public void setDepartmentId(Integer departmentId) {this.departmentId = departmentId;}

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
