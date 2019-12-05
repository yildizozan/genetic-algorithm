package com.yildizozan;

public class Individual implements Comparable {
	private double[] chromosome;
	private double fitness = -1;

	public Individual(double[] chromosome) {
		this.chromosome = chromosome;
	}

	Individual() {
		this.chromosome = new double[2];

		double x1;
		double x2;

		do {
			x1 = Math.random() * 5;
			x2 = Math.random() * 5;
		} while (5 < x1 + x2);

		this.setGene(0, x1);
		this.setGene(1, x2);
	}

	double[] getChromosome() {
		return this.chromosome;
	}

	int getChromosomeLength() {
		return this.chromosome.length;
	}

	void setGene(int offset, double gene) {
		this.chromosome[offset] = gene;
	}

	double getGene(int offset) {
		return this.chromosome[offset];
	}

	double getFitness() {
		this.fitness = Problem.solve(chromosome[0], chromosome[1]);
		return this.fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		for (double i : this.chromosome) {
			output.append(" ").append(i);
		}
		return output.toString();
	}

	public double getX1() {
		return this.chromosome[0];
	}

	public void setX1(double val) {
		this.chromosome[0] = val;
	}

	public double getX2() {
		return this.chromosome[1];
	}

	public void setX2(double val) {
		this.chromosome[1] = val;
	}

	@Override
	public int compareTo(Object o) {
		Individual other = (Individual) o;
		if (this.getFitness() > other.getFitness()) {
			return -1;
		} else if (this.getFitness() < other.getFitness()) {
			return 1;
		}
		return 0;
	}
}
