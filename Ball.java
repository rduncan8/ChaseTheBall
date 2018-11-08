import java.awt.Graphics;
import java.util.Random;

public class Ball 
{
    private int x, y;
    private int vx = 1;
    private int vy = 1;
    private final int DIAMETER = ShapeViewer.getDIAMETER();
    private final int FRAME_WIDTH = ShapeViewer.getFrameWidth();
    private final int FRAME_HEIGHT = ShapeViewer.getFrameHeight();
    Random rand = new Random();
  
    public Ball(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void paint(Graphics g)
    {     
        g.setColor(ShapeViewer.getColor());
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
    
    public void move()
    {
        if (x > 0 && (x + DIAMETER) <= FRAME_WIDTH &&
               y > 0 && (y + DIAMETER) <= FRAME_HEIGHT)
        {
            x += vx;
            y += vy;
        }
        if ((x + DIAMETER + 5) > FRAME_WIDTH)
        {
            x -= 1;
            vx = -1;
        }
        if ((y + DIAMETER + 40) > FRAME_HEIGHT)
        {
            y -= 1;
            vy = -1;
        }
        if (x == 0)
        {
            x += 1;
            vx = 1;
        }
        if (y == 0)
        {
            y += 1;
            vy = 1;
        }
    }
    
    public int getXPos()
    {
        return x;
    }
    
    public int getYPos()
    {
        return y;
    }
    
    public void disappear()
    {
        x = -5 - DIAMETER;
        y = -5 - DIAMETER;
    }
    
    public void reappear()
    {
        x = rand.nextInt(FRAME_WIDTH - DIAMETER);
        y = rand.nextInt(FRAME_HEIGHT - DIAMETER);
    }
}
