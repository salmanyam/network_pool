/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nirjhor
 */

public class ReadThread implements Runnable
{
	private Thread thr;
	private NetworkUtil nc;
	public Store st;
	public int p;
	public int status = 0;
	

	public ReadThread(NetworkUtil nc) 
	{
		this.nc = nc;
		this.thr = new Thread(this);
		thr.start();
	}
	
	public ReadThread()
	{
	}
	
	public void run() 
	{
		try
		{
			while(true)
			{
				Object o=nc.read();
				System.out.println ("1st : " + o);  
				System.out.println(o.getClass());
				if(o instanceof Store)
				{	
					System.out.println ("2nd : "+ o);  					
					Store d=(Store)o;
					set(d);
				
				}
				
				if(o instanceof String)	
				{
					String d = (String) o;
					setint(d);
				}			
			}
		}catch(Exception e)
		{
			System.out.println (e);                        
		}			
                nc.closeConnection();
		
	}
	
	public void set(Store o)
	{
		status = 1;
		st = o;
	}	
	public void  setint(String o)
	{
		status = 1;
		p = Integer.parseInt(o);
	}	
		
	public Store get()
	{
	//	status = 1;	
		return st;
	}	
	public int  getint()
	{		
		System.out.println(p);
		return p;
	}	
	
}



