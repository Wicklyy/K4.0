package View.Adaptateurs;
import View.CollecteurEvenements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class AdaptateurCharger implements ActionListener {
	CollecteurEvenements control;

	public AdaptateurCharger(CollecteurEvenements c) {
		control = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		    JFileChooser chooser = new JFileChooser("src/Global/saves/");
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (file == null) {
                    return;
                }

                String fileName = chooser.getSelectedFile().getAbsolutePath();
				System.err.println(fileName);
				control.open(fileName);

            }

	}
}