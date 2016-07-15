package com.ld.practice.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioDemo1 {
	
	public static void main(String[] args) {
		try {
			readToBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件到 buffer 中并输出
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
	
	/**
	 * selector 基础操作
	 */
	public void nioSelector() throws IOException{
		SocketChannel channel = SocketChannel.open();
		
		Selector selector = Selector.open();
		channel.configureBlocking(false);	// 切换 channel 到非阻塞模式
		
//		SelectionKey.OP_CONNECT
//		SelectionKey.OP_ACCEPT
//		SelectionKey.OP_READ
//		SelectionKey.OP_WRITE
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);	// 向Selector注册通道
		
		while (true) {
			int readyChannel = selector.select();	// 准备好的 channel 数目
			if (readyChannel == 0) continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();	// 注册到 selector 的通道
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key1 = iterator.next();
				if (key1.isAcceptable()) {
					SocketChannel socketChannel = (SocketChannel) key1.channel();	// 访问 key1 中 channel
					Selector selector1 = key1.selector(); // 访问 key1 selector
					
				} else if (key1.isConnectable()) {
					
				} else if (key1.isReadable()) {
					
				} else if (key1.isWritable()) {
					
				}
				// Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
				// 下次该通道变成就绪时，Selector会再次将其放入已选择键集中
				iterator.remove();
			}
		}
		
//		wakeUp()
//		某个线程调用select()方法后阻塞了，即使没有通道已经就绪，也有办法让其从select()方法返回。只要让其它线程在第一个线程调用select()方法的那个对象上调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。
//		如果有其它线程调用了wakeup()方法，但当前没有线程阻塞在select()方法上，下个调用select()方法的线程会立即“醒来（wake up）”。
//
//		close()
//		用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。
	}

}
