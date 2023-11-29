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
    	FileRead = false;
    	Algorithm.FileRead = false;
    	Algorithm.Boards.clear();
    	Algorithm.Pieces.clear();
    	Algorithm.ty.repaint();
    	
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open New File");
        int result = fileChooser.showOpenDialog(fileContentTextArea);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());

            try {
        		InnerPanel.innerPanel.removeAll(); 
        		InnerPanel.innerSheetsPanel.removeAll();
            	Algorithm.CutListAlgorithm(selectedFile.getAbsolutePath());
            	FileRead = true;
            	Algorithm.FileRead = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	public static void AddFile(JTextArea fileContentTextArea) {
		if(Algorithm.FileRead)
		{	
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Add a File");
		int result = fileChooser.showOpenDialog(fileContentTextArea);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());
           
            try {
            	Algorithm.CutListAlgorithm(selectedFile.getAbsolutePath());
            	FileRead = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        	}
		}
        else
        {
        	JOptionPane.showMessageDialog(null, "Please open a file before adding one");
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
        int numSpace = 0;
        for(int i = 0 ; i < kerfThicknessText.length() ; i++ ) {
            char c = kerfThicknessText.charAt(i);
            if(c==47)
            	numSlash++;
            if(c==32)
            	numSpace++;
        }
            
        //Validates if the input is a valid double or integer
        if(numSlash == 0 && numSpace ==0) {
            try {
            	if(Double.parseDouble(kerfThicknessText)>0)
            	{
            	InnerPanel.kerfThickness = Double.parseDouble(kerfThicknessText)*10;
            	//Displays the blade thickness on the GUI
           		InnerPanel.kerfThicknessLabel.setText("Kerf Thickness: " + InnerPanel.kerfThickness/10 +" in.");
            	Kerf = true;  
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, 
               				"Invalid input. Please enter a valid positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            	}
            		
            } catch (NumberFormatException ex) {
           		//Handle invalid input (not a double or integer)
           		JOptionPane.showMessageDialog(null, 
           				"Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
           	}
        }
        if(numSlash > 1 || kerfThicknessText.charAt(0)==47 || kerfThicknessText.charAt(kerfThicknessText.length()-1)==47 ||
           numSpace > 1 || kerfThicknessText.charAt(0)==32 || kerfThicknessText.charAt(kerfThicknessText.length()-1)==32) {
          	JOptionPane.showMessageDialog(null, 
           		"Invalid input. Please enter a valid fraction.", "Invalid Input", JOptionPane.ERROR_MESSAGE);	
        }
        
        else if (numSpace > 0 && numSlash==0)
        {
        	JOptionPane.showMessageDialog(null, 
               		"Invalid input. Cant enter mix fraction without a fraction", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
            
        else if(numSlash == 1 && numSpace ==0){
           	String[] numbers = new String[2];
           	numbers = kerfThicknessText.split("/");
           	try {
           		double num1 =Double.parseDouble(numbers[0]);
          		double num2 =Double.parseDouble(numbers[1]);
           			
           		if(num2==0){ 
           			JOptionPane.showMessageDialog(null, 
           				"Invalid input. Cannot divide by 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            	}
           		else if (num1 < 0 || num2 < 0)
           		{
           			JOptionPane.showMessageDialog(null, 
               				"Invalid input. Please input positive numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
           		}
            	else{
            		InnerPanel.kerfThickness = (num1/num2)*10;
            		InnerPanel.kerfThickness = Double.parseDouble(BLT.format(InnerPanel.kerfThickness));
            		InnerPanel.kerfThicknessLabel.setText("kerf Thickness: " + kerfThicknessText+" in.");
                	Kerf = true;  
            	}
                        
            } catch (NumberFormatException ex){
                //Handle invalid input (not a double or integer)
            	JOptionPane.showMessageDialog(null, 
                      "Invalid input. Please enter a valid fraction with numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        else if(numSlash == 1 && numSpace ==1){
        	String[] number = new String[2];
           	number = kerfThicknessText.split(" ");
           	
        	String[] fraction = new String[2];
           	fraction = number[1].split("/");


           	try {
           		double num0 =Double.parseDouble(number[0]);
          		double num1 =Double.parseDouble(fraction[0]);
          		double num2 =Double.parseDouble(fraction[1]);
           			
           		if(num2 ==0){ 
           			JOptionPane.showMessageDialog(null, 
           				"Invalid input. Cannot divide by 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            	}
           		else if (num0 < 0 || num1<0 || num2<0)
           		{
           			JOptionPane.showMessageDialog(null, 
               				"Invalid input. Please input positive numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
           		}
           		
            	else{
            		InnerPanel.kerfThickness = (((num0 * num2)+num1)/num2)*10;
            		InnerPanel.kerfThickness = Double.parseDouble(BLT.format(InnerPanel.kerfThickness));
            		InnerPanel.kerfThicknessLabel.setText("kerf Thickness: " + kerfThicknessText+" in.");
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
    	Algorithm.calc = true;
    	Algorithm.DrawAlg();
    	Algorithm.ty.revalidate();
    	}
    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, 
                    "Please open a valid file and submit a kerf thickness before running", "No File Detected", JOptionPane.ERROR_MESSAGE);
    	}
    	
    }
    
	public static void captureScreen(JFrame frame, JTextArea fileContentTextArea) {
		try {
	        BufferedImage screenshot = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        frame.paint(screenshot.getGraphics());

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Save Screenshot");
	        int result = fileChooser.showOpenDialog(fileContentTextArea);

	        if (result == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            ImageIO.write(screenshot, "png", fileToSave);
	            JOptionPane.showMessageDialog(null, "Screenshot saved successfully!");
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error capturing and saving screenshot.");
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