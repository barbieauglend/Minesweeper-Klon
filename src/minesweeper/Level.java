package minesweeper;

public enum Level
{
	//Kinderleicht,
	//Normal,
	//Schwer,
	//McGyver
        
        Kinderleicht ("Kinderleicht"),
        Normal ("Normal"),
        Schwer ("Schwer"),
        McGyver ("McGyver");

    private final String name;       

    private Level(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
