package strategy;

import java.text.DecimalFormat;

public class Single
{
	private String name;
	private String lastName;
	private String city;
	private int age;
	private double height;
	private double weight;
	private boolean smokes;
	private boolean hasPets;
	
	public Single(String name, String lastName, String city, int age, double height,
	              double weight, boolean smokes, boolean hasPets)
	{
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.smokes = smokes;
		this.hasPets = hasPets;
	}
	
	public String getName()     {return name;}
	public String getLastName() {return lastName;}
	public String getCity()     {return city;}
	public int getAge()         {return age;}
	public double getHeight()   {return height;}
	public double getWeight()   {return weight;}
	public boolean smokes()     {return smokes;}
	public boolean hasPets()    {return hasPets;}

	public String toString()
	{
		return name + " " + lastName + " from " + city + ", " + age + " years old, " +
		new DecimalFormat("#.##").format(height) + "m tall, " +
		new DecimalFormat("#.#").format(weight) + "kg, " +
		(smokes ? "Smoker" : "Non-smoker") + ", " + (hasPets ? "Pet owner" : "No pets");
	}
}