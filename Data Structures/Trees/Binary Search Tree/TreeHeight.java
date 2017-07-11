package com.datastructure.tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class TreeHeight {

	static class BST {
		TreeNode root;

		public void insert(int data[]) {

			if (data == null || data.length < 1)
				return;

			if (root == null) {
				root = new TreeNode(data[0]);
			}
			
			for (int i = 1; i < data.length; i++) {
				TreeNode node = root;
				boolean flag = true;
				while (flag) {
					if (data[i] <= node.data) {
						if (node.left == null) {
							node.left = new TreeNode(data[i]);
							flag = false;
						} else
							node = node.left;
					} else if (data[i] > node.data) {
						if (node.right == null) {
							node.right = new TreeNode(data[i]);
							flag = false;
						} else
							node = node.right;
					}
				}
			}
		}
		
		
		public int treeHeight(){
			return treeHeight(root);
		}
		
		private int treeHeight(TreeNode node) {
			if (node == null)
				return 0;
			else {
				/* compute the height of each subtree */
				int lDepth = treeHeight(node.left);
				int rDepth = treeHeight(node.right);

				/* use the larger one */
				if (lDepth > rDepth)
					return (lDepth + 1);
				else
					return (rDepth + 1);
			}
		}
		
		/**
		 * Prints the BST level wise
		 */
		private void printLevelWiseTree() {

			Queue<TreeNode> parentQueue = new PriorityQueue<TreeNode>();
			Queue<TreeNode> childQueue = new PriorityQueue<TreeNode>();

			parentQueue.add(root);
			while (!parentQueue.isEmpty()) {
				TreeNode node = parentQueue.poll();
				System.out.print(node.data + " ");

				if (node.left != null)
					childQueue.add(node.left);

				if (node.right != null)
					childQueue.add(node.right);

				if (parentQueue.isEmpty()) {
					parentQueue.addAll(childQueue);
					childQueue.clear();
					System.out.println();
				}
			}

		}
	}

	static class TreeNode implements Comparable<TreeNode> {
		int data;
		TreeNode left, right;

		public TreeNode(int data) {
			this.data = data;
			left = right = null;
		}

		@Override
		public int compareTo(TreeNode o) {
			return (data < o.data) ? -1 : ((data == o.data) ? 0 : 1);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferInput in = new BufferInput();
		int n = in.nextInt();
		int arr[] = in.nextIntArray(n);
		BST bst = new BST();
		bst.insert(arr);
		System.out.println(bst.treeHeight());
	}

	static class BufferInput {
		
		/**
		 *  A constant holding default buffer size, 2<sup>16</sup> bytes of memory.
		 * 
		 **/
		final private int BUFFER_SIZE = 1 << 16;
		
		private DataInputStream din;
		
		private byte[] buffer;
		
		private int bufferPointer, bytesRead;
		
		public BufferInput() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public BufferInput(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
			
		/**
		 * Reads an integer value.
		 * @return
		 * @throws IOException
		 */
		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}
		
		/**
		 * Reads an array of integers.
		 * @return
		 * @throws IOException
		 */
		public int[] nextIntArray(int n) throws IOException {
			int arr[] = new int[n];
			for(int i = 0; i < n; i++){
				arr[i] = nextInt();
			}
			return arr;
		}
		
		/**
		 * Fills the buffer from input stream.
		 * 
		 * @throws IOException
		 */
		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		/**
		 * 
		 * @return
		 * @throws IOException
		 */
		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}	
}
