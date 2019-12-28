package travelling_salesman_package;
import java.util.ArrayList;
import java.util.Collections;

import given_functions.CS2004;

public class TSPObject {
	
	private ArrayList<Integer> tour;
	private double fitness;
	
	public TSPObject(ArrayList<Integer> t, int noOfCities) {
		if(t == null) {
			tour = RandomPerm(noOfCities);
		} else {
			tour = (ArrayList<Integer>) t.clone();
		}
	}
	//Random starting point for the Tour
	//Random Permutation
	public TSPObject(int n) {
		tour = RandomPerm(n);
	}

	//n = number of cities
	 
	private ArrayList<Integer> RandomPerm(int n) {
		
		tour = new ArrayList<>();
		
		//need to take away 1 since index starts at 0
		for (int i = 0; i <n - 1;i++ )
		{
			tour.add(i);
		}
		Collections.shuffle(tour); //Shuffles the cities to create a random permutation of a list
		return tour;
		
		
	}
	

	
	public double fitnessFunction(double[][] distances) {
		double s = 0;
		for (int i = 0; i < tour.size()-1; i++)
		{
			
			int a = tour.get(i);
			int b = tour.get(i+1);
			s = s + distances[a][b]; //This calculates the distance
		}		
		Integer end_city = tour.get(tour.size()-1);
		Integer start_city = tour.get(0);
		s = s + distances[end_city][start_city]; //uses the distances to create a tour
		
		fitness = s;
		return s;	
	}
	

	
	//creates small change
	public void smallChange() {
		ArrayList<Integer> tour2 = new  ArrayList<Integer>();
		tour2 = (ArrayList<Integer>)tour.clone();
		int b = CS2004.UI(0,tour2.size()-1);
		int c = CS2004.UI(0,tour2.size()-1);

		Collections.swap(tour, b, c);
	}
	
	

	public ArrayList<Integer> getTour() {
		return tour;
	}
	
	public double getFitness() {
		return fitness;
	}

}


