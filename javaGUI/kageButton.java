package javaGUI5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class kageButton extends JButton {
	StateManager stateManager;
	
	public kageButton(StateManager stateManager){
		super("Shadow");
		addActionListener(new kageListener());
		this.stateManager = stateManager;
	}
	
	class kageListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setKage (!stateManager.getKage());
		}
	}
}


