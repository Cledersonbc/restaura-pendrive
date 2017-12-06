package desktop;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WindowUnhide extends JFrame implements ActionListener {
	private JComboBox<String> rootMenu;
	private JButton about;
	private JButton recovery;
	private JButton cancel;
	private JProgressBar progressBar;
	private JTextArea founds;
	private UnhideThread unhideT;

	public WindowUnhide() {
		/* -- Initializing Components -- */
		rootMenu = new JComboBox<String>();
		about = new JButton("Sobre");
		cancel = new JButton("Parar");
		recovery = new JButton("Recuperar");
		progressBar = new JProgressBar();
		founds = new JTextArea();

		/* ** mainPanel: all panels + output **/
		/* ** middlePanel: status and menu **/
		/* ** bottomPanel: action buttons **/
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		JPanel recovPanel = new JPanel(new GridLayout(1, 2, 2, 2));
		JPanel middlePanel = new JPanel(new GridLayout(2, 2, 2, 2));

		/* -- Setup components -- */
		rootMenu.addActionListener(this);
		about.addActionListener(this);
		recovery.addActionListener(this);
		cancel.addActionListener(this);
		progressBar.setValue(0);
		founds.setEditable(false);

		// Update Disks available on pc
		updateRootMenu();

		recovPanel.add(cancel);
		recovPanel.add(recovery);
		bottomPanel.add(about);
		bottomPanel.add(recovPanel);
		middlePanel.add(new JLabel("Progresso:"));
		middlePanel.add(progressBar);
		middlePanel.add(new JLabel("Unidade:"));
		middlePanel.add(rootMenu);

		mainPanel.add(middlePanel, BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(founds));
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		this.setContentPane(mainPanel);
		this.setTitle("Restaura Pendrive");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(430, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void updateRootMenu() {
		File[] roots = File.listRoots();

		rootMenu.removeAllItems();
		for (int i = 0; i < roots.length; i++) {
			String root = roots[i].toString();

			if (!root.equals("C:\\")) {
				rootMenu.addItem(root);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("comboBoxChanged")) {
			//updateRootMenu();
		}
		else if (command.equals("Recuperar")) {
			File rootPath = new File(rootMenu.getSelectedItem().toString());
			unhideT = new UnhideThread(founds, rootPath, progressBar, recovery);
			unhideT.start();
		}
		else if (command.equals("Parar")) {
			try {
				unhideT.kill();
			}
			catch (Exception exp) {
				
			}
		}
		else if (command.equals("Sobre")) {
			JOptionPane.showMessageDialog(null, "Programa para restaurar arquivos em pendrives, ocultados por vírus.\n"
					+ "Nota: esse programa não remove o vírus ou recupera arquivos deletados.\n\n"
					+ "Licença: GNU GENERAL PUBLIC LICENSE\n"
					+ "Autor: Clederson Cruz - 2017\n"
					+ "Github: https://github.com/Cledersonbc");
		}

	}

}
