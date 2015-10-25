package evol;

import java.util.ArrayList;
import java.util.Collections;

public class GA extends TrainingStrategy {

	// crossover rate
	private double cRate;
	// mutation rate
	private double mRate;
	// number of generations
	private int gens;
	// population size
	private int popSize;
	// pts = 5 -> 5-point crossover
	private int pts;
	// percent of pop to replace in each generation
	private double repl;
	// number of parents for crossover
	private int numParents = 2;

	// actual population
	private ArrayList<Chromosome> pop;

	// neural net
	private FeedForwardANN net;

	/**
	 * Creates a new GA instance
	 * 
	 * @param cRate
	 *            crossover rate between 0 and 1
	 * @param mRate
	 *            mutation rate between 0 and 1
	 * @param gens
	 *            number of generations
	 * @param pop
	 *            population size
	 * @param pts
	 *            number of points for cross over (e.g. pts = 5 -> 5-point
	 *            crossover)
	 * @param repl
	 *            percentage of population to replace each generation, between 0
	 *            and 1
	 */
	public GA(double cRate, double mRate, int gens, int popSize, int pts, double repl) {
		this.cRate = cRate;
		this.mRate = mRate;
		this.gens = gens;
		this.popSize = popSize;
		this.pts = pts;
		this.repl = repl;

	}

	/** Creates new population of random individuals */
	protected void initPopulation() {
		pop = new ArrayList<Chromosome>();
		for (int i = 0; i < popSize; i++) {
			pop.add(new Chromosome(net));
		}
	}

	/**
	 * Calls optimizer on weights of the given neural net
	 */
	protected void optimize(FeedForwardANN net) {
		this.net = net;

		initPopulation();

		run();
	}

	/**
	 * Runs optimization
	 */
	private void run() {
		// runs for specified number of generations
		for (int g = 0; g < gens; g++) {
			// sorts population in ascending order 
			// lower index -> lower error -> better fitness
			Collections.sort(pop);
			
			// create popSize * repl offspring
			int numOffspring = (int) Math.floor(popSize * repl);
			
			ArrayList<Chromosome> newGen = new ArrayList<Chromosome>();

			for (int o = 0; o < numOffspring; o++) {
				// step 1: select() numParents parents
				ArrayList<Chromosome> parents = new ArrayList<Chromosome>();
				for (int p = 0; p < numParents; p++) {
					parents.add(select());
				}
				
				// step 2: conduct crossover() with parents
				Chromosome offspring = crossover(parents);

				// step 3: mutate() offspring
				mutate(offspring);

				// step 4: evaluate offspring
				offspring.evaluate();
				
				newGen.add(offspring);
			}

			// TODO: replace least fit individuals with all the offspring

		}
	}

	/**
	 * Selects a single Chromosome for crossover/mutation
	 * 
	 * @return selected Chromosome
	 */
	protected Chromosome select() {
		// TODO: stub
		return null;
	}

	/**
	 * Creates an offspring Chromosome using x-point crossover
	 * 
	 * @param parents
	 *            an arraylist of parent Chromosomes
	 */
	protected Chromosome crossover(ArrayList<Chromosome> parents) {
		// TODO: stub
		return null;
	}

	/**
	 * Randomly mutates the given chromosome
	 */
	protected Chromosome mutate(Chromosome c) {
		// TODO: stub
		return null;
	}

	//
	// below here are just getters and setters
	//

	public double getcRate() {
		return cRate;
	}

	public void setcRate(double cRate) {
		this.cRate = cRate;
	}

	public double getmRate() {
		return mRate;
	}

	public void setmRate(double mRate) {
		this.mRate = mRate;
	}

	public int getGens() {
		return gens;
	}

	public void setGens(int gens) {
		this.gens = gens;
	}

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public double getRepl() {
		return repl;
	}

	public void setRepl(double repl) {
		this.repl = repl;
	}

	public ArrayList<Chromosome> getPop() {
		return pop;
	}

	public void setPop(ArrayList<Chromosome> pop) {
		this.pop = pop;
	}

	public FeedForwardANN getNet() {
		return net;
	}

	public void setNet(FeedForwardANN net) {
		this.net = net;
	}

}
