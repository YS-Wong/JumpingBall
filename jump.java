import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class jump extends Thread{
    JFrame f;
	static JButton j,m;
	static JRadioButton j1,j2,j3;
	int ix,iy,X,Y,level;
	static boolean b=true;
	//The method tx() and ty() aer used to change the direction of the Ball
	void tx(){
		ix=-ix;
	}
	void ty(){
		iy=-iy;
	}
	void absty(){
		iy=-Math.abs(iy);
	}
	void levelChange(int L){
		level=L;
	}
	jump(){
		X=500;
		Y=300;
		ix=1;
		iy=-1;
		level=5;
	}
	public void run(){
		try{
			if(j1.isSelected())levelChange(5);
			if(j2.isSelected())levelChange(2);
			if(j3.isSelected())levelChange(1);
			while(true){
				sleep(level);
				j.setLocation(j.getLocation().x+ix,j.getLocation().y+iy);
				int jx=j.getLocation().x;
				int jy=j.getLocation().y;
				int my=m.getLocation().y;
				int mx=m.getLocation().x;
				if(jx+105==X|jx==0)tx();
				if(jy==0)ty();
				if(0<=jy+25-my&&jy+25-my<5&&my>30){
					if(mx-99<jx&&jx<mx+99){
						absty();
					}
				}
				if(jy>300){
					b=false;
					j.setEnabled(true);
					m.setEnabled(true);
					j.setLocation(125,200);
					m.setLocation(265,200);
					j.setText("You Lose");
					m.setText("Restart");
					j1.setVisible(true);
                    j2.setVisible(true);
                    j3.setVisible(true);
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void jumpBall(){
		f=new JFrame();
		j=new JButton("BY WYX");
		m=new JButton("Start");
		j1=new JRadioButton("easy",true);
		j2=new JRadioButton("middle");
		j3=new JRadioButton("difficult");
		ButtonGroup J=new ButtonGroup();
		J.add(j1);
		J.add(j2);
		J.add(j3);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension sc=kit.getScreenSize();
		int fx=(sc.width-X)/2;
		int fy=(sc.height-Y)/2;
		f.setBounds(fx,fy,X,Y);
		f.setTitle("JumpingBall");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
        f.setLayout(null);
         j.setBounds(125,200,100,25);f.add(j);
        m.setBounds(265,200,100,25);f.add(m);
         j1.setBounds(205,80,100,15);f.add(j1);
        j2.setBounds(205,100,100,15);f.add(j2);
        j3.setBounds(205,120,100,15);f.add(j3);
        m.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        j1.setVisible(false);
                        j2.setVisible(false);
                        j3.setVisible(false);
                        b=true;
                        j.setEnabled(false);
						m.setEnabled(false);
						new jump().start();
						f.addMouseMotionListener(new MouseMotionListener(){
							public void mouseDragged(MouseEvent e) {
								if(b)
								m.setLocation(e.getPoint());
							}
							public void mouseMoved(MouseEvent e) {
								if(b)
								m.setLocation(e.getPoint());
							}
						});
                    }
                });
        f.setVisible(true);
	}
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		new jump().jumpBall();
    }
}
