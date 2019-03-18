package br.edu.cleardrive.view.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.edu.cleardrive.App;
import br.edu.cleardrive.thread.RecoveryFiles;
import br.edu.cleardrive.util.DriveUtils;
import br.edu.cleardrive.util.StringUtils;
import br.edu.cleardrive.view.ComponentManager;
import br.edu.cleardrive.view.ComponentName;

public class ButtonEventHandler implements ActionListener {
	
	private RecoveryFiles recoveryFiles;

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals( ComponentName.ABOUT_BUTTON.name() )) {
			aboutAction();
		}
		else if (command.equals( ComponentName.DRIVE_CHANGED_COMBOBOX.name() )) {
			driveChangeAction();
		}
		else if (command.equals( ComponentName.RECOVER_BUTTON.name() )) {
			recoveryAction();
		}
		else if (command.equals( ComponentName.PROTECT_BUTTON.name() )) {
		/* TODO 
		 * - Copy files to tmp DIR
		 * - format to NTFS
		 * - make root DIR
		 * - remove permissions of drive
		 * - add permissions to root DIR only (and sub files/dirs)
		 * */
		}
		else if (command.equals( ComponentName.STOP_BUTTON.name() )) {
			stopRecoveryAction();
		}
	}
	
	
	private void aboutAction() {
		StringBuilder message = new StringBuilder();
		message.append("Software desenvolvido por "+ App.AUTHOR +"\n");
		message.append("para recuperação de arquivos ocultados em\n");
		message.append("pendrives ou unidades de disco.\n");
		message.append("\n");
		message.append("Versão: "+ App.VERSION);
		
		String title = "Sobre";
		
		new JOptionPane();
		JOptionPane.showMessageDialog(null, message.toString(), title, JOptionPane.PLAIN_MESSAGE);
	}
	
	
	private void driveChangeAction() {
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());
		File[] roots = File.listRoots();
		
		listedDrives.removeAllItems();
		for (File root : roots) {
			String cDisk = "C:\\";
			
			if (DriveUtils.validPath(root) && !root.toString().equals(cDisk)) {
				listedDrives.addItem(root.toString());
			}
		}
		
	}
	
	
	private void recoveryAction() {
		@SuppressWarnings("unchecked") // Ever will be a ComboBox
		JComboBox<String> listedDrives = (JComboBox<String>) ComponentManager
				.get(ComponentName.DRIVE_CHANGED_COMBOBOX.name());
		
		String drive = (String) listedDrives.getSelectedItem();
		if (StringUtils.isNullOrEmpty(drive)) {
			return;
		}
		
		ComponentManager.enableConflictingButtons(false);
		recoveryFiles = new RecoveryFiles();
		recoveryFiles.start();
	}
	
	private void stopRecoveryAction() {
		if (Objects.nonNull(recoveryFiles)) {
			ComponentManager.enableConflictingButtons(true);
			recoveryFiles.stopThread();
		}
	}

}
