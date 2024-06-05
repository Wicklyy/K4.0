package View.Adaptateurs;
import View.CollecteurEvenements;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurSuggestion implements ActionListener {
	CollecteurEvenements control;

	public AdaptateurSuggestion(CollecteurEvenements c) {
		control = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.commande("Suggestion");
	}
}