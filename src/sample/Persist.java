package sample;

import java.util.prefs.Preferences;

public class Persist {
	private static final Preferences prefs = Preferences.userNodeForPackage(Persist.class);
	
	public static Level loadLevel3() {
		return Level.valueOf(prefs.get(Level.class.getSimpleName(), Level.LEICHT.name()));
	}
        
        public static Level loadLevel2() {
		return Level.valueOf(prefs.get(Level.class.getSimpleName(), Level.MEDIUM.name()));
	}
        
        public static Level loadLevel() {
		return Level.valueOf(prefs.get(Level.class.getSimpleName(), Level.MCGYVER.name()));
	}
        
	public static void storeLevel(Level d) {
		prefs.put(Level.class.getSimpleName(), d.name());
	}
	
	public static void setCustom(int width, int height, int mines) {
		prefs.putInt("CustomWidth", width);
		prefs.putInt("CustomHeight", height);
		prefs.putInt("CustomMines", mines);
	}
	
	public static int getCustomWidth() {
		return prefs.getInt("CustomWidth", 5);
	}
	public static int getCustomHeight() {
		return prefs.getInt("CustomHeight", 5);
	}
	public static int getCustomMines() {
		return prefs.getInt("CustomMines", 5);
	}
}