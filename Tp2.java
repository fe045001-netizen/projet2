package Project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Tp2 {

	public static int Lislongeur(int[] t) {
		int n = t.length;
		if (n == 0)
			return 0;
		int[] tab = new int[n];
		Arrays.fill(tab, 1);
		int max = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (t[j] < t[i]) {
					tab[i] = Math.max(tab[i], tab[j] + 1);
				}
			}
			max = Math.max(max, tab[i]);
		}
		return max;
	}

	public static List<Integer> trouverPivots(int[] t) {
		int n = t.length;
		List<Integer> pivots = new ArrayList<>();
		if (n == 0)
			return pivots;

		int[] maxL = new int[n];
		int[] minR = new int[n];

		maxL[0] = t[0];
		for (int i = 1; i < n; i++)
			maxL[i] = Math.max(maxL[i - 1], t[i]);
		minR[n - 1] = t[n - 1];
		for (int i = n - 2; i >= 0; i--)
			minR[i] = Math.min(minR[i + 1], t[i]);

		for (int i = 0; i < n; i++) {
			if (maxL[i] == t[i] && minR[i] == t[i])
				pivots.add(t[i]);
		}
		return pivots;
	}

	public static int[][] spiralMatrix(int n) {
		int[][] m = new int[n][n];
		int top = 0, left = 0, bottom = n - 1, right = n - 1;
		int val = 1;
		while (top <= bottom && left <= right) {
			for (int j = left; j <= right; j++)
				m[top][j] = val++;
			top++;
			for (int i = top; i <= bottom; i++)
				m[i][right] = val++;
			right--;
			for (int j = right; j >= left; j--)
				m[bottom][j] = val++;
			bottom--;
			for (int i = bottom; i >= top; i--)
				m[i][left] = val++;
			left++;
		}
		return m;
	}

	public static int maxRectangle(int[][] matrix) {
		int n = matrix.length;
		if (n == 0)
			return 0;
		int m = matrix[0].length;
		int[] hauteur = new int[m];
		int Max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				hauteur[j] = (matrix[i][j] == 0) ? 0 : hauteur[j] + 1;
			}
			Max = Math.max(Max, Plusgrandrectangle(hauteur));
		}
		return Max;
	}

	private static int Plusgrandrectangle(int[] h) {
		Stack<Integer> st = new Stack<>();
		int max = 0;
		for (int i = 0; i <= h.length; i++) {
			int cur = (i == h.length ? 0 : h[i]);
			while (!st.isEmpty() && cur < h[st.peek()]) {
				int hauteur = h[st.pop()];
				int width = st.isEmpty() ? i : i - st.peek() - 1;
				max = Math.max(max, hauteur * width);
			}
			st.push(i);
		}
		return max;
	}

	public static boolean Permutationcirculaire(int[] t) {
		int n = t.length;
		boolean[] seen = new boolean[n + 1];
		for (int v : t) {
			if (v < 1 || v > n || seen[v])
				return false;
			seen[v] = true;
		}
		int start = -1;
		for (int i = 0; i < n; i++)
			if (t[i] == 1) {
				start = i;
				break;
			}
		for (int i = 0; i < n; i++) {
			int expected = (i + 1);
			if (t[(start + i) % n] != expected)
				return false;
		}
		return true;
	}

	public static int kadane(int[] t) {
	    int maxJM = t[0];
	    int maxFinici = t[0];
	    for (int i = 1; i < t.length; i++) {
	        maxFinici = Math.max(t[i], maxFinici + t[i]);
	        maxJM = Math.max(maxJM, maxFinici);
	    }
	    return maxJM;
	}

	public static int trouverMajoritaire(int[] t) {
	    int n = t.length;
	    if (n == 0)
	        return -1;

	 
	    int candidat = t[0];
	    int compteur = 1;

	    for (int i = 1; i < n; i++) {
	        if (t[i] == candidat) {
	            compteur++;
	        } else {
	            compteur--;
	            if (compteur == 0) {
	                candidat = t[i];
	                compteur = 1;
	            }
	        }
	    }

	    int occurrences = 0;
	    for (int val : t) {
	        if (val == candidat)
	            occurrences++;
	    }

	    return (occurrences > n / 2) ? candidat : -1;
	}

	public static List<Integer> trouverNombresManquants(int[] t) {
	    int n = t.length;
	    List<Integer> manquants = new ArrayList<>();
	    boolean[] present = new boolean[n + 1];

	    for (int val : t) {
	        if (val >= 1 && val <= n) {
	            present[val] = true;
	        }
	    }

	    for (int i = 1; i <= n; i++) {
	        if (!present[i]) {
	            manquants.add(i);
	        }
	    }

	    return manquants;
	}

	public static int differenceDiagonales(int[][] matrice) {
	    int n = matrice.length;
	    int diagPrincipale = 0;
	    int diagSecondaire = 0;

	    for (int i = 0; i < n; i++) {
	        diagPrincipale += matrice[i][i];
	        diagSecondaire += matrice[i][n - 1 - i];
	    }

	    System.out.println("Somme diagonale principale: " + diagPrincipale);
	    System.out.println("Somme diagonale secondaire: " + diagSecondaire);

	    return Math.abs(diagPrincipale - diagSecondaire);
	}

	public static boolean isMagic3x3(int[][] m) {
	    int cible = m[0][0] + m[0][1] + m[0][2];
	    for (int i = 0; i < 3; i++) {
	        int ligne = 0, colonne = 0;
	        for (int j = 0; j < 3; j++) {
	            ligne += m[i][j];
	            colonne += m[j][i];
	        }
	        if (ligne != cible || colonne != cible)
	            return false;
	    }
	    int d1 = m[0][0] + m[1][1] + m[2][2];
	    int d2 = m[0][2] + m[1][1] + m[2][0];
	    return d1 == cible && d2 == cible;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Entrez la taille du tableau : ");
		int n = sc.nextInt();
		int[] t = new int[n];
		System.out.println("Entrez les éléments du tableau :");
		for (int i = 0; i < n; i++)
			t[i] = sc.nextInt();

		System.out.println(" la longeur du tableau est  = " +Lislongeur (t));
		System.out.println(" Pivots = " + trouverPivots(t));

		System.out.print("\nEntrez la taille n pour une matrice spirale : ");
		int size = sc.nextInt();
		int[][] spiral = spiralMatrix(size);
		System.out.println(" Spirale " + size + "x" + size + ":");
		for (int[] row : spiral)
			System.out.println(Arrays.toString(row));

		System.out.print("\nEntrez la taille de la matrice (lignes colonnes) : ");
		int rows = sc.nextInt();
		int cols = sc.nextInt();
		int[][] mat = new int[rows][cols];
		System.out.println("Entrez les éléments de la matrice (0 ou 1) :");
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				mat[i][j] = sc.nextInt();
		System.out.println(" Plus grand rectangle de 1 = " + maxRectangle(mat));

		System.out.print("\nEntrez la taille du tableau pour permutation circulaire : ");
		int n2 = sc.nextInt();
		int[] circ = new int[n2];
		System.out.println("Entrez les éléments :");
		for (int i = 0; i < n2; i++)
			circ[i] = sc.nextInt();
		System.out.println(" Permutation circulaire ? " + Permutationcirculaire(circ));

		
		System.out.print("\nEntrez la taille du tableau pour somme max : ");
		int n3 = sc.nextInt();
		int[] arr = new int[n3];
		System.out.println("Entrez les éléments :");
		for (int i = 0; i < n3; i++)
			arr[i] = sc.nextInt();
		System.out.println(" Somme max sous-tableau = " + kadane(arr));

		System.out.print("\nEntrez la taille du tableau pour élément majoritaire : ");
		int n4 = sc.nextInt();
		int[] maj = new int[n4];
		System.out.println("Entrez les éléments :");
		for (int i = 0; i < n4; i++)
			maj[i] = sc.nextInt();
		System.out.println(" Élément majoritaire = " + trouverMajoritaire(maj));

		System.out.print("\nEntrez la taille du tableau pour nombres absents : ");
		int n5 = sc.nextInt();
		int[] missing = new int[n5];
		System.out.println("Entrez les éléments (entre 1 et " + n5 + ") :");
		for (int i = 0; i < n5; i++)
			missing[i] = sc.nextInt();
		System.out.println("Nombres absents = " + trouverNombresManquants(missing));

		System.out.print("\nEntrez la taille de la matrice carrée : ");
		int n6 = sc.nextInt();
		int[][] matDiag = new int[n6][n6];
		System.out.println("Entrez les éléments de la matrice :");
		for (int i = 0; i < n6; i++)
			for (int j = 0; j < n6; j++)
				matDiag[i][j] = sc.nextInt();
		System.out.println(" Différence absolue des diagonales = " + differenceDiagonales(matDiag));

		System.out.println("\nEntrez les éléments de la matrice 3x3 :");
		int[][] magic = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				magic[i][j] = sc.nextInt();
		System.out.println(" Matrice magique ? " + isMagic3x3(magic));

		sc.close();
	}
}
