/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rifat
 */

public class ReadThreadServer implements Runnable
{
	private Thread thr;
	private NetworkUtil nc;
	public Server server;
	public int i = 1;
	

	public ReadThreadServer(Server sv,NetworkUtil nc) 
	{
		this.server=sv;
		this.nc = nc;
		this.thr = new Thread(this);
		thr.start();
	}
	
	public void run() 
	{
		try
		{
			while(true)
			{
				Object o=nc.read();
			
				if(o instanceof Store)
				{
					Store d=(Store)o;		
					System.out.println (d);				
					this.sendOthers(d);
				}
				
				else if(o instanceof String)
				{
					System.out.println(o);
					nc.write(""+(server.count-1));
					i++;
				}			
			}
		}catch(Exception e)
		{
			System.out.println (e);                        
		}			
        nc.closeConnection();
		
	}	
		
	public void sendOthers(Store data)
	{
		try
		{
		
		for(int i=0;i<this.server.clients.size();i++)
		{
			ClientInfo ci=this.server.clients.get(i);
			System.out.println (ci.name + " " + ci.nc);
			if(!ci.name.equals(""+data.networkPlayer)) 
			{
				System.out.println ("Sending to : " + ci.name);
				ci.nc.write(data);
			}	
		}
		}catch(Exception e)
		{
			System.out.println (e);
		}
	}	
		
	
	
}



