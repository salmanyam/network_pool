
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.lang.*;
import java.util.Random;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.*;

class Store implements Serializable
{
	public AudioClip Hit, Wall;
	private  URL Hiturl, Wallurl;
	int len = 0,  k, j, powerTake, shoot,width = 0, px, py, newgameFlag = 0; 
	int board = 1, w = 0, bb = 0 , r = 0 , stopcount;
	double result = 0, a = 286, b = 322, m, a1, a2, b1, b2, c1, c2, outerBall=500.0;
	double dx = 0.01, dy = 0.01, v, powerx;
    ImageIcon image, background,Queball,ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10, demoImage, prl1, prl2, tempr, scoreboard; 
    int mouseX, mouseY, var = 0, pbwidth = 0;
    boolean allstopped=false , gamefinished = false , boardfinished = false, soundflag = true, networkFlag = false;
    Ball ball[] = new Ball[11];
    Ball demo;
    int score1 = 0, score2 = 0,c = 0, click1 = 1, click2 = 0, ctrl = 0, click11 = 1, click12 = 0; 
    int temp, plr1 = 1, plr2 = 2, networkPlayer = 1,player = 1, firstPlayer = 1;
    int ontable = 0, count = 0, ontable1 = 1, ballinhand = 0, soundint, ondemo, onQue, FoulQue = 0;
    double QueX, QueY, linex, liney, fwidth = 460;
   
  
    double  z;
    String p1, p2, p[] = new String[2];
    String  str1, str2, temp1, temp2;
    
    Font font1 = new Font("Helvetica", Font.PLAIN + Font.BOLD + Font.TRUETYPE_FONT,  22);
    Font font2 = new Font("TimesRoman", Font.PLAIN,  20);
    Font font3 = new Font("Courier", Font.PLAIN,  18);

    Font font4 = new Font("Helvetica", Font.BOLD,  16);
    Font font5 = new Font("Helvetica", Font.ITALIC,  16);
    Font font6 = new Font("Helvetica", Font.BOLD + Font.ITALIC,  16);
    Font font7 = new Font("Helvetica", Font.BOLD,  14);
    
    public Store()
    {
    	
    }
    public Store(PoolPanel ob)
    {
    	demo = new Ball(0, 0, 22.0, 0);
    	networkFlag = ob.networkFlag;
    	
    	ball = ob.ball;
    	plr1 = ob.plr1;
    	plr2 = ob.plr2;
    	board = ob.board;
    	temp = ob.temp;
    	c = ob.c;
    	len = ob.len;
    	player = ob.player; firstPlayer = ob.firstPlayer;
    	networkPlayer = ob.networkPlayer;
    	newgameFlag = ob.newgameFlag;
    	k = ob.k; j = ob.j; powerTake = ob.powerTake; shoot = ob.shoot;width = ob.width; px = ob.px; py = ob.py;
    	w = ob.w; bb = ob.bb; r = ob.r; stopcount = ob.stopcount;
    	result = ob.result; a = ob.a; b = ob.b; m = ob.m; a1 = ob.a1; a2 = ob.a2; b1 = ob.b1; b2 = ob.b2; c1 = ob.c1; c2 = ob.c2; outerBall = ob.outerBall;
    	v = ob.v; powerx = ob.powerx;
    	var = ob.var; pbwidth = ob.pbwidth;
    	allstopped = ob.allstopped; gamefinished = ob.gamefinished; boardfinished = ob.boardfinished; soundflag = ob.soundflag; networkFlag = ob.networkFlag;
    	score1 = ob.score1; score2 = ob.score2; click1 = ob.click1; click2 = ob.click2; ctrl = ob.ctrl;
    	ontable = ob.ontable; count = ob.count; ontable1 = ob.ontable1; ballinhand = ob.ballinhand; soundint = ob.soundint; ondemo = ob.ondemo; onQue = ob.onQue; FoulQue = ob.FoulQue;
    	z = ob.z; QueX = ob.QueX; QueY = ob.QueY; linex = ob.linex; liney = ob.liney; fwidth = ob.fwidth;
    	
    	if(networkFlag)
    	{
  	     	p1 = ob.p[ob.networkPlayer]; 
    	} 
    	else
    	{
  	    	p = ob.p;
    	}
    	demo.x = ball[10].x + 50;
    	demo.y = ball[10].y ;
    	dx = ( demo.x + demo.diameter / 2) - (ball[10].x + ball[10].diameter / 2 );
    	dy = ( demo.y + demo.diameter / 2) - (ball[10].y + ball[10].diameter / 2 );
    }
}
class PoolPanel extends JPanel	implements MouseListener, MouseMotionListener,Runnable, Serializable
{
	public AudioClip Hit, Wall, Congrat, Wall2, Quefall;
	private  URL Hiturl, Wallurl, Congraturl, Wall2url, Queurl;
	int len = 0,  k, j, powerTake, shoot,width = 0, px, py, newgameFlag = 0, networkPlayer = 1; 
	int board = 1, w = 0, bb = 0 , r = 0 , stopcount;
	double result = 0, a = 286, b = 322, m, a1, a2, b1, b2, c1, c2, outerBall=500.0;
	double dx = 0.01, dy = 0.01, v, powerx;
    ImageIcon image, background,Queball,ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10, demoImage, prl1, prl2, tempr, scoreboard, faul; 
    int mouseX, mouseY, var = 0, pbwidth = 0;
    boolean allstopped=false , gamefinished = false , boardfinished = false, soundflag = true, networkFlag = false, queFaul = false;
    Ball ball[] = new Ball[11];
    Ball demo;
    int score1 = 0,c = 0, score2 = 0, click1 = 1, click2 = 0, ctrl = 0; 
    int temp, plr1 = 1, plr2 = 2, player = 1, firstPlayer = 1;
    int ontable = 0, count = 0, ontable1 = 1, ballinhand = 0, soundint, ondemo, onQue, FoulQue = 0;
    double QueX, QueY, linex, liney, fwidth = 460;
    
  
    double  z;
    String p1, p2, p[] = new String[2];
    static String  str1, str2, temp1, temp2;
    public Thread game;
    Font font1 = new Font("Helvetica", Font.PLAIN + Font.BOLD + Font.TRUETYPE_FONT,  22);
    Font font2 = new Font("TimesRoman", Font.PLAIN,  20);
    Font font3 = new Font("Courier", Font.PLAIN,  18);

    Font font4 = new Font("Helvetica", Font.BOLD,  16);
    Font font5 = new Font("Helvetica", Font.ITALIC,  16);
    Font font6 = new Font("Helvetica", Font.BOLD + Font.ITALIC,  16);
    Font font7 = new Font("Helvetica", Font.BOLD,  14);
    
    NetworkUtil nc ;
    ReadThread tp;
    
    double bc[][] = {
                     	{550, 300},
                        {580, 280},
                        {580, 315},
                        {610, 263},
                        {610, 295},
                        {610, 328},
                        {640, 240},
                        {640, 275},
                        {640, 310},
                        {640, 345}
    
    
                    };
    
    
   
  public void modify(Store ob)
  {
    	ball = ob.ball;
    	plr1 = ob.plr1;
    	plr2 = ob.plr2;
    	board = ob.board;
    	temp = ob.temp;
    	len = ob.len;
    	networkPlayer = ob.networkPlayer;
    	player = ob.player;
    	firstPlayer = ob.firstPlayer;
    	newgameFlag = ob.newgameFlag;
    	k = ob.k; j = ob.j; powerTake = ob.powerTake; shoot = ob.shoot;width = ob.width; px = ob.px; py = ob.py;
    	w = ob.w; bb = ob.bb; r = ob.r; stopcount = ob.stopcount;
    	result = ob.result; a = ob.a; b = ob.b; m = ob.m; a1 = ob.a1; a2 = ob.a2; b1 = ob.b1; b2 = ob.b2; c1 = ob.c1; c2 = ob.c2; outerBall = ob.outerBall;
    	v = ob.v; powerx = ob.powerx;
    	var = ob.var; pbwidth = ob.pbwidth;
    	allstopped = ob.allstopped; gamefinished = ob.gamefinished; boardfinished = ob.boardfinished; soundflag = ob.soundflag; networkFlag = ob.networkFlag;
    	score1 = ob.score1; score2 = ob.score2; click1 = ob.click1; click2 = ob.click2; ctrl = ob.ctrl;
    	ontable = ob.ontable; count = ob.count; ontable1 = ob.ontable1; ballinhand = ob.ballinhand; soundint = ob.soundint; ondemo = ob.ondemo; onQue = ob.onQue; FoulQue = ob.FoulQue;
    	z = ob.z; QueX = ob.QueX; QueY = ob.QueY; linex = ob.linex; liney = ob.liney; fwidth = ob.fwidth;
    	
    	if(networkFlag)
    	{
  	     	p1 = ob.p[ob.networkPlayer]; 
    	} 
    	else
    	{
  	    	p = ob.p;
    	}
    	demo.x = ball[10].x + 50;
    	demo.y = ball[10].y ;
    	dx = ( demo.x + demo.diameter / 2) - (ball[10].x + ball[10].diameter / 2 );
    	dy = ( demo.y + demo.diameter / 2) - (ball[10].y + ball[10].diameter / 2 );
    	
    	repaint();
  }	
  public PoolPanel()
  {   
   
    image = new ImageIcon(getClass().getResource("table.png"));
     
    
    background = new ImageIcon(getClass().getResource("table2.png"));
    Queball = new ImageIcon(getClass().getResource("Queball.png"));
    ball1 = new ImageIcon(getClass().getResource("ball1.png"));
    ball2 = new ImageIcon(getClass().getResource("ball2.png"));
    ball3 = new ImageIcon(getClass().getResource("ball3.png"));
    ball4 = new ImageIcon(getClass().getResource("ball4.png"));
    ball5 = new ImageIcon(getClass().getResource("ball5.png"));
    ball6 = new ImageIcon(getClass().getResource("ball6.png"));
    ball7 = new ImageIcon(getClass().getResource("ball7.png"));
    ball8 = new ImageIcon(getClass().getResource("ball8.png"));
    ball9 = new ImageIcon(getClass().getResource("ball9.png"));
    ball10 = new ImageIcon(getClass().getResource("ball10.png"));
    demoImage = new ImageIcon(getClass().getResource("circle.png"));
    prl1 = new ImageIcon(getClass().getResource("prl1.png"));
    prl2 = new ImageIcon(getClass().getResource("prl2.png"));
    tempr = new ImageIcon(getClass().getResource("tempr.png"));
    scoreboard = new ImageIcon(getClass().getResource("scoreboard.png"));
    faul = new ImageIcon(getClass().getResource("faul.png"));
    
    str1 = "PLAYER1";
    str2 = "PLAYER2";
    
    for(int i = 0; i < 10; i++)
    {
    	ball[i] = new Ball(bc[i][0], bc[i][1], 22.0, 1.0);
    	ball[i].setvxvy(0.0, 0.0);
    	
    }
    try{
    	
    	Hiturl = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/BUMPLOUD.wav" );
    	Wallurl = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/drop.wav" );
    	Congraturl = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/CONGRATS.wav" );
    	Wall2url = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/BUMPER.wav" );
    	Queurl = new URL( "file:" + System.getProperty("user.dir") + "/"+"sound/BOMB.wav" );
    }catch (MalformedURLException e) 
     {
          System.err.println(e.getMessage());
     }
    Hit = Applet.newAudioClip(Hiturl);
    Wall = Applet.newAudioClip(Wallurl);
    Congrat = Applet.newAudioClip(Congraturl);
    Wall2 = Applet.newAudioClip(Wall2url);
    Quefall = Applet.newAudioClip(Queurl);
   
     
    ball[10] = new Ball(360, 300, 22, 3);
    ball[10].setvxvy(0.0, 0.0);
    
    demo = new Ball(ball[10].x + 30, ball[10].y + 30, 22, 0);
    game = new Thread(this);
    game.start();
    
    
    addMouseListener(this);
    addMouseMotionListener(this);

       
  }  
  
  
  public void mouseClicked( MouseEvent me ) {
  	
  	mouseX = me.getX();
  	mouseY = me.getY();
  	
  	
  	if((( mouseX > 120 ) && ( mouseX < 795 ))  &&  (( mouseY > 130 ) && ( mouseY < 465 )))
  	{
  		ontable = 1;
  		//click = 1;
  	}
    else
    {
    	ontable = 0;
    	//click = 0;
    }
    	
    	
    if( ontable == 1 )
    {
    	demo.x = ball[10].x + 50;
    	demo.y = ball[10].y ;
    	dx = ( demo.x + demo.diameter / 2) - (ball[10].x + ball[10].diameter / 2 );
    	dy = ( demo.y + demo.diameter / 2) - (ball[10].y + ball[10].diameter / 2 );
    //	linex = ( ball[10].x+ ball[10].diameter / 2 ) + ( dx * 4 );
    //	liney = (ball[10].y + ball[10].diameter / 2 ) + ( dy * 4 );
    	
    	repaint();
    	
    }
    
   
   
  
  	//result = Math.tan(60 * Math.PI / 180);	
  	//JOptionPane.showMessageDialog(null,  mouseX + "," + mouseY);
  
 //   var = 1;
 
 //   repaint();
 
    
   //  if(me.getX() >= 880 && me.getX() <= 930 && me.getY() >= 200 && me.getY() <= 500)
  	  
   // getpower();
   
   
    a = demo.x + 11;
    b = demo.y + 11;
  	
  	if(a <= 785 && b <= 455 && b >= 113 && a >= 121)
  	{
  	
     	m = ((ball[10].y + 11) - (double)b) / ((ball[10].x + 11) - (double)a);
    	result = Math.toDegrees(Math.atan(m));
  	
 
    	if(a > ball[10].x + 11)
    	{
        	a1 = 11 * Math.cos(result * Math.PI / 180)  ;
        	a2 = 11 * Math.sin(result * Math.PI / 180)  ;
    	
        	b1 = 120 * Math.cos(result * Math.PI / 180);
        	b2 = 120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = 110 * Math.cos(result * Math.PI / 180);
        	c2 = 110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1 ;
        //	demo.y = ball[10].y + b2 ;
    	
    	
    	
    	}
     	else if(a < ball[10].x + 15)
    	{
     		a1 = -11 * Math.cos(result * Math.PI / 180)  ;
        	a2 = -11 * Math.sin(result * Math.PI / 180)  ;
    	
        	b1 = -120 * Math.cos(result * Math.PI / 180);
        	b2 = -120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = -110 * Math.cos(result * Math.PI / 180);
        	c2 = -110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1;
        //	demo.y = ball[10].y + b2;
     	}
  	
     	//JOptionPane.showMessageDialog(null,  a1 + "," + a2);
  	
  	
  	}
  //	z =Math.toDegrees(Math.acos(a1/11.0));
  //	JOptionPane.showMessageDialog(null, z);	
   	repaint(); 
  
  }

  public void mouseEntered( MouseEvent me )
  {
  	
  	
  
  	
  }

  public void mouseExited( MouseEvent me )
  {
  	
  	
  }

  public void mousePressed( MouseEvent me )
  {  
  	  mouseX = me.getX();
  	  mouseY = me.getY();
  	  
  	  if(me.getX() >= 880 && me.getX() <= 930 && me.getY() >= 200 && me.getY() <= 500)
  	       powerTake = 1;
  	  
  	  else 
  	  	powerTake = 0;
  	
  	if(((mouseX > ball[10].x) && (mouseX < (ball[10].diameter + ball[10].x))) && ((mouseY > ball[10].y) && (mouseY < (ball[10].diameter + ball[10].y))))
  	{
  			onQue = 1;
  			ontable1 = 0;
  	}
    else 
    {
    	onQue = 0;  
    	ontable1 = 1;
    }	
  	  
  	if((( mouseX > demo.x ) && ( mouseX < ( demo.x + demo.diameter )))  && (( mouseY > demo.y ) && ( mouseY < ( demo.y + demo.diameter ))))
    {
    	ondemo = 1;
    
    }
    	
    else
    {
    	ondemo = 0;
    }
 
 /*  
    if((( mouseX > 120 ) && ( mouseX < 770 ))  &&  (( mouseY > 113 ) && ( mouseY < 435 )))
    {	
    	ontable = 1;	    
	    demo.x = ball[10].x + 5;
	    demo.y = ball[10].y - 40 + 5;
	    dx = ( demo.x + demo.diameter/ 2) - (ball[10].x  + ball[10].diameter / 2 );
	    dy = ( demo.y + demo.diameter / 2) - (ball[10].y + ball[10].diameter / 2 );
	    linex = ( ball[10].x  + ball[10].diameter / 2 ) + ( dx * 4 );
	    liney = ( ball[10].y + ball[10].diameter / 2 ) + ( dy * 4 );	    	
	    repaint();	    
    }	
  	  
 */
  }

  public void mouseReleased( MouseEvent me )
  {
  	if(powerTake == 1)
  	{
  		 powerTake=0;
  		 shoot=1;
  		 
  	//	 JOptionPane.showMessageDialog(null, powerTake + "," + shoot);	 
  	 }
  	 
  	 
  	 
  	 repaint();
  	 
  	 
  }

  public void mouseDragged(MouseEvent me) 
  {
  	mouseX = me.getX();
  	mouseY = me.getY();
  	
  
    
    if(ondemo == 1 && onQue == 0)
    {
    	demo.x = mouseX;
    	demo.y = mouseY;
    	if( demo.x > 770)
    		demo.x = 770 - demo.radius/2 + 5;
		if( demo.x < 125)
    		demo.x = 125 + demo.radius/2 - 5;
    	if( demo.y < 130)
    		demo.y = 130 + demo.radius -8;
		if( demo.y > 446)
    		demo.y = 466 - demo.diameter - 2 ;
    	dx = (demo.x+demo.diameter/2) - (ball[10].x+ball[10].diameter/2);
    	dy = (demo.y+demo.diameter/2) - (ball[10].y+ball[10].diameter/2);
    //	linex = ball[10].x + ball[10].diameter/2 + (dx*6);
     //   liney = ball[10].y + ball[10].diameter/2 + (dy*6);
    		
    }
    
    //a = me.getX();
  //	b = me.getY();
  
   
   if(onQue == 1 && ballinhand == 1)
    {    
    		
 		if((( mouseX > 120 ) && ( mouseX < 795 ))  &&  (( mouseY > 130 ) && ( mouseY < 465 )))
 		{
 			
 			ball[10].x=mouseX;
    		ball[10].y=mouseY;
    	}
 	}
    	
    a = demo.x + 11;
    b = demo.y + 11;
  	
  	if(a <= 785 && b <= 455 && b >= 113 && a >= 121)
  	{
  	
     	m = ((ball[10].y + 11) - (double)b) / ((ball[10].x + 11) - (double)a);
    	result = Math.toDegrees(Math.atan(m));
  	
 
    	if(a > ball[10].x + 11)
    	{
        	a1 = 11 * Math.cos(result * Math.PI / 180)  ;
        	a2 = 11 * Math.sin(result * Math.PI / 180)  ;
    	
        	b1 = 120 * Math.cos(result * Math.PI / 180);
        	b2 = 120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = 110 * Math.cos(result * Math.PI / 180);
        	c2 = 110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1 ;
        //	demo.y = ball[10].y + b2 ;
    	
    	
    	
    	}
     	else if(a < ball[10].x + 11)
    	{
     		a1 = -11 * Math.cos(result * Math.PI / 180)  ;
        	a2 = -11 * Math.sin(result * Math.PI / 180)  ;
    	
        	b1 = -120 * Math.cos(result * Math.PI / 180);
        	b2 = -120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = -110 * Math.cos(result * Math.PI / 180);
        	c2 = -110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1;
        //	demo.y = ball[10].y + b2;
     	}
  	
     	//JOptionPane.showMessageDialog(null,  a1 + "," + a2);
  	
  	
  	}
  	
  		
   	repaint();
  	
    	
  }

  public void mouseMoved(MouseEvent me)
  {
  
  /*
  	a = me.getX();
  	b = me.getY();
  	
  	if(a <= 770 && b <= 435 && b >= 113 && a >= 121)
  	{
  	
     	m = ((ball[10].y + 22) - (double)b) / ((ball[10].x + 15) - (double)a);
    	result = Math.toDegrees(Math.atan(m));
  	
 
    	if(a > ball[10].x + 15)
    	{
        	a1 = 11 * Math.cos(result * Math.PI / 180) ;
        	a2 = 11 * Math.sin(result * Math.PI / 180) ;
    	
        	b1 = 120 * Math.cos(result * Math.PI / 180);
        	b2 = 120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = 110 * Math.cos(result * Math.PI / 180);
        	c2 = 110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1 ;
        //	demo.y = ball[10].y + b2 ;
    	
    	
    	
    	}
     	else if(a < ball[10].x + 15)
    	{
     		a1 = -11 * Math.cos(result * Math.PI / 180) ;
        	a2 = -11 * Math.sin(result * Math.PI / 180) ;
    	
        	b1 = -120 * Math.cos(result * Math.PI / 180);
        	b2 = -120 * Math.sin(result * Math.PI / 180);
    	
        	c1 = -110 * Math.cos(result * Math.PI / 180);
        	c2 = -110 * Math.sin(result * Math.PI / 180);
        	
        //	demo.x = ball[10].x + b1;
        //	demo.y = ball[10].y + b2;
     	}
  	
     	//JOptionPane.showMessageDialog(null,  a1 + "," + a2);
  	
  	
  	
     	repaint();
  	}
  	*/
  	
  } 

 	
  public void getpower()
  {
  	while(shoot == 0)
  	{
  		while(powerTake == 1)
  		{
 			try{
 				Thread.sleep(50);
  				width = width % 300 + 10;
  				repaint();
  				} catch(InterruptedException e){}
  		
  		}  
    } 
    
    v = width / 15;
  	powerx = Math.sqrt((v * v) / ((dx * dx) + (dy * dy)));
    	
    ball[10].setvxvy(powerx * dx * 2 , powerx * dy * 2 );
  	
  //	JOptionPane.showMessageDialog(null, ball[10].vx + "," + ball[10].vy);
  	width = 0;
  }
/*  
  public void QueballChecking()
  {

  	if(ball[10].gone == 1)
  	{
        FoulQue = 1;
  		JOptionPane.showMessageDialog(null , "Foul. Opposition ball in hand");
  		ball[10].x = QueX;
  		ball[10].y = QueY;
  		ball[10].setvxvy(0.0, 0.0);
  		ball[10].gone = 0;
  				
  		
  	}
  	
  }
  */
  public void setball()
  {  
 
   	for(int i=0; i<10; i++)
   	{
   		ball[i].setxy(bc[i][0] , bc[i][1] );
   		ball[i].setvxvy(0.0,0.0);
   	}
   	
	ball[10].setvxvy(0.0,0.0);
  }
  
  public void run()
  {
  	int i = 0; 
   int x = 0;
   int y = 0;
	
   while(gamefinished == false)
   {
   	 
   	 
   	 if(board >=2) setball();
   	 while(boardfinished == false)
	 {	
	 	if(!networkFlag || (networkPlayer == player))
	 	{
	 				
	    	getpower();
		
		
	
	    	while (!allstopped)
	    	{
		    ontable1 = 0;
			for(i = 0 ;  i < 11 ;  i++)
			{
				if(ball[i].gone == 0) ball[i].move();
				
				else if(ball[i].gone ==1) ball[i].stop = 1; 
			}
			
			for(i=0; i <11; i++)
			{
				if( ((ball[i].x > 135 && ball[i].x < 151+c) && (ball[i].y > 135 && ball[i].y < 145+c)) ||((ball[i].x > 123+c && ball[i].x < 143+(c+2)) && (ball[i].y > 440-(c+2) && ball[i].y < 455+c))||((ball[i].x > 433-c && ball[i].x < 475+c) && (ball[i].y > 122 && ball[i].y < 131+c))||
				 ((ball[i].x  > 430-c && ball[i].x  < 465+c) && (ball[i].y > 445-c && ball[i].y < 465)) ||((ball[i].x > 765-c && ball[i].x < 790)&&(ball[i].y > 125 && ball[i].y < 145+c))||
				  ((ball[i].x > 760-c && ball[i].x < 790) && (ball[i].y  > 440-c && ball[i].y  < 460)))
				   
				{
					count++;
					
			
					ball[i].gone = 1;
					ball[i].v=0.0;
					
					
			
						
					if(i >= 0 && i <= 9) 
							bb++;
						
					
						
					else if(i == 10) r++;
				
				//	if(r = 0) Quefall.play();
					if(i == 10)
					{
						 outerBall  = outerBall + 22.0; 
						 Quefall.play();
						 soundflag = false;
						 queFaul = true;
					}
					else
						soundflag = true;   
					if(soundflag) Wall.play();
					ball[i].x = 76;
					outerBall = outerBall - 22.0;
					ball[i].y = outerBall;
						
					repaint();
					/*	
						for(j = 22; j <= 220; j = j + 22)
						{
							for(k = 0; k < 500 - j; k++)
							{
								try{
 			                     	Thread.sleep(50);
 			                     	ball[i].y = k;
 			                     	repaint();
  		
  			                    }catch(InterruptedException e){}
							}
							
						}
					*/
				}

	    	}
				
			for(i = 0 ;  i < 11 ;  i++)
			{
				if(ball[i].gone == 0) checkwallbounce( ball[i] ); 
			}
			
			
			for(i = 0, stopcount = 0; i < 11; i++)
			{
				if(ball[i].stop == 1) stopcount++;
			} 
			
			if(stopcount == 11) allstopped = true;
			
			
			
        	for(x = 0 ;  x < 11 ;  x++)
			{
				for(y = x + 1 ;  y < 11 ;  y++) 
				{
					
					if (separation(ball[x] , ball[y]) <= radsum(ball[x] , ball[y])) 
					{
					    
					    ballinhand = 0;  
						Collide(ball[x] , ball[y]);
				        
				        if(Math.sqrt((ball[x].x - ball[y].x) * (ball[x].x - ball[y].x) - (ball[x].y - ball[y].y) * (ball[x].y - ball[y].y)) <= 22 && bb >= 0 && soundflag)	
				     	
				        	Hit.play();	
				        		
					}
					
								
				}
								
			}
			try 
				{
					 Thread.sleep(40);          
  				}catch (InterruptedException e) {}
			
			repaint();	
  		
		}
		ontable1 = 1;
		for(i=0; i<11; i++) 
		{
			ball[i].setvxvy(0,0);
			ball[i].stop = 0;
		}	
		
		shoot=0;
		stopcount=0;
		allstopped=false;
		
		
		checkplayer();
		checkQue();
		
		if(count == 10)
		{
			JOptionPane.showMessageDialog(null, "BORDFINISHED");
			boardfinished = true;
		}	
		
		r=0;
	 	bb=0;
	 	try
			{
			//	new NetworkUtil(nc.getSocket(),new Store(this));
		    	if(networkFlag )
		     	nc.write(new Store(this));
		     	repaint();
			
			}
			catch(Exception e)
			{
			}
		
		
	 	}
	 	else
	  	 {
	  	 	while(tp.status == 0);
	  	 	System.out.println(tp.status);
        	tp.status = 0;
         		
	  	 	modify(tp.get());
	  	 	repaint();
	  	 }
	//	QueballChecking();
	
	 	
		
		 
  
  	 } // board loop
  	 board++;
     //JOptionPane.showMessageDialog(null, "board " + board);
    // temp = plr1;
  	// plr1 = plr2 ;
  	// plr2 = plr1;
  /*	if(score1 >= 15) 
  	{
  		JOptionPane.showMessageDialog(null, "p1 has won the game");
  	//	JOptionPane.showMessageDialog(null, "PLAY AGAIN?");
  	}
  	else if(score2 >= 15) 
  	{
  		JOptionPane.showMessageDialog(null, "p2 has won the game");
  	//	JOptionPane.showMessageDialog(null, "PLAY AGAIN?");
  	}
  	else if(score1 == score2)
  	{
  		JOptionPane.showMessageDialog(null, "SCORE LEVEL");
  	//	JOptionPane.showConfirmDialog(null,"Would you like to go Forward??");
  	}
  	*/
     checkgame();
  	
     newGame();
     
     for(i=0; i<11 ; i++ ) ball[i].gone = 0;
  	 repaint();
  	 
    }
    if(networkPlayer != player && networkFlag )
  	{
  		while(tp.status == 0);
  	 	System.out.println(tp.status);
    	tp.status = 0;     	 	
  	 	modify(tp.get());
  	 	repaint();
  	}
      // game loop
  	
  }
  
  public void CheckPlayer()
  {
 
  	
  		if(plr1 == 1) 
		{
			if(bb > 0 )
			{
				score1 = score1 + bb;
				plr1 = 1;
				click1 = 1;
				click2 = 0;
				//JOptionPane.showMessageDialog(null , " Now " + score1);
			}
	
			else 
			{ 
				score2 = score2 + bb;
				plr1 = 2;
				click1 = 0;
				click2 = 1; 
			    //JOptionPane.showMessageDialog(null , " Now " + score2);
	    	}
		}
		else if(plr1 == 2) 
		{
			if(bb > 0 )
			{
				score2 = score2 + bb;
				plr1 = 2;
				click2 = 1;
				click1 = 0;
				//JOptionPane.showMessageDialog(null , " Now " + score2);
			}
			else 
			{ 
				score1 = score1 + bb;
				plr1 = 1;
				click2 = 0;
				click1 = 1;
			   //JOptionPane.showMessageDialog(null , " Now " + score1);
			}
		}
	 	
  }
  public void checkplayer()
  {
  	if( board % 2 == 1 )
  	{
  		if( player == 1 ) 
		{
			if( bb > 0 || r > 0 )
			{
				score1 = score1 + bb;
				player = 1;
				click1 = 1;
				click2 = 0;
			}
			else
			{
				score2 = score2 + bb;
				 player = 2;
				 click1 = 0;
				 click2 = 1;
			}
		}
		else if( player == 2 ) 
		{
			if( bb > 0 || r > 0 )
			{
				score2 = score2 + bb;
				player = 2;
				click2 = 1;
				click1 = 0;
			}
			else
		    {
		    	score1 = score1 + bb;
				player = 1;
				click2 = 0;
				click1 = 1;
		    }
		}
  	}
  	if( board % 2 == 0 )
  	{
  		//JOptionPane.showMessageDialog(null, player + "");
  		if( player == 1 ) 
		{
			
			if( bb > 0 || r > 0 )
			{
				score1 = score1 + bb;
				player = 1;
				click1 = 1;
				click2 = 0;
			}
			else
			{
				score2 = score2 + bb;
				 player = 2;
				 click1 = 0;
				 click2 = 1;
			}
		}
		else if( player == 2 ) 
		{
			if( bb > 0 || r > 0 )
			{
				score2 = score2 + bb;
				player = 2;
				click2 = 1;
				click1 = 0;
			}
			else
		    {
		    	score1 = score1 + bb;
				player = 1;
				click2 = 0;
				click1 = 1;
		    }
		}
  	}
  }
  
  public void checkQue()
  {

  	if(ball[10].gone == 1)
  	{
  		ballinhand = 1;
  		JOptionPane.showMessageDialog(null , "Foul. Opposition ball in hand");
  		queFaul = false;
  		ball[10].x=360;
  		ball[10].y=300;
  		ball[10].setvxvy(0.0,0.0);
  		ball[10].gone=0;
  		
  		count--;
  		
  		
  	}
  	else 
  		ballinhand = 0;
  	
  }
  public void NewBoard()
  {
    
   // board++;	
  	boardfinished = false;
  	player = firstPlayer;
    if(newgameFlag == 0)
  	{	
  		checkplayer();
  	}
  	else 
  	{
  		newgameFlag = 1;
  		ball[10].x = 360;
     	ball[10].y = 300;
  	}
  	firstPlayer = player;
  	checkgame();
 
  	for( int i = 0; i < 11 ; i++ ) ball[i].gone = 0;
  	repaint();	
  }
  
  public void checkclick()
  {
  	if(board % 2 == 1)
  	{
  		 
  		click1 = 1;
  	    click2 = 0;
 
 
  	}
  	if(board % 2 == 0)
  	{
  		click1 = 0;
  		click2 = 1;
  	}
  }
  public void newGame()
  {
  //	game = new Thread();
    
  	allstopped = false ;
    gamefinished = false ;
    boardfinished = false;
  
   // board  = 0;
    onQue = 0;
    ondemo = 0 ;
    ontable = 0 ; 
    b = r = 0 ;
    count = 0;
    ontable1 = 1;
    shoot=0 ;
    dx = 0.01;
    dy = 0.01;
    len = 0;
    width = 0;
    plr1 = 1; plr2 = 2;
    checkclick();
   // temp = click1;
   // click1 = click2;
   // click2 = temp;
   score1 = 0;
   score2 = 0;
    result = 0;
    ballinhand = 0;
    a = 286; b = 32; outerBall=500.0;
    newgameFlag = 1;
    pbwidth = 0;
    
    setball();
    NewBoard();
    //JOptionPane.showMessageDialog(null, board + "");
    
    
   
  }
  public void checkgame()
  {
  	if( score1 >= 15 || score2 >= 29 ) 
  	{
  		//gamefinished = true;
  		if( score1 >= 15 ) JOptionPane.showMessageDialog( null , "Winner of the game is " + p1 );
 		else if( score2 >= 15 )JOptionPane.showMessageDialog( null , "Winner of the game is " + p2 );

	}  
  }

 public void paintComponent( Graphics g )
   {
   	
  	// super.paintComponent(g)  	 
  	 super.paintComponent( g );
   	 int xcount = 0,ycount = 0;
   	 p1 = str1;
   	 p2 = str2;
	// background.paintIcon( this, g, 0, 0 );
	 setBackground(Color.CYAN);
	 image.paintIcon( this, g, 100, 100 );
	 
	 
	 
	 Queball.paintIcon(this, g, (int)ball[10].x, (int)ball[10].y);
	 
	 g.setColor(Color.lightGray);
     g.fillRect(75, 100, 25, 400);
     
     g.setColor(Color.lightGray);
     g.fillRect(820, 100, 25, 400);
     
	 ball1.paintIcon(this, g, (int)ball[0].x, (int)ball[0].y);
	 ball2.paintIcon(this, g, (int)ball[1].x, (int)ball[1].y);
	 ball3.paintIcon(this, g, (int)ball[2].x, (int)ball[2].y);
	 ball4.paintIcon(this, g, (int)ball[3].x, (int)ball[3].y);
	 ball5.paintIcon(this, g, (int)ball[4].x, (int)ball[4].y);
	 ball6.paintIcon(this, g, (int)ball[5].x, (int)ball[5].y);
	 ball7.paintIcon(this, g, (int)ball[6].x, (int)ball[6].y);
	 ball8.paintIcon(this, g, (int)ball[7].x, (int)ball[7].y);
	 ball9.paintIcon(this, g, (int)ball[8].x, (int)ball[8].y);
	 ball10.paintIcon(this, g, (int)ball[9].x, (int)ball[9].y);
	 
	 g.setColor(Color.MAGENTA);
	 if(count == 1)
	 	g.fillRect(825, 460, 15, 40);
	 else if(count == 2)
	 	g.fillRect(825, 420, 15, 80);
	 else if(count == 3)
	 	g.fillRect(825, 380, 15, 120);
	 else if(count == 4)
	 	g.fillRect(825, 340, 15, 160);
	 else if(count == 5)
	 	g.fillRect(825, 300, 15, 200);
	 else if(count == 6)
	 	g.fillRect(825, 260, 15, 240);
	 else if(count == 7)
	 	g.fillRect(825, 220, 15, 280);
	 else if(count == 8)
	 	g.fillRect(825, 180, 15, 320);
	 else if(count == 9)
	 	g.fillRect(825, 140, 15, 360);
	 else if(count == 10)
	 	g.fillRect(825, 100, 15, 400);
/*	 
	 if(count == 1)
	 	g.fillRect(825, 460, 15, 40);
	 else if(count == 2)
	 	g.fillRect(825, 420, 15, 80);
*/	 
    if(ontable == 1 && ontable1 == 1)
    {
        g.setColor(Color.RED);
        for(int i = 0; i < 2; i++)
        {
    	    g.drawLine((int)ball[10].x + 11 + i - (int)a1, (int)ball[10].y + 11 + i - (int)a2,(int) ball[10].x + 11 + i - (int)c1,(int) ball[10].y + 11 + i - (int)c2);
        }
    
         g.setColor(Color.magenta);
         g.drawLine((int)ball[10].x + 11 + (int)a1, (int)ball[10].y +11 + (int)a2, (int)a, (int)b);
         
         g.setColor( Color.red );
  	  	 g.drawOval( (int)demo.x , (int)demo.y , (int)demo.diameter, (int)demo.diameter);
  	  	 
  	  	
    }
  
  //  if( ontable == 1 )
  	//  {
  	  	
  	  //	g.drawLine( (int)(ball[10].x + a1), (int)(ball[10].y + a2) , (int)linex , (int)liney);
  	//  }
    
    g.setColor(Color.WHITE);
    g.fillRoundRect(880,200,50,300,10,10);
    g.setColor(Color.CYAN);
    
    for(int i = 1; i < 10; i++)
    {
        g.drawLine(880,200 + i * 30,930,200 + i * 30);
    }
    g.setColor(Color.MAGENTA);
    
  	for(int i = 0, j = 0; i <= 100; i = i + 10, j++)
  	{
  		g.drawString(i + ".",940,200 + j * 30 + 5);
  	}
  	g.setColor(Color.MAGENTA);
  	
  	g.setFont(font1);
  	g.drawString("POWER", 865, 180);
  	
  	g.setColor(Color.MAGENTA);	
	g.fillRoundRect(880, 200, 50, width, 10, 10);
	
	//For Player Drawing
	//...................
	
	if(click1 == 0)
	{
	    g.setFont(font4);
	    prl1.paintIcon(this, g, 245, 3);
	    g.drawString(p1,225,90);
	}
	
	if(click2 == 0)
	{ 
		g.setFont(font4);
		prl2.paintIcon(this, g, 650, 3);
	    g.drawString(p2, 640, 90);
	}
	
	if(click1 == 1)
	{
		tempr.paintIcon(this, g, 245, 3);
		g.setColor(Color.orange);
		g.setFont(font1);
		g.drawString(p1,230,90);
		
		g.setFont(font4);
		g.setColor(Color.WHITE);
		g.fillRect(0, 640, 200, 40);
		g.setColor(Color.red);
		g.drawString("Now " + p1 + "'s term !!! " , 0, 660);
		
		
	}
	else if(click2 == 1)
	{
		
		tempr.paintIcon(this, g, 650, 3);
		g.setColor(Color.orange);
		g.setFont(font1);
		g.drawString(p2, 640, 90);
		
		g.setFont(font4);
		g.setColor(Color.WHITE);
		g.fillRect(0, 640, 200, 40);
		g.setColor(Color.blue);
		g.drawString("Now " + p2 + "'s term !!! " , 0, 660);
			
		
	}
	scoreboard.paintIcon(this, g, 330, 3);
	g.setColor(Color.GREEN);
	g.drawString("SCOREBOARD", 420, 30);
	g.setColor(Color.RED);
	g.setFont(font1);
	g.drawString(score1 + " ", 355, 70);
	g.setColor(Color.MAGENTA);
	g.drawString(score2 + " ", 570, 70);
	g.setFont(font7);
	g.setColor(Color.CYAN);
	g.drawString(p1, 340, 30);
	g.drawString(p2, 555, 30);
	
	if(click1 == 1)
	{
		g.setFont(font4);
		g.drawString(p1 + "'s term", 417, 70);	
	}
	if(click2 == 1)
	{
		g.setFont(font4);
		g.drawString(p2 + "'s term", 417, 70);	
	} 
		
    if(queFaul)
    {
    	faul.paintIcon(this, g, 250, 120);
    }
     	
   } 
   	
  public void checkwallbounce(Ball b)
  {
		double  Xleft = b.x;
		double Ytop = b.y;
		double Ybottom = b.y +  b.diameter; 
		double Xright = b.x +  b.diameter;  
		
		if (Xleft < 125.0)
		{
			
		    Wall2.play();
			b.x = 125.0;
			b.wallbounceX();
		} 
		
		if (Ytop < 130.0)
		{
			
		    Wall2.play();
			b.y = 130.0;
			b.wallbounceY();
		}
		
		if (Xright > 792.0)
		{
			
		    Wall2.play();
			b.x = 792.0 -  b.diameter;
			b.wallbounceX();
		} 
		
		if (Ybottom > 465.0)
		{
			
		    Wall2.play();
			b.y = 465.0 -  b.diameter;
			b.wallbounceY();
		}
	}
   	
   	public double separation(Ball b1, Ball b2) 
	{ 
		double x1, x2, y1, y2;
		x1 = b1.x + b1.diameter/2;
		x2 = b2.x + b2.diameter/2;
		y1 = b1.y + b1.diameter/2;
		y2 = b2.y + b2.diameter/2;
		
		return   Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
    public double radsum(Ball b1, Ball b2) 
	{
		return (b1.diameter/2  +  b2.diameter/2);
	}
	
   	public void Collide(Ball b1, Ball b2) 
	{
		
		 
		double Dx1, Dx2, Dy1, Dy2; 
		double X1, X2, Y1, Y2; 
		double DxR, DyR; 
		double Dx, Dy; 
		double Vp1, Vp2, Vs1, Vs2; 
		double newVs1, newVs2; 
		double distance; 
		double tempvx,tempvy;
		boolean exp;
		
		
		
		X1 = b1.x + b1.diameter/2 ;
		X2 = b2.x + b2.diameter/2 ;
		Y1 = b1.y + b1.diameter/2 ;
		Y2 = b2.y + b2.diameter/2 ;
		
		
		DxR = (X2 - X1); 
		DyR = (Y2 - Y1);
		
		distance = Math.sqrt(DxR * DxR + DyR * DyR); 
		
		Dx = radsum(b1,b2) * DxR / distance; 
		Dy = radsum(b1,b2) * DyR / distance;
		
		
		
/*
		if (b1.mass == b2.mass)
		{
			X2 = (X2 - (Dx - DxR));
			Y2 = (Y2 - (Dy - DyR));
			
			b2.x = X2 - b2.radius;
			b2.y = Y2 - b2.radius;
		}
		else
		{
	*/		
			X2 = (X2 + (Dx - DxR));
			Y2 = (Y2 + (Dy - DyR));
			
			b2.x = X2 - b2.radius;
			b2.y = Y2 - b2.radius;
	//	}		
		
		
		Dx1 = (b1.radius / radsum(b1,b2)) * (X2 - X1);
		Dx2 = (b2.radius / radsum(b1,b2)) * (X2 - X1);
		
		Dy1 = (b1.radius / radsum(b1,b2)) * (Y2 - Y1);
		Dy2 = (b2.radius / radsum(b1,b2)) * (Y2 - Y1);
		
		
		Vs1 = StraightVelocity(b1.vx, b1.vy, Dx1, Dy1, b1.radius);
		Vp1 = PerpendicularVelocity(b1.vx, b1.vy, Dx1, Dy1, b1.radius);
		
		Vs2 = StraightVelocity(b2.vx, b2.vy, Dx2, Dy2, b2.radius);
		Vp2 = PerpendicularVelocity(b2.vx, b2.vy, Dx2, Dy2, b2.radius);
	
		
		newVs1 = CollisionVelocity(Vs1, Vs2, b1.mass, b2.mass);
		newVs2 = CollisionVelocity(Vs2, Vs1, b2.mass, b1.mass);
	
		
		tempvx = XVelocity(newVs1, Vp1, Dx1, Dy1, b1.radius);
		tempvy = YVelocity(newVs1, Vp1, Dx1, Dy1, b1.radius);
		exp = b1.checkvxvy( tempvx , tempvy );
		b1.setvxvy(tempvx , tempvy);
		
		
		tempvx = XVelocity(newVs2, Vp2, Dx2, Dy2, b2.radius);
		tempvy = YVelocity(newVs2, Vp2, Dx2, Dy2, b2.radius);
		exp =  exp && b2.checkvxvy(tempvx , tempvy);
		b2.setvxvy(tempvx , tempvy);
		
		if(exp)
		{
			
		//	Hit.play();
			
		}
	//	if(soundflag) Hit.play();
		
	}
	public double StraightVelocity(double Vx, double Vy, double Dx, double Dy, double R)
	{
		return Vx * Dx / R + Vy * Dy / R;
	} 
	public double PerpendicularVelocity(double Vx, double Vy, double Dx, double Dy, double R)
	{
		return Vy * Dx / R - Vx * Dy / R;
	}
	public double XVelocity(double Vs, double Vp, double Dx, double Dy, double R)
	{
		return Vs * Dx / R - Vp * Dy / R;
	} 
	public double YVelocity(double Vs, double Vp, double Dx, double Dy, double R)
	{
		return Vs * Dy / R + Vp * Dx / R;
	} 
	public double CollisionVelocity(double V1, double V2, double m1, double m2) 
	{
		return V1 * (m1-m2) / (m1+m2) + V2 * (2 * m2) / (m1 + m2);
	}
   	
   	
}


class Ball implements Serializable
{
	public double x, y;
	public int flag;
	public double diameter, radius, mass;
	public double vx, vy, v, m;
	public int stop, gone;
	public String name;
	
	public Ball(double x, double y, double diameter , double mass)
	{
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.mass = mass;
		this.radius = diameter/2;
		flag = 0;
		gone = 0;	
	}
	
	public void setxy(double x , double y)
	{
		this.x = x;
		this.y = y;
	}

	public void setvxvy(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
		this.v = Math.sqrt((vx * vx) + (vy * vy));
	}
	
	public void wallbounceX() 
	{
		vx = - vx; 
	}
	public void wallbounceY() 
	{
		vy = - vy; 
	}
	public void move() 
	{	  	 	
		if(v > 1.0)
		{
			x = x + vx;
			y = y + vy;
			stop = 0;
			
			flag++;
			if(flag == 3)
			{
				v = v - 1.0;
				m = Math.sqrt((v * v) / ((vx * vx) + (vy * vy)));
				
				vx = m * vx;
				vy = m * vy;
			
				flag = 0;			
			}			
		}
		
		else if(v <= 1.0) stop = 1;
		
	}
	
	public boolean checkvxvy( double vx , double vy )
	{
		if( vx == this.vx && vy == this.vy )
		{
			return false;
		}
		
		return true;
	}


}


class PoolFrame extends JFrame implements Serializable 
{
	static JSlider powerSlider;
	PoolPanel poolPanel;
	
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    
    private javax.swing.JMenu jMenuGame;
    private javax.swing.JMenu jMenuShot;
    private javax.swing.JMenu jMenuVenue;
    private javax.swing.JMenu jMenuOption;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenu jMenuDifficulty;
    
    private javax.swing.JMenuBar jMenuBar1;
    
    private javax.swing.JMenuItem jMenuItemNewgame;
    private javax.swing.JMenuItem jMenuItemHalt;
    private javax.swing.JMenuItem jMenuItemContent;
    private javax.swing.JMenuItem jMenuItemInstruction;
    private javax.swing.JMenuItem jMenuItemStrategy;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemLoad;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemUndo;
    
    
    private javax.swing.JMenuItem jMenuItemEasy;
    private javax.swing.JMenuItem jMenuItemModerate;
    private javax.swing.JMenuItem jMenuItemHard;
    private javax.swing.JMenuItem jMenuItemNetwork;
	
	public PoolFrame()
	{
		super( "Pool Game" );
	//	initComponents();
		
		powerSlider = new JSlider( SwingConstants.HORIZONTAL, 0, 100, 0 );
		
	    poolPanel = new PoolPanel();
		powerSlider.setMajorTickSpacing( 5 );
		powerSlider.setBackground( Color.MAGENTA );
		powerSlider.setPaintTicks( true );
		powerSlider.setPaintLabels( true );
		powerSlider.setPaintTrack( true );
		initComponents();
				
		add( poolPanel, BorderLayout.CENTER );
	//	add( powerSlider, BorderLayout.SOUTH );
	}
	
	private void initComponents(){
		
		Font font1 = new Font("Helvetica", Font.PLAIN,  22);
        Font font2 = new Font("TimesRoman", Font.PLAIN + Font.BOLD,  14);
        Font font3 = new Font("Courier", Font.PLAIN + Font.BOLD,  18);
		Font font4 = new Font("Helvetica", Font.BOLD,  16);
		Font font5 = new Font("Helvetica", Font.ITALIC,  16);
        Font font6 = new Font("Helvetica", Font.BOLD + Font.ITALIC,  16);

        jMenuBar1 = new javax.swing.JMenuBar();

        
        jMenuGame = new javax.swing.JMenu();
        
        jMenuItemNewgame = new javax.swing.JMenuItem();        
        jMenuItemLoad = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        
       
        jMenuVenue = new javax.swing.JMenu();
        
        jMenuShot = new javax.swing.JMenu();
        
        jMenuItemUndo = new javax.swing.JMenuItem();
        jMenuItemHalt = new javax.swing.JMenuItem();
        
        jMenuOption = new javax.swing.JMenu();
        
        jMenuDifficulty = new javax.swing.JMenu();
        
        jMenuItemEasy = new javax.swing.JMenuItem();
        jMenuItemModerate = new javax.swing.JMenuItem();
        jMenuItemHard = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        
        jMenuHelp = new javax.swing.JMenu();
        
        jMenuItemContent = new javax.swing.JMenuItem();
        jMenuItemInstruction = new javax.swing.JMenuItem();
        jMenuItemStrategy = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jMenuItemNetwork = new javax.swing.JMenuItem();

        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setSize (1000, 700);
        
        jMenuGame.setFont(font4);
        jMenuGame.setText("File");

        jMenuItemNewgame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNewgame.setFont(font3);
        jMenuItemNewgame.setText("New Game");
        jMenuItemNewgame.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			
        		    poolPanel.newGame();
        		 //   PoolPanel.score1 = 0;
        		   // PoolPanel.score2 = 0;
        		}
        	}
        );
        jMenuGame.add(jMenuItemNewgame);
        

        jMenuItemLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemLoad.setFont(font3);
        jMenuItemLoad.setText("Load Game"); 
        jMenuItemLoad.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			String name = JOptionPane.showInputDialog(null,"Enter file name:","Load...",JOptionPane.PLAIN_MESSAGE);
        		////	if(new File(name+".pool").exists())
        		//	{
        		//		JOptionPane.showMessageDialog(null,"File does not exist.","Error",JOptionPane.ERROR_MESSAGE);
        	//		}
        		//	else
        	//		{
        			
        			try {
					      Store object2;
					      FileInputStream fis = new FileInputStream( name + ".pool");
					      ObjectInputStream ois = new ObjectInputStream(fis);
					      object2 = (Store)ois.readObject();
					      ois.close();
					      poolPanel.modify(object2);
					      repaint();
					     // System.out.println("object2: " + object2);
					    }
					    catch(Exception e) {
					      System.out.println("Exception during deserialization: " + e);
					      //System.exit(0);
					    }
        		//	}
        		}
        	}
        );      
        jMenuGame.add(jMenuItemLoad);
        
        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setFont(font3);
        jMenuItemSave.setText("Save Game as");
        jMenuItemSave.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			String name = JOptionPane.showInputDialog( null , "Enter the file name:","Save Game As....",JOptionPane.PLAIN_MESSAGE );
        			if(name.length()==0)
        			{
        				JOptionPane.showMessageDialog(null,"Invalid file name.","Error",JOptionPane.WARNING_MESSAGE);
        			}
        			if(new File(name + ".pool").exists())
        			{
        				int flag = JOptionPane.showConfirmDialog( null, "File already exists.Overwrite?","Overwrite..",JOptionPane.YES_NO_OPTION);
        				if(flag == 0)
        				{
        					try {
								  //CaromPanel object1 = new CaromPanel(this);
								  //System.out.println("object1: " + object1);
								  FileOutputStream fos = new FileOutputStream(name+".pool");
								  ObjectOutputStream oos = new ObjectOutputStream(fos);
								  oos.writeObject(new Store(poolPanel));
								  oos.flush();
								  oos.close();
								 }
								catch(Exception e) 
								{
								  System.out.println("Exception during serialization: " + e);
								  //System.exit(0);
								}
        				}
        			}
        			else
        			{
        					try {
								  //MyClass object1 = new MyClass("Hello", -7, 2.7e10);
								  //System.out.println("object1: " + object1);
								  FileOutputStream fos = new FileOutputStream(name+".pool");
								  ObjectOutputStream oos = new ObjectOutputStream(fos);
								  oos.writeObject(new Store(poolPanel));
								  oos.flush();
								  oos.close();
								 }
								catch(Exception e) 
								{
								  System.out.println("Exception during serialization: " + e);
								  //System.exit(0);
								}
        			}
        		}
        	}
        );
        jMenuGame.add(jMenuItemSave);
        
        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExit.setFont(font3);
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			System.exit(0);
        		}
        	}
        );
        jMenuGame.add(jMenuItemExit);
        jMenuBar1.add(jMenuGame);
        
        
        
        jMenuShot.setFont(font4);
        jMenuShot.setText("Player");

        jMenuItemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUndo.setFont(font3);
        jMenuItemUndo.setText("Change Name");
        jMenuItemUndo.addActionListener(
        	
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			if(!poolPanel.networkFlag)
        			{
        		     poolPanel.temp1 = JOptionPane.showInputDialog(null,"Enter first player name:","Change Names..",JOptionPane.PLAIN_MESSAGE);
        		     poolPanel.temp2 = JOptionPane.showInputDialog(null,"Enter second player name:","Change Names..",JOptionPane.PLAIN_MESSAGE);
        		     
        		     if(poolPanel.temp1.length() != 0) poolPanel.str1 = poolPanel.temp1;
        		     if(poolPanel.temp2.length() != 0) poolPanel.str2 = poolPanel.temp2;
        		     repaint();
        			}
        			else
        				poolPanel.p[poolPanel.networkPlayer] = JOptionPane.showInputDialog(null,"Enter  player name:","Change Names..",JOptionPane.PLAIN_MESSAGE);

        				

        		}
        	}       
         );
        jMenuShot.add(jMenuItemUndo);
        
        

        jMenuItemHalt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemHalt.setFont(font3);
        jMenuItemHalt.setText("Nothing");
        jMenuShot.add(jMenuItemHalt);

        jMenuBar1.add(jMenuShot);
        
        
        jMenuOption.setFont(font4);
        jMenuOption.setText("Options"); 
        	
        jMenuItemNetwork.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItemNetwork.setFont(font3);
        jMenuItemNetwork.setText("Network Game"); 
        
        jMenuItemNetwork.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			poolPanel.networkFlag = true;  
        			String ip =   JOptionPane.showInputDialog(null,"Enter Server IP:","Server Address...",JOptionPane.PLAIN_MESSAGE);          			
        			poolPanel.p1 = JOptionPane.showInputDialog(null,"Enter your name:","Player name...",JOptionPane.PLAIN_MESSAGE);       		
        		
        			try
    			 	{
				      poolPanel.nc = new NetworkUtil(ip,33333);
				    }
				    catch(Exception e)
				    {
				    	JOptionPane.showMessageDialog( null,"Could not connect to server","Error",JOptionPane.ERROR_MESSAGE);			    	
				    	
				    }
        			poolPanel.tp = new ReadThread(poolPanel.nc);
        			poolPanel.nc.write("join");
        			//for(int i= 0; i<10000;i++)System.out.println("d");
        			while(poolPanel.tp.status == 0);
        			poolPanel.tp.status = 0;
        			poolPanel.networkPlayer = poolPanel.tp.getint();        		
        			poolPanel.p[ poolPanel.networkPlayer-1 ] = poolPanel.p1;
        			int n = 2 - poolPanel.networkPlayer;
        			if(n != 0 )
        			{
        				JOptionPane.showMessageDialog(null,"Connected!!Wait for another " + n); 
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(null,"Your turn will come soon " );
        			}
        			
        			poolPanel.game.stop();
        			poolPanel.game = new Thread(poolPanel);
        			poolPanel.game.start();
        			poolPanel.newGame();  
        		}
        	}
        );
        jMenuOption.add(jMenuItemNetwork); 

        jMenuDifficulty.setFont(font3);
        jMenuDifficulty.setText("Difficulty"); 
        
        jMenuItemEasy.setFont(font3);	       
        jMenuItemEasy.setText("Easy");
        
        jMenuItemEasy.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			int temp = JOptionPane.showConfirmDialog(null,"Would you like to play an easy game?" ,"Caution",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        			if(temp == 0)
        			{       			
	        		//	poolPanel.setDifficulty("Easy"); 
	        			poolPanel.newGame();  
	        			poolPanel.score1 = 0;
        		        poolPanel.score2 = 0;    
        		        poolPanel.c=4;			
	        			repaint();
        			}
        		}
        	}
        );
        jMenuDifficulty.add(jMenuItemEasy);
        
        jMenuItemModerate.setFont(font3);
        jMenuItemModerate.setText("Moderate");
        jMenuItemModerate.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{
        			int temp = JOptionPane.showConfirmDialog(null,"Would you like to play a moderate game?" ,"Caution",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);        		
        			if(temp == 0)
        			{       			
	        		//	poolPanel.setDifficulty("Moderate"); 
	        			poolPanel.newGame(); 
	        			poolPanel.score1 = 0;
        		        poolPanel.score2 = 0;
        		        poolPanel.c=2;     			
	        			repaint();
        			}
        		}
        	}
        );
        jMenuDifficulty.add(jMenuItemModerate);
        
        jMenuItemHard.setFont(font3);
        jMenuItemHard.setText("Hard");
        jMenuItemHard.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{        			
        			int temp = JOptionPane.showConfirmDialog(null,"Would you like to play a hard game?" ,"Caution",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        			if(temp == 0)
        			{       			
	        	 
	        			poolPanel.newGame();
	        			poolPanel.score1 = 0;
        		        poolPanel.score2 = 0;      			
	        			repaint();
        			}
        		}
        	}
        );
        jMenuDifficulty.add(jMenuItemHard);
        
        jMenuOption.add(jMenuDifficulty);


        jCheckBoxMenuItem1.setSelected(false);
        jCheckBoxMenuItem1.setFont(font3);
        jCheckBoxMenuItem1.setText("Auto Power Reset");
        jMenuOption.add(jCheckBoxMenuItem1);
        
        

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setFont(font3);
        jCheckBoxMenuItem2.setText("Sound");
        jCheckBoxMenuItem2.addItemListener(
        	
        	new ItemListener()
        	{
        			public void itemStateChanged(ItemEvent event)
        			{
        				if(jCheckBoxMenuItem2.isSelected())
        				{
        					poolPanel.soundflag = true;
        				}
        				else
        				{
        					poolPanel.soundflag = false;
        				}
        				
        			}        		
        	}
        );
        		     
        jMenuOption.add(jCheckBoxMenuItem2);
        
      

        jCheckBoxMenuItem4.setSelected(false);
        jCheckBoxMenuItem4.setFont(font3);
        jCheckBoxMenuItem4.setText("Color Blind Tips");
        jMenuOption.add(jCheckBoxMenuItem4);

        jMenuBar1.add(jMenuOption);

        jMenuHelp.setFont(font4);   
        jMenuHelp.setText("Help");


        jMenuItemInstruction.setFont(font3);
        jMenuItemInstruction.setText("Instructions");
        jMenuItemInstruction.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{   
        			try
        			{
        				
        				helpLoader help = new helpLoader("help.png"); 
        			}
        			
        			catch(Exception e){
        			}        			    			
        			
        		}
        	}
        );
        jMenuHelp.add(jMenuItemInstruction);


        jMenuItemAbout.setFont(font3);
        jMenuItemAbout.setText("About");
         jMenuItemAbout.addActionListener(
        	new ActionListener()
        	{
        		public void actionPerformed(ActionEvent event)
        		{   
        			try
        			{
        				helpLoader help = new helpLoader("Contact.png"); 
        			}
        			
        			catch(Exception e){
        			}        			       			
        		}
        	}
        );
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar1.add(jMenuHelp);
        jMenuBar1.setForeground(Color.blue);
        jMenuBar1.setToolTipText("Menubar");
        jMenuBar1.setBorderPainted(true);

        setJMenuBar(jMenuBar1);      	
              
      
    }
}


class Main 
{
	static int close = 0 ;

	public static void main( String args [] )
	{	
		SplashScreenMain scrn = new SplashScreenMain();
			
		StartFrame start = new StartFrame();
		start.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		start.setResizable(false);
		start.setAlwaysOnTop(true);
		start.pack();
		start.setSize( 1024, 740 );
		start.setVisible( true );	
		PoolFrame window = new PoolFrame();		
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setResizable(true);
		window.pack();
		window.setSize( 1024, 740 );		
		window.setVisible( true );
		window.show();
		
	
	}		
}



