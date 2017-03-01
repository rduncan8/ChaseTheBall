import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class ShapeViewer extends JPanel implements KeyListener
{
    private final static int FRAME_WIDTH = 1000;
    private final static int FRAME_HEIGHT = 800;
    private static int DIAMETER;
    ArrayList<Ball> ballList = new ArrayList<Ball>();
    Random rand = new Random();
    private int numOfBalls;    
    private int collected = 0;
    private static Color color;
    UserBall userBall;
    
    public ShapeViewer()
    {   
        JFrame shapesFrame = new JFrame("Moving Shapes");
        shapesFrame.add(this);
        shapesFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        shapesFrame.setVisible(true);
        shapesFrame.setResizable(false);
        shapesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);   
        
        while (!OptionsViewer.animate())
        {
            numOfBalls = OptionsViewer.getNumOfBalls();
            DIAMETER = OptionsViewer.getDiameter();
            color = OptionsViewer.getColor();
            try
            {
                Thread.sleep(100);
            }
            catch (Exception Ex) {}
        }
        
        if (OptionsViewer.animate())
        {
            startAnimation();
        }   
    }
    
    private void startAnimation()
    {
        userBall = new UserBall(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
        for (int i = 0; i < numOfBalls; i++)
        {
            ballList.add(new Ball(rand.nextInt(FRAME_WIDTH - DIAMETER),
                                  rand.nextInt(FRAME_HEIGHT - DIAMETER)));
        }
        while (true)
        {
            move();
            repaint();
            try
            {
                Thread.sleep(10);
            }
            catch (Exception ex) {}
        }
    }
    
    private void move()
    {
        for (Ball ball : ballList)
        {
            ball.move();
            collides();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (OptionsViewer.animate())
        {
            super.paintComponent(g);
            for (Ball ball : ballList)
            {
                ball.paint(g);
            }
            userBall.paint(g);
        }
        
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                userBall.moveUp();
                collides();
                break;
            case KeyEvent.VK_DOWN:
                userBall.moveDown();
                collides();
                break;
            case KeyEvent.VK_LEFT:
                userBall.moveLeft();
                collides();
                break;
            case KeyEvent.VK_RIGHT:
                userBall.moveRight();
                collides();
                break;
        }
        repaint(); 
    }    
    @Override
    public void keyTyped(KeyEvent e) {}    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public void collides()
    {
        for (Ball ball : ballList)
        {
            if (touching(ball, userBall))
            {
                userBall.grow();
                ball.disappear();
                collected++;
                if (collected == numOfBalls)
                {
                    reset();
                }
            }
        }
    }
    
    private boolean touching(Ball ball, UserBall userBall)
    {
        int x2, x1, y2, y1;
        double distance;
        
        x1 = ball.getXPos() + (DIAMETER / 2);
        y1 = ball.getYPos() + (DIAMETER / 2);
        x2 = userBall.getXPos() + (userBall.getDiameter() / 2);
        y2 = userBall.getYPos() + (userBall.getDiameter() / 2);
        
        distance = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);                
        if (distance <= ((DIAMETER + userBall.getDiameter()) * 25))
            return true;
        else
            return false;
    }
    
    private void reset()
    {
        for (Ball ball : ballList)
        {
            ball.reappear();
        }
        userBall.shrink();
        collected = 0;
    }
    
    public static int getFrameWidth()
    {
        return FRAME_WIDTH;
    }
    
    public static int getFrameHeight()
    {
        return FRAME_HEIGHT;
    }
    
    public static int getDIAMETER()
    {
        return DIAMETER;
    }
    
    public static Color getColor()
    {
        return color;
    }
}
