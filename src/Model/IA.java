package Model;

public abstract class IA {
    Plateau plateau;

    public static IA nouvelle(Plateau p) {
        IA resultat = null;
        String type = "Aleatoire";

        switch (type) {
            case "Aleatoire":
                resultat = new IAAleatoire();
                break;
            case "Facile":
                resultat = new IAFacile();
                break;
            case "Medium":
                resultat = new IAMedium();
                break;
            case "Difficile":
                resultat = new IADifficile();
                break;
        }
        if (resultat != null) {
            resultat.plateau = p;
        }
        return resultat;
    }

    public int MinMaxIA(Plateau p,int depth, boolean player1){
        int value;
        if(!p.exist(0,1) && !p.exist(1, 0)){
            if(player1){
                return -1;
            }else{
                return 1;
            }
        }
        if (depth == 0){
            return 0;
        }
        if(player1){
            value = 0;
            for (int i = 0; i < p.hauteur; i++){
                for (int j = 0; j < p.longueur; j++){
                    if(p.exist(i,j)){
                        value = Math.max(value,MinMaxIA(p,depth-1,!player1));
                    }
                }
            }
        }
        else{
            value = 0;
            for (int i = 0; i < p.hauteur; i++){
                for (int j = 0; j < p.longueur; j++){
                    if(p.exist(i,j)){
                        value = Math.min(value,MinMaxIA(p,depth-1,!player1));
                    }
                }
            }
        }
        return value;
    }

    void joue() {
        throw new UnsupportedOperationException();
    }
}
