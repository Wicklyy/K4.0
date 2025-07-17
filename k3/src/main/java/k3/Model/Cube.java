package k3.Model;

enum Color{
    VOID(0),
    BLACK(1),
    WHITE(2),
    RED(3),
    GREEN(4),
    BLUE(5),
    YELLOW(6),
    NEUTRAL(7);

    public final int value;

    private Color(int value) {
        this.value = value;
    }

    public static Color getColor(int value) throws Exception{
        for(Color c : Color.values()){
            if (c.value == value){
                return c;
            }
        }
        throw new Exception("This color doesn't exist");
    }
}

public class Cube {
    Color c;

    public Cube(int color_id){
        try{
            c=Color.getColor(color_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
