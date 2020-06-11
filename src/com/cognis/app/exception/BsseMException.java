package com.cognis.app.exception;

public class BsseMException extends Exception{
    //##01 BEGIN
    public BsseMException() {
        super();
    }

   
    public BsseMException(String message) {
        super(message);
    }

  
    public BsseMException(String message, Throwable cause) {
        super(message, cause);
    }

  
    public BsseMException(Throwable cause) {
        super(cause);
    }
    //##01 END
}
