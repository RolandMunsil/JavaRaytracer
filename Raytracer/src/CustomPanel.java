import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CustomPanel extends JPanel 
{
    private BufferedImage canvas;
    
    public int displayWidth;
    public int displayHeight;
    public int unscaledWidth;
    public int unscaledHeight;
    
    public int antialiasingAmount;

    public CustomPanel(int displayWidth, int displayHeight, int antialiasingAmount) 
    {
        canvas = new BufferedImage(displayWidth * antialiasingAmount, displayHeight * antialiasingAmount, BufferedImage.TYPE_INT_ARGB);
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        
        this.unscaledWidth = displayWidth * antialiasingAmount;
        this.unscaledHeight = displayHeight * antialiasingAmount;
        
        this.antialiasingAmount = antialiasingAmount;
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
        
        BufferedImage scaledDown = GenerateScaledDownScreen();
        g2.drawImage(scaledDown, null, null);
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
    
    public void setRectangle(int xLow, int xHigh, int yLow, int yHigh, Color color)
    {
    	for(int x = xLow; x <= xHigh; x++)
    	{
    		for(int y = yLow; y <= yHigh; y++)
    		{
    			setPixel(x, y, color);
    		}
    	}
    }
    
    public BufferedImage GenerateScaledDownScreen()
    {
    	BufferedImage scaledDown = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_INT_ARGB);
        
        for(int x = 0; x < displayWidth; x++)
        {
        	for(int y = 0; y < displayHeight; y++)
            {
        		int RSum = 0;
        		int GSum = 0;
        		int BSum = 0;
        		
        		for(int subX = 0; subX < antialiasingAmount; subX++)
        		{
        			for(int subY = 0; subY < antialiasingAmount; subY++)
            		{
        				int color = canvas.getRGB((x * antialiasingAmount)  + subX, (y * antialiasingAmount) + subY);
        				RSum += (color & 0x00FF0000) >> 16;
        				GSum += (color & 0x0000FF00) >> 8;
        				BSum += (color & 0x000000FF);
            		}
        		}
        		
        		int averagedColor = 0xFF000000 |
        				((RSum / (antialiasingAmount * antialiasingAmount)) << 16) |
        				((GSum / (antialiasingAmount * antialiasingAmount)) << 8) |
        				((BSum / (antialiasingAmount * antialiasingAmount)));
        		
        		scaledDown.setRGB(x, y, averagedColor);
            }
        }
        
        return scaledDown;
    }
    
    public void SaveUnscaledScreen(String path) throws IOException
    {
    	ImageIO.write(canvas, "png", new File(path));
    }
    
    public void SaveScaledDownScreen(String path) throws IOException
    {
    	ImageIO.write(GenerateScaledDownScreen(), "png", new File(path));
    }
}