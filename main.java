package game;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.Random;
import java.text.SimpleDateFormat;
import javax.swing.*;


public class PandaGame extends JPanel
{

/**
   * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame frame = new JFrame("Panda Game designed by Jitendra chaudhary");
	
	JPanel p= new JPanel();
	int myX = 207;
    int myY = 100;
    
	 static Canvas canvas;
	
	  static int seconds = 0;
		
			static int score=0;
	   
		
	      static int minutes = 0;
			static int milliseconds = 0;
	     static  Calendar date = Calendar.getInstance();
	     static SimpleDateFormat sdf = new SimpleDateFormat("ss");
		       
	static int x11,y11;
	static int x21,y21;
	static int x31,y31;
	
	 JMenuItem menuItem; 
	 
	 
	 JMenuBar menuBar = new JMenuBar();
	 
	 JMenu m1=new JMenu("Menu");

	 JMenu m2=new JMenu("Help");
	 
	
	 
	
	 JMenuItem m11 = new JMenuItem("Start");
	 JMenuItem m12 = new JMenuItem("Stop");
	 JMenuItem m13 = new JMenuItem("Exit");
	 
	 JMenuItem m21 = new JMenuItem("About");
	 JMenuItem m22 = new JMenuItem("Help");
	 
	  
	  static  JLabel label = new JLabel("Time :--00 Sec");
	  
	  static JLabel lab2 = new JLabel("Score:- 00");
	  
		static Random random = new Random();
		
		
	  
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			++milliseconds;
			if (milliseconds >= 400)
			{
			++seconds;
			milliseconds = 0;
			}
			if (seconds >= 60)
			{
			time_up=false;		
			++minutes;
			seconds = 0;
			
			}
			date.set(Calendar.MILLISECOND, milliseconds);
			date.set(Calendar.SECOND, seconds);
			
			label.setText(" Time --" + sdf.format(date.getTime()) +" Sec");
			
			}
			};
		 Timer timer = new Timer(1, timerListener);
	
	
      
	 BufferStrategy bufferStrategy;
	 static boolean bouncing;
	 static boolean time_up=true;
	 static boolean showball=true;
	 static boolean check_key=false;
	 static boolean key_downing=false;
	static boolean stop_once=false;
	
	

	PandaGame()
	{	
		
		 JLayeredPane lpane = new JLayeredPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setPreferredSize(new Dimension(445, 450));
		frame.setResizable(false);
	
		 frame.setLayout(new BorderLayout());
	        frame.add(lpane, BorderLayout.CENTER);
	        lpane.setBounds(0, 0,445, 400);
       canvas = new Canvas();
       canvas.setBounds(0, 0, 445, 400);
       
       canvas.setIgnoreRepaint(true);
       lpane.add(canvas);
	    frame.pack();
	 
	    canvas.setBackground(Color.GREEN);


	    menuBar.add( m1 );  
	      
	     menuBar.add( Box.createHorizontalGlue() ); 
	     menuBar.setBackground(Color.cyan);
	     menuBar.add(m2); 
	     
	  m1.add(m11);
	  m1.add(m12);
	  m1.add(m13);
	  m2.add(m21);
	  m2.add(m22);
	  
	
	   
	  
	        label.setBounds(280, 0, 125, 25);
	        label.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 15));
	        
	        label.setForeground(Color.YELLOW);
	        lab2.setBounds(200, 30, 125, 25);
	        lab2.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 18));
	        lab2.setForeground(Color.YELLOW);
	       
	        JPanel menuHeader = new JPanel( new BorderLayout() );  
	        menuHeader.add(label, BorderLayout.NORTH); 
	        
	        menuHeader.add(lab2, BorderLayout.NORTH); 
	        
	        m1.setForeground(Color.BLUE);
	        m2.setForeground(Color.BLUE);
	        m1.setBackground(Color.ORANGE);
	      
	        menuHeader.add(menuBar, BorderLayout.CENTER);  
	        menuHeader.setBackground(Color.BLUE);
	  
	        frame.add(menuHeader, BorderLayout.NORTH);  
	    
	       
	   
	    frame.setVisible(true);
	    frame.setResizable(true);
	    canvas.createBufferStrategy(1);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        
		
		canvas.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent evt) {
	                moveIt(evt);
	        
	          
	                
	            }

				
	        });
			
		m13.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				int messageType = JOptionPane.QUESTION_MESSAGE;
			      String[] options = {"OK", "Cancel"};
			      int code = JOptionPane.showOptionDialog(frame, 
			         "Are you really sure to exit ?", 
			         "Option Dialog Box", 1, messageType, 
			         null, options, "OK");
			      
			   
			      if (code==0){
			    	  
			    	  System.exit(0);
			    	  
			      }
			      else{
			    	  code=-1;
			      }
			   }
				
			

			});
		
		
		m11.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
                   score=0;
                   myX=207;
                   myY=100;
				bouncing = true;
				timer.start();
				stop_once=false;
				time_up=true;
				
				date.set(Calendar.MILLISECOND, milliseconds);
				date.set(Calendar.SECOND, seconds);
			
				
			}

			});
		
		m12.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bouncing = false;
				stop_time();
				
			}

			});
		
		
		m22.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

       Help h1=new Help();
		h1.setVisible(true);
		
		JLabel na = new JLabel("<html>Controll the panda(blue in color) to attack enemies(red in color) or prevent touching them <br><br>" 
		+ "(1)Use up arrow key to move upward the panda <br>"+
		 "(2)Use Down arrow key to move Downward the panda<br>" +"(3)Use left side arrow key to move left the pand<br>" +"(4)Use right side arrow key to move right the pand</html>");
        na.setFont(new Font("Serif", Font.ITALIC, 18));
        na.setAlignmentX(0.5f);
        na.setForeground(Color.blue);
        h1.add(na);
			}

			});
		m21.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				 Help h2=new Help();
					h2.setVisible(true);
					
					JLabel about = new JLabel("<html>About<br>" +"---->This game is doveloped by<br>----> Jitendra Chaudhary , Roll No.-UG201110012<br>" 
					+ "---->In this game you have only limeted time(60 Sec.)<br>"+
							 "---->Controll carefully the panda to attack enemies or prevent touching them <br>"  
					+"---->In each attack on enemies you will get 5 points.<br>" +"  Hope ,You will enjoy it.</html>");
					        about.setFont(new Font("Serif", Font.ITALIC, 18));
					        about.setAlignmentX(0.5f);
					        about.setForeground(Color.blue);
					        h2.add(about);
			}
			});
		
	
	}
	public int Score(int src) { 
	src=src + 5;
	return src ;
	}
	public void stop_time(){
		timer.stop();
		
		seconds=0;
		milliseconds=0;
		time_up=true;
		label.setText("Time:- 00 Sec ");
		
	}

	
	void out_score(){
		seconds=0;
		if(stop_once){
			bouncing=false;
		AboutDialog ad = new AboutDialog();
		ad.setVisible(true);
		}
		
	}
	
	public static   int rand_num(Random aRandom){
	    
	    long range = 100;
	   
	    long fraction = (long)(range * aRandom.nextDouble());
	    
	    int randomNumber =  (int)(fraction + 10);
	   
		return randomNumber;    
	    
	  }
	
	 public void start()throws Exception
	 {
	 	
	 for(int i=1;i<=100;i++)
	 {	
		 Balls1 b1=new Balls1(i*2,i*3,i);
		 b1.start();	
	  Balls3 b3=new Balls3(i*2,i*3,i);
	    b3.start();
	    Balls2 b2=new Balls2(i*2,i*3,i);
	    b2.start();
	 Thread.sleep(1000);
	 }
	 
	 }

public static void main(String[] args) throws Exception
{   	       
PandaGame f = new PandaGame();

f.start();


}

public class Help extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Help() {

        help();
    }

    public final void help() {

    	 setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

         add(Box.createRigidArea(new Dimension(0, 10)));


       
        add(Box.createRigidArea(new Dimension(0, 3)));

        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        close.setAlignmentX(0.2f);
        
        add(close);
        
        setTitle("Help");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
    }
}

class AboutDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5878654757948969049L;

	public AboutDialog() {

        initUI();
        
        
    }

    public final void initUI() {

    	 setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

         add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel name = new JLabel("Sorry,You have been out......!!!");
        name.setFont(new Font("Serif", Font.BOLD, 20));
        name.setAlignmentX(0.5f);
        name.setForeground(Color.RED);
        add(name);
        
        JLabel nam = new JLabel("Your Game is over");
        nam.setFont(new Font("Serif", Font.ITALIC, 18));
        nam.setAlignmentX(0.5f);
        nam.setForeground(Color.BLUE);
        add(nam);

        add(Box.createRigidArea(new Dimension(0, 50)));

        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                dispose();
              
                lab2.setText("Score:-00" );
              myX=207;
              myY=100;
            }
        });

        close.setAlignmentX(0.2f);
        add(close);
        
        setTitle("Score" );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 200);
    }
    
    
}


  
public class Balls3 extends Thread
{
int dx,dy,x3,y3;


public Balls3(int x,int y,int n){
	this.n=n;
	this.x3=330;
	this.y3=400;
	
	dx=0;
	dy=5;
	
}

Dimension d;
int n=0;
public void run()
{
	int posy=0;
	Color[] colors={Color.RED};
	Paint();

while(bouncing)
{
	
try

{
	Graphics g=canvas.getGraphics();
	g.setColor(colors[n % colors.length]);
	n++;
	g.fillOval(x3,y3,10,10);

	posy=y3;
	
	if(y3>0){
		
	dy=+dy;
	
	y3=y3-dy;
	
	}
	x31=x3;
	y31=y3;
	
	Thread.sleep(+ rand_num( random));
	
	if(key_downing){
		if((myX+8)==x31 && myY==y31)
		{
			
			score=Score(score);
			lab2.setText("socre:-" + score);
			
			g.dispose();
		}
		
		
	}
	
if(!check_key){
	
		if((myX+8)==x31 && myY==y31 && stop_once==false){
			stop_once=true;
			out_score(); 
			stop_time();
     	  
		}
	
	
}

	g.setColor(Color.YELLOW);
	g.fillOval(x3,posy,15,30);
	
}

catch(Exception ex)
{
System.out.println(ex);
}
}
}
}
public  class Balls2 extends Thread
{
int dx,dy;
 int x2,y2,z1,z2;

public Balls2(int x,int y,int n){
	this.n=n;
	this.x2=215;
	this.y2=400;
	dx=0;
	
	dy=5;
	z1=15;
	z2=30;
}

Dimension d;
int n=0;
public void run()
{
	int posy=0;
	Color[] colors={Color.RED};
	
	while(bouncing)		
{	
try
{
	
	Graphics g=canvas.getGraphics();
	g.setColor(colors[n % colors.length]);
	n++;
	g.fillOval(x2,y2,10,10);

	posy=y2;

	if(y2>0 && y2<400)
	dy=+dy;
	
	y2=y2-dy;
	
	x21=x2;
	
	y21=posy;
	
	Thread.sleep(+ rand_num( random));
	
	g.setColor(Color.YELLOW);
	
	
	
	
	g.fillOval(x2,posy,z1,z2);
	
	if(key_downing){
		
		if((myX+8)==x2 && myY==y21){
	
		score=Score(score);
		
		lab2.setText("socre:-" + score);
		
		g.setColor(Color.YELLOW);
		
		}
	
	}
	
	
	
if(!check_key){
		
		if((myX+8)==x2 && myY==y21 && stop_once==false){
			stop_once=true;
			out_score(); 
			stop_time();
			 
			
		}
		
	}
posy=y21;

}

catch(Exception ex)
{
System.out.println(ex);
}
}
	
}
}


public  class Balls1 extends Thread
{
int dx,dy;
 int x1,y1;

public Balls1(int x,int y,int n){
	this.n=n;
	this.x1=100;
	this.y1=400;
	dx=0;
	
	dy=5;
	
}

Dimension d;
int n=0;
public void run()
{
	int posy=0;
	Color[] colors={Color.RED};
	
	while(bouncing)		
{	
try
{
	
	Graphics g=canvas.getGraphics();
	g.setColor(colors[n % colors.length]);
	n++;
	g.fillOval(x1,y1,10,10);

	posy=y1;

	if(y1>0 && y1<400)
	dy=+dy;
	
	y1=y1-dy;
	
	x11=x1;
	y11=posy;
	Thread.sleep(+ rand_num( random));
	
	g.setColor(Color.YELLOW);
	
	
	
	g.fillOval(x1,posy,15,30);
	
	
	if(key_downing){
		
		
		if((myX+8)==x1 && myY==y11){
	
		score=Score(score);
		
		lab2.setText("socre:-" + score);
	
		g.setColor(Color.YELLOW);

		
		}
	}
	
	g.fillOval(x1,posy,15,30);
if(!check_key){
		
		if((myX+8)==x1 && myY==y11 && stop_once==false){
			stop_once=true;
			out_score(); 
			stop_time();
			
			System.out.println("I hi");  
			g.dispose();
		}
		
	}


}

catch(Exception ex)
{
System.out.println(ex);
}
}
	
}
}


public void moveIt(KeyEvent evt) {
	if(!check_key){

        	 switch (evt.getKeyCode()){ 
           case KeyEvent.VK_DOWN:{
        	   check_key=true;
        	   key_downing=true;
               if(myY<350){
               	myY +=5;
               }
               if(myY>330){
            	   stop_once=true;
            	   out_score(); 
            	   stop_time();
               }
               
               if(!time_up){
            	   stop_once=true;
            	   out_score(); 
            	   stop_time();
               }
              
           }
           
               break;
           case KeyEvent.VK_UP:{
        	   check_key=true;
        	   key_downing=false;
               if(myY>0){
               	myY-=5;
               }
               
               if(!time_up){
            	   stop_once=true;
            	   out_score(); 
            	   stop_time();
               }
           }
          
               break;
              
           case KeyEvent.VK_LEFT:{
        	   key_downing=false;
           	if(myX>92){
           		
               myX -= 115;
               
           	}
           	
            if(!time_up){
         	   stop_once=true;
         	   out_score(); 
         	   stop_time();
            }
               
           } 
               break;
           case KeyEvent.VK_RIGHT:
           {
        	   key_downing=false;
        	  
           	if(myX<322){
           		
               myX += 115;
             
           	}
            if(!time_up){
         	   stop_once=true;
         	   out_score(); 
         	   stop_time();
            }
           	
           }
           
               break;
          
    }
	}
	else{
		 check_key=false;	
		
	}
    	 
   }


public void Paint() {
	Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
    
    g.clearRect(0, 0, 445,400);
    
    
    GradientPaint gp8 = new GradientPaint(0, 0, 
   	     Color.blue, 0, 400, Color.blue, true);
   g.setPaint(gp8);
    Paint(g);
    
    
    GradientPaint gp3 = new GradientPaint(0, 0, 
     	     Color.yellow, 0, 400, Color.yellow, true);

          g.setPaint(gp3);
          g.fillRect(330, 0, 15, 400);
          
          GradientPaint gp4 = new GradientPaint(0, 0, 
	          	     Color.yellow, 0, 400, Color.yellow, true);

	               g.setPaint(gp4);
	               g.fillRect(215, 0, 15, 400);
	               
	               GradientPaint gp5 = new GradientPaint(0, 0, 
	    	          	     Color.yellow, 0, 400, Color.yellow, true);

	    	               g.setPaint(gp5);
	    	               g.fillRect(100, 0, 15, 400);
	    	            
    
    bufferStrategy.show();
   
}
protected void Paint(Graphics2D k) {
    k.fillOval(myX, myY, 30, 8);
    k.fillOval(myX+2, myY, 26, 50);
    k.fillOval(myX-8, myY+18, 46, 8);
  
  
}
}


