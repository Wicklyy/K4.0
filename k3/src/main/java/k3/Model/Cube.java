package k3.Model;

    /** Cube class
     * 
     */
public class Cube{

    Color cube_color;


    /** Cube class constructor
     * @param color_id Color of the cube
     * @see Color
     */
    public Cube(Color color_id){
        try{
            cube_color = color_id;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String toString(){
        switch (cube_color.getInt()) {
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
    /** getColor method 
     * Indicates the cube's color
     * @return the color of the cube
     * @see Color
     */
    public Color getColor() {
        return cube_color;
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
        if ((this.cube_color == null) ? (other.cube_color != null) : !this.cube_color.equals(other.cube_color)) {
            return false;
        }

        return true;
    }

}
