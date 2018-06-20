package javaGUI5;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class FileInButton extends JButton {
	StateManager stateManager;
	public FileInButton(StateManager stateManager){
		super("LOAD");
		addActionListener(new fileInListener());
		this.stateManager = stateManager;
	}
	
	class fileInListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
				 JFileChooser fc = new JFileChooser();
				    int returnVal = fc.showOpenDialog(null);  // ファイルロード用のダイアログを開く
				if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
			        File file = fc.getSelectedFile();
			        stateManager.canvas.mediator.file = file;
			    }
			stateManager.canvas.mediator.fileIn();
		}
	}

}