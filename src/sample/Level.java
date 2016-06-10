package sample;

public enum Level {
	MCGYVER(25, 25, 99), MEDIUM(16, 16, 40),LEICHT(8, 8, 10) {
		@Override
		public int getWidth() {
			return Persist.getCustomWidth();
		}
		@Override
		public int getHeight() {
			return Persist.getCustomHeight();
		}
		@Override
		public int getMines() {
			return Persist.getCustomMines();
		}
	};
	
	private final int width;
	private final int height;
	private final int mines;
	
	private Level(int w, int h, int m) {
		width = w;
		height = h;
		mines = m;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getMines() {
		return mines;
	}
}