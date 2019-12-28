package travelling_salesman_package;

import java.util.ArrayList;
import java.util.Collections;

import given_functions.CS2004;

public class Algorithms {
	//Random mutation
		public static TSPObject RandomRestartHillClimber (int iter, int restart, double[][] distances, boolean print) {
			ArrayList<TSPObject> Solutions = new ArrayList<>();
			for (int i=1; i<=restart; i++){
				Solutions.add(RandomMutation (iter,distances,print));
			}
			
			return bestSol(Solutions);
		}
		
		public static TSPObject bestSol(ArrayList<TSPObject> solution) {
			ArrayList<Double> fitness = new ArrayList<>();
			
			for(int i = 0; i < solution.size(); i++) {
				fitness.add(solution.get(i).getFitness());
			}
			
			int min = fitness.indexOf(Collections.min(fitness));
			
			return solution.get(min);
			
			
		}
		
	  
		

		//Stochastic Hill Climber
		public static TSPObject StochasticHillClimber(int iter, double[][] distances, boolean print)
		{
			int n = distances.length;
			TSPObject solution = new TSPObject(n);//creates random solution
			for (int i = 1; i <= iter; i++) //For loop iterated the amount of times needed
			{
				TSPObject OldSolution = new TSPObject(solution.getTour(),n); //solution is copied into OldSolution
				double f1 = OldSolution.fitnessFunction(distances); //first fitness is evaluated within the loop
				
				solution.smallChange(); 
				
				TSPObject NewSolution = new TSPObject(solution.getTour(),n);
				
				double f2 = NewSolution.fitnessFunction(distances); //The new fitness is evaluated with a different variable

				//If the newest fitness is worse than the old one, keep the old solution
				
	            
	            
	            //if a random number is greater than the probability acceptance then the probability is
	            //not good enough so we keep the old fitness
	           
				
				//If the probability acceptance is 0.6 and random is 0.7 it would go back to the old solution so accepting the old solution is better
	            
	            
				if(CS2004.UR(0, 1) > PRAccept(f2,f1,95)) {
					solution=OldSolution;
				}
				
				if(print) {
					System.out.println("Iteration " + i + ": " +" Fitness: " + f1);
				}
			}
		
			return(solution);//returns current solution
		}
		
		

		
		
		private static double PRAccept(double newFitness, double oldFitness, int t) {
			
			return 1.0/(1.0+Math.exp((newFitness-oldFitness)/t));
		}
		
		
		   //Random Restart Hill Climber
			protected static TSPObject RandomMutation (int iter, double[][] distances, boolean print)
			{
				int n = distances.length;
				TSPObject solution = new TSPObject(n);//creates random solution
				for (int i = 1; i <= iter; i++) //For loop iterated the amount of times needed
				{
					
					
					TSPObject OldSolution = new TSPObject(solution.getTour(),n); //copies solution into OldSolution
					double f1 = OldSolution.fitnessFunction(distances); //First fitness is evaluated within the loop
					
					solution.smallChange(); //Small change is made in solution
					
					TSPObject NewSolution = new TSPObject(solution.getTour(),n);
					
					double f2 = NewSolution.fitnessFunction(distances); //The new fitness is evaluated with a different variable

					//If the newest fitness is worse than the old one, keep the old solution
					if (f2 > f1)
					{
						solution=OldSolution;
					}
					if(print) {
						System.out.println("Iteration " + i + ": " +" Fitness: " + f1);
					}
				}
				
				return(solution);//returns current solution
			}


		
		//Simulated Annealing
		protected static TSPObject SimulatedAnnealing(int iter, double[][] distances, boolean print, double coolingrate, double temperature)
		{
			int n = distances.length;
			TSPObject solution = new TSPObject(n);//create random solution
			
			double currentTemp = temperature;
			for (int i = 1; i <= iter; i++) //loops for the number of iterations specified
			{
				
				
				TSPObject OldSolution = new TSPObject(solution.getTour(),n); //solution is copied into OldSolution
				double f1 = OldSolution.fitnessFunction(distances); //evaluates the first fitness within the loop
				
				solution.smallChange(); //small change is performed in solution
				
				TSPObject NewSolution = new TSPObject(solution.getTour(),n);
				
				double f2 = NewSolution.fitnessFunction(distances); //fitness is evaluated to new different variable

				//keep old solution if the new fitness is worse
				if (f2 > f1)
				{
					double p = PR(f2,f1,currentTemp);
					
					if(p<CS2004.UR(0, 1)) {
						solution=OldSolution;
					}
					else
						solution=NewSolution;
				}
				if(print) {
					System.out.println("Iteration " + i + ": " +" Fitness: " + f1);
				}
				
				currentTemp *= coolRate(temperature, i);
			}
			
			return(solution);//returns current solution
		}
		
		private static double coolRate(double initialTemp, int iter) {
		    return Math.exp((Math.log(Math.pow(10,-100))-Math.log(initialTemp))/iter); //figure out the landar 
	    }
		

		
		

		
		
		//PR- probability
		private static double PR(double newFitness, double oldFitness, double currentTemperature) {
			//New Fitness
			//Old Fitness
			double changeOfFitness = newFitness - oldFitness;
			
			return Math.exp(-changeOfFitness/currentTemperature);
			//Current Temperature
			
		}
}
