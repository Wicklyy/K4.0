package View;

public interface CollecteurEvenements {
    void clicSouris(int l, int c);
    boolean commande(String c);
    void ImporterVue(InterfaceGraphique vue); 
    public void addMenu(Menu m);
}