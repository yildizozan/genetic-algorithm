package com.yildizozan;

/**
 * Common inteface selection
 */
public interface Selection {

	/**
	 * Common behavior
	 *
	 * @param population
	 *
	 * @return Poplation
	 */
	Population selection(Population population);
}
