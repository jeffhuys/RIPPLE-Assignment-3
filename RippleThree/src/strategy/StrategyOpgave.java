package strategy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import strategy.matchers.*;

/**
 * LEES DIT
 * Wat ik heb begrepen hieruit is dat de bedoeling is om elk algoritme te
 * "encapsuleren" en verwisselbaar te maken.
 * Zie: http://en.wikipedia.org/wiki/Strategy_pattern
 * 
 * @author Jeff
 */

public class StrategyOpgave
{

        private static String inGet(Scanner in, String prompt) {
            System.out.print(prompt);
            return in.nextLine();
        }
        private static String inGet(String prompt) {
            Scanner in = new Scanner(System.in);
            System.out.print(prompt);
            return in.nextLine();
        }
        
	enum Method {Blind, City, Age, Selection};
	
	public static void main(String[] args)
	{
		ArrayList<Single> allTheSingleLadies = new ArrayList<Single>();
		ArrayList<Single> allTheSingleGentlemen = new ArrayList<Single>();

		generateContent(3000, allTheSingleLadies, false);
		generateContent(3000, allTheSingleGentlemen, true);
		
		Single subject;
		ArrayList<Single> eligibles;
		Random r = new Random();
		if (r.nextBoolean())
		{
			subject = allTheSingleGentlemen.get(r.nextInt(allTheSingleGentlemen.size()));
			eligibles = allTheSingleLadies;
		} else {
			subject = allTheSingleLadies.get(r.nextInt(allTheSingleLadies.size()));
			eligibles = allTheSingleGentlemen;
		}
                //eligibles = allTheSingleLadies;
                
                /*
                String name = inGet("Please enter your name: ");
                String surName = inGet("Please enter your surname: ");
                String city = inGet("Please enter your city: ");
                int age = Integer.decode(inGet("Please enter your age: "));
                double height = Double.parseDouble(inGet("Please enter your height in meters: "));
                int weight = Integer.decode(inGet("Please enter your weight in kg: "));
                boolean smoker = false;
                if(inGet("Do you smoke? Yes/No: ").toLowerCase().equals("yes")) 
                    smoker = true;
                
                boolean pets = false;
                if(inGet("Do you have pets? Yes/No: ").toLowerCase().equals("yes"))
                    pets = true;
                
                */
                subject = new Single("Jeff", "Huijsmans", "Almere", 19, 1.79, 68, true, true);

		System.out.println("We're going to find a match for:\n" + subject);
		
		Single match;
		//System.out.println("\nBlind match:\n" + anybody(match(subject, eligibles, Method.Blind, null)));
                
                // DIT IS NU INTERCHANGEABLE
                Context context;
                context = new Context(new StrategyBlindMatch());
                System.out.println("\nBlind match:\n" + anybody(context.executeStrategy(subject, eligibles, null)));
                context = new Context(new StrategyCityMatch());
                System.out.println("\nCity match:\n" + anybody(context.executeStrategy(subject, eligibles, null)));	
                int[] minMaxAge = {2, 2};
                context = new Context(new StrategyAgeMatch());
                System.out.println("\nAge match:\n" + anybody(context.executeStrategy(subject, eligibles, minMaxAge)));
                
		Criterion[] criteria = {new Criterion(Criterion.Type.Smokes, true),
		                        new Criterion(Criterion.Type.Age, subject.getAge()),
		                        new Criterion(Criterion.Type.Weight, 0, subject.getWeight())};
                context = new Context(new StrategySelectionMatch());
                System.out.println("\nSelection match:\n" + anybody(context.executeStrategy(subject, eligibles, criteria)));
                
                
		System.out.println("\nSelection match (smoker, same age, weights less):\n" + anybody(match(subject, eligibles, Method.Selection, criteria)));
	}
	
	private static String anybody(Single single)
	{
		if (single != null) return single.toString();
		else return "Nobody out there :'(";
	}
	
	private static void generateContent(int amount, ArrayList<Single> people, boolean isAGuy)
	{
		String[] femaleNames = {"Gwen","Kate", "Nelly", "Annie",
			"Sheena", "Olivia", "Helen", "Lily", "Rihanna", "Wilson",
			"LeAnn", "Missy", "Karen", "Cyndi", "Shania", "Alecia",
			"Shakira", "Britney", "Avril", "Janet", "Kylie", "Madonna"};
		String[] maleNames = {"Michael", "Freddy", "Elvis", "John", "Paul",
			"Enrique", "Bryan", "Jon", "George", "Phil", "Stevie",
			"Justin", "Robert", "James", "Johnny", "Marvin", "Bob",
			"Mick", "Jim", "Bruce", "Kurt", "Anthony"};
		String[] lastNames = {"Stefani", "Tunstall", "Furtado", "Lennox",
			"Easton", "Newton", "Reddy", "Allen", "Fenty", "Philips",
			"Rimes", "Elliott", "Carpenter", "Lauper", "Twain", "Moore",
			"Ripoll", "Spears", "Lavigne", "Jackson", "Minogue", "Ciccone",
			"Mercury", "Presley", "Lennon", "McCartney",
			"Iglesias", "Adams", "Bon Jovi", "Michael", "Collins", "Wonder",
			"Timberlake", "Kelly", "Hetfield", "Cash", "Gaye", "Marley",
			"Jagger", "Morrison", "Dickinson", "Cobain", "Kiedis"};
		String[] cities = {"Ankara", "Moscow", "London", "Rome", "Berlin", "Madrid",
			"Budapest", "Warsaw", "Vienna", "Oslo", "Reykjavik", "Amsterdam",
			"Stockholm", "Helsinki", "Dublin", "Paris", "Copenhagen",
			"Lisbon", "Berne", "Athens", "Brussels", "Almere"};
		Random r = new Random();
		String[] names = isAGuy ? maleNames : femaleNames;
		for (int i = 0; i < amount; i++)
		{
			people.add(new Single(names[r.nextInt(names.length)],
			                      lastNames[r.nextInt(lastNames.length)],
			                      cities[r.nextInt(cities.length)],
			                      18 + r.nextInt(35), // age
			                      1.50 + r.nextDouble() * 0.50, // height
			                      55 + r.nextDouble() * 45, // weight
			                      r.nextBoolean(), // smoker
			                      r.nextBoolean())); // has pets
		}
	}
	
	public static Single match(Single subject, ArrayList<Single> database, Method method, Object argument)	{
		Random r = new Random();
		
		switch(method) {
		case Blind:
			if (database.isEmpty()) return null;
			else return database.get(r.nextInt(database.size()));
			
		case City:
			ArrayList<Single> rightCity = new ArrayList<Single>();
			for (Single single : database)
			{
				if (single.getCity().equals(subject.getCity()))
				{
					rightCity.add(single);
				}
			}
			if (rightCity.isEmpty()) return null;
			else return rightCity.get(r.nextInt(rightCity.size()));
			
		case Age:
			ArrayList<Single> rightAge = new ArrayList<Single>();
			int maxYounger = ((int[])argument)[0];
			int maxOlder   = ((int[])argument)[1];
			for (Single single : database)
			{
				if (single.getAge() >= subject.getAge() - maxYounger &&
				    single.getAge() <= subject.getAge() + maxOlder    )
				{
					rightAge.add(single);
				}
			}
			if (rightAge.isEmpty()) return null;
			else return rightAge.get(r.nextInt(rightAge.size()));
			
		case Selection:
			ArrayList<Single> rightCriteria = new ArrayList<Single>();
			Criterion[] criteria = (Criterion[])argument;
			for (Single single : database)
			{
				boolean include = true;
				for (Criterion criterion : criteria)
				{
					if (!criterion.applies(single))
					{
						include = false;
						break;
					}
				}
				if (include) rightCriteria.add(single);
			}
			if (rightCriteria.isEmpty()) return null;
			else return rightCriteria.get(r.nextInt(rightCriteria.size()));
			
		default:
			return null;
		}
	}
        
}