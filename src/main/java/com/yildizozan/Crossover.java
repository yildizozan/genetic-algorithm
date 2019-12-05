package com.yildizozan;

/**
 * Common interface for Crossover
 */
public interface Crossover {

	/**
	 * Common crossover behavior
	 *
	 * @param population    Population
	 * @param crossoverRate double
	 *
	 * @return Popuulation
	 */
	Population crossover(Population population, double crossoverRate);
}
