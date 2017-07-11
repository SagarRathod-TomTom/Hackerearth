package com.datastructure.tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author Sagar Rathod
 * @version 1.0
 *
 */
public class TreeDiameter {

	/**
	 * 
	 * Binary Tree holds the reference to the root object 
	 *
	 */
	static class BinaryTree {

		private TreeNode root;

		BinaryTree(int rootData) {
			root = new TreeNode(rootData);
		}

		/**
		 * Inserts the tree node on the specified path
		 * For eg. if path is "L", node will be placed to the left of the root,
		 * and if the path is "R", node will be placed to right of the root.
		 * Path may contain combination of L or R, the node will be placed
		 * at the path by traversing the root, accordingly, if subsequent
		 * node is not present, -1 node will be placed.
		 * 
		 * @param data
		 * @param path
		 */
		public void insert(int data, String path) {
			char arr[] = path.toCharArray();

			TreeNode node = root;
			int len = arr.length - 1;
			for (int i = 0; i < len; i++) {
				if (arr[i] == 'L') {

					if (node.left == null) {
						node.left = new TreeNode(-1);
					}

					node = node.left;
				} else if (arr[i] == 'R') {

					if (node.right == null) {
						node.right = new TreeNode(-1);
					}

					node = node.right;
				}
			}

			if (arr[len] == 'L') {

				TreeNode temp = node.left;

				if (temp == null) {
					temp = new TreeNode(data);
					node.left = temp;
				}
				temp.data = data;

			} else {
				TreeNode temp = node.right;
				if (temp == null) {
					temp = new TreeNode(data);
					node.right = temp;
				}

				temp.data = data;

			}
		}

		/**
		 * finds the depth of the tree.
		 * 
		 * @param node
		 * @return
		 */
		int maxDepth(TreeNode node) {
			if (node == null)
				return 0;
			else {
				/* compute the depth of each subtree */
				int lDepth = maxDepth(node.left);
				int rDepth = maxDepth(node.right);

				/* use the larger one */
				if (lDepth > rDepth)
					return (lDepth + 1);
				else
					return (rDepth + 1);
			}
		}

		/**
		 * Finds the diameter of the tree.
		 * The diameter of a tree (sometimes called the width),
		 * is the number of nodes on the longest path between two 
		 * leaves in the tree
		 * 
		 * @param node
		 * @return
		 */
		int treeDiameter(TreeNode node) {
			if (node == null) {
				return 0;
			}

			int leftMaxPath = maxDepth(node.left);
			int rightMaxPath = maxDepth(node.right);

			int leftDiameter = treeDiameter(node.left);
			int rightDiameter = treeDiameter(node.right);

			return Math.max(leftMaxPath + rightMaxPath + 1, Math.max(leftDiameter, rightDiameter));
		}

		int treeDiameter() {
			return treeDiameter(root);
		}
	}

	/**
	 * 
	 * Node in the binary tree.
	 *
	 */
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
		int t = in.nextInt() - 1;
		int root = in.nextInt();
		BinaryTree binaryTree = new BinaryTree(root);
		while (t-- > 0) {
			String path = in.nextString();
			int data = in.nextInt();
			binaryTree.insert(data, path);
		}

		System.out.println(binaryTree.treeDiameter());
	}

	static class BufferInput {
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

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		/**
		 * 
		 * @return
		 * @throws IOException
		 */
		public String nextString() throws IOException {

			// Skip all whitespace characters from the stream
			byte c = read();
			while (Character.isWhitespace(c)) {
				c = read();
			}

			StringBuilder builder = new StringBuilder();
			builder.append((char) c);
			c = read();
			while (!Character.isWhitespace(c)) {
				builder.append((char) c);
				c = read();
			}

			return builder.toString();
		}

		public char nextChar() throws IOException {
			byte c = read();
			while (Character.isWhitespace(c)) {
				c = read();
			}
			return (char) c;
		}

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

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

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
