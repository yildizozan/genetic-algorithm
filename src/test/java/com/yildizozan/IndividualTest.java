package com.yildizozan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IndividualTest {

	@Test
	void isValid() {
		for (int i = 0; i < 100000; i++) {
			Individual individual = new Individual();
			final double x1 = individual.getGene(0);
			final double x2 = individual.getGene(1);

			// Alt sınır
			assertTrue(0 <= x1 + x2);

			// Üst sınır
			assertTrue(x1 + x2 <= 5);
		}
	}

	@Test
	void getChromosome() {
	}

	@Test
	void getChromosomeLength() {
		Individual individual = new Individual();
		assertEquals(2, individual.getChromosomeLength(), "Must be 2!");
	}

	@Test
	void setGene() {

	}

	@Test
	void getGene() {
	}

	@Test
	void getFitness() {
	}

	@Test
	void setFitness() {
	}

	@Test
	void testToString() {
	}

	@Test
	void compareTo() {
	}
}