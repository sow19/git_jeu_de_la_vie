package constants;

import java.util.Arrays;

public final class NeighborsType {
	private NeighborsType() {
		
	}
	
	public static final int[][] GAMEOFLIFE = 
		{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	
	public static final int[][] TYPE2 = 
			{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static final int[][] TYPE3 = 
			{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}, {0, 0}};
	
	public static int[][] getType(String type) {
		if(type.toUpperCase() == "TYPE2") {
			return TYPE2;
		} else if(type.toUpperCase() == "TYPE3") {
			return TYPE3;
		} else {
			return GAMEOFLIFE;
		}
	}

	public static String coordRepresentation(String type) {
		int[][] nType = getType(type);
		
		StringBuilder sb = new StringBuilder();
        String delimiteur = ", ";

        for (int i = 0; i < nType.length; i++) {
            sb.append("(");
            sb.append(nType[i][0]);
            sb.append(delimiteur);
            sb.append(nType[i][1]);
            sb.append(")");

            if (i < nType.length - 1) {
                sb.append(delimiteur);
            }
        }

        return sb.toString();

	}

	public static int[][] stringToCoord(String chaineCoordonnees) {
		// Vérifier si la chaîne de caractères est vide
		if (chaineCoordonnees == null || chaineCoordonnees.trim().isEmpty()) {
			return GAMEOFLIFE;
		}
	
		// Vérifier si la chaîne de caractères est au bon format
		String regex = "\\((-?\\d+),(-?\\d+)\\)(;\\((-?\\d+),(-?\\d+)\\))*";
		if (!chaineCoordonnees.matches(regex)) {
			return GAMEOFLIFE;
		}
	
		// Convertir la chaîne de caractères en un tableau 2D d'entiers
		String[] pairesCoordonnees = chaineCoordonnees.split(";");
		int[][] tableauCoordonnees = new int[pairesCoordonnees.length][2];
		for (int i = 0; i < pairesCoordonnees.length; i++) {
			String[] coordonnees = pairesCoordonnees[i].replaceAll("\\(|\\)", "").split(",");
			tableauCoordonnees[i][0] = Integer.parseInt(coordonnees[0]);
			tableauCoordonnees[i][1] = Integer.parseInt(coordonnees[1]);
		}
		
		// System.out.println(Arrays.deepToString(tableauCoordonnees));
		
		return tableauCoordonnees;
	}
}
