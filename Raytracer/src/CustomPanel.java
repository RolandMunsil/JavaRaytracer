import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CustomPanel extends JPanel 
{
    private BufferedImage canvas;

    public CustomPanel(int width, int height) 
    {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    
    @Override
    public Dimension getPreferredSize() 
    {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
    public void clearPanel(int color)
    {
    	for(int x = 0; x < canvas.getWidth(); x++)
    	{
    		for(int y = 0; y < canvas.getHeight(); y++)
        	{
    			setPixel(x, y, color);
        	}
    	}
    }
    
    public void setPixel(int x, int y, Color color)
    {
    	if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight())
    	{
    		canvas.setRGB(x, y, color.getRGB());
    	}
    }
    
    public void setPixel(int x, int y, int color)
    {
    	if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight())
    	{
    		canvas.setRGB(x, y, color);
    	}
    }
}