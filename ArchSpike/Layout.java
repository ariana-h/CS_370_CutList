package ArchSpike;
import javax.swing.*;
import javax.swing.border.LineBorder;

import CutList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Layout extends JFrame{
	public Layout(String title) {
		
		super(title);
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(DimMax);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//buttons
		JButton selectButton = new JButton("Open File");

		
		Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(1,2));
        mainContainer.setBackground(Color.WHITE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.WHITE));
       
        
        
     //top panel
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK, 2));
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(5));
        mainContainer.add(topPanel, BorderLayout.NORTH);
        
        JLabel fileNameLabel = new JLabel("Selected file: ");
        JTextArea fileContentTextArea = new JTextArea("\t");
        
        topPanel.add(selectButton);
        topPanel.add(fileNameLabel);
        topPanel.add(fileContentTextArea);

        
        double[] array = new double[40];
        selectButton.addActionListener(e -> {openFile(fileContentTextArea,array);});
        
        
    //left middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new LineBorder(Color.BLACK, 2));
        middlePanel.setLayout(new FlowLayout(4,4,4));
        middlePanel.setBackground(Color.WHITE);
        
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9,4,5,5));
        gridPanel.setBorder(new LineBorder(Color.BLACK, 2));
        gridPanel.setBackground(Color.LIGHT_GRAY);
        
        
        
        Container c = new Container();
        Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container c4 = new Container();
        Container c5 = new Container();
        Container c6 = new Container();
        Container c7 = new Container();
        Container c8 = new Container();
        
        
        gridPanel.add(c);
        gridPanel.add(c1);
        gridPanel.add(c2);
        gridPanel.add(c3);
        gridPanel.add(c4);
        gridPanel.add(c5);
        gridPanel.add(c6); 
        gridPanel.add(c7);
        gridPanel.add(c8);
        
        c.setLayout(new FlowLayout());
        c1.setBackground(Color.WHITE);
        JLabel length = new JLabel("Length          ");
        JLabel width = new JLabel("Width           ");
        JLabel quantity = new JLabel("Quantity           ");
        JLabel label0 = new JLabel("Label");
        c.add(length);
        c.add(width);
        c.add(quantity);
        c.add(label0);
        
        array[0] = 3.42;
        array[1] = 0.02;
        		
        JTextArea l_input1 = new JTextArea(/*array[0]*/"700\t");
        JTextArea l_input2 = new JTextArea(/*array[3]+*/"640\t");
        JTextArea l_input3 = new JTextArea(array[6]+"\t");
        JTextArea l_input4 = new JTextArea(array[9]+"\t");
        JTextArea l_input5 = new JTextArea(array[12]+"\t");
        JTextArea l_input6 = new JTextArea(array[15]+"\t");
        JTextArea l_input7 = new JTextArea(array[18]+"\t");
        
        JTextArea w_input1 = new JTextArea(/*array[1]+*/"300\t");
        JTextArea w_input2 = new JTextArea(/*array[4]+*/"240\t");
        JTextArea w_input3 = new JTextArea(array[7]+"\t");
        JTextArea w_input4 = new JTextArea(array[10]+"\t");
        JTextArea w_input5 = new JTextArea(array[13]+"\t");
        JTextArea w_input6 = new JTextArea(array[16]+"\t");
        JTextArea w_input7 = new JTextArea(array[19]+"\t");
        
        JTextArea q_input1 = new JTextArea(/*array[2]+*/"1\t");
        JTextArea q_input2 = new JTextArea(/*array[5]+*/"1\t");
        JTextArea q_input3 = new JTextArea(array[8]+"\t");
        JTextArea q_input4 = new JTextArea(array[11]+"\t");
        JTextArea q_input5 = new JTextArea(array[14]+"\t");
        JTextArea q_input6 = new JTextArea(array[17]+"\t");
        JTextArea q_input7 = new JTextArea(array[20]+"\t");
        
        JTextArea la_input1 = new JTextArea("\t");
        JTextArea la_input2 = new JTextArea("B\t");
        JTextArea la_input3 = new JTextArea("\t");
        JTextArea la_input4 = new JTextArea("\t");
        JTextArea la_input5 = new JTextArea("\t");
        JTextArea la_input6 = new JTextArea("\t");
        JTextArea la_input7 = new JTextArea("\t");
        
        
        
        c1.setLayout(new FlowLayout());
        c1.setBackground(Color.WHITE);
        //JLabel label1 = new JLabel((Wood.getLength()));
        c1.add(l_input1);
        c1.add(w_input1);
        c1.add(q_input1);
        c1.add(la_input1);
        
        c2.setLayout(new FlowLayout());
        c2.setBackground(Color.WHITE);
        c2.add(l_input2);
        c2.add(w_input2);
        c2.add(q_input2);
        c2.add(la_input2);
        
        c3.setLayout(new FlowLayout());
        c3.setBackground(Color.WHITE);
        c3.add(l_input3);
        c3.add(w_input3);
        c3.add(q_input3);
        c3.add(la_input3);

        c4.setLayout(new FlowLayout());
        c4.setBackground(Color.WHITE);
        c4.add(l_input4);
        c4.add(w_input4);
        c4.add(q_input4);
        c4.add(la_input4);

        c5.setLayout(new FlowLayout());
        c5.setBackground(Color.WHITE);
        c5.add(l_input5);
        c5.add(w_input5);
        c5.add(q_input5);
        c5.add(la_input5);
        
        c6.setLayout(new FlowLayout());
        c6.setBackground(Color.WHITE);
        c6.add(l_input6);
        c6.add(w_input6);
        c6.add(q_input6);
        c6.add(la_input6);
        
        c7.setLayout(new FlowLayout());
        c7.setBackground(Color.WHITE); 
        c7.add(l_input7);
        c7.add(w_input7);
        c7.add(q_input7);
        c7.add(la_input7);
        
        
        c8.setLayout(new FlowLayout());
        c8.setBackground(Color.WHITE);
        JLabel label8 = new JLabel("Blade Thickness"); 
        c8.add(label8);
        JTextArea text8 = new JTextArea("\t");
        c8.add(text8);
        JButton b = new JButton("submit");
        c8.add(b);
        
       
        
  
    //main body label
    /*  JLabel label = new JLabel("Panels will be displayed later", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(new LineBorder(Color.BLACK,2));
	*/
        
        JLabel label = new JLabel("Panels will be displayed later", SwingConstants.CENTER) {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g); 
	           CreateShape(g);
	            repaint();
	        }
	    };
	    
	    label.setOpaque(true);
        label.setBorder(new LineBorder(Color.BLACK,2));
        
        
        middlePanel.add(gridPanel);
        mainContainer.add(label);
        mainContainer.add(middlePanel, BorderLayout.WEST);


     
	}
	
	
    private void openFile(JTextArea fileContentTextArea, double[] array) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());

            try {
            	array = CutListAlgorithm(selectedFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
	public double[] CutListAlgorithm(String name) throws IOException
	{
		CutList.main(name);
		ArrayList<Wood> List = CutList.GetWood();
		

		double[] array=new double[List.size()*5];
        for(Wood W : List){
        	for(int i=0; i<List.size()*4;i+=3) {
        		array[i] = W.GetLength();
        		array[i+1]= W.GetWidth();
        		array[i+2]= W.GetAmount();
        		//array[i]= W.GetName();
        	}
		}
		
        return array;

	}
	
	private static void CreateShape(Graphics g) 
	{
	  	Graphics2D g2d = (Graphics2D) g.create();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 50, 750, 350);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 750, 350);
        g.setColor(Color.CYAN);
        g.fillRect(80,80,390,190);
        g.setColor(Color.BLACK);
        g.drawRect(80, 80, 390, 190);
        
	}

}
