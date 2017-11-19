import java.io.IOException;

public class Life {

	private final int game_size_x = 20;
	private final int game_size_y = 20;
	private int[][] board = new int[game_size_x][game_size_y];
	private int neighbours;
	private boolean alive;
	/**
	 * cell index's variables, so it's possible to access the specific cell in both
	 * arayy's
	 */
	private int ind_x = 0;
	private int ind_y = 0;
	private int[][] cell_neighbours; // in this array we keep the number of
										// neighbours of each cell

	/**
	 * Getter
	 * 
	 * @return the size of the game_x
	 */
	public int get_game_size_x() {
		return game_size_x;
	}

	/**
	 * Getter
	 * 
	 * @return the size of the game_t
	 */
	public int get_game_size_y() {
		return game_size_y;
	}

	/**
	 * Public get-er to check if the specified cell is alive(1) or dead(0) Used by
	 * LifeDesigner class
	 *
	 * @param index_x
	 * @param index_y
	 * @return int as the state of the cell, 1 for alive, 0 for dead
	 */
	public int get_cell_info(int index_x, int index_y) {
		return board[index_x][index_y];
	}

	/**
	 * This is a private method for a number of neighbours the specified cell has
	 * 
	 * @param index_x
	 *            place of the cell in the array
	 * @param index_y
	 * @return returns int -> the number of neighbours the cell has
	 */

	private int get_cell_neighbours(int index_x, int index_y) {
		return this.cell_neighbours[index_x][index_y];
	}

	/**
	 * Sets the cell on the specified index to be either alive(1) or dead(0)
	 * 
	 * @param index_x
	 *            place of the cell in the this.board int[][] array
	 * @param index_y
	 * @param alive
	 *            1 for alive, 0 for dead
	 */
	private void set_cell_life(int index_x, int index_y, int alive_or_dead) {
		this.board[index_x][index_y] = alive_or_dead;
	}

	/**
	 * Checks if the cell is currently alive or dead
	 * 
	 * @param cell_index
	 *            is int , and its either 1 for alive or dead 0
	 * @return returns true if cell_index is 1, and false if its 0.
	 */
	private boolean is_alive(int cell_index) {

		if (cell_index == 1) {
			return true;
		} else if (cell_index == 0) {
			return false;
		} else {
			System.out.println("Cell is neither 0-dead or 1-alive. ");
			return false;
		}
	}

	/**
	 * This private void is only used in the method find_neighbours() to simplify
	 * code
	 * 
	 * @param index_x
	 *            x index in the array this.board[][]
	 * @param index_y
	 *            y index in the array this.board[][]
	 */
	private void neighbours_check(int index_x, int index_y) {
		if ((index_x >= 0 && index_x < this.board.length) && (index_y >= 0 && index_y < this.board.length)) {
			if (this.is_alive(this.board[index_x][index_y]) == true) {
				this.neighbours += 1;
			}
		}
	}

	/**
	 * This methods finds how many neighbours each cell or (board[index_y][index_y])
	 * has, and sets the value into the int[][] cell_neighbours array at the same
	 * index.
	 */
	private void find_neighbours() {
		for (int index_x = 0; index_x < this.board.length; index_x++) {
			for (int index_y = 0; index_y < this.board[index_x].length; index_y++) {
				// variable neighbours always resets back to zero, for the next cell
				this.neighbours = 0;
				this.ind_x = index_x;
				this.ind_y = index_y;

				/*
				 * Here we calculate how many neighbours each cells has for each index arround
				 */
				this.neighbours_check(index_x - 1, index_y - 1); // Upper Left
				this.neighbours_check(index_x, index_y - 1); // Above
				this.neighbours_check(index_x + 1, index_y - 1); // Upper Right
				this.neighbours_check(index_x + 1, index_y);// Right
				this.neighbours_check(index_x + 1, index_y + 1);// Lower right
				this.neighbours_check(index_x, index_y + 1);// Under
				this.neighbours_check(index_x - 1, index_y + 1);// Lower Left
				this.neighbours_check(index_x - 1, index_y); // Left

				/*
				 * And here we save the value into the cell_neighbours[][] array
				 */
				this.cell_neighbours[index_x][index_y] = this.neighbours;
			}
		}

	}

	/**
	 * Loops through the int[][] cell_neighbours array and applies the rules() for
	 * each cell depending on the number of neighbours. We also call the
	 * find_neighbours(), inside this method.
	 */
	private void loop_and_apply_rules() {

		this.cell_neighbours = new int[game_size_x][game_size_y];

		this.find_neighbours(); // this method will fill the cell_neighbours array;
		// here im looping through the cell_neighbours array, and applying the rules for
		// each cell,
		// depending on the number of its neighbours.

		for (int index_x = 0; index_x < this.cell_neighbours.length; index_x++) {
			for (int index_y = 0; index_y < this.cell_neighbours[index_x].length; index_y++) {
				ind_x = index_x;
				ind_y = index_y;
				int neibrs = cell_neighbours[index_x][index_y]; // toto je pocet susedov co musim aplikovat na pole
																// board
				this.rules(neibrs);

			}
		}
	}

	/**
	 * In this method we make the rules/algoritm for the Game of Life.
	 * 
	 * @param neighbours_count
	 *            -Number of neighbours the specified cell has.
	 */
	private void rules(int neighbours_count) {

		int cell = this.board[ind_x][ind_y];
		alive = this.is_alive(cell);

		if (alive == true) {
			// if the cell is alive, one of the 3 rules will happen
			if (neighbours_count < 2) { // rule 1
				alive = false;
				this.board[ind_x][ind_y] = 0;
			} else if (neighbours_count == 2 || neighbours_count == 3) { // rule 2
				alive = true;
				this.board[ind_x][ind_y] = 1;
			} else if (neighbours_count > 3) { // rule 3
				alive = false;
				this.board[ind_x][ind_y] = 0;
			}
		} else if (alive == false && neighbours_count == 3) {
			// if the cell is dead, rule n.4 happens
			alive = true;
			this.board[ind_x][ind_y] = 1;
		}
	}

	/**
	 * This method is called on the object to "play" one turn
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void turn() throws InterruptedException, IOException {
		// this method you actually call on the object, to pass 1 turn
		System.out.println("halo" + this.game_size_x);
		this.loop_and_apply_rules();
		for (int i = 0; i < this.game_size_x; i++) {
			for (int j = 0; j < this.game_size_y; j++) {
				System.out.print(this.board[i][j] + " ");
			}
			System.out.println();
		}

		// Thread.sleep(1000);

	}

	/*
	 * These two methods will not work with the gui, its only for standart output
	 * version of the program
	 */
	public void show_start() {
		for (int i = 0; i < this.game_size_x; i++) {
			System.out.println();
			for (int j = 0; j < this.game_size_y; j++) {
				System.out.print(this.board[i][j] + " ");
			}
		}
		System.out.println("\n");
	}

	public void make_cell(int index_x, int index_y) {
		// This method makes a cell with given index alive
		this.board[index_x][index_y] = 1;
	}
}
