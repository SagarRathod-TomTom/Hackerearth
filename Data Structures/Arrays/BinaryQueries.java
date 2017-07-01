package com.datastructure.array;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A solution to the problem at hackerearth
 * {@link https://www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/range-query-2/}
 * 
 * @author Sagar Rathod.
 *
 */
public class BinaryQueries {

	public static void main(String[] args) throws IOException {

		BufferInput in = new BufferInput();
		BufferOutput bufferOutput = new BufferOutput();
		int n = in.nextInt();
		int q = in.nextInt();

		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		int query;
		int left, right;
		int flipBith = -1;
		while (q-- > 0) {
			query = in.nextInt();

			if (query == 0) {
				left = in.nextInt();
				right = in.nextInt() - 1;

				if (arr[right] == 0) {
					bufferOutput.writeString("EVEN\n");
				} else
					bufferOutput.writeString("ODD\n");
			} else {
				flipBith = in.nextInt() - 1;
				arr[flipBith] = arr[flipBith] == 1 ? 0 : 1;
			}
		}

		bufferOutput.flush();
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

		public long nextLong() throws IOException {
			long ret = 0;
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

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

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

	static class BufferOutput {

		private DataOutputStream dout;
		final private int BUFFER_SIZE = 1 << 16;
		private byte[] buffer;
		int pointer = 0;

		public BufferOutput() {
			buffer = new byte[BUFFER_SIZE];
			dout = new DataOutputStream(System.out);
		}

		public BufferOutput(OutputStream out) {
			buffer = new byte[BUFFER_SIZE];
			dout = new DataOutputStream(out);
		}

		public void writeBytes(byte arr[]) throws IOException {

			int bytesToWrite = arr.length;

			if (pointer + bytesToWrite >= BUFFER_SIZE) {
				flush();
			}

			for (int i = 0; i < bytesToWrite; i++) {
				buffer[pointer++] = arr[i];
			}
		}

		public void writeString(String str) throws IOException {
			writeBytes(str.getBytes());
		}

		private void flush() throws IOException {
			dout.write(buffer, 0, pointer);
			pointer = 0;
		}

	}

}
