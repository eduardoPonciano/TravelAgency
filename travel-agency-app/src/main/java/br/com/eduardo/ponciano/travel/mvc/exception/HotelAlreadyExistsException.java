package br.com.eduardo.ponciano.travel.mvc.exception;

public class HotelAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HotelAlreadyExistsException() {
        super();
    }

    public HotelAlreadyExistsException(String message) {
        super(message);
    }

    public HotelAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
