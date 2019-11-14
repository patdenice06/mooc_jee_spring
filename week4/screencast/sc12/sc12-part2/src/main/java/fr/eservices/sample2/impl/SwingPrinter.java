package fr.eservices.sample2.impl;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.sample2.api.Printer;

@Component
@Qualifier("swing")
public class SwingPrinter implements Printer {

	@Override
	public void print(String message) {
		JOptionPane.showMessageDialog(null, message, "Nice 2 meet U", JOptionPane.INFORMATION_MESSAGE);
	}
}
