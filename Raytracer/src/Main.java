import java.awt.Color;

import javax.swing.JFrame;

public class Main 
{
	static int width = 1280;
	static int height = 720;
	static int actualWidth = width - 16;
	static int actualHeight = height - 38;
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("3D Parametric Equation");

        CustomPanel panel = new CustomPanel(actualWidth, actualHeight);
        frame.setBounds(0, 0, width, height);

        frame.add(panel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Sphere sphere = new Sphere(new Point3D(0, 450, 500), 100);
        YPlane plane = new YPlane(500);
        
        for(int x = 0; x < actualWidth; x++)
        {
        	for(int y = 0; y < actualHeight; y++)
            {
        		double adjX = x - (actualWidth / 2);
        		double adjY = y - (actualHeight / 2);
        		
        		Point3D origin = new Point3D(adjX, adjY, 0);
        		Vector3D direction = new Vector3D(adjX / 600, adjY / 600, 1);
        		
        		Ray ray = new Ray(direction, origin);
        		
        		double tValSphere = sphere.getIntersectionValue(ray);
        		double tValPlane = plane.getIntersectionValue(ray);
        		
        		Color color = Color.GREEN;
        		if(tValPlane != Ray.NO_INTERSECTION && tValSphere != Ray.NO_INTERSECTION)
        		{
        			if(tValPlane < tValSphere)
        			{
        				color = plane.getColorAt(ray.GetPointAt(tValPlane));
        			}
        			else
        			{
        				color = sphere.getColorAt(ray.GetPointAt(tValSphere));
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
        		panel.repaint();
            }
        }
	}
}
