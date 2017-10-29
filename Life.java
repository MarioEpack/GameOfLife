import java.io.IOException;

public class Life {

	public int game_size_x = 10;
	public int game_size_y = 10;
	private int[][] size = new int[game_size_x][game_size_y];
	private int neighbours;
	private boolean alive;
	private int x = 0;
	private int y = 0;
	private int[][] cell_neighbours = new int[game_size_x][game_size_y]; // in this array we keep the number of
																			// neighbours of each cell

	public void set_field_size(int size_x, int size_y) {
		// method to change to size of the game
		this.game_size_x = size_x;
		this.game_size_y = size_y;
	}

	public Life(int size_x, int size_y) {
		this.game_size_x = size_x;
		this.game_size_y = size_y;
	}

	private boolean is_alive(int cell_index) {
		// method to find out if the cell is alive or dead
		if (cell_index == 1) {
			return true;
		} else if (cell_index == 0) {
			return false;
		} else {
			System.out.println("Cell is neither 0-dead or 1-alive. ");
			return false;
		}
	}

	private void find_neighbours() {
		// in this method, we find out how many alive cells are around our current cell,
		// ulozim si hodnoty do nemenneho pola, a kazda bunka si checkne hodnoty z toho
		// stareho nemenneho pola
		for (int index_x = 0; index_x < this.size.length; index_x++) {
			for (int index_y = 0; index_y < this.size[index_x].length; index_y++) {
				// variable neighbours always resets back to zero, for the next cell
				neighbours = 0;
				x = index_x;
				y = index_y;

				// tento try bude treba prerobit, hranicne indexy skipuju ostatne if-y.
				try {
					// Here we count how many alive cells are around our current cell, so we can
					// add the number of neighbours into array cell_neighbours
					if (this.is_alive(this.size[index_x - 1][index_y - 1]) == true) { // Upper Left
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x][index_y - 1]) == true) { // Above
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x + 1][index_y - 1]) == true) { // Upper Right
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x + 1][index_y]) == true) { // Right
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x + 1][index_y + 1]) == true) { // Lower right
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x][index_y + 1]) == true) { // Under
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x - 1][index_y + 1]) == true) { // Lower Left
						neighbours += 1;
					}
					if (this.is_alive(this.size[index_x - 1][index_y]) == true) { // Left
						neighbours += 1;
					}
					cell_neighbours[index_x][index_y] = neighbours;

				} catch (ArrayIndexOutOfBoundsException e) {
					// just to ignore errors
				}
			}
		}
	}

	private void loop_and_apply_rules() {

		this.find_neighbours(); // this method will fill the cell_neighbours array;
		// here im looping through the cell_neighbours array, and applying the rules for
		// each cell,
		// depending on the number of its neighbours.

		for (int index_x = 0; index_x < this.cell_neighbours.length; index_x++) {
			for (int index_y = 0; index_y < this.cell_neighbours[index_x].length; index_y++) {
				x = index_x;
				y = index_y;
				int neibrs = cell_neighbours[index_x][index_y]; // toto je pocet susedov co musim aplikovat na pole size
				this.rules(neibrs);

				// potrebujem pozret v array cell_neighbours pocet susedov, a potom to pravidla
				// aplikovat na pole size

			}
		}
	}

	private void rules(int neighbours_count) {

		int cell = this.size[x][y];
		alive = this.is_alive(cell);

		if (alive == true) {
			// if the cell is alive, one of the 3 rules will happen
			if (neighbours_count < 2) { // rule 1
				alive = false;
				this.size[x][y] = 0;
			} else if (neighbours_count == 2 || neighbours_count == 3) { // rule 2
				alive = true;
				this.size[x][y] = 1;
			} else if (neighbours_count > 3) { // rule 3
				alive = false;
				this.size[x][y] = 0;
			}
		} else if (alive == false && neighbours_count == 3) {
			// if the cell is dead, rule n.4 happens
			alive = true;
			this.size[x][y] = 1;
		}
	}

	public void turn() throws InterruptedException, IOException {
		// this method you actually call on the object, to pass 1 turn

		Thread.sleep(1000);
		this.loop_and_apply_rules();
		for (int i = 0; i < this.game_size_x; i++) {
			System.out.println();
			for (int j = 0; j < this.game_size_y; j++) {
				System.out.print(this.size[i][j] + " ");
			}
		}
		Thread.sleep(1000);
		System.out.println("\n");

	}

	public void show_start() {
		for (int i = 0; i < this.game_size_x; i++) {
			System.out.println();
			for (int j = 0; j < this.game_size_y; j++) {
				System.out.print(this.size[i][j] + " ");
			}
		}
		System.out.println("\n");
	}

	public void make_block() {

		this.size[5][4] = 1;
		this.size[4][4] = 1;
		this.size[4][5] = 1;
		this.size[5][5] = 1;

	}

	public void make_beacon() {
		this.size[5][4] = 1;
		this.size[4][4] = 1;
		this.size[4][5] = 1;
		this.size[5][5] = 1;

		this.size[7][6] = 1;
		this.size[6][6] = 1;
		this.size[6][7] = 1;
		this.size[7][7] = 1;
	}

	public void make_cell(int index_x, int index_y) {
		// This method makes a cell with given index alive
		this.x = index_x;
		this.x = index_y;
		this.size[index_x][index_y] = 1;
	}
}
