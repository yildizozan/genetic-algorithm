package com.yildizozan;

class Problem {
	static double solve(double x1, double x2) {
		return 20 * x1 * x2 + 16 * x2 - 2 * Math.pow(x1, 2) - Math.pow(x2, 2) - Math.pow(x1 + x2, 2);
	}
}
