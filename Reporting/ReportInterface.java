package Reporting;


public class ReportInterface {

	private static Report logger;
	private static int verbosityThreshold;
	
	private ReportInterface() {
	}
	
	private static void init(){
		System.out.println("Made a report object");
		logger = new Report();
	}
	
	public static void logError(String s){
		if(logger == null){
			init();
		}
		
		logger.logMessage(0,s,true);
		
	}
	
	public static void logInfo(int level, String s) {
	
		if(logger == null){
			init();
		}
		
		if(level <= verbosityThreshold){
			logger.logMessage(level, s, false);
		}
		
	}
	
	public static void setVerbosity(int l) {
		verbosityThreshold = l;
		
		if(verbosityThreshold > 3)
			verbosityThreshold = 3;
			
		if(verbosityThreshold < 0)
			verbosityThreshold = 0;
			
	}
	
}