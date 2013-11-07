import java.lang.Thread;
import Reporting.ReportInterface;
import Messages.*;
import CommonInfrastructure.*;

public class TestMain {

	public static void main(String [] args) {
	
		//Make the components
		System.out.println("Creating necessary components");
      	ServerInterface.init();
      	ReportInterface.setVerbosity(3);
		
		
	}
	
}
