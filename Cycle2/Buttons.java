import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.*;


public class Buttons {
	static String kerfThicknessText;
	static DecimalFormat BLT;
	
	public static void openFile(JTextArea fileContentTextArea){
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
            	Algorithm.DrawAlg();    
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
            		Algorithm.DrawAlg();
            	}
                        
            } catch (NumberFormatException ex){
                //Handle invalid input (not a double or integer)
            	JOptionPane.showMessageDialog(null, 
                      "Invalid input. Please enter a valid fraction with numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
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