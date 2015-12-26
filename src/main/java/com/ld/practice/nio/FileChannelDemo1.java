package com.ld.practice.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
	
	public static void main(String[] args) {
		try {
			readToBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取到 buffer 中并输出
	 * 
	 * @throws IOException
	 */
	public static void readToBuffer() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("D:\\workspace\\eclipse\\practice\\src\\date\\nio-date.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		int bytesRead = inChannel.read(buf); // 从 inChannel 读到 buf 中
		while (bytesRead != -1) {
			buf.flip(); // 反转 buf 到读模式
			
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			
			buf.clear(); // 反转 buf 到写模式
			bytesRead = inChannel.read(buf);
		}
		
		aFile.close();
	}

}
