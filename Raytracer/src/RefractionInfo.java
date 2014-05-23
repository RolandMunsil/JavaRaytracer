public class RefractionInfo 
{
	boolean wasTotalInternalReflection;
	Vector3D refractedVector;
	
	public RefractionInfo(boolean wasTotalInternalReflection, Vector3D refractedVector) 
	{
		this.wasTotalInternalReflection = wasTotalInternalReflection;
		this.refractedVector = refractedVector;
	}
}
