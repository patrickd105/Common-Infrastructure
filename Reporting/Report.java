package Reporting;

//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE//
//                                                                               //
// Class Name: Report                                                     //
// Functinality: keep track of user logins, logouts and errors in output files   //
// Version: 1.0                                                                  //
// Created: 10/24/2013                                                           //
//                                                                               //
//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE//

import java.io.*;

class  Report
{
   //private member variables 
   private File logRecord;
   private PrintWriter logRecordOutput;
   private PrintWriter errorRecordOutput;

   //constructor instantiates the files and PrintWriter objects
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

   public void newLogin(int ID)
   {
      this.logRecordOutput.println("LOGIN client with ID = " + ID + "\n");
      this.logRecordOutput.flush();
   }
   public void logout(int ID)
   {
      this.logRecordOutput.println("LOGOUT client with ID = " + ID + "\n");
      this.logRecordOutput.flush();
   }
   
   
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
   
   
   
   
   protected void finalize()
   {
      this.errorRecordOutput.close();
      this.logRecordOutput.close();
   }
}
