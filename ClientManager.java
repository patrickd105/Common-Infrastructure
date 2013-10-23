import java.io.*;
import java.net.*;
import java.security.*;
import java.util.ArrayList;

/**
 * Title:        Sample Server
 * Description:  This utility will accept input from a socket, posting back to the socket before closing the link.
 * It is intended as a template for coders to base servers on. Please report bugs to brad at kieser.net
 * Copyright:    Copyright (c) 2002
 * Company:      Kieser.net
 * @author B. Kieser
 * @version 1.0
 */

public class ClientManager {

  private int port, maxConnections;
  private ArrayList<Listener> clientList;
  private MessageHandler handler;
  //TODO: Need some sort of structure for storing the threads
  
  //Constructor
  public ClientManager(int p, int mc, MessageHandler mh) {
  	port = p;
  	maxConnections = mc;
  	clientList = new ArrayList<Listener>();
  	handler = mh;
  }
  	
  
  
  
  // Listen for incoming connections and handle them
  public void acceptConnections() {
    int i=0;

    try{
      ServerSocket ioSock = new ServerSocket(port);
      Socket server;

	  System.out.println("Accepting connections");
	  
      while((i < maxConnections) || (maxConnections == 0)){
      
        server = ioSock.accept();
        System.out.println("Accepted a new client");
        Listener conn_c= new Listener(server, i, handler);
        clientList.add(conn_c);
        Thread t = new Thread(conn_c);
        t.start();
        
        i++;
      }
    } catch (IOException ioe) {
      System.out.println("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }

}