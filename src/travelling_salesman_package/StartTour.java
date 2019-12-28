package travelling_salesman_package;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import given_functions.MST;
import given_functions.TSP;

public class StartTour{
	static String filename = "D:\\Eclipse 2\\TSP_51.txt";
	static String separator = " ";
	static String filenameOPT = "D:\\Eclipse 2\\TSP_51_OPT.txt";
	
	public static double[][] locations = TSP.ReadArrayFile(filename, separator);
	public static void main(String[] args){
		//running all the algorithms 
		getOptimal();
		getLocal();
		long beginTime = System.currentTimeMillis();
		for(int i = 0; i<10; i++){
			//Amount of iterations
			
			//change number to do amount of tours
			//Simple hill climbing(mutation)
		setHillClimb(10,locations,false);
		
			//random restart hill climbing
		//setRandomRestartHillClimb(10,10,locations,false);
		
		
		//simulated annealing 
		//setSimulatedAnnealing(1000000,10,locations,false,10);
		
		
		//stochastic hill climbing
		//setStochasicHillClimb(10,locations,false);
		
		}
		//geting the timing for the algorithms 
		long endTime = System.currentTimeMillis();
		long finalTime = endTime-beginTime;
		
		//long seconds = TimeUnit.SECONDS.convert(finalTime, TimeUnit.NANOSECONDS);
		System.out.println("Final time was "+ finalTime +" milliseconds");

	}
	/**
	 * 
	 * @param iterations
	 * @param nodes
	 * @param print
	 */
	//setting it like this so that its easier to change when creating the report
	
	private static void setHillClimb(int iterations, double[][] nodes, boolean print){
		Algorithms al = new Algorithms();
		//creates new instance of algorithms class
		TSPObject newHillClimbingTour = al.RandomMutation(iterations, nodes, print);
		System.out.println("Random Mutation Hill Climbing "+newHillClimbingTour.getFitness());
		//print out the best fitness of that iteration
	}
	/**
	 * 
	 * @param iterations
	 * @param restarts
	 * @param nodes
	 * @param print
	 */
	private static void setRandomRestartHillClimb(int iterations,int restarts, double[][] nodes, boolean print){
		Algorithms al = new Algorithms();
		TSPObject newRestartHillClimbingTour = al.RandomRestartHillClimber(iterations, 10, nodes, false);
		System.out.println("Random Restart Hill Climbing " +newRestartHillClimbingTour.getFitness());
	}
	
	private static void setSimulatedAnnealing(int iterations, int temp, double[][] nodes, boolean print, double coolingrate){
		Algorithms al = new Algorithms();
		TSPObject newSimulatedAnnealingTour = al.SimulatedAnnealing(iterations, nodes, false, coolingrate, 0.0003);
		System.out.println("Simulated Annealing "+newSimulatedAnnealingTour.getFitness());
	}
	
	private static void setStochasicHillClimb(int iterations, double[][] nodes, boolean print){
		Algorithms al = new Algorithms();
		TSPObject newStochasticHillClimbing = al.StochasticHillClimber(iterations, nodes, false);
		System.out.println("Stochastic Hill Climbing "+newStochasticHillClimbing.getFitness());
	}
	private static void getOptimal(){
		ArrayList<Integer> OptimumSolution = TSP.ReadIntegerFile("D:\\Eclipse 2\\TSP_48_OPT.txt");
		TSPObject OptimumSolutionution = new TSPObject(OptimumSolution,locations.length); //creating an object
		double optimumFitness = OptimumSolutionution.fitnessFunction(locations);
		System.out.println("Local Optima " +optimumFitness);
		
	}
	
	private static void getLocal(){
		double globalFitnessNumber = MST.MSTFitness(locations);
		System.out.println("Global Optima MST: "+globalFitnessNumber);
		
	}
}