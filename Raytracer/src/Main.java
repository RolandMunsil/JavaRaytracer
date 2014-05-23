import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

public class Main 
{
	static int width = 1280;
	static int height = 720;
	static int actualWidth = width - 16;
	static int actualHeight = height - 38;
	
	public static final Color SKY_COLOR = new Color(154, 206, 235);
	public static final int MAX_REFLECTIONS = 16;
	public static final int ANTIALIASING_AMOUNT = 4;
	public static final double ZOOM = 1;
	public static final double FOCAL_LENGTH = 700.0; //Sort of like FOV
	
	public static Camera camera = new Camera(new Point3D(0, 0, -800), 0, 0);
	
	/*
	public static Sphere sphere = new Sphere(Color.BLACK, new Point3D(350, -200, 0), 300, .5);
	public static Sphere sphere2 = new Sphere(Color.WHITE, new Point3D(-350, -200, 0), 300, .5);
	public static YPlane plane = new YPlane(-800, 0);
	
	public static Cuboid cube = new Cuboid(new Point3D(400, 0, -300), 200, 200, 200, Color.RED, .5);
	
	public static Renderable[] renderedObjects = { sphere, plane, sphere2, cube };
	*/
	
	public static YPlane plane = new YPlane(-800, .5);
	public static Cuboid largeCube = new Cuboid(new Point3D(0, 0, 0), 400, 400, 400, Color.BLACK, .7);
	public static Sphere rotatingSphere = new Sphere(Color.GRAY, new Point3D(0, 0, -600), 75, .6);
	
	
	public static Renderable[] renderedObjects = { largeCube, rotatingSphere, plane };
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Ray Tracer");

        CustomPanel panel = new CustomPanel(actualWidth, actualHeight, ANTIALIASING_AMOUNT);
        frame.setBounds(0, 0, width, height);

        frame.add(panel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int totalFrames = 900;
        
        for(int frameNum = 0; frameNum < totalFrames; frameNum++)
        {
        	double angleHoriz = -((frameNum / 200.0) * Math.PI * 2);
        	double angleVert = -(Math.PI / 4) * Math.sin((frameNum / 240.0) * Math.PI * 2);
        	
        	camera.angleHoriz = angleHoriz;
        	camera.angleVert = angleVert;
        	
        	camera.center = new Point3D(0, 0, 0);
        	Point3D point = camera.GetAdjustedForCameraRotation(new Point3D(0, 0, 800));
        	camera.center = new Point3D(-point.x, -point.y, -point.z);
        	
        	rotatingSphere.center = Utils.RotatePointAroundPoint(new Point3D(0, 0, 0), new Point3D(0, 0, -600), frameNum / 45.0, Math.sin(frameNum / 45.0));
        	
        	panel.clearPanel(0xFFFFFFFF);
        	
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
	        		
	        		Point3D origin = new Point3D(adjX * (1.0 / ZOOM), adjY * (1.0 / ZOOM), 0);
	        		origin = Point3D.Add(origin, camera.center);
	        		origin = camera.GetAdjustedForCameraRotation(origin);
	        		
	        		Vector3D direction = camera.GetAdjustedForCameraRotation(new Vector3D(adjX * (1 / FOCAL_LENGTH), adjY * (1 / FOCAL_LENGTH), 1));
	        		
	        		Ray ray = new Ray(direction, origin);
	        		
	        		Color color = GetColorAt(ray, null, MAX_REFLECTIONS);
	
	        		panel.setPixel(x, y, color);
	            }
	        	//panel.repaint();
	        }
	        panel.repaint();
	        
	        try {
				panel.SaveScaledDownScreen("C:\\Users\\Roland\\Desktop\\JavaRaytracer Renders\\image" + String.format("%03d", frameNum + 1) + ".png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				
				//double size = .25;
				//
				//reflectVector.x += (Math.random() * size) - size / 2;
				//reflectVector.y += (Math.random() * size) - size / 2;
				//reflectVector.z += (Math.random() * size) - size / 2;
				
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
