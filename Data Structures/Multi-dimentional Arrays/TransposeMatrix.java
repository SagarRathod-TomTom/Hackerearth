package com.datastructure.array;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class TransposeMatrix {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int arr[][] = new int[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++ ){
				arr[i][j] = sc.nextInt();
			}
		}
		
		int transpose[][] = new int[m][n];
		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				transpose[row][col] = arr[col][row];
			}
		}
		
		for(int row = 0; row < m; row++){
			for(int col = 0; col < n; col++){
				System.out.print(transpose[row][col] + " ");
			}
			System.out.println();
		}
		
	}

}
