package com.yildizozan;

import java.util.*;

public class Population {
	private Individual[] population;
	private double populationFitness = -1;

	Population(int populationSize) {
		this.population = new Individual[populationSize];
	}

	/**
	 * TODO: Delete!
	 *
	 * @param populationSize
	 * @param generate
	 */
	public Population(int populationSize, boolean generate) {
		this.population = new Individual[populationSize];
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			this.population[individualCount] = new Individual();
		}
	}

	Individual[] getIndividuals() {
		return this.population;
	}

	Individual getFittest(int offset) {
		Arrays.sort(this.population, new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});
		return this.population[offset];
	}

	double calcFitness() {
		double sumFitness = 0;

		for (Individual indv : population) {
			sumFitness += indv.getFitness();
		}

		return sumFitness;
	}

	double getPopulationFitness() {
		return this.populationFitness;
	}

	void setPopulationFitness(double fitness) {
		this.populationFitness = fitness;
	}

	int size() {
		return this.population.length;
	}

	Individual setIndividual(int offset, Individual individual) {
		return population[offset] = individual;
	}

	public Individual getIndividual(int offset) {
		return population[offset];
	}

	public void shuffle() {
		Random rnd = new Random();
		for (int i = population.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Individual a = population[index];
			population[index] = population[i];
			population[i] = a;
		}
	}

	public ArrayList<Individual> getPopulation() {
		ArrayList<Individual> result = new ArrayList<>();
		Collections.addAll(result, population);
		return result;
	}
}