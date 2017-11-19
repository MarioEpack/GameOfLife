import javax.swing.SwingUtilities;

public class LifeMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LifeGui window = new LifeGui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
