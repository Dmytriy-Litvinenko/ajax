package testtask.model;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import testtask.util.validation.UniqueDepartmentName;

public class Department {

    private Integer id;

    @UniqueDepartmentName
    @NotEmpty
    @Length(max=20)
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
