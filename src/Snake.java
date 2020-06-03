public class Snake {
    public enum Type {Snake, Mouse, Empty}
    private Type type;

    public Snake(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString(){
        if(getType() == Type.Snake)
            return "\u001B[32m\uD83D\uDC0D\u001B[0m";
        if(getType() == Type.Mouse)
            return "\u001B[37m\uD83D\uDC01\u001B[0m";
        else return " ";
    }
}
