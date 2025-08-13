package k3.Model;


public class Cube{
    Color c;

    public Cube(Color color_id){
        try{
            c = color_id;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String toString(){
        switch (c.getInt()) {
            case 0: return "VOID";
            case 1: return "BLACK";
            case 2: return "WHITE";
            case 3: return "RED";
            case 4: return "GREEN";
            case 5: return "BLUE";
            case 6: return "YELLOW";
            case 7: return "NEUTRAL";
            default:
                return "UKNOWN";
        }
    }

    public Color getColor() {
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Cube other = (Cube) obj;
        if ((this.c == null) ? (other.c != null) : !this.c.equals(other.c)) {
            return false;
        }

        return true;
    }

}
