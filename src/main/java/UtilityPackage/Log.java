package UtilityPackage;

import org.apache.log4j.Logger;

public class Log {
	
private static Logger log = Logger.getLogger(Log.class.getName());
	
	public static void testStart(String sTestName){
		log.info("************* "+sTestName + " going to Execute *************");
	}
	
	public static void endTest (String sTestName){
		log.info("************* "+sTestName + " Execution Completed *************");
	}
	
	public static void warn(String message){
		log.warn(message);
				
	}
	
	public static void error(String message){
		log.error(message);
	}
	
	public static void fatal (Exception e){
		log.fatal(e);
	}
	
	public static void fatal (String message){
		log.fatal(message);
	}
	
	public static void info (String message){
		log.info(message);
	}
	public static void debug (String message){
		log.debug(message);
	}

}
