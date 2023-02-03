package system.base.util;

import org.apache.log4j.Logger;

public class RNException extends Exception {
    
        final static Logger logger = Logger.getLogger(RNException.class);

	public RNException() {
		// TODO Auto-generated constructor stub
	}

	public RNException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RNException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public RNException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RNException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}


}
