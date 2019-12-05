package com.yildizozan;

public abstract class AbstractGeneticAlgorithm {

	/**
	 * Rate for crossover random variable
	 */
	protected double crossoverRate;

	/**
	 * Size of population for probability
	 */
	private int populationSize;

	/**
	 * Rate for mutation random variable
	 */
	private double mutationRate;

	/**
	 * @param populationSize int
	 * @param mutationRate   double
	 * @param crossoverRate  double
	 */
	AbstractGeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}

	final void start() {

		Population population = this.initPopulation();

		this.evalPopulation(population);

		// Keep track of current generation
		int generation = 1;

		while (generation < 100) {
			// Print fittest individual from population
			//this.fitness(population);

//			Individual individual = population.getFittest(0);
//			System.out.printf("%d Gen %f\t%s\n", generation, individual.getFitness(), individual.toString());

			// Steps
			population = this.selection(population);
			population = this.crossover(population);
			population = this.mutation(population);

			this.evalPopulation(population);

			generation++;
		}
	}

	/**
	 * @param population Crossover after work.
	 *
	 * @return Population
	 */
	public Population mutation(Population population) {

		double randomVariable = Math.random();

		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.getIndividual(0).getChromosomeLength(); j++) {
				if (randomVariable < mutationRate) {
					int row = (int) (Math.random() * population.size());
					Individual individual = population.getIndividual(row);
					if (Math.random() < 0.5) {
						individual.setX1(individual.getX1() + Math.random());
					} else {
						individual.setX1(individual.getX2() + Math.random());
					}
				}
			}
		}

		return population;
	}

	/**
	 * @return Population
	 */
	public Population initPopulation() {
		return new Population(this.populationSize, true);
	}

	/**
	 * Calculating individual fitness calculate ops!
	 *
	 * @param individual Individual
	 *
	 * @return double
	 */
	private double calcFitness(Individual individual) {

		final double x1 = individual.getGene(0);
		final double x2 = individual.getGene(1);

		double fitness = Problem.solve(x1, x2);

		// Store fitness
		individual.setFitness(fitness);

		return fitness;
	}

	/**
	 * Evalution population sum fitness whole individual
	 *
	 * @param population Population
	 */
	public void evalPopulation(Population population) {
		double populationFitness = 0;
		for (Individual individual : population.getIndividuals()) {
			populationFitness += calcFitness(individual);
		}
		population.setPopulationFitness(populationFitness);
	}

	/**
	 * Need to implement for method!
	 *
	 * @param population Population
	 *
	 * @return Population
	 */
	public abstract Population crossover(Population population);

	/**
	 * Need to implement for method!
	 *
	 * @param population Population
	 *
	 * @return Population
	 */
	public abstract Population selection(Population population);


}
