package com.datastructure.array;

import java.util.Scanner;

/**
 * 
 * @author sagar_rathod
 * Solution to the problem at below url:
 * 
 * {@linkplain https://www.hackerearth.com/practice/data-structures/arrays/multi-dimensional/practice-problems/algorithm/roy-and-symmetric-logos-1/}
 *
 */
public class SymmetricLogos {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		while(t-- > 0){
			int n = sc.nextInt();
			char arr[][] = new char[n][n];
			
			for(int i = 0; i < n; i++){
				arr[i] = sc.next().toCharArray();
			}
			
			//
			int mid = n / 2;
			boolean flag = true;
			//x-axis
			for(int rowA = 0, rowB = n - 1; rowA <= mid && rowB >= mid; rowA++, rowB--){
				for(int col = 0; col < n; col++){
					if(arr[rowA][col] != arr[rowB][col]){
						flag = false;
					}
				}
			}
			
			//y-axis
			for(int colA = 0, colB = n - 1; colA <= mid && colB >= mid; colA++, colB--){
				for(int row = 0; row < n; row++){
					if(arr[row][colA] != arr[row][colB]){
						flag = false;
					}
				}
			}
			
			if(flag){
				System.out.println("YES");
			}
			else
				System.out.println("NO");
		}
	}

}
