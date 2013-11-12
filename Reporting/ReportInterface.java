package Reporting;

/**
 *ReportInterface
 *Class to log errors and other general actions that should be recorded
 *@author Common Infrastructure
 */
public class ReportInterface {

	private static Report logger;
	private static int verbosityThreshold;
	
    /**
     *Constructor
     */
	private ReportInterface() {
	}
	
    /**
     *Initializes the ReportInterface
     */
	private static void init(){
		System.out.println("Made a report object");
		logger = new Report();
	}
	
    /**
     *Logs an error
     *@param s: the error message to be logged
     */
	public static void logError(String s){
		if(logger == null){
			init();
		}
		
		logger.logMessage(0,s,true);
		
	}
	
    /**
     *Logs some info
     *@param level: the priority level s: the message to log
     */
	public static void logInfo(int level, String s) {
	
		if(logger == null){
			init();
		}
		
		if(level <= verbosityThreshold){
			logger.logMessage(level, s, false);
		}
		
	}
	
    /**
     *Sets a threshold for what priority of info should be logged
     *@param l: the level they want to set it at
     */
	public static void setVerbosity(int l) {
		verbosityThreshold = l;
		
		if(verbosityThreshold > 3)
			verbosityThreshold = 3;
			
		if(verbosityThreshold < 0)
			verbosityThreshold = 0;
			
	}
	
}