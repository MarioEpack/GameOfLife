import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class sets up a graphic interface for simulating class Life JFrame fills
 * up with JButtons, where each JButton is representing one cell
 * 
 * Known bug: If you try to click on the cell after you played some turns, all
 * the dead cells will come alive
 * 
 * @see https://en.wikipedia.org/wiki/Conway's_Game_of_Life
 * @author Mario Alina
 * 
 */
public class LifeGui implements ActionListener {

	private JFrame frame;
	private JFrame sidebar;
	private GridLayout grid;
	private JButton cell;
	private JButton start;

	private String dead = "dead";
	private Life life = new Life();

	private int grid_size_x;
	private int grid_size_y;

	private JButton[][] cells;
	private String[][] cell_ids;

	/**
	 * Creates the application.
	 */
	public LifeGui() {
		this.grid_size_x = this.life.get_game_size_x();
		this.grid_size_y = this.life.get_game_size_y();
		this.initialize();
	}

	/**
	 * This method is called in the Constructor ,Sets up the whole GUI
	 */
	private void initialize() {

		int cell_number = 0;
		this.make_board(); // here i initialize my JFrame
		this.make_sidebar(); // here i initialize my second JFrame - sidebar for start button

		// here I init my arrays
		this.cells = new JButton[grid_size_x][grid_size_y];
		this.cell_ids = new String[grid_size_x][grid_size_y];

		for (int index_x = 0; index_x < this.cells.length; index_x++) {
			for (int index_y = 0; index_y < this.cells[index_x].length; index_y++) {
				// tu si nahadzem do dvojrozmerneho pola btny a pridam k nim "AL"
				this.cell = new JButton(dead + cell_number);
				this.cells[index_x][index_y] = this.cell;
				this.cell.addActionListener(this);
				frame.getContentPane().add(this.cell);
				// tu si nahadzem nazvy tych butnov do druheho String dvojrozmerneho pola
				this.cell.setBackground(Color.BLACK);
				String cell_id = this.cell.getActionCommand();
				this.cell_ids[index_x][index_y] = cell_id;
				cell_number++;
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void make_board() {
		this.frame = new JFrame("Game of Life");
		this.grid = new GridLayout(this.grid_size_x, this.grid_size_y, 0, 0);
		this.frame.setLocationRelativeTo(null);
		this.frame.setSize(1000, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(this.grid);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the sidebar.
	 */
	private void make_sidebar() {
		this.sidebar = new JFrame("Sidebar");
		this.start = new JButton("TURN");
		this.start.addActionListener(this);
		this.sidebar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sidebar.setSize(200, 200);
		this.sidebar.setLocationRelativeTo(this.frame);
		this.sidebar.add(start);
		this.sidebar.setAlwaysOnTop(true);
		this.sidebar.setVisible(true);
	}

	/**
	 * Used in actionPerformed Swing method, to change the Color of a button, green
	 * alive / black dead - calls a get-er from Life class to check the cell info
	 * and applies the change for the given btn
	 * 
	 * 
	 * @param index_x
	 * @param index_y
	 */
	private void set_cell(int index_x, int index_y) {
		if (this.life.get_cell_info(index_x, index_y) == 1) {
			System.out.println(this.life.get_cell_info(index_x, index_y));
			this.cells[index_x][index_y].setBackground(Color.GREEN);
		} else {
			this.cells[index_x][index_y].setBackground(Color.BLACK);
			this.cells[index_x][index_y].setText("dead");
			this.cell_ids[index_x][index_y] = "dead";

		}
	}

	@Override
	/*
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent click) {

		String clicked_cell;
		clicked_cell = click.getActionCommand();

		if (click.getActionCommand() == "TURN") {
			try {

				for (int i = 0; i < this.life.get_game_size_x(); i++) {
					for (int j = 0; j < this.life.get_game_size_y(); j++) {
						this.set_cell(i, j);
					}
				}
				this.life.turn();

				System.out.println("end of loop");

			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
		// V tejto loope menim farbu JButnov po kliky na ne / "ozivaju".
		for (int index_x = 0; index_x < this.cells.length; index_x++) {
			for (int index_y = 0; index_y < this.cells[index_x].length; index_y++) {
				if (clicked_cell.equals(cells[index_x][index_y].getText())) {
					this.cells[index_x][index_y].setText("alive");
					this.cells[index_x][index_y].setBackground(Color.GREEN);
					this.cell_ids[index_x][index_y] = "alive"; // premenujem nazov buttnu v poli nazvov btnov
					life.make_cell(index_x, index_y); // + musim ozivit bunku v classe life
				}
			}

		}
	}

}
