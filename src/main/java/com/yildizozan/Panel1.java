package com.yildizozan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panel1 extends JPanel {


	private static final long serialVersionUID = 1L;
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private static int pointWidth = 10;
	private int labelPadding = 12;
	/**
	 * change the line color to the best you want;
	 */
	private Color lineColor = new Color(255, 255, 254);
	private Color pointColor = new Color(255, 0, 255);
	private Color gridColor = new Color(200, 200, 200, 200);
	private int numberYDivisions = 6;
	private List<Individual> scores;
	private int padding = 20;


	/**
	 * Math_Graph is a constructor method
	 *
	 * @returns List scores;
	 */
	public Panel1(List<Individual> scores) {
		this.scores = scores;
	}

	/* creating the method createAndShowGui in the main method, where we create the frame too and pack it in the panel*/
	public static void createAndShowGui() {
		List<Double> scores = new ArrayList<>();
		Random random = new Random();
		int maxDataPoints = 20;
		int maxScore = 8;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.add(random.nextDouble() * maxScore);

		}

		/* Main panel */
		Graph mainPanel = new Graph(scores);
		mainPanel.setPreferredSize(new Dimension(700, 600));

		/* creating the frame */
		JFrame frame = new JFrame("Sample Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (scores.size() - 1);
		double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

		List<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < scores.size(); i++) {
			int x1 = (int) (i * xScale + padding + labelPadding);
			int x2 = (int) ((getMaxScore() - scores.get(i).getX2()) * yScale + padding);
			graphPoints.add(new Point(x1, x2));
		}

		g2.setColor(Color.WHITE);
		//fill the rect
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) -
				labelPadding, getHeight() - 2 * padding - labelPadding);
		g2.setColor(Color.BLUE);

		for (int i = 0; i < numberYDivisions + 1; i++) {
			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int y0 = getHeight() - ((i * (getHeight() - padding * 2 -
					labelPadding)) / numberYDivisions + padding + labelPadding);
			int y1 = y0;
			if (scores.size() > 0) {
				g2.setColor(gridColor);
				g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
				g2.setColor(Color.BLACK);
				String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) *
						((i * 8.0) / numberYDivisions)) * 100)) / 100.0 + "";
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
			}
			g2.drawLine(x0, y0, x1, y1);
		}

		for (int i = 0; i < scores.size(); i++) {
			if (scores.size() > 1) {
				int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
				int x1 = x0;
				int y0 = getHeight() - padding - labelPadding;
				int y1 = y0 - pointWidth;
				if ((i % ((int) ((scores.size() / 8.0)) + 3)) == 0) {
					g2.setColor(gridColor);
					g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
					g2.setColor(Color.BLACK);
					String xLabel = i + "";
					FontMetrics metrics = g2.getFontMetrics();
					int labelWidth = metrics.stringWidth(xLabel);
					g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
				}
				g2.drawLine(x0, y0, x1, y1);
			}
		}


		g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
		g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() -
				padding, getHeight() - padding - labelPadding);

		Stroke oldStroke = g2.getStroke();
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}

		g2.setStroke(oldStroke);
		g2.setColor(pointColor);
		for (int i = 0; i < graphPoints.size(); i++) {
			int x = graphPoints.get(i).x - pointWidth / 2;
			int y = graphPoints.get(i).y - pointWidth / 2;
			int ovalW = pointWidth;
			int ovalH = pointWidth;
			g2.fillOval(x, y, ovalW, ovalH);
		}
	}

	private double getMinScore() {
		double minScore = Double.MAX_VALUE;
		for (Individual score : scores) {
			minScore = Math.min(minScore, score.getFitness());
		}
		return minScore;
	}

	private double getMaxScore() {
		double maxScore = Double.MIN_VALUE;
		for (Individual score : scores) {
			maxScore = Math.max(maxScore, score.getFitness());
		}
		return maxScore;
	}

	public List<Individual> getScores() {
		return scores;
	}

	/* setting scores */
	public void setScores(List<Individual> scores) {
		this.scores = scores;
		invalidate();
		this.repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}
