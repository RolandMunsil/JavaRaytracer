import java.awt.Color;

import javax.swing.JFrame;

public class Main 
{
	static int width = 1680;
	static int height = 1050;
	static int actualWidth = width - 16;
	static int actualHeight = height - 38;
	
	public static Sphere sphere = new Sphere(new Point3D(0, -100, 300), 300);
	public static YPlane plane = new YPlane(-400);
	
	public static Renderable[] renderedObjects = { sphere, plane };
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("3D Parametric Equation");

        CustomPanel panel = new CustomPanel(actualWidth, actualHeight, 8);
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
        		
        		Point3D origin = new Point3D(adjX, adjY, 0);
        		Vector3D direction = new Vector3D(adjX / 600, adjY / 600, 1);
        		
        		Ray ray = new Ray(direction, origin);
        		
        		double tValSphere = sphere.getIntersectionValue(ray);
        		double tValPlane = plane.getIntersectionValue(ray);
        		
        		Color color = new Color(154, 206, 235);
        		if(tValPlane != Ray.NO_INTERSECTION && tValSphere != Ray.NO_INTERSECTION)
        		{
        			if(tValPlane < tValSphere)
        			{
        				color = plane.getColorAt(ray.GetPointAt(tValPlane));
        			}
        			else
        			{
        				Point3D hitPoint = ray.GetPointAt(tValSphere);
        				color = sphere.getColorAt(hitPoint);
        				
        				Vector3D reflectVector = direction.GetReflected(sphere.getNormalVectorAt(hitPoint));
            			Ray reflectRay = new Ray(reflectVector, hitPoint);
            			
            			Color color2 = new Color(154, 206, 235);
            			double tValPlane2 = plane.getIntersectionValue(reflectRay);
            			if(tValPlane2 != Ray.NO_INTERSECTION)
            			{
            				color2 = plane.getColorAt(reflectRay.GetPointAt(tValPlane2));
            			}
            			color = color2;
        			}
        		}
        		else if (tValPlane != Ray.NO_INTERSECTION && tValSphere == Ray.NO_INTERSECTION)
        		{
        			color = plane.getColorAt(ray.GetPointAt(tValPlane));
        		}
        		else if (tValPlane == Ray.NO_INTERSECTION && tValSphere != Ray.NO_INTERSECTION)
        		{
        			color = sphere.getColorAt(ray.GetPointAt(tValSphere));
        		}
        		else if (tValPlane == Ray.NO_INTERSECTION && tValSphere == Ray.NO_INTERSECTION)
        		{
        			//No intersection, Do nothing
        		}
        		panel.setPixel(x, y, color);
            }
        	panel.repaint();
        }
	}
	
	public static HitInfo GetFirstHitObject(Ray ray)
	{
		HitInfo closestHit = new HitInfo(false, null, Double.MAX_VALUE);
		
		for(int i = 0; i < renderedObjects.length; i++)
		{
			double intersection = renderedObjects[i].getIntersectionValue(ray);
			
			if(intersection != Ray.NO_INTERSECTION && intersection < closestHit.tValue)
			{
				closestHit = new HitInfo(true, renderedObjects[i], intersection);
			}
		}
		
		return closestHit;
	}	
}
