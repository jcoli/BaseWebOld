package system.base.util;

import org.apache.log4j.Logger;

public class UtilException extends Exception {
    
        final static Logger logger = Logger.getLogger(UtilException.class);

	public UtilException() {
		// TODO Auto-generated constructor stub
	}

	public UtilException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UtilException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UtilException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
