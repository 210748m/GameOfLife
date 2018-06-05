package gameOfLife;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;

	/**
	 * A program that replicates Conway's Game of Life in Java
	 * @author Mudit Chandna
	 * @version March 2018
	 */

public class cellMaker {

	private static Random r = new Random(); // Creates a random variable
	private static int cellSize = 1; // Creates the size of the cell
	private static int cellAmount = 40; // Creates the number of cells
	private static int width = cellSize*cellAmount; // Allows for the modification of the size of the array
	private static int height = width; // Width has to equal height
	private static GridLayout ex = new GridLayout(width, height); // Making a GridLayout called ex
	private static int[][] cell = new int[width][height]; // Creates the creation of the displayed array
	private static int[][] buffer = new int[width][height]; // Creates the buffered array that will equal the displayed array
	private static int alive = 1; // In the console 1 is alive
	private static int dead = 0; // In the console 0 is dead
	
	public static void main (String[] args) {
		main(); //Calls main
	} // End of main method
	
	/*
	 * Main method uses frame to create a new JFrame from the regular or gliderGun methods
	 * Then the while loop starts and calls the frame to be visible and then iterates
	 */
	
	public static void main(){
		//regular(); //Uncomment to use
		gliderGun(); //Uncomment to use

		JFrame frame3 = frame(); //Creates the first frame
		while(true){
			// Calls the frame method
			frame3.setVisible(true); // Sets the new frame to be visible and true
			iteration(frame3); // Calls the iteration method		
		} // End of while loop
	} // End of main
	
	public static JFrame frame(){
		JFrame frame1 = new JFrame("Game Of Life"); // Makes a new JFrame called Game of Life
		frame1.setLayout(ex); // Setting the layout to be GridLayout 
		frame1.setBounds(100, 100, 1000, 1000); // Setting the size to be 1000 by 1000 pixels
		for (int j = 0; j < cell.length; j++) { // Going through each cell
			for (int k = 0; k < cell[0].length; k++) { // Going through each cell
				if (cell[j][k] == 1) { // Checking if it is alive
					JLabel label = new JLabel(); // Creating new JLabel
					label.setBackground(Color.GREEN.brighter()); // Setting the color to be GREEN
					label.setOpaque(true); // Sets the colour to be opaque
					label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Sets the border to be gray
					frame1.add(label); // Adding it to the JFrame
					label.setVisible(true); // Sets this label to be visible in the JFrame
				} // End of if loop
				else {
					JLabel label = new JLabel(); // Creating new JLabel
					label.setBackground(Color.BLACK); // Setting the color to be WHITE
					label.setOpaque(true); // Sets the colour to be opaque
					label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Sets the border to be gray
					frame1.add(label); // Adding it to the JFrame
					label.setVisible(true); // Sets the label to be visible
				} // End of else loop
			} // End of for loop
		} // End of for loop
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the close button to terminate the program
		
		return frame1; // Returns the newly made JFrame
	} // End of frame
	
	/**
	 * This method makes a duplicate of cell then calls the count method to find the neighbours
	 * then according to buffer, cell is changed to be alive or dead. Then the frame is cleared
	 * and then it is reset according to cell.
	 * @param frame1
	 */
	
	public static void iteration(JFrame frame1) {
		// First store the display in a seperate array that we shall work with
		for (int x = 0; x < width; x++) { // Go through each cell
			for (int y = 0; y < height; y++) { // Go through each cell
				buffer[x][y] = cell[x][y]; // Store the element in the same place as the buffer
			} // End of for loop
		} // End of for loop
		
		// Count the number of neighbors and change the array accordingly
		
		for(int j = 1; j < buffer.length - 1; j++){ // Go through each cell
			for(int k = 1; k < buffer[0].length - 1; k++){ // First go through each and every cell
				int neighbour = count(buffer, j, k); // neighbour is the number of neighbors
				if (buffer[j][k] == alive) { // Will check if the cell is alive
					if (neighbour < 2 || neighbour > 3) { // If there are less than 2 neighbors or more than 3 neighbors then the cell is dead
						cell[j][k] = dead; // The cell will die
					} // End of if loop
				} // End of if loop
				else {   
					if (neighbour == 3 ) { // If the cell has exactly 3 neighbors then do this
						cell[j][k] = alive; // The cell will come to life
					} // End of if loop
				} // End of else loop
			} // End of for loop
		} // End of for loop
		frame1.getContentPane().removeAll(); // Removes everything from the JFrame
		
		frame1.setLayout(ex); // Setting the layout to be GridLayout 
		frame1.setBounds(100, 100, 1000, 1000); // Setting the size to be 1000 by 1000 pixels
		for (int j = 0; j < cell.length; j++) { // Going through each cell
			for (int k = 0; k < cell[0].length; k++) { // Going through each cell
				if (cell[j][k] == 1) { // Checking if it is alive
					JLabel label = new JLabel(); // Creating new JLabel
					label.setBackground(Color.GREEN.brighter()); // Setting the color to be GREEN
					label.setOpaque(true); // Sets the colour to be opaque
					label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Sets the border to be gray
					frame1.add(label); // Adding it to the JFrame
					label.setVisible(true); // Sets this label to be visible in the JFrame
				} // End of if loop
				else {
					JLabel label = new JLabel(); // Creating new JLabel
					label.setBackground(Color.BLACK); // Setting the color to be WHITE
					label.setOpaque(true); // Sets the colour to be opaque
					label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Sets the border to be gray
					frame1.add(label); // Adding it to the JFrame
					label.setVisible(true); // Sets the label to be visible
				} // End of else loop
			} // End of for loop
		} // End of for loop
		frame1.getContentPane().repaint(); // Repaints the frame like it was before
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the close button to terminate the program
		frame1.setVisible(true); // Sets the JFrame to be called when drar() is called
	} // End of the method
	
	/** This method will count the number of neighbors using the cell array,
	 *  and the current cell. 
	 *  @param array, a 2D Array
	 *  @param x, the row coordinate of the 2D array
	 *  @param y, the column coordinate of the 2D array
	 *  @return neighbours, the number of neighbors
	 */
	
	private static int count(int[][] array, int x, int y) { 
		int neighbours = 0; // Declaration of neighbours
		int a = 0; // a will act as counter for ne
		int[] ne = {array[x + 1][y - 1], array[x + 1][y], array[x + 1][y + 1], array[x][y + 1], 
					array[x - 1][y + 1], array[x - 1][y], array[x - 1][y - 1], array[x][y - 1]};
		// ne is the array that contains the 8 values of the neighbors of the specified cell
		while (a < 8){ // a will count 8 neighbors
			if (ne[a] == 1){ // This will check if the element is alive
				neighbours++; // This will count the number of neighbors
			} // End of if loop
			a++; // This will increase the number of a
		} // End of while loop
		return neighbours; // returns neighbors in iteration
	} // End of count
	
	public static void regular(){
		int[] arr = {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // This shows the chance of a cell that will be alive
		for (int x = 1; x < width - 1; x++) { // Count the row
			for (int y = 1; y < height - 1; y++) { // Count the column
		    	int ind = r.nextInt(arr.length); // Will randomly select an element 
		    	cell[x][y] = arr[ind]; // Will display that randomly selected element
		    } // End of for loop
    	} // End of for loop
	}
	
	public static void gliderGun(){
		cell[5][1] = alive; // Set alive
		cell[6][1] = alive; // Set alive
		cell[5][2] = alive; // Set alive
		cell[6][2] = alive; // Set alive
		cell[3][14] = alive; // Set alive
		cell[3][13] = alive; // Set alive
		cell[4][12] = alive; // Set alive
		cell[5][11] = alive; // Set alive
		cell[6][11] = alive; // Set alive
		cell[7][11] = alive; // Set alive
		cell[8][12] = alive; // Set alive
		cell[9][13] = alive; // Set alive
		cell[9][14] = alive; // Set alive
		cell[6][15] = alive; // Set alive
		cell[4][16] = alive; // Set alive
		cell[8][16] = alive; // Set alive
		cell[5][17] = alive; // Set alive
		cell[7][17] = alive; // Set alive
		cell[6][17] = alive; // Set alive
		cell[6][18] = alive; // Set alive
		cell[3][21] = alive; // Set alive
		cell[3][22] = alive; // Set alive
		cell[4][21] = alive; // Set alive
		cell[4][22] = alive; // Set alive
		cell[5][21] = alive; // Set alive
		cell[5][22] = alive; // Set alive
		cell[2][23] = alive; // Set alive
		cell[6][23] = alive; // Set alive
		cell[1][25] = alive; // Set alive
		cell[2][25] = alive; // Set alive
		cell[6][25] = alive; // Set alive
		cell[7][25] = alive; // Set alive
		cell[3][35] = alive; // Set alive
		cell[3][36] = alive; // Set alive
		cell[4][35] = alive; // Set alive
		cell[4][36] = alive; // Set alive
	} // End of gliderGun
} // End of the class