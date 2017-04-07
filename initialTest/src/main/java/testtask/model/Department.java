package testtask.model;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import testtask.util.validation.NotRussianLetters;
import testtask.util.validation.OvalUniqueDepartmentName;
import testtask.util.validation.OvalUniqueEmail;
import testtask.util.validation.UniqueDepartmentName;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "departments")
public class Department {

    @Id
    private Integer id;

    @CheckWith(value = OvalUniqueDepartmentName.class, message = "Not Unique department name")
    //@UniqueDepartmentName
    @NotEmpty
    @Length(max = 20)
    //@NotRussianLetters
    private String name;

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
