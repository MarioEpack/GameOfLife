import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LifeDesigner implements ActionListener {

	private JFrame frame;
	private JFrame sidebar;
	private GridLayout grid;
	private JButton cell;
	private JButton start;

	private int grid_size_x;
	private int grid_size_y;
	private int grid_size;
	private String dead = "dead";

	private JButton[] cells;
	private String[] cell_ids;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LifeDesigner window = new LifeDesigner(30, 30);
					window.frame.setVisible(true);
					window.sidebar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Creates the application.
	 */
	public LifeDesigner(int grid_size_x, int grid_size_y) {
		this.grid_size_x = grid_size_x;
		this.grid_size_y = grid_size_y;
		this.grid_size = grid_size_x * grid_size_y;
		this.initialize();
	}

	/*
	 * This method is called in the Constructor
	 */
	private void initialize() {

		this.make_board(); // here i initialize my JFrame
		this.make_arrays(); // here i initialize my arrays depending on the size of the grid
		this.make_sidebar();

		for (int i = 0; i < grid_size; i++) { // vtejto loope si do pola JButtunov nahodim,
			cell = new JButton(dead + i);
			cells[i] = cell; // tolko JButtunov kolko je grid_size,
			this.cell.addActionListener(this); // na kazdy button spravim Alistener
			frame.getContentPane().add(this.cell); // a hned ho vyhodim na contentPane
		}
		JButton btn;
		String cell_id;
		for (int i = 0; i < this.cells.length; i++) { // tato loopa prejde vsetkymi
			btn = this.cells[i]; // JButnnmi a ich nazov ulozi do druheho pola
			btn.setBackground(Color.BLACK);
			cell_id = btn.getActionCommand(); // cell_ids;
			this.cell_ids[i] = cell_id;
		}

	}

	/*
	 * Initialize the arrays depending on the size of the grid
	 */
	private void make_arrays() {
		this.cells = new JButton[grid_size_x * grid_size_y];
		this.cell_ids = new String[grid_size_x * grid_size_y];
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void make_board() {
		this.frame = new JFrame("Game of Life");
		this.grid = new GridLayout(this.grid_size_x, this.grid_size_y, 0, 0);
		this.frame.setLocationRelativeTo(null);
		this.frame.setSize(this.grid_size_x + 1000, this.grid_size_y + 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(this.grid);
	}

	private void make_sidebar() {
		this.sidebar = new JFrame("Sidebar");
		this.start = new JButton("START");
		this.sidebar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sidebar.setSize(200, 200);
		this.sidebar.setLocationRelativeTo(null);
		this.sidebar.add(start);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent click) {

		String clicked_cell;
		clicked_cell = click.getActionCommand(); // a potom ho setnem na nove
		if (click.getActionCommand() == "START") {
			// tu bude kod ktory spusti cely life program
			// tu bude kod ktory spusti cely life program
			// tu bude kod ktory spusti cely life program
			Life spustacLife = new Life(this.grid_size_x, this.grid_size_y);

		} else {
			System.out.println("Bunka " + clicked_cell + " je uz ziva.");
			for (int i = 0; i < grid_size; i++) {

				if (clicked_cell.equals(cells[i].getText())) {
					cells[i].setText("alive");
					cells[i].setBackground(Color.GREEN);
				}
			}
		}
	}

}
