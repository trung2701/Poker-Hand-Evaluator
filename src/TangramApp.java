
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shinnozuka
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TangramApp extends JFrame{
    JButton flip, rotateShape, reset, shurikenShape, cShape; // Function for player 
    JTextField rotation; //for filling rotation degree
    JButton blue, darkGray, green, mangeta, orange, pink, black, cyan, violet, white, yellow, yellowGreen; // Color for tangram shape
    JPanel mainPanel, colorControlPanel, shapeControlPanel;  //3 main panel      
    CreateTanShape newPanel = new CreateTanShape(); //Create a CreateTanshape panel
    Cshape cShapePanel = new Cshape(); // Create a Cshape panel
    Shuriken shuriken = new Shuriken(); //Create a Shuriken panel
    TangramApp frame; 
    TanShape clone;
    Color color;
    
    public static void main(String [] agrs){ 
        TangramApp tangram = new TangramApp();
        tangram.init();        
    }
    
    public TangramApp(){
        super("Tangram Application");
    }
    
    public void init(){        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 800);
        this.setVisible(true);
        this.setResizable(false);        
        
        mainPanel = new JPanel(); //Create main panel
        mainPanel.setPreferredSize(new Dimension(700, 600)); //set size for panel
        mainPanel.setLayout(new GridLayout(1,0)); //set layout for panel       
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());  //set border for panel                     
        mainPanel.add(newPanel); //main panel with initial panel(shape without puzzle)
        
//Creat blue Button and its behavior        
        blue = new JButton(); 
        blue.setPreferredSize(new Dimension(50, 50));
        blue.setBackground(Color.BLUE);       
        blue.setBorder(BorderFactory.createRaisedBevelBorder());
        blue.addActionListener(new BlueActionListener());
        
//Creat darkGray Button and its behavior        
        darkGray = new JButton();  
        darkGray.setPreferredSize(new Dimension(50, 50));
        darkGray.setBackground(Color.DARK_GRAY);
        darkGray.setBorder(BorderFactory.createRaisedBevelBorder());
        darkGray.addActionListener(new DarkGrayActionListener());
        
//Creat green Button and its behavior        
        green = new JButton();  
        green.setPreferredSize(new Dimension(50, 50));
        green.setBackground(Color.GREEN);
        green.setBorder(BorderFactory.createRaisedBevelBorder());
        green.addActionListener(new GreenActionListener());
        
//Creat mangeta Button and its behavior        
        mangeta = new JButton();        
        mangeta.setPreferredSize(new Dimension(50, 50));
        mangeta.setBackground(Color.MAGENTA);
        mangeta.setBorder(BorderFactory.createRaisedBevelBorder());
        mangeta.addActionListener(new MangetaActionListener());
        
//Creat orange Button and its behavior        
        orange = new JButton();        
        orange.setPreferredSize(new Dimension(50, 50));
        orange.setBackground(Color.ORANGE);
        orange.setBorder(BorderFactory.createRaisedBevelBorder());
        orange.addActionListener(new OrangeActionListener());
        
//Creat pink Button and its behavior        
        pink = new JButton();        
        pink.setPreferredSize(new Dimension(50, 50));
        pink.setBackground(Color.PINK);
        pink.setBorder(BorderFactory.createRaisedBevelBorder());
        pink.addActionListener(new PinkActionListener());
        
//Creat black Button and its behavior        
        black = new JButton();        
        black.setPreferredSize(new Dimension(50, 50));
        black.setBackground(Color.BLACK);
        black.setBorder(BorderFactory.createRaisedBevelBorder());
        black.addActionListener(new BlackActionListener());
        
//Creat cyan Button and its behavior        
        cyan = new JButton();        
        cyan.setPreferredSize(new Dimension(50, 50));
        cyan.setBackground(Color.CYAN);
        cyan.setBorder(BorderFactory.createRaisedBevelBorder());
        cyan.addActionListener(new CyanActionListener());
        
//Creat violet Button and its behavior        
        violet = new JButton();        
        violet.setPreferredSize(new Dimension(50, 50));
        violet.setBackground(new Color(153,51,255));
        violet.setBorder(BorderFactory.createRaisedBevelBorder());
        violet.addActionListener(new VioletActionListener());
        
//Creat white Button and its behavior        
        white = new JButton(); 
        white.setPreferredSize(new Dimension(50, 50));
        white.setBackground(Color.WHITE);
        white.setBorder(BorderFactory.createRaisedBevelBorder());
        white.addActionListener(new WhiteActionListener());
        
//Creat yellow Button and its behavior        
        yellow = new JButton();     
        yellow.setPreferredSize(new Dimension(50, 50));
        yellow.setBackground(Color.YELLOW);
        yellow.setBorder(BorderFactory.createRaisedBevelBorder());
        yellow.addActionListener(new YellowActionListener());
        
//Creat yellowGreen Button and its behavior        
        yellowGreen = new JButton();        
        yellowGreen.setPreferredSize(new Dimension(50, 50));
        yellowGreen.setBackground(new Color(153,255,153));
        yellowGreen.setBorder(BorderFactory.createRaisedBevelBorder());
        yellowGreen.addActionListener(new YellowGreenActionListener());
        
        colorControlPanel = new JPanel();
        colorControlPanel.setPreferredSize(new Dimension(150, 600));
        colorControlPanel.setLayout(new GridLayout(6,2));
        ArrayList<JPanel> panelContainingColor = new ArrayList<>(12); //Create ArrayList containing panel's color 
        for(int i=0; i<12; i++){
            JPanel clone = new JPanel(); //Create new panel - a small panel
            panelContainingColor.add(clone); //Adding small Panel to ArrayList
            panelContainingColor.get(i).setLayout(new FlowLayout(FlowLayout.CENTER,40, 40)); //Set Layout for each small panel in ArrayList
        }        
//Adding each color button to each small panel        
        panelContainingColor.get(0).add(blue);
        panelContainingColor.get(1).add(darkGray);
        panelContainingColor.get(2).add(green);
        panelContainingColor.get(3).add(mangeta);
        panelContainingColor.get(4).add(orange);
        panelContainingColor.get(5).add(pink);
        panelContainingColor.get(6).add(black);
        panelContainingColor.get(7).add(cyan);
        panelContainingColor.get(8).add(violet);
        panelContainingColor.get(9).add(white);
        panelContainingColor.get(10).add(yellow);
        panelContainingColor.get(11).add(yellowGreen);
//Adding small panel to colorControlPanel        
        for(int i=0; i<12; i++){
            colorControlPanel.add(panelContainingColor.get(i));
        }
//Creat button opening shuriken shape panel        
        shurikenShape = new JButton();
        shurikenShape.setPreferredSize(new Dimension(50, 50));
        shurikenShape.setBorder(BorderFactory.createRaisedBevelBorder());  
        shurikenShape.addActionListener(new shurikenShape());        
        try { //Adding image to Shuriken button
            BufferedImage img = ImageIO.read(new File("D:\\trung\\TangramApp\\TangramApp\\Shuriken.jpg"));            
            BufferedImage scaledImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            graphics2D.drawImage(img, 0, 0, 50, 50, null);
            graphics2D.dispose();
            ImageIcon myIcon = new ImageIcon(scaledImage);
            shurikenShape.setIcon(myIcon);
        } catch (IOException ex) {
            Logger.getLogger(TangramApp.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
//Create button opening cShape panel        
        cShape = new JButton();
        cShape.setPreferredSize(new Dimension(50, 50));
        cShape.setBorder(BorderFactory.createRaisedBevelBorder());
        cShape.addActionListener(new CshapeActionListener());
        try { // adding image to cshape button
            BufferedImage img = ImageIO.read(new File("D:\\trung\\TangramApp\\TangramApp\\cShape.jpg"));            
            BufferedImage scaledImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            graphics2D.drawImage(img, 0, 0, 50, 50, null);
            graphics2D.dispose();
            ImageIcon myIcon = new ImageIcon(scaledImage);
            cShape.setIcon(myIcon);
        } catch (IOException ex) {
            Logger.getLogger(TangramApp.class.getName()).log(Level.SEVERE, null, ex);
        }        
//Create flip button                
        flip = new JButton("Flipping", new ImageIcon("rotate.jpeg"));        
        flip.setBackground(Color.LIGHT_GRAY);
        flip.setPreferredSize(new Dimension(100, 50));
        flip.setBorder(BorderFactory.createRaisedBevelBorder());
        flip.addActionListener(new ShapeFlipping());        
//Create textfield containing rotation degree        
        rotation = new JTextField();
        rotation.setPreferredSize(new Dimension(100, 50));
        rotation.setFont(new Font("Perpetua Titling MT", 1, 23));        
        rotation.setText("Degree");        
        rotation.addMouseListener(new RotationListioner());
//Create rotation button         
        rotateShape = new JButton("Rotation");
        rotateShape.setBackground(Color.LIGHT_GRAY);
        rotateShape.setPreferredSize(new Dimension(100, 50));
        rotateShape.setBorder(BorderFactory.createRaisedBevelBorder());
        rotateShape.addActionListener(new RotationActionListener());         
//Create reset button        
        reset = new JButton("Reset");
        reset.setBackground(Color.LIGHT_GRAY);
        reset.setPreferredSize(new Dimension(100, 50));
        reset.setBorder(BorderFactory.createRaisedBevelBorder());
        reset.addActionListener(new ResetMainPanel());
        
//adding control button to panel        
        shapeControlPanel = new JPanel();
        shapeControlPanel.setPreferredSize(new Dimension(900, 100));
        shapeControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        shapeControlPanel.add(shurikenShape);
        shapeControlPanel.add(cShape);        
        shapeControlPanel.add(flip);
        shapeControlPanel.add(rotation);
        shapeControlPanel.add(rotateShape);
        shapeControlPanel.add(reset);

//Adding elements to container        
        Container window = this.getContentPane();
        window.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        window.add(mainPanel);
        window.add(colorControlPanel);
        window.add(shapeControlPanel);
        window.revalidate();
    }
//12 classes event color handling for shapes on 3 panels: shuriken panel, cshape panel and initial panel    
class BlueActionListener implements ActionListener{
     @Override
     public void actionPerformed(ActionEvent e) {
             for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.BLUE);       
                    newPanel.repaint();                    
                    
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.BLUE);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.BLUE);
                    shuriken.repaint();
                }                        
            }
        }
    }

class DarkGrayActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
             for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){                                    
                    newPanel.createShape.get(i).setColor(Color.DARK_GRAY);     
                    newPanel.repaint();                               
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.DARK_GRAY);
                    cShapePanel.repaint();
                }
             
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.DARK_GRAY);
                    shuriken.repaint();
                }                        
            }
        }
    }

class GreenActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
             for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.GREEN);                                       
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.GREEN);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.GREEN);
                    shuriken.repaint();
                }                        
            }
        }
    }

class MangetaActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
             for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.MAGENTA);                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.MAGENTA);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.MAGENTA);
                    shuriken.repaint();
                }                        
            }
        }
    }

class OrangeActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.ORANGE);                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.ORANGE);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.ORANGE);
                    shuriken.repaint();
                }                        
            }
        }
    }

class PinkActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.PINK);                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.PINK);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.PINK);
                    shuriken.repaint();
                }                        
            }
        }
    }

class BlackActionListener implements ActionListener{
     @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.BLACK);                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.BLACK);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.BLACK);
                    shuriken.repaint();
                }                        
            }
        }
    }

class CyanActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(Color.CYAN);                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(Color.CYAN);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(Color.CYAN);
                    shuriken.repaint();
                }                        
            }
        }
    }

class VioletActionListener implements ActionListener{   
        @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).setColor(new Color(153,51,255));                    
                    newPanel.repaint();
                } 
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).setColor(new Color(153,51,255));
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).setColor(new Color(153,51,255));
                    shuriken.repaint();
                }                        
            }
        }
    }

class WhiteActionListener implements ActionListener{
     @Override
     public void actionPerformed(ActionEvent e) {
            for(int i =0; i<newPanel.createShape.size(); i++){
            if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                newPanel.createShape.get(i).setColor(Color.WHITE);                
                newPanel.repaint();
            } 
            
            if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                cShapePanel.cShapePane.get(i).setColor(Color.WHITE);
                cShapePanel.repaint();
            }
            
            if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                shuriken.shape.get(i).setColor(Color.WHITE);
                shuriken.repaint();
            }                        
        }
        }
        
    }
    
class YellowActionListener implements ActionListener{
     @Override
     public void actionPerformed(ActionEvent e) {
        for(int i =0; i<newPanel.createShape.size(); i++){
            if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                newPanel.createShape.get(i).setColor(Color.YELLOW);                
                newPanel.repaint();
            } 
            
            if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                cShapePanel.cShapePane.get(i).setColor(Color.YELLOW);
                cShapePanel.repaint();
            }
            
            if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                shuriken.shape.get(i).setColor(Color.YELLOW);
                shuriken.repaint();
            }                        
        }    
    }        
}

class YellowGreenActionListener implements ActionListener{
    @Override
     public void actionPerformed(ActionEvent e) {
        for(int i =0; i<newPanel.createShape.size(); i++){
            if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                newPanel.createShape.get(i).setColor(new Color(153,255,153));                
                newPanel.repaint();
            } 
            
            if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                cShapePanel.cShapePane.get(i).setColor(new Color(153,255,153));
                cShapePanel.repaint();
            }
            
            if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                shuriken.shape.get(i).setColor(new Color(153,255,153));
                shuriken.repaint();
            }                        
        }
    }
}

//class shapeflipping to handle fliping the shape when flip button is clicked
class ShapeFlipping implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){ 
            for(int i=0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).flip(); 
                    newPanel.repaint();
                }
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).flip(); 
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).flip();
                    shuriken.repaint();                        
                }
            }
            
        }
    }

//class resetmainpanel to handle reset button
class ResetMainPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainPanel.removeAll();      
            newPanel = new CreateTanShape();
            mainPanel.add(newPanel);            
            validate();
            }
        }

//class used to open Cshape panel when C button is clicked
class CshapeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainPanel.removeAll();
            cShapePanel = new Cshape();
            mainPanel.add(cShapePanel);
            validate();
        }
    }

//class used to open shuriken panel when C button is clicked
class shurikenShape implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainPanel.removeAll();
            shuriken = new Shuriken();
            mainPanel.add(shuriken);
            validate();
        }
    }

//class handle rotate button
class RotationActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            double angle = 0;
            try{
                angle = Double.parseDouble(rotation.getText());                
            }catch(Exception exc){
                JOptionPane.showMessageDialog(frame,"You must enter a Number!!!");
            }            
            double radian = Math.toRadians(angle);
            for(int i=0; i<newPanel.createShape.size(); i++){
                if(newPanel.createShape.get(i).getColor().equals(Color.RED)){
                    newPanel.createShape.get(i).rotate(radian);
                    newPanel.repaint();
                }
            
                if(cShapePanel.cShapePane.get(i).getColor().equals(Color.RED)){
                    cShapePanel.cShapePane.get(i).rotate(radian);
                    cShapePanel.repaint();
                }
            
                if(shuriken.shape.get(i).getColor().equals(Color.RED)){
                    shuriken.shape.get(i).rotate(radian);
                    shuriken.repaint();
                }
            }
        }           
    }

//class handle textfield
class RotationListioner extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            rotation.setText("");
        }                
    }
}

class Cshape extends JPanel{
    ArrayList<TanShape> cShapePane = new ArrayList<>();
    TanShape smallTriTan1Hint, smallTriTan2Hint, medTriTanHint, largeTriTan1Hint, largeTriTan2Hint, squareTanHint, parallelHint;      
    Dimension d = this.getSize();              
//create x-coordinate for shapes on paanel    
    double xsmallTriTan1 = 50 + Math.random()*650;
    double xsmallTriTan2= 50 + Math.random()*650;
    double xmedTriTan = 50 + Math.random()*650;    
    double xlargeTriTan1 = 50 + Math.random()*650;
    double xlargeTriTan2 = 50 + Math.random()*650;
    double xsquareTan = 50 + Math.random()*650;
    double xparallelogramTan = 50 + Math.random()*650;
//Create y-coordinate for shapes on panel    
    double ysmallTriTan1 = 30 + Math.random()*550;
    double ysmallTriTan2 = 30 + Math.random()*550;
    double ymedTriTan1 = 30 + Math.random()*550;    
    double ylargeTriTan1 = 30 + Math.random()*550;
    double ylargeTriTan2 = 30 + Math.random()*550;
    double ysquareTan = 30 + Math.random()*550;
    double yparallelogramTan = 30 + Math.random()*550;        
    
    public Cshape(){   
        this.setPreferredSize(new Dimension(700, 600));
        smallTriTan1Hint = new SmallTriTan(321, 197);
        smallTriTan1Hint.rotate(3.141592653589793);
        smallTriTan1Hint.setColor(Color.LIGHT_GRAY);        
        smallTriTan2Hint = new SmallTriTan(392, 388);
        smallTriTan2Hint.flip();        
        smallTriTan2Hint.setColor(Color.LIGHT_GRAY);
        medTriTanHint = new MedTriTan(249, 292);
        medTriTanHint.rotate(-0.7853981633974483);
        medTriTanHint.setColor(Color.LIGHT_GRAY);
        largeTriTan1Hint = new LargeTriTan(249, 198);
        largeTriTan1Hint.setColor(Color.LIGHT_GRAY);
        largeTriTan2Hint = new LargeTriTan(249, 387);
        largeTriTan2Hint.setColor(Color.LIGHT_GRAY);
        largeTriTan2Hint.rotate(-1.570796326794897);
        squareTanHint = new SquareTan(380, 186);
        squareTanHint.setColor(Color.LIGHT_GRAY);
        parallelHint = new ParallelogramTan(344, 399);
        parallelHint.flip();
        parallelHint.rotate(0.7853981633974483);
        parallelHint.setColor(Color.LIGHT_GRAY);
        
        cShapePane.add(new SmallTriTan(xsmallTriTan1, ysmallTriTan1));        
        cShapePane.add(new SmallTriTan(xsmallTriTan2, ysmallTriTan2));
        cShapePane.add(new MedTriTan(xmedTriTan, ymedTriTan1));         
        cShapePane.add(new LargeTriTan(xlargeTriTan1, ylargeTriTan1));
        cShapePane.add(new LargeTriTan(xlargeTriTan2, ylargeTriTan2));
        cShapePane.add(new SquareTan(xsquareTan, ysquareTan));
        cShapePane.add(new ParallelogramTan(xparallelogramTan, yparallelogramTan));
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new Cshape.MyMouseListener());
        addMouseMotionListener(new Cshape.MyMouseMotionListener());                      
    }
            
    @Override
    public void paintComponent(Graphics g){       
        super.paintComponent(g);                      
        Graphics2D g2d = (Graphics2D) g;     
//adding shape on panel      
        smallTriTan1Hint.drawShape(g2d);
        smallTriTan2Hint.drawShape(g2d);
        medTriTanHint.drawShape(g2d);
        largeTriTan1Hint.drawShape(g2d);
        largeTriTan2Hint.drawShape(g2d);
        squareTanHint.drawShape(g2d);
        parallelHint.drawShape(g2d);
                
        for(int i=0; i<cShapePane.size(); i++){
            cShapePane.get(i).drawShape(g2d);
        }              
    }    
//class to handle when press and release shape    
    class MyMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
        for(int i=0; i<cShapePane.size(); i++){        
            if(cShapePane.get(i).contains(e.getX(), e.getY())){                 
                cShapePane.get(i).setColor(Color.RED);                 
                repaint();
            }else{                
                if(cShapePane.get(i).getColor().equals(Color.BLUE)){
                    cShapePane.get(i).setColor(Color.BLUE);                    
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.DARK_GRAY)){
                    cShapePane.get(i).setColor(Color.DARK_GRAY);
                    repaint();
                }else{                                
                if(cShapePane.get(i).getColor().equals(Color.GREEN)){
                    cShapePane.get(i).setColor(Color.GREEN);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.MAGENTA)){
                    cShapePane.get(i).setColor(Color.MAGENTA);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.ORANGE)){
                    cShapePane.get(i).setColor(Color.ORANGE);
                    repaint();                    
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.PINK)){
                    cShapePane.get(i).setColor(Color.PINK);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.BLACK)){
                    cShapePane.get(i).setColor(Color.BLACK);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.CYAN)){
                    cShapePane.get(i).setColor(Color.CYAN);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(new Color(153,51,255))){
                    cShapePane.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.WHITE)){
                    cShapePane.get(i).setColor(Color.WHITE);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(new Color(153,51,255))){
                    cShapePane.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(Color.YELLOW)){
                    cShapePane.get(i).setColor(Color.YELLOW);
                    repaint();
                }else{                
                if(cShapePane.get(i).getColor().equals(new Color(153,255,153))){
                    cShapePane.get(i).setColor(new Color(153,255,153));
                    repaint();
                }else{                
                cShapePane.get(i).setColor(new Color(204,204,255));
                repaint();
                }
            }}}}}}}}}}}}
        }
            }                                           
        }
        
        @Override
         public void mouseReleased(MouseEvent e){
            for(int i=0; i<cShapePane.size(); i++){
            if(cShapePane.get(i).contains(e.getX(), e.getY())){                
                repaint();
            }                            
        }
    }
}
//clss to handle when moving shape
    class MyMouseMotionListener extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e){
              for(int i=0; i<cShapePane.size(); i++){
                if(cShapePane.get(i).contains(e.getX(), e.getY())){
                    cShapePane.get(i).move(e.getX(), e.getY());                
                    }
                }
            }
        }    
    }

class Shuriken extends JPanel{
    ArrayList<TanShape> shape = new ArrayList<>(); //arraylist to contain shapes
    TanShape smallTriTan1Hint, smallTriTan2Hint, medTriTanHint, largeTriTan1Hint, largeTriTan2Hint, squareTanHint, parallelHint;  
    Dimension d = this.getSize();                      
//Create x-coordinate of shape    
    double xsmallTriTan1 = 50 + Math.random()*650;
    double xsmallTriTan2= 50 + Math.random()*650;
    double xmedTriTan = 50 + Math.random()*650;    
    double xlargeTriTan1 = 50 + Math.random()*650;
    double xlargeTriTan2 = 50 + Math.random()*650;
    double xsquareTan = 50 + Math.random()*650;
    double xparallelogramTan = 50 + Math.random()*650;
//Create y-coordiante of shape
    double ysmallTriTan1 = 30 + Math.random()*550;
    double ysmallTriTan2 = 30 + Math.random()*550;
    double ymedTriTan1 = 30 + Math.random()*550;    
    double ylargeTriTan1 = 30 + Math.random()*550;
    double ylargeTriTan2 = 30 + Math.random()*550;
    double ysquareTan = 30 + Math.random()*550;
    double yparallelogramTan = 30 + Math.random()*550;        
    
    public Shuriken(){   
        this.setPreferredSize(new Dimension(700, 600));
//Create pre-defined shape on panel        
        smallTriTan1Hint = new SmallTriTan(519, 271);        
        smallTriTan1Hint.setColor(Color.LIGHT_GRAY);        
        smallTriTan2Hint = new SmallTriTan(328, 343);
        smallTriTan2Hint.rotate(1.570796326794897);        
        smallTriTan2Hint.setColor(Color.LIGHT_GRAY);
        medTriTanHint = new MedTriTan(423, 342);
        medTriTanHint.rotate(3.926990816987241);
        medTriTanHint.setColor(Color.LIGHT_GRAY);
        largeTriTan1Hint = new LargeTriTan(400, 200);
        largeTriTan1Hint.setColor(Color.LIGHT_GRAY);
        largeTriTan1Hint.rotate(-1.570796326794897);
        largeTriTan2Hint = new LargeTriTan(305, 271);
        largeTriTan2Hint.setColor(Color.LIGHT_GRAY);        
        largeTriTan2Hint.rotate(3.141592653589793);
        squareTanHint = new SquareTan(459, 282);
        squareTanHint.setColor(Color.LIGHT_GRAY);
        parallelHint = new ParallelogramTan(388, 390);        
        parallelHint.rotate(0.7853981633974483);
        parallelHint.setColor(Color.LIGHT_GRAY);
        
//Adding shape to array shape        
        shape.add(new SmallTriTan(xsmallTriTan1, ysmallTriTan1));        
        shape.add(new SmallTriTan(xsmallTriTan2, ysmallTriTan2));
        shape.add(new MedTriTan(xmedTriTan, ymedTriTan1));         
        shape.add(new LargeTriTan(xlargeTriTan1, ylargeTriTan1));
        shape.add(new LargeTriTan(xlargeTriTan2, ylargeTriTan2));
        shape.add(new SquareTan(xsquareTan, ysquareTan));
        shape.add(new ParallelogramTan(xparallelogramTan, yparallelogramTan));
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new Shuriken.MyMouseListener());
        addMouseMotionListener(new Shuriken.MyMouseMotionListener());                      
    }
            
    @Override
    public void paintComponent(Graphics g){       
        super.paintComponent(g);                      
        Graphics2D g2d = (Graphics2D) g;    
//Adding pre-defined shape to panel        
        smallTriTan1Hint.drawShape(g2d);
        smallTriTan2Hint.drawShape(g2d);
        medTriTanHint.drawShape(g2d);
        largeTriTan1Hint.drawShape(g2d);
        largeTriTan2Hint.drawShape(g2d);
        squareTanHint.drawShape(g2d);
        parallelHint.drawShape(g2d);
//Adding shape to panel                
        for(int i=0; i<shape.size(); i++){
            shape.get(i).drawShape(g2d);
        }              
    }    
    
    class MyMouseListener extends MouseAdapter{
        @Override
//Implementing mouse press on each shape        
        public void mousePressed(MouseEvent e){
            for(int i=0; i<shape.size(); i++){        
            if(shape.get(i).contains(e.getX(), e.getY())){                 
                shape.get(i).setColor(Color.RED);                 
                repaint();
            }else{                
                if(shape.get(i).getColor().equals(Color.BLUE)){
                    shape.get(i).setColor(Color.BLUE);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.DARK_GRAY)){
                    shape.get(i).setColor(Color.DARK_GRAY);
                    repaint();
                }else{                                
                if(shape.get(i).getColor().equals(Color.GREEN)){
                    shape.get(i).setColor(Color.GREEN);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.MAGENTA)){
                    shape.get(i).setColor(Color.MAGENTA);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.ORANGE)){
                    shape.get(i).setColor(Color.ORANGE);
                    repaint();                    
                }else{                
                if(shape.get(i).getColor().equals(Color.PINK)){
                    shape.get(i).setColor(Color.PINK);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.BLACK)){
                    shape.get(i).setColor(Color.BLACK);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.CYAN)){
                    shape.get(i).setColor(Color.CYAN);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(new Color(153,51,255))){
                    shape.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.WHITE)){
                    shape.get(i).setColor(Color.WHITE);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(new Color(153,51,255))){
                    shape.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(Color.YELLOW)){
                    shape.get(i).setColor(Color.YELLOW);
                    repaint();
                }else{                
                if(shape.get(i).getColor().equals(new Color(153,255,153))){
                    shape.get(i).setColor(new Color(153,255,153));
                    repaint();
                }else{                
                shape.get(i).setColor(new Color(204,204,255));
                repaint();
                }
            }}}}}}}}}}}}
        }
            }
        }

        @Override
//Implementing mouse release on each shape        
            public void mouseReleased(MouseEvent e){
            for(int i=0; i<shape.size(); i++){
            if(shape.get(i).contains(e.getX(), e.getY())){                
                repaint();
            }                            
        }
    }
}
    class MyMouseMotionListener extends MouseAdapter{
        @Override
//Implementing moving shape on panel        
        public void mouseDragged(MouseEvent e){
            for(int i=0; i<shape.size(); i++){
                if(shape.get(i).contains(e.getX(), e.getY())){
                    shape.get(i).move(e.getX(), e.getY());                
                    }
                }
            }    
        }
}
    

class CreateTanShape extends JPanel{
    ArrayList<TanShape> createShape = new ArrayList<>();
    TanShape clone;    
    Dimension d = this.getSize();      
    Graphics2D g2d;
    Double[] xVertex, yVertex;        
    
    double xsmallTriTan1 = 50 + Math.random()*650;
    double xsmallTriTan2= 50 + Math.random()*650;
    double xmedTriTan = 50 + Math.random()*650;    
    double xlargeTriTan1 = 50 + Math.random()*650;
    double xlargeTriTan2 = 50 + Math.random()*650;
    double xsquareTan = 50 + Math.random()*650;
    double xparallelogramTan = 50 + Math.random()*650;
    
    double ysmallTriTan1 = 30 + Math.random()*550;
    double ysmallTriTan2 = 30 + Math.random()*550;
    double ymedTriTan1 = 30 + Math.random()*550;    
    double ylargeTriTan1 = 30 + Math.random()*550;
    double ylargeTriTan2 = 30 + Math.random()*550;
    double ysquareTan = 30 + Math.random()*550;
    double yparallelogramTan = 30 + Math.random()*550;        
    
    public CreateTanShape(){   
        this.setPreferredSize(new Dimension(700, 600));
        createShape.add(new SmallTriTan(xsmallTriTan1, ysmallTriTan1));                
        createShape.add(new SmallTriTan(xsmallTriTan2, ysmallTriTan2));
        createShape.add(new MedTriTan(xmedTriTan, ymedTriTan1));         
        createShape.add(new LargeTriTan(xlargeTriTan1, ylargeTriTan1));
        createShape.add(new LargeTriTan(xlargeTriTan2, ylargeTriTan2));
        createShape.add(new SquareTan(xsquareTan, ysquareTan));
        createShape.add(new ParallelogramTan(xparallelogramTan, yparallelogramTan));
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new CreateTanShape.MyMouseListener());
        addMouseMotionListener(new CreateTanShape.MyMouseMotionListener());                      
    }
            
    @Override
    public void paintComponent(Graphics g){       
        super.paintComponent(g);
        g2d = (Graphics2D) g;              
        for(int i=0; i<createShape.size(); i++){
            createShape.get(i).drawShape(g2d); 
        }
    }        
    
    class MyMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            for(int i=0; i<createShape.size(); i++){        
            if(createShape.get(i).contains(e.getX(), e.getY())){                 
                createShape.get(i).setColor(Color.RED);                 
                repaint();
            }else{                
                if(createShape.get(i).getColor().equals(Color.BLUE)){
                    createShape.get(i).setColor(Color.BLUE);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.DARK_GRAY)){
                    createShape.get(i).setColor(Color.DARK_GRAY);
                    repaint();
                }else{                                
                if(createShape.get(i).getColor().equals(Color.GREEN)){
                    createShape.get(i).setColor(Color.GREEN);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.MAGENTA)){
                    createShape.get(i).setColor(Color.MAGENTA);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.ORANGE)){
                    createShape.get(i).setColor(Color.ORANGE);
                    repaint();                    
                }else{                
                if(createShape.get(i).getColor().equals(Color.PINK)){
                    createShape.get(i).setColor(Color.PINK);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.BLACK)){
                    createShape.get(i).setColor(Color.BLACK);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.CYAN)){
                    createShape.get(i).setColor(Color.CYAN);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(new Color(153,51,255))){
                    createShape.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.WHITE)){
                    createShape.get(i).setColor(Color.WHITE);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(new Color(153,51,255))){
                    createShape.get(i).setColor(new Color(153,51,255));
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(Color.YELLOW)){
                    createShape.get(i).setColor(Color.YELLOW);
                    repaint();
                }else{                
                if(createShape.get(i).getColor().equals(new Color(153,255,153))){
                    createShape.get(i).setColor(new Color(153,255,153));
                    repaint();
                }else{                
                createShape.get(i).setColor(new Color(204,204,255));
                repaint();
                }
            }}}}}}}}}}}}
        }
            }
        }    
               
        public void mouseReleased(MouseEvent e){
            for(int i=0; i<createShape.size(); i++){
            if(createShape.get(i).contains(e.getX(), e.getY())){                
                repaint();
            }                            
        }
        }
    }

    class MyMouseMotionListener extends MouseMotionAdapter{
        @Override
         public void mouseDragged(MouseEvent e){
            for(int i=0; i<createShape.size(); i++){
                if(createShape.get(i).contains(e.getX(), e.getY())){
                    createShape.get(i).move(e.getX(), e.getY());                
                    }
                }
            }
    }    
}

abstract class TanShape {

    private double cX,cY;
    private Shape shape;
    private static int Size = 200;  // Size set to 100 by default
    private AffineTransform translator = new AffineTransform();
    final double root2 = Math.sqrt(2);
    Graphics2D g2d; 
    Color a;

    public TanShape(double cX, double cY){
        a =  new Color(204,204,255);        
	double[] xOff = getXOff(); 
	double[] yOff = getYOff(); 

	double[] xpoints = new double[xOff.length];
	double[] ypoints = new double[yOff.length];

	for (int i=0; i<xOff.length; i++){
	    xpoints[i] = xOff[i] * Size;
	    ypoints[i] = yOff[i] * Size;
	}

	GeneralPath gp = new GeneralPath();
	float xp = new Double(xpoints[0]).floatValue();
	float yp = new Double(ypoints[0]).floatValue();
	gp.moveTo(xp,yp);
	for (int i=1; i < xpoints.length; i++){
	  xp = new Double(xpoints[i]).floatValue();
          yp = new Double(ypoints[i]).floatValue();
	    gp.lineTo(xp,yp);
	}
	gp.closePath();
	translator.setToTranslation(cX,cY);
	this.cX = cX;
	this.cY = cY;
	shape = gp.createTransformedShape(translator);
    }

    abstract double[] getXOff(); 
    abstract double[] getYOff();

    public void setSize(int s){
	Size = s;
    }

    public static int getSize(){
	return Size;
    }

    public double getX(){
	return cX;
    }

   public double getY(){
	return cY;
    }

    public void rotate(double theta){ // angle to rotate shape by (in radians)
	AffineTransform rotator = AffineTransform.getRotateInstance(theta,cX,cY);
	shape = rotator.createTransformedShape(shape);
    }

    public void flip(){  // mirror the shape across the Y-axis through its centre 
	AffineTransform origin = AffineTransform.getTranslateInstance(-cX,-cY);
	AffineTransform reset = AffineTransform.getTranslateInstance(cX,cY);
	AffineTransform flipper = AffineTransform.getScaleInstance(-1,1);
	flipper.concatenate(origin);
	reset.concatenate(flipper);
	shape = reset.createTransformedShape(shape);
    }

    public void move(double nX, double nY){  // move the shape's centre to these coordinates
	translator.setToTranslation(nX-cX, nY-cY);
	shape = translator.createTransformedShape(shape);
	cX = nX;
	cY = nY;
    }

    public boolean contains(double x, double y){  // tests whether the given point lies within the shape
	return shape.contains(x,y);
    }

    public Color getColor()
    {
        return this.a;
    }
    
    public void setColor(Color shapeColor){
        a = shapeColor;
    }        
    
    public void drawShape(Graphics2D g2d){  // displays the shape in the given color with a black outline        
	g2d.setColor(a);
	g2d.fill(shape);
	g2d.setColor(Color.BLACK);
	g2d.draw(shape);
        
    }        
}

class SmallTriTan extends TanShape {

    public SmallTriTan(double cX, double cY){
	super(cX,cY);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (6 * root2), -1/ (6 * root2), 2/ (6*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (6 * root2), 2/ (6 * root2), -1/ (6*root2) };
	return yOff;
    }
}

class MedTriTan extends TanShape {

    public MedTriTan(double cX, double cY){
	super(cX,cY);
    }

    public double[] getXOff(){
        double[] xOff = { -1.0/6 , -1.0/6, 2.0/6 };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1.0/6 , 2.0/6, -1.0/6 };
	return yOff;
    }
}

class LargeTriTan extends TanShape {

    public LargeTriTan(double cX, double cY){
	super(cX,cY);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (3 * root2), -1/ (3 * root2), 2/ (3*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (3 * root2), 2/ (3 * root2), -1/ (3*root2) };
	return yOff;
    }
}

class SquareTan extends TanShape {

    public SquareTan(double cX, double cY){
	super(cX,cY);
    }

    public double[] getXOff(){
        double[] xOff = { -1/ (4 * root2), -1/ (4 * root2), 1/ (4*root2) , 1/(4*root2) };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1/ (4 * root2), 1/ (4 * root2), 1/ (4*root2), -1/(4*root2) };
	return yOff;
    }
}

class ParallelogramTan extends TanShape {

    public ParallelogramTan(double cX, double cY){
	super(cX,cY);
    }

    public double[] getXOff(){
        double[] xOff = { -3.0/8 , -1.0/8, 3.0/8 , 1.0/8 };
	return xOff;
    }

    public double[] getYOff(){
        double[] yOff = { -1.0/8, 1.0/8, 1.0/8, -1.0/8 };
	return yOff;
    }
}

