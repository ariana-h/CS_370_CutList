package ArchSpike;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import CutList;

public class PicturePerfect extends CutList{

	private static TexturePaint slatetp;
	private static BufferedImage slate;
	static JFrame window = new JFrame();
	static JButton but = new JButton("click me");
	
	 // 1% of users window
	 private static int winX , winY;
	

	public static void main(String[] args) throws IOException
	{
		CreateWindow();	
		CreateButton();
			    // !! change to JLabel!!
			    JLabel painting = new JLabel() {
			        @Override
			        protected void paintComponent(Graphics g) {
			            super.paintComponent(g); 
			           CreateShape(g);
			           CreateImage(g);
			            repaint();
			        }
			    };
			    window.add(painting);
			    window.setVisible(true);
		
		   but.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e)
	            { try {
				CutList();
				} catch (IOException e1) {}	
	            }});
   }



	private static void CreateWindow() throws IOException 
	{
		//create all images
		slate = ImageIO.read(new File("PineApple.jpg"));
		//create window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(DimMax);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	    
		winX = (int)(DimMax.getWidth()*0.01);
		winY = (int)(DimMax.getHeight()*0.01);
	}
	
	private static void CreateButton() 
	{
		window.add(but);
	    but.setBounds(300, 100, 100, 100); 
	}
	
	private static void CreateImage(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g ;
	    Rectangle P = new Rectangle(600,600,400,400);
	    slatetp = new TexturePaint(slate, P);
		g2.setPaint(slatetp);
        g2.fill(P);
	}
	
	private static void CreateShape(Graphics g) 
	{
	  	Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.drawRect(200, 200, 100, 100);
        g2d.setStroke(new BasicStroke(20));
        g.setColor(Color.RED);
        g.drawRect(500, 500, 50, 100);
        g.setColor(Color.BLUE);
        g.fillRect(winX*50, 0, winX*50, winY*50); 
        
        // test draw
		 g.setColor(Color.black);
	     g.setFont(new Font("verdana",0,40));
	     g.drawString("Live", 100, 500);
	     g.setFont(new Font("Comic Sans MS",Font.PLAIN,40));
	     g.drawString("Laugh", 100, 550);
	     g.setFont(new Font("Freestyle Script",0, 64));
	     g.drawString("Love", 100, 600);
	}	
	

	private static void CutList() throws IOException
	{
		CutList.main(null);
		ArrayList<Wood> List = CutList.GetWood();
		
		for(Wood W : List)
		{
			System.out.println("Mainboard: "+W.GetWoodtype());
			System.out.println("Grain: "+W.GetGrain());
			System.out.println("Length: "+W.GetLength());
			System.out.println("Width: "+W.GetWidth());
			System.out.println("Amount: "+W.GetAmount());
			System.out.println("Name: "+W.GetName());
			System.out.println("\n");
		}
		
		List.get(6).RotateWood(List.get(6));
		
		Wood W = List.get(6);
		System.out.println("Mainboard: "+W.GetWoodtype());
		System.out.println("Grain: "+W.GetGrain());
		System.out.println("Length: "+W.GetLength());
		System.out.println("Width: "+W.GetWidth());
		System.out.println("Amount: "+W.GetAmount());
		System.out.println("Name: "+W.GetName());
		System.out.println("\n");
	}
	
}