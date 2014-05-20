import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

public class Main 
{
	static int width = 1680;
	static int height = 1050;
	static int actualWidth = width - 16;
	static int actualHeight = height - 38;
	
	public static final Color SKY_COLOR = new Color(154, 206, 235);
	public static final int MAX_REFLECTIONS = 10;
	public static final int ANTIALIASING_AMOUNT = 1;
	
	public static Sphere sphere = new Sphere(Color.BLACK, new Point3D(350, -200, 600), 300, .5);
	public static Sphere sphere2 = new Sphere(Color.WHITE, new Point3D(-350, -200, 600), 300, .5);
	public static YPlane plane = new YPlane(-500, 0);
	
	public static Renderable[] renderedObjects = { sphere, plane, sphere2 };
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("3D Parametric Equation");

        CustomPanel panel = new CustomPanel(actualWidth, actualHeight, ANTIALIASING_AMOUNT);
        frame.setBounds(0, 0, width, height);

        frame.add(panel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for(int x = 0; x < panel.unscaledWidth; x++)
        {
        	for(int y = 0; y < panel.unscaledHeight; y++)
            {
        		double adjX = x - (panel.unscaledWidth / 2);
        		double adjY = y - (panel.unscaledHeight / 2);
        		
        		adjX /= panel.antialiasingAmount;
        		adjY /= panel.antialiasingAmount;
        		
        		//This is so that positive y will be upwards instead of downwards
        		adjY *= -1;
        		
        		Random random = new Random();
        		
        		Point3D origin = new Point3D(adjX, adjY, 0);
        		Vector3D direction = new Vector3D(adjX / 900, adjY / 900, 1);
        		
        		Ray ray = new Ray(direction, origin);
        		
        		Color color = GetColorAt(ray, null, MAX_REFLECTIONS);

        		panel.setPixel(x, y, color);
            }
        	panel.repaint();
        }
	}
	
	public static HitInfo GetFirstHitObject(Ray ray, Renderable objToIgnore)
	{
		HitInfo closestHit = new HitInfo(false, null, Double.MAX_VALUE);
		
		for(int i = 0; i < renderedObjects.length; i++)
		{
			if(renderedObjects[i] == objToIgnore)
			{
				continue;
			}
			
			double intersection = renderedObjects[i].getIntersectionValue(ray);
			
			if(intersection != Ray.NO_INTERSECTION && intersection < closestHit.tValue)
			{
				closestHit = new HitInfo(true, renderedObjects[i], intersection);
			}
		}
		
		return closestHit;
	}	
	
	public static Color GetColorAt(Ray ray, Renderable objToIgnore, int maxReflections)
	{
		//Set to default
		Color color = SKY_COLOR;
		
		HitInfo hitInfo = GetFirstHitObject(ray, objToIgnore);
		if(hitInfo.didHit)
		{
			Point3D hitPoint = ray.GetPointAt(hitInfo.tValue);
			
			color = hitInfo.hitObject.getColorAt(hitPoint);
			
			if(maxReflections > 0)
			{
				Vector3D reflectVector = ray.ToVector3D().GetReflected(hitInfo.hitObject.getNormalVectorAt(hitPoint));
				Ray reflectRay = new Ray(reflectVector, hitPoint);
				
				Color reflectionColor = GetColorAt(reflectRay, hitInfo.hitObject, maxReflections - 1);
				
				color = LinearInterpolate(color, reflectionColor, hitInfo.hitObject.reflectivity);
			}
		}
		
		return color;
	}

	public static Color LinearInterpolate(Color color1, Color color2, double interpAmount)
	{
		double reverseInterpAmount = 1 - interpAmount;
		return new Color(
				(int)((color1.getRed() * reverseInterpAmount) + (color2.getRed() * interpAmount)),
				(int)((color1.getGreen() * reverseInterpAmount) + (color2.getGreen() * interpAmount)),
				(int)((color1.getBlue() * reverseInterpAmount) + (color2.getBlue() * interpAmount))
				); 
	}
}
