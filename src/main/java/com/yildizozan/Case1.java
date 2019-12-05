package com.yildizozan;

public class Case1 extends AbstractGeneticAlgorithm {

	/**
	 * @param populationSize int
	 * @param mutationRate   double
	 * @param crossoverRate  double
	 */
	Case1(int populationSize, double mutationRate, double crossoverRate) {
		super(populationSize, mutationRate, crossoverRate);
	}

	/**
	 * OnePointCrossover
	 *
	 * @param population Population
	 *
	 * @return Population
	 */
	@Override
	public Population crossover(Population population) {
		return new OnePointCrossover().crossover(population, this.crossoverRate);
	}

	/**
	 * RouletteWheelSelection
	 *
	 * @param population Population
	 *
	 * @return Population
	 */
	@Override
	public Population selection(Population population) {
		return new RouletteWheelSelection().selection(population);
	}


}
