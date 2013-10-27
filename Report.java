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
   private File errorRecord;
   private PrintWriter logRecordOutput;
   private PrintWriter errorRecordOutput;

   //constructor instantiates the files and PrintWriter objects
   public Report()
   {
      try
      {
      this.logRecord = new File("logRecords.txt");
      this.logRecordOutput = new PrintWriter(this.logRecord);
      this.errorRecord = new File("errorRecords.txt");
      this.errorRecordOutput = new PrintWriter(this.errorRecord);
      
      }catch(FileNotFoundException e)
      {
         System.out.println("Error opening the output files");
      }
   }

   public void newLogin(int ID)
   {
      this.logRecordOutput.println("LOGIN client with ID = " + ID + "\n");
   }
   public void logout(int ID)
   {
      this.logRecordOutput.println("LOGOUT client with ID = " + ID + "\n");
   }
   public void reportError(String errorMessage)
   {
      this.errorRecordOutput.println(errorMessage);
   }
   protected void finalize()
   {
      this.errorRecordOutput.close();
      this.logRecordOutput.close();
   }
}
