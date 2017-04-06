package testtask.exception;

import java.sql.SQLException;

public class DAOException extends Exception{

    public DAOException () {
        super();

    }
    public DAOException (String message) {
        super(message);
        this.printStackTrace(System.out);
    }

}
