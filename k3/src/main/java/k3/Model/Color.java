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
    public int getInt(){
        return value;
    }

    // switch is better than a for loop O(1)
    public static Color getColor(int value) throws Exception{
        switch (value) {
            case 0: return Color.VOID;
            case 1: return Color.BLACK;
            case 2: return Color.WHITE;
            case 3: return Color.RED;
            case 4: return Color.GREEN;
            case 5: return Color.BLUE;
            case 6: return Color.YELLOW;
            case 7: return Color.NEUTRAL;        
            default:
                throw new Exception("This color doesn't exist");
        }        
    }
}
