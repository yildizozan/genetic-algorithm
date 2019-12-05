package com.yildizozan;

public class Case2 extends AbstractGeneticAlgorithm {

	/**
	 * @param populationSize int
	 * @param mutationRate   double
	 * @param crossoverRate  double
	 */
	Case2(int populationSize, double mutationRate, double crossoverRate) {
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
		return new TwoPointCrossover().crossover(population, this.crossoverRate);
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
		return new RankSelection().selection(population);
	}


}
