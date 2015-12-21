/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rifat
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{
	private ServerSocket ServSock;
	ArrayList<ClientInfo> clients;
	int count=1;
		
	Server()  
	{
		try
		{			
		this.clients=new ArrayList<ClientInfo>();	
		ServSock = new ServerSocket(33333);
		while (true)
		{
			ServerThread m = new ServerThread(this,ServSock.accept());
		}
		}catch(Exception e)
		{
			System.out.println("Server starts:"+e);
		}
	}
	
	public void display()
	{
		System.out.println ("***********************");
		for(int i=0;i<clients.size();i++)
		{
			ClientInfo ci=clients.get(i);
			System.out.println (ci.name + "  " + ci.nc);
		}
		
		
		
		
		System.out.println ("***********************");

		
	}
	public static void main(String args[]) 	throws UnknownHostException, IOException 
	{
		Server objServer = new Server();
	}
}

 class ServerThread implements Runnable
 {
	private Socket ClientSock;
	private Thread thr;
	private NetworkUtil nc;
	Server server;

	ServerThread(Server sv,Socket client) 
	{
		this.server=sv;
		this.ClientSock = client;
		this.thr = new Thread(this);
		thr.start();
	}

	public void run() 
	{
		this.nc=new NetworkUtil(ClientSock);
		ClientInfo cinfo=new ClientInfo(""+this.server.count++,this.nc);			
		this.server.clients.add(cinfo);
		this.server.display();		
		new ReadThreadServer(this.server,this.nc);
	//	new WriteThread(this.nc,"Server");					
	}
}