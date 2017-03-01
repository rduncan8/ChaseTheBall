import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class OptionsViewer
{
    private static boolean startAnimation = false;
    JFrame optionsFrame = new JFrame("Options");
    private static JComboBox combo = new JComboBox();
    private static JRadioButton red = new JRadioButton("Red");
    private static JRadioButton green = new JRadioButton("Green");
    private static JRadioButton blue = new JRadioButton("Blue");
    private static JRadioButton twentyFive = new JRadioButton("25");
    private static JRadioButton fifty = new JRadioButton("50");
    private static JRadioButton seventyFive = new JRadioButton("75");
    
    public OptionsViewer()
    {
        optionsFrame.setLayout(new GridLayout(4, 1));
        optionsFrame.setSize(300, 500);
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        optionsFrame.setResizable(false);
        optionsFrame.setLocation(1050, 100);
        numOfBallsPanel();
        colorPanel();
        diameterPanel();
        startPanel();
        optionsFrame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        OptionsViewer options = new OptionsViewer();
        ShapeViewer shapeViewer = new ShapeViewer();      
    }
    
    public void numOfBallsPanel()
    {
        JPanel numOfBallsPanel = new JPanel();
        numOfBallsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Number of Balls"));
        combo.addItem(1);
        combo.addItem(2);
        combo.addItem(3);
        combo.addItem(4);
        combo.addItem(5);
        combo.addItem(6);
        combo.addItem(7);
        combo.addItem(8);
        combo.addItem(9);
        combo.addItem(10);
        combo.setSelectedItem(5);
        numOfBallsPanel.add(combo);
        optionsFrame.add(numOfBallsPanel);
    }
    
    public void colorPanel()
    {
        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(new TitledBorder(new EtchedBorder(), "Color of Balls"));
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(red);
        colorGroup.add(green);
        colorGroup.add(blue);
        colorPanel.add(red);
        colorPanel.add(green);
        colorPanel.add(blue);
        red.setSelected(true);
        optionsFrame.add(colorPanel);
    }
    
    public void diameterPanel()
    {
        JPanel diameterPanel = new JPanel();
        diameterPanel.setBorder(new TitledBorder(new EtchedBorder(), "Diameter of Balls"));
        ButtonGroup diameterGroup = new ButtonGroup();
        diameterGroup.add(twentyFive);
        diameterGroup.add(fifty);
        diameterGroup.add(seventyFive);
        diameterPanel.add(twentyFive);
        diameterPanel.add(fifty);
        diameterPanel.add(seventyFive);
        fifty.setSelected(true);
        optionsFrame.add(diameterPanel);
    }
    
    public void startPanel()
    {
        JPanel startPanel = new JPanel();
        startPanel.setBorder(new TitledBorder(new EtchedBorder(), "Start"));
        startPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setText("<html>You control the black ball with the arrow keys.  </br >Try to capture all of the other balls.  </br >Click the start button to begin.</htnl>");
        JButton button = new JButton("Start");        
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startAnimation = true;
                optionsFrame.dispose();
            }
        });
        startPanel.add(label, BorderLayout.NORTH);
        startPanel.add(button, BorderLayout.SOUTH);
        optionsFrame.add(startPanel);
    }
    
    public static boolean animate()
    {
        return startAnimation;
    }
    
    public static int getNumOfBalls()
    {
        return (int)combo.getSelectedItem(); //need to actually set this
    }
    
    public static Color getColor()
    {
        if (red.isSelected())
            return Color.RED;
        if (green.isSelected())
            return Color.GREEN;
        else
            return Color.BLUE;
    }
    
    public static int getDiameter()
    {
        if (twentyFive.isSelected())
            return 25;
        if (fifty.isSelected())
            return 50;
        else
            return 75;
    }
}
