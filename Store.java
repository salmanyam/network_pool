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
    static int score1 = 0, score2 = 0, click1 = 1, click2 = 0, ctrl = 0; 
    int temp, plr1 = 1, plr2 = 2, networkPlayer = 1,player = 1, firstPlayer = 1;
    int ontable = 0, count = 0, ontable1 = 1, ballinhand = 0, soundint, ondemo, onQue, FoulQue = 0;
    double QueX, QueY, linex, liney, fwidth = 460;
   
  
    double  z;
    String p1, p2, p[] = new String[2];
    static String  str1, str2, temp1, temp2;
    
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