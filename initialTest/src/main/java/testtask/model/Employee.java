package testtask.model;


import net.sf.oval.constraint.*;
import testtask.util.validation.FullName;
import testtask.util.validation.NotALetter;
import testtask.util.validation.UniqueEmployeeEmail;

import java.util.Date;

public class Employee {

    private Integer id;

    @FullName
    private String name;

    @Email(message = "please, enter correct email!")
    @UniqueEmployeeEmail
    private String email;

    @NotNull(message = "Salary cannot be empty")
    @NotNegative(message = "Salary cannot be negative")
    private Integer salary;

    @NotNull(message = "date cannot have a null value!")
    private Date birthDate;

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
