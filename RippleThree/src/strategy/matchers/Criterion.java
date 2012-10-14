package strategy.matchers;

import strategy.Single;

public class Criterion
{
	public static enum Type {City, Age, Height, Weight, Smokes, HasPets};
	
	Type type;
	Object minVal;
	Object maxVal;
	
	public Criterion(Type type, Object value)
	{
		this(type, value, value);
	}
	
	public Criterion(Type type, Object minVal, Object maxVal)
	{
		this.type = type;
		this.minVal = minVal;
		this.maxVal = maxVal;
	}
	
	public boolean applies(Single single)
	{
		switch(type)
		{
		case City:
			return single.getCity().equals(minVal);
			
		case Age:
			return ((Number)minVal).doubleValue() <= single.getAge() &&
			       ((Number)maxVal).doubleValue() >= single.getAge()  ;
			
		case Height:
			return ((Number)minVal).doubleValue() <= single.getHeight() &&
			       ((Number)maxVal).doubleValue() >= single.getHeight()  ;
			
		case Weight:
			return ((Number)minVal).doubleValue() <= single.getWeight() &&
			       ((Number)maxVal).doubleValue() >= single.getWeight()  ;
			
		case Smokes:
			return ((Boolean)minVal).booleanValue() == single.smokes();
			
		case HasPets:
			return ((Boolean)minVal).booleanValue() == single.hasPets();
			
		default:
			return false;
		}
	}
}