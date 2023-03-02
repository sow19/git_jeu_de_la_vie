package constants;

public final class NeighborsType {
	private NeighborsType() {
		
	}
	
	public static final int[][] GAMEOFLIFE = 
		{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	
	public static final int[][] TYPE2 = 
			{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static final int[][] TYPE3 = 
			{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}, {0, 0}};
}
