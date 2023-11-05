import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class Buttons {
	static String kerfThicknessText;
	static DecimalFormat BLT;
	private static boolean FileRead = false , Kerf = false;
	
	 public static void addFileButton(JButton fileButton, JTextArea fileContentTextArea) {
	        JPopupMenu popupMenu = new JPopupMenu();
	        JMenuItem item1 = new JMenuItem("Open new file");
	        JMenuItem item2 = new JMenuItem("Add file to project");
	        
	        item1.addActionListener(e -> openFile(fileContentTextArea));
	        item2.addActionListener(e -> AddFile(fileContentTextArea));
	        
	        popupMenu.add(item1);
	        popupMenu.add(item2);

	        fileButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                popupMenu.show(fileButton, 0, fileButton.getHeight());
	            }
	        });
	 }
	
	public static void openFile(JTextArea fileContentTextArea){
		 InnerPanel.innerPanel.removeAll(); 
		 InnerPanel.innerSheetsPanel.removeAll();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(fileContentTextArea);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());

            try {
            	Algorithm.CutListAlgorithm(selectedFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }       
    }
	
	public static void AddFile(JTextArea fileContentTextArea) {
		//clear all
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(fileContentTextArea);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());
           
            try {
            	Algorithm.CutListAlgorithm(selectedFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	    public static void submitAction(){
	        BLT = new DecimalFormat("0.000");
	        kerfThicknessText = InnerPanel.text.getText();

	        if (kerfThicknessText.isEmpty()) {
	            JOptionPane.showMessageDialog(null, 
	            	"Please enter a value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        int numSlash = 0;
	        for(int i = 0 ; i < kerfThicknessText.length() ; i++ ) {
	            char c = kerfThicknessText.charAt(i);
	            if(c==47)
	            	numSlash++;
	        }
	            
	        //Validates if the input is a valid double or integer
	        if(numSlash == 0) {
	            try {
	            	InnerPanel.kerfThickness = Double.parseDouble(kerfThicknessText);
	            	//Displays the blade thickness on the GUI
	           		InnerPanel.kerfThicknessLabel.setText("Kerf Thickness: " + InnerPanel.kerfThickness);
	            	Kerf = true;  
	            } catch (NumberFormatException ex) {
	           		//Handle invalid input (not a double or integer)
	           		JOptionPane.showMessageDialog(null, 
	           				"Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	           	}
	        }
	        if(numSlash > 1 || kerfThicknessText.charAt(0)==47 || kerfThicknessText.charAt(kerfThicknessText.length()-1)==47) {
	          	JOptionPane.showMessageDialog(null, 
	           		"Invalid input. Please enter a valid fraction.", "Invalid Input", JOptionPane.ERROR_MESSAGE);	
	        }
	            
	        else if(numSlash == 1){
	           	String[] numbers = new String[2];
	           	numbers = kerfThicknessText.split("/");
	           	try {
	           		double num1 =Double.parseDouble(numbers[0]);
	          		double num2 =Double.parseDouble(numbers[1]);
	           			
	           		if(num2 == 0){ 
	           			JOptionPane.showMessageDialog(null, 
	           				"Invalid input. Cannot divide by 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	            	}
	            	else{
	            		InnerPanel.kerfThickness = num1/num2;
	            		InnerPanel.kerfThickness = Double.parseDouble(BLT.format(InnerPanel.kerfThickness));
	            		InnerPanel.kerfThicknessLabel.setText("kerf Thickness: " + kerfThicknessText);
	                	Kerf = true;  
	            	}
	                        
	            } catch (NumberFormatException ex){
	                //Handle invalid input (not a double or integer)
	            	JOptionPane.showMessageDialog(null, 
	                      "Invalid input. Please enter a valid fraction with numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	    
	    public static void Draw()
	    {
	    	if(FileRead && Kerf)
	    	{
	    	Algorithm.DrawAlg();
	    	Algorithm.ty.revalidate();
	    	}
	    	
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, 
	                    "Please open a valid file and submit a kerf thickness before running", "No File Detected", JOptionPane.ERROR_MESSAGE);
	    	}
	    	
	    }
	    
		public static void Grid(ItemEvent e) {
	        if(e.getStateChange() == ItemEvent.SELECTED) {
	            Algorithm.grid = true;
	        } else {
	        	Algorithm.grid = false;
	        }
	        
	    }
		
		public static void WoodGrain(ItemEvent e) {
	        if(e.getStateChange() == ItemEvent.SELECTED) {
	           
	        } else {
	        	
	        }
	        
	    }
		
		public static void captureScreen(JFrame frame) {
			try {
		        BufferedImage screenshot = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
		        frame.paint(screenshot.getGraphics());

		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Save Screenshot");
		        int userSelection = fileChooser.showSaveDialog(null);

		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fileChooser.getSelectedFile();
		            ImageIO.write(screenshot, "png", fileToSave);
		            JOptionPane.showMessageDialog(null, "Screenshot saved successfully!");
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error capturing and saving screenshot.");
		    }
	    }
		
		public static void Label(ItemEvent e) {
	        if(e.getStateChange() == ItemEvent.SELECTED) {
	            Algorithm.Label = true;
	        } else {
	        	Algorithm.Label = false;
	        }
	        
	    }
		
		public static void Measure(ItemEvent e) {
	        if(e.getStateChange() == ItemEvent.SELECTED) {
	            Algorithm.Measure = true;
	        } else {
	        	Algorithm.Measure = false;
	        }
	        
	    }    
	 
	}
