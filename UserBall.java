import java.awt.Graphics;
import java.awt.Color;

public class UserBall
{
    private int x;
    private int y;
    private int diameter = ShapeViewer.getDIAMETER();
    private final int FRAME_WIDTH = ShapeViewer.getFrameWidth();
    private final int FRAME_HEIGHT = ShapeViewer.getFrameHeight();
    
    public UserBall(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, diameter, diameter);
    }
    
    public void moveUp()
    {
        if (y > 5)
            y -= 5;
    }
    
    public void moveDown()
    {
        if ((y + diameter + 20) < (FRAME_HEIGHT - 5))
            y += 5;
    }
    
    public void moveLeft()
    {
        if (x > 5)
            x -= 5;
    }
    
    public void moveRight()
    {
        if ((x + diameter) < (FRAME_WIDTH - 5))
            x += 5;
    }
    
    public int getXPos()
    {
        return x;
    }
    
    public int getYPos()
    {
        return y;
    }
    
    public int getDiameter()
    {
        return diameter;
    }
    
    public void grow()
    {
        diameter += 4;
    }
    
    public void shrink()
    {
        diameter = ShapeViewer.getDIAMETER();
    }
}
