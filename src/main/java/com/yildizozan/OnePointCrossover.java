package com.yildizozan;

public class OnePointCrossover implements Crossover {
	/**
	 * Implement one point crossover
	 *
	 * @param population    Population
	 * @param crossoverRate double
	 *
	 * @return Population
	 */
	@Override
	public Population crossover(Population population, double crossoverRate) {
		int[] pairs = Misc.suffle(population.size());

		// Loop over current population by fitness
		for (int populationIndex = 1; populationIndex < population.size() / 2; populationIndex++) {

			// Determine mother and father index
			final int motherIndex = pairs[2 * populationIndex - 1];
			final int fatherIndex = pairs[2 * populationIndex];

			// Select mother & father parent
			Individual mother = population.getIndividual(motherIndex);
			Individual father = population.getIndividual(fatherIndex);

			double randomVariable = Math.random();
			if (randomVariable < crossoverRate) {
				final int cpoint = 1;

				// Swap gene
				double dummy = mother.getX2();
				mother.setX2(father.getX2());
				father.setX2(dummy);

				// Set mother father
				population.setIndividual(motherIndex, mother);
				population.setIndividual(fatherIndex, father);
			}

		}

		return population;
	}
}
