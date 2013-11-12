package CommonInfrastructure;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.ArrayList;
import Reporting.*;
import Messages.*;

/**SOME CODE TAKEN FROM THIS SOURCE
 * Title:        Sample Server
 * Description:  This utility will accept input from a socket, posting back to the socket before closing the link.
 * It is intended as a template for coders to base servers on. Please report bugs to brad at kieser.net
 * Copyright:    Copyright (c) 2002
 * Company:      Kieser.net
 * @author B. Kieser
 * @version 1.0
 */

/**
 *ClientManager
 *Manages the clients that are connected to the server
 *@author Common Infrastructure
 */
class ClientManager implements Runnable {

  private int port, maxConnections;
  private ArrayList<Listener> clientList;
  private ArrayList<Thread> threadList;
  private MessageHandler handler;
  //TODO: Need some sort of structure for storing the threads
  
  /**
   *Constructor that initializes the global variables
   *@param p: port number. mc: max connections. mh: the MessageHandler
   */
  public ClientManager(int p, int mc, MessageHandler mh) {
  	port = p;
  	maxConnections = mc;
  	clientList = new ArrayList<Listener>();
	threadList = new ArrayList<Thread>();
  	handler = mh;
  }
  	
    /**
     *Stops a specified client thread
     *@param id: the id of the client thread to be stopped
     */
  public void stopThread(int id) {
  	try{
  		ReportInterface.logInfo(3,"Attempting to stop thread " + id);
  		
  		clientList.get(id).close();
  		//threadList.get(id).sleep(300);
		threadList.get(id).join();
	}
	catch(InterruptedException e)
	{
		ReportInterface.logError("stopThread error: "+ e);
	}
  }
  
  
  /**
   *Run stub for the thread
   */
  public void run() {
  	acceptConnections();
  }
  
  /**
   *Listens for incoming connections and handle them
   */
  public void acceptConnections() {
    int currentClientNum=0;

    try{
      ServerSocket ioSock = new ServerSocket(port);
      Socket server;

	  ReportInterface.logInfo(1,"Accepting connections");
	  
      while((currentClientNum < maxConnections) || (maxConnections == 0)){
      
        server = ioSock.accept();
        ReportInterface.logInfo(2,"Accepted a new client");
        System.out.println("Client Connected");
        Listener conn_c= new Listener(server, currentClientNum, handler);
        clientList.add(conn_c);
        Thread t = new Thread(conn_c);
        t.start();
        threadList.add(t);
        currentClientNum++;
      }
    } catch (IOException ioe) {
      ReportInterface.logError("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }
  
  /**
   *Returns a Listener of the specified id
   *@param id: the id of the Listener to be returned
   *@return the Listener of the specified id
   */
  public Listener getListener(int id){
  	return clientList.get(id);
  }
  
}