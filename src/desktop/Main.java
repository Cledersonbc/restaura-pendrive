package desktop;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Aplicação para restauração de arquivos em pendrives
 * @author Clederson Cruz
 *
 */

public class Main {

	public static void main(String[] args) {
		 try {
			 UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
	    }
		 catch (Exception e){
			 
		 }
		 String osname = System.getProperty("os.name").toLowerCase();
		if (!osname.contains("windows")) {
			JOptionPane.showMessageDialog(null, "Essa aplicação não funcionará bem em uma plataforma diferente do Windows.",
					"Alerta", JOptionPane.WARNING_MESSAGE);
		}
		new WindowUnhide();

	}

}
