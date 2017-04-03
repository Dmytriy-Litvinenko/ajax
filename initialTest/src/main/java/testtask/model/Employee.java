package testtask.model;


import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import testtask.util.validation.UniqueEmployeeEmail;

import java.util.Date;

public class Employee {

    private Integer id;

    private String name;

    //@Email(message = "Email incorrect")
    @UniqueEmployeeEmail
    private String email;

    @NotNull
    private Integer salary;

    @NotNull
    private Date birthDate;

    //@Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Integer departmentId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

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
