package com.yildizozan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
	private static ArrayList<Individual> result1;
	private static boolean isPause = false;
	private static boolean isStart = false;

	public static void main(String[] args) {
		Case1 case1 = new Case1(10, 0.1, 0.6);
		Population population = case1.initPopulation();
		case1.evalPopulation(population);

		// Keep track of current generation
		int generation = 1;

		while (!isPause) {
			// Steps
			population = case1.selection(population);
			population = case1.crossover(population);
			population = case1.mutation(population);

			case1.evalPopulation(population);

			result1 = population.getPopulation();
			System.out.println(result1);

			generation++;
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel panel1 = new JPanel();
				panel1.add(new Panel1(result1));

//				JPanel panel2 = new JPanel();
//				panel2.add(new Panel2(result1));
//
//				JPanel panel3 = new JPanel();
//				panel3.add(new JLabel("Case 3"));

				//Create the panel that contains the "cards".
				final JPanel cards = new JPanel(new CardLayout());
				cards.add(panel1, "Case 1");
//				cards.add(panel2, "Case 2");
//				cards.add(panel3, "Case 3");

				//create button to allow chnage to next card
				JButton buttonStart = new JButton("Start");
				buttonStart.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						isStart = true;
						isPause = false;
					}
				});

				//create button to allow chnage to previous card
				JButton buttonPause = new JButton("Pause");
				buttonPause.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						isPause = true;
					}
				});

				//create button to allow chnage to previous card
				JButton buttonStop = new JButton("Stop");
				buttonStop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						isStart = false;
					}
				});

				//create panel to hold buttons which will allow switching between cards
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(buttonStart);
				buttonPanel.add(buttonPause);
				buttonPanel.add(buttonStop);

				frame.add(cards);
				frame.add(buttonPanel, BorderLayout.SOUTH);

				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
