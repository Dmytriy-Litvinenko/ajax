package testtask.exception;

import java.sql.SQLException;

public class DAOException extends Exception{

    public DAOException () {
        super();

    }
    public DAOException (String message, Throwable cause) {
        super(message,cause);
        //this.printStackTrace(System.out);
    }

}
