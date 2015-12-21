/**
 * @(#)StartFrame.java
 *
 *
 * @author Nirjhor
 * @version 1.00 2008/10/28
 */
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*; 
import java.awt.event.*;
import java.applet.*;
import java.net.*;

 public class StartFrame extends JFrame implements MouseListener
 {
	
	private ImageIcon pic = new ImageIcon(getClass().getResource("background.png"));
	private JLabel label = new JLabel();		
	private FlowLayout layout;
	private URL winurl;
	public AudioClip Win;
	
	
	
    public StartFrame() 
    {
    	super( "POOL GAME" );
    	layout = new FlowLayout();
    	label.setIcon( pic );    	
    	add ( label );    	
    	addMouseListener( this ); 
	   	try
		{	
			winurl = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/pool.mid" );
		}catch (MalformedURLException e) 
	    {
	         System.err.println(e.getMessage());
	     }    	
	
		Win = Applet.newAudioClip(winurl);
		Win.loop();
	   	
    		
    }
    
    public void mousePressed( MouseEvent event )
    {
    	Win.stop();
    	setVisible(false);
    	dispose();    	
    	
    }
    
    public void mouseClicked( MouseEvent event )
    {
    	Win.stop();
    	setVisible(false);
    	dispose();    
    }
    
    public void mouseReleased( MouseEvent event )
    {
    }
    
    public void mouseEntered( MouseEvent event )
    {
    }
    
    public void mouseExited( MouseEvent event )
    {
    }
    
    
    
}