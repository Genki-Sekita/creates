package javaGUI5;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class FileOutButton extends JButton {
	StateManager stateManager;
	public FileOutButton(StateManager stateManager){
		super("SAVE");
		addActionListener(new fileOutListener());
		this.stateManager = stateManager;
	}
	
	class fileOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 JFileChooser fc = new JFileChooser();
			    int returnVal = fc.showSaveDialog(null);  // ファイルセーブ用のダイアログを開く
			if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
		        File file = fc.getSelectedFile();
		        stateManager.canvas.mediator.file = file;
		    }
			stateManager.canvas.mediator.fileOut();
		}
	}
	

}
