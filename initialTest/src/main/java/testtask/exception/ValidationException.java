package testtask.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

    private Map<String, String> mapError = new HashMap<>();

    public ValidationException(Map<String, String> mapError) {
        this.mapError = mapError;
    }

    public Map<String, String> getMapError() {
        return mapError;
    }
}
