import javax.swing.SwingUtilities;

public class LifeMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LifeDesigner window = new LifeDesigner(30, 30);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
