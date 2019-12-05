package com.yildizozan;

import java.util.Random;

public class RankSelection implements Selection {

	/**
	 * Implement RankSelection algorithm
	 *
	 * @param population Population
	 *
	 * @return Population
	 */
	@Override
	public Population selection(Population population) {
		// Create new population
		Population newPopulation = new Population(population.size());

		final double populationFitness = population.getPopulationFitness();
		final int populationSize = population.size();

		// Probabilities
		double[] probab = new double[populationSize];

		// Cumulative probabilities
		double[] cprobab = new double[populationSize];

		// Cumulative probably set
		for (int i = 0; i < populationSize; i++) {
			probab[i] = population.getIndividual(i).getFitness() / populationFitness;
			cprobab[i] = population.getIndividual(i).getFitness() / populationFitness;
		}

		// Cumulative probably set
		for (int i = 1; i < populationSize; i++) {
			cprobab[i] = cprobab[i - 1] + probab[i];
		}

		Random random = new Random();
		for (int i = 0; i < populationSize; i++) {
			final int index = Misc.find(cprobab, random.nextDouble());
			newPopulation.setIndividual(i, population.getIndividual(index));
		}

		return newPopulation;
	}
}