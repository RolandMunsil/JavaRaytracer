import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

public class Main 
{
	//THINGS TO TRY
	//Looking through 2 refractspheres - lens or something?
	//Nodistortion cube with refracting sphere in center - see if it looks wrong
	
	
	static int width = 1280 + 16;
	static int height = 720 + 38;
	static int actualWidth = width - 16;
	static int actualHeight = height - 38;
	
	public static Color niceBlue = 		new Color(0.1f, 0.5f, 1.0f);
	public static Color niceYellow = 	new Color(1.0f, 0.9f, 0.1f);
	public static Color niceRed =		new Color(1.0f, 0.3f, 0.1f);
	public static Color niceGreen = 	new Color(0.3f, 1.0f, 0.1f);
	public static Color goodGray = 		new Color(.75f, .75f, .75f);
	
	public static final Color BACKGROUND_COLOR = new Color(154, 206, 235);
	public static final double GLOBAL_LIGHT_LEVEL = 0;
	public static final int MAX_REFLECTIONS = 16;
	public static final int MAX_REFRACTIONS = 16;
	public static final int ANTIALIASING_AMOUNT = 13;
	public static double ZOOM = 1;
	public static double CAMERA_SIZE = 1 * ZOOM;
	public static double FOCAL_LENGTH = 700.0 * ZOOM; //Sort of like FOV
	
	public static final boolean DO_LIGHTING = true;
	
	public static boolean SAVE_IMAGES = false;
	
	//public static Camera camera = new Camera(new Point3D(1000, 200, -1000), -Math.PI / 4, -Math.PI / 10);
	//public static Camera camera = new Camera(new Point3D(-200, 200, -400), .2, -.1);
	//public static Camera camera = new Camera(new Point3D(0, 600, 0), 0, -Math.PI / 2);
	//public static Camera camera = new Camera(new Point3D(1099.8883652832799, 843.5992676516778, -799.115673436598), -0.9424777960769379, -0.5553603672697958);
	
	
	
	;
	
//	public static Sphere sphere = new Sphere(Color.BLACK, new Point3D(350, -200, 0), 300, .5);
//	public static Sphere sphere2 = new Sphere(Color.WHITE, new Point3D(-350, -200, 0), 300, .5);
	
	//public static Hyperbola hyperbola = new Hyperbola(goodGray, new Point3D(0, 400, 0), 200, 0, .9, 1.9);
	
	/*
	
	public static YPlane plane = new YPlane(-600, .4, 0, 1);
	public static Sphere smallSphere = new Sphere(goodGray, new Point3D(800, 0, 0), 75, .8, 0, 1.5);
//	
	public static Cuboid cube = new Cuboid(new Point3D(0, 0, 0), 800, 800, 800, goodGray, .5, 0, 1.5);
	
	public static Renderable[] renderedObjects = { plane, cube, smallSphere };
	
	*/
	
	
	
//	public static Hyperbola hyperbola = new Hyperbola(Color.BLACK, new Point3D(0, 400, 0), 200, .5);
	
//	public static Sphere rotatingSphere = new Sphere(goodGray, new Point3D(0, 0, -600), 75, .75, 0, 1);
	
//	public static Sphere sphere1 = new Sphere(Color.RED, new Point3D(0, 0, 500), 400, .5);
//	public static Sphere sphere2 = new Sphere(Color.BLUE, new Point3D(0, 0, -500), 400, .5);
//	
	
	
	//public static Sphere sphere4 = new Sphere(niceBlue, new Point3D(0, 0, 0), 400, .75, 0, Ray.WATER_REFR_INDEX);
	
//	////////////////////////////////////////
//	// TWO REFRACTIVE SPHERES IN A LINE TO CREATE A LENS THING
//	////////////////////////////////////////
//
//  public static Camera camera = new Camera(new Point3D(0, 0, -600), 0, 0);	
//  //public static Camera camera = new Camera(new Point3D(1099.8883652832799, 843.5992676516778, -799.115673436598), -0.9424777960769379, -0.5553603672697958);
//
//	public static YPlane plane = new YPlane(-600, .4, 0, 1);
//	public static Sphere closeSphere = new Sphere(niceBlue, new Point3D(0, 0, 0), 500, 0, .8, 1.5);
//	public static Sphere farSphere = new Sphere(niceBlue, new Point3D(0, 0, 1500), 400, 0, .8, 1.5);
//	
//	public static Cuboid farthestCube = new Cuboid(new Point3D(-800, 0, 2400), 200, 200, 200, niceRed, .75, 0, 0);
//	
//	public static Renderable[] renderedObjects = { plane, closeSphere, farSphere, farthestCube };
//	
//	////////////////////////////////////////	
	
	
	
//	////////////////////////////////////////
//	// REFRACTIVE CUBE W/ SPHERE INSIDE
//	////////////////////////////////////////
//
//	public static Camera camera = new Camera(new Point3D(1099.8883652832799, 843.5992676516778, -799.115673436598), -0.9424777960769379, -0.5553603672697958);	
//
//	public static YPlane plane = new YPlane(-400, .4, 0, 1);
//	public static Cuboid refractingCube = new Cuboid(new Point3D(0, 0, 0), 800, 800, 800, goodGray, 0, .8, 1.5);
//	public static Sphere encasedSphere = new Sphere(niceBlue, new Point3D(0, 0, 0), 200, .6, 0, 1.5);
//	
//	public static Renderable[] renderedObjects = { plane, refractingCube, encasedSphere };
//	
//	////////////////////////////////////////
	
	public static Camera camera = new Camera(new Point3D(0, 0, 0), 0, 0);	

	public static YPlane plane = new YPlane(-400, 0, 0, 1);
	public static Cuboid wall = new Cuboid(new Point3D(0, 0, 1000), 100000, 100000, 4, niceRed, .0, 0, 1.5);
	public static Sphere sideSphere = new Sphere(niceBlue, new Point3D(-400, 0, 600), 200, .3, 0, 1.5);
	
	public static Renderable[] renderedObjects = { plane, wall, sideSphere };
	
	
//	
//	public static int dis = 1400;
//	
//	public static Light aLight = new Light(new Point3D(0, 400, -dis), 6000, .5);
//	public static Light bLight = new Light(new Point3D(0, 400, dis), 6000, .5);
//	public static Light cLight = new Light(new Point3D(-dis, 400, 0), 6000, .5);
//	public static Light dLight = new Light(new Point3D(dis, 400, 0), 6000, .5);
	
	public static Light aboveLight = new Light(new Point3D(-1000, 500, 400), 3000, 1);
	
	public static Light[] lights = { aboveLight };
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Ray Tracer");

        CustomPanel panel = new CustomPanel(actualWidth, actualHeight, ANTIALIASING_AMOUNT);
        frame.setBounds(0, 0, width, height);

        frame.add(panel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        renderedObjects = new Renderable[26];
//        
//        for(int x = 0; x < 5; x++)
//        {
//        	for(int z = 0; z < 5; z++)
//            {
//            	int adjX = x * 400;
//            	int adjZ = z * 400;
//            	Sphere sphere = new Sphere(new Color(170, 0, 0), new Point3D(adjX - 800, 0, adjZ - 800), 175, .5, 0, 0);
//            	renderedObjects[(x * 5) + z] = sphere;
//            }
//        }
//        renderedObjects[25] = plane;
        
        
//        int size = 5;
//        double radius = 100;
//        
//        lights = new Light[size * size * size];
//        for(int x = 0; x < size; x++)
//        {
//        	for(int y = 0; y < size; y++)
//            {
//        		for(int z = 0; z < size; z++)
//                {
//                	double adjX = (x - (size / 2.0)) * radius;
//                	double adjY = (y - (size / 2.0)) * radius;
//                	double adjZ = (z - (size / 2.0)) * radius;
//                	
//                	lights[(x * size * size) + (y * size) + z] = new Light(new Point3D(adjX + 1100, adjY + 1100, adjZ), 6000, .75 / ((double)size * size * size));
//                }
//            }
//        }
        
        
        
        int totalFrames = 900;
        
        
        for(int frameNum = 0; frameNum < totalFrames; frameNum++)
        {
//        	double angleHoriz = -((frameNum / 200.0) * Math.PI * 2);
//        	double angleVert = -(Math.PI / 4) * Math.sin((frameNum / 240.0) * Math.PI * 2);
//        	
//        	camera.angleHoriz = angleHoriz;
//        	camera.angleVert = angleVert;
//        	
//        	camera.center = new Point3D(0, 0, 0);
//        	Point3D point = camera.GetAdjustedForCameraRotation(new Point3D(0, 0, 1600));
//        	camera.center = new Point3D(-point.x, -point.y, -point.z);
//        	
//        	rotatingSphere.center = Utils.RotatePointAroundPoint(new Point3D(0, 0, 0), new Point3D(0, 0, -600), frameNum / 45.0, Math.sin(frameNum / 45.0));
//        	
//        	//panel.clearPanel(0xFFFFFFFF);  
        	
        	//largeCube.refractionIndex += .05;
        	
        	//rotatingSphere.center.z += 10;
        	//sphere4.refractionIndex -= 1 / 40.0;
        	
        	//closeSphere.center.z -= 30;
        	
        	//encasedSphere.center.z += 40;
        	
        	//SetZoom(ZOOM * 1.4);
        	
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
	        		
	        		Point3D origin = new Point3D(adjX * (1.0 / CAMERA_SIZE), adjY * (1.0 / CAMERA_SIZE), 0);
	        		origin = Point3D.Add(origin, camera.center);
	        		origin = camera.GetAdjustedForCameraRotation(origin);
	        		
	        		Vector3D direction = camera.GetAdjustedForCameraRotation(new Vector3D(adjX * (1 / FOCAL_LENGTH), adjY * (1 / FOCAL_LENGTH), 1));
	        		
	        		Ray ray = new Ray(direction, origin, Ray.AIR_REFR_INDEX);
	        		
	        		Color color = GetColorAt(ray, MAX_REFLECTIONS, MAX_REFRACTIONS);
	
	        		panel.setPixel(x, y, color);
	        		
	        		lights[0].center.x = -1000 + ((Math.random() * 400) - 200);
	        		lights[0].center.y = 500   + ((Math.random() * 400) - 200);
	        		lights[0].center.z = 300   + ((Math.random() * 400) - 200);
	            }
	        	panel.repaint();
	        }
	        panel.repaint();
	        
	        if(SAVE_IMAGES)
	        {
		        try 
		        {
		        	panel.SaveScaledDownScreen("C:\\Users\\Roland\\Desktop\\JavaRaytracer Renders\\image" + String.format("%03d", frameNum + 1) + ".png");
				} 
		        catch (IOException e) 
		        {
					e.printStackTrace();
				}
	        }
        }
        
        
        /*
        panel.repaint();
        
        
        int startDiff = 1024;
        
        for(int skipAmount = startDiff; skipAmount > 0; skipAmount /= 2)
        {
        	for(int x = 0; x < panel.unscaledWidth; x += skipAmount)
	        {
	        	for(int y = 0; y < panel.unscaledHeight; y += skipAmount)
	            {
	        		if(skipAmount != startDiff)
	        		{
	        			if((x / skipAmount % 2) == 0 &&
	        			   (y / skipAmount % 2) == 0)
	        			{
	        				continue;
	        			}
	        		}
	        		
	        		double adjX = x - (panel.unscaledWidth / 2);
	        		double adjY = y - (panel.unscaledHeight / 2);
	        		
	        		adjX /= panel.antialiasingAmount;
	        		adjY /= panel.antialiasingAmount;
	        		
	        		//This is so that positive y will be upwards instead of downwards
	        		adjY *= -1;
	        		
	        		Point3D origin = new Point3D(adjX * (1.0 / CAMERA_SIZE), adjY * (1.0 / CAMERA_SIZE), 0);
	        		origin = Point3D.Add(origin, camera.center);
	        		origin = camera.GetAdjustedForCameraRotation(origin);
	        		
	        		Vector3D direction = camera.GetAdjustedForCameraRotation(new Vector3D(adjX * (1 / FOCAL_LENGTH), adjY * (1 / FOCAL_LENGTH), 1));
	        		
	        		Ray ray = new Ray(direction, origin, Ray.AIR_REFR_INDEX);
	        		
	        		Color color = GetColorAt(ray, MAX_REFLECTIONS, MAX_REFRACTIONS);
	
	        		//panel.setPixel(x, y, color);
	        		panel.setRectangle(x, x + skipAmount - 1, y, y + skipAmount - 1, color);
	        		panel.repaint();
	            }
	        	
	        	//panel.repaint();
	        }
        }
        */
        
	}
	
	public static void SetZoom(double value)
	{
		ZOOM = value;
		CAMERA_SIZE = 1 * ZOOM;
		FOCAL_LENGTH = 700.0 * ZOOM; 
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
	
	public static Color GetColorAt(Ray ray, int maxReflections, int maxRefractions)
	{
		//Set to default
		Color color = BACKGROUND_COLOR;
		
		HitInfo hitInfo = GetFirstHitObject(ray/*, objToIgnore*/);
		if(hitInfo.didHit)
		{
			Point3D hitPoint = ray.GetPointAt(hitInfo.tValue);
			
			color = hitInfo.hitObject.getColorAt(hitPoint);
			
			Color reflectColor = null;
			Color refractColor = null;
			
			if(maxReflections > 0 && hitInfo.hitObject.reflectivity > 0)
			{
				Vector3D reflectVector = ray.ToVector3D().GetReflected(hitInfo.hitObject.getNormalVectorAt(hitPoint));
				//reflectVector = Utils.RotateVectorAroundCenter(reflectVector, (Math.random() - .5) / 1, (Math.random() - .5) / 1);
				double length = 1;//reflectVector.GetLength();
				reflectVector.x += (Math.random() - .5) * (length / 2);
				reflectVector.y += (Math.random() - .5) * (length / 2);
				reflectVector.z += (Math.random() - .5) * (length / 2);
				
				Ray reflectRay = new Ray(reflectVector, hitPoint, ray.containingMaterialRefractIndex);
				reflectColor = GetColorAt(reflectRay/*, hitInfo.hitObject*/, maxReflections - 1, maxRefractions);
			}
			
			if(maxRefractions > 0 && hitInfo.hitObject.refractivity > 0)
			{
				double from = ray.containingMaterialRefractIndex;
				double to = (from == hitInfo.hitObject.refractionIndex ? Ray.AIR_REFR_INDEX : hitInfo.hitObject.refractionIndex);
				
				RefractionInfo refractInfo = ray.ToVector3D().GetRefracted(hitInfo.hitObject.getNormalVectorAt(hitPoint), from, to);
				//refractInfo.refractedVector = Utils.RotateVectorAroundCenter(refractInfo.refractedVector, (Math.random() - .5) / 20, (Math.random() - .5) / 20);
				
				Ray refractRay = new Ray(refractInfo.refractedVector, hitPoint, refractInfo.wasTotalInternalReflection ? from : to);
				refractColor = GetColorAt(refractRay, maxReflections, maxRefractions - 1);
			}
			
			if(reflectColor == null && refractColor == null)
			{
				//Do nothing
			}
			else if (reflectColor != null && refractColor == null) //Only reflect, no refract
			{
				color = WeightedAverage(reflectColor, hitInfo.hitObject.reflectivity, 
						color);
			}
			else if (reflectColor == null && refractColor != null) //Only refract, no reflect
			{
				color = WeightedAverage(refractColor, hitInfo.hitObject.refractivity,
						color);
			}
			else if (reflectColor != null && refractColor != null) //Both refract AND reflect
			{
				color = WeightedAverage(reflectColor, hitInfo.hitObject.reflectivity, 
						refractColor, hitInfo.hitObject.refractivity,
						color);
			}
			
			//color = LinearInterpolate(color, reflectionColor, hitInfo.hitObject.reflectivity);
			//color = LinearInterpolate(color, refractionColor, .8);
			
			
			//Adjust for light
		
			if(DO_LIGHTING)
			{
				double lightLevel = GetLightLevelAt(ray.GetPointAt(hitInfo.tValue), hitInfo.hitObject);
				
				int newR = (int) (color.getRed() * lightLevel);
				int newG = (int) (color.getGreen() * lightLevel);
				int newB = (int) (color.getBlue() * lightLevel);
				
				if(newR > 255) newR = 255;
				if(newG > 255) newG = 255;
				if(newB > 255) newB = 255;
				
				color = new Color(newR, newG, newB);
			}
		}
		
		return color;
	}
	
	public static double GetLightLevelAt(Point3D point, Renderable associatedObj)
	{
		//Default light level
		double lightLevel = GLOBAL_LIGHT_LEVEL;
		
		for(Light light : lights)
		{
			Ray lightRay = new Ray(point, light.center, 0);
			
			HitInfo info = GetFirstHitObject(lightRay/*, associatedObj*/);
			
			//First check if there is anything in the way OTHER than the associated Object
			if(info.tValue > 1 || info.tValue == Ray.NO_INTERSECTION)
			{				
				lightLevel += light.GetLightLevelAt(point);
			}
		}
		
		return lightLevel;
	}

	public static Color WeightedAverage(Color color1, double weight1, Color color2)
	{
		return new Color(
				(int)((color1.getRed() * weight1) + (color2.getRed() * (1 - weight1))),
				(int)((color1.getGreen() * weight1) + (color2.getGreen() * (1 - weight1))),
				(int)((color1.getBlue() * weight1) + (color2.getBlue() * (1 - weight1)))
				); 
	}
	
	public static Color WeightedAverage(Color color1, double weight1, Color color2, double weight2, Color color3)
	{
		double weight3 = 1 - (weight1 + weight2);
		return new Color(
				(int)((color1.getRed() * weight1) + (color2.getRed() * weight2) + (color3.getRed() * weight3)),
				(int)((color1.getGreen() * weight1) + (color2.getGreen() * weight2) + (color3.getGreen() * weight3)),
				(int)((color1.getBlue() * weight1) + (color2.getBlue() * weight2) + (color3.getBlue() * weight3))
				);
		
		
	}
}
