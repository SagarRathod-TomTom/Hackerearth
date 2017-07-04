package com.datastructure.array;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MonkWelcome {
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferInput in = new BufferInput();
		int n = in.nextInt();
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++){
			arr[i] = in.nextInt();
		}

		int value;
		char ch = ' ';
		for(int i = 0; i < n; i++){
			value = in.nextInt();
			wr.write(Integer.toString(arr[i] + value));
			wr.write(ch);
		}
		wr.flush();
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
		
		public char nextChar() throws IOException{
			byte c = read();
			while(Character.isWhitespace(c)){
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
