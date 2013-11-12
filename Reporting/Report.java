package Reporting;

import java.io.*;

/**
 *Report
 *The actual Report logger
 *@author Common Infrastructure
 */
class  Report
{
   //private member variables 
   private File logRecord;
   private PrintWriter logRecordOutput;
   private PrintWriter errorRecordOutput;

   /**
    *constructor instantiates the files and PrintWriter objects
    */
   public Report()
   {
      try
      {
      this.logRecord = new File("logRecords.txt");
      this.logRecordOutput = new PrintWriter(this.logRecord);
      
      System.out.println(" -- Log files created -- ");
      
      }catch(FileNotFoundException e)
      {
         System.out.println("Error opening the output files");
      }
   }

    /**
     *logs in a client to record where the report came from
     *@param ID: the clientID
     */
   public void newLogin(int ID)
   {
      this.logRecordOutput.println("LOGIN client with ID = " + ID + "\n");
      this.logRecordOutput.flush();
   }
    /**
     *logs out a client
     *@param ID: the clientID to log out
     */
   public void logout(int ID)
   {
      this.logRecordOutput.println("LOGOUT client with ID = " + ID + "\n");
      this.logRecordOutput.flush();
   }
   
   /**
    *the method to log a method
    *@param level: priority of the message s: the message isError: whether or not it's an error
    */
   public void logMessage(int level, String s, boolean isError)
	{
		if(!isError)
		{
			switch(level)
			{
				case 0:
					logRecordOutput.print("URGENT - ");
					break;
				case 1:
					logRecordOutput.print("IMPORTANT - ");
					break;
				case 2:
					logRecordOutput.print("INFO - ");
					break;
				case 3:
					logRecordOutput.print("DEBUG - ");
					break;	
				default:
					logRecordOutput.print("DEBUG - ");
			}
		}
		else
		{
			logRecordOutput.print("*** ERROR *** : ");
		}
		
		logRecordOutput.println(s);
		logRecordOutput.flush();
		
	}									
   
   
   
   /**
    *Finalize the report
    */
   protected void finalize()
   {
      this.errorRecordOutput.close();
      this.logRecordOutput.close();
   }
}
