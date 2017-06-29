package com.ld.practice.nio.echoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lidong9144@163.com 17-6-28.
 */
public class EchoServer {

    public static SelectorLoop connectionBell;
    public static SelectorLoop readBell;
    public boolean isReadBellRunning = false;

    public static void main(String[] args) throws IOException {
        new EchoServer().startServer();
    }

    public void startServer() throws IOException {
        connectionBell = new SelectorLoop();    // 准备好一个闹钟.当有链接进来的时候响.
        readBell = new SelectorLoop();  // 准备好一个闹钟,当有read事件进来的时候响.

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        ServerSocket socket = ssc.socket();
        socket.bind(new InetSocketAddress("localhost", 7878));

        // 给闹钟规定好要监听报告的事件,这个闹钟只监听新连接事件.
        ssc.register(connectionBell.getSelector(), SelectionKey.OP_ACCEPT);
        new Thread(connectionBell).start();
    }

    public class SelectorLoop implements Runnable {

        private Selector selector;
        private ByteBuffer buffer = ByteBuffer.allocate(1024);

        public SelectorLoop() throws IOException {
            this.selector = Selector.open();
        }

        public Selector getSelector() {
            return this.selector;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    this.selector.select(); // 阻塞,只有当至少一个注册的事件发生的时候才会继续.

                    Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        this.dispatch(key); // 处理事件. 可以用多线程来处理.
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void dispatch(SelectionKey key) throws IOException, InterruptedException {
            if (key.isAcceptable()) {
                // 这是一个connection accept事件, 并且这个事件是注册在 ServerSocketChannel 上的.
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);

                sc.register(readBell.getSelector(), SelectionKey.OP_READ);

                // 如果读取线程还没有启动,那就启动一个读取线程.
                if (!EchoServer.this.isReadBellRunning) {
                    synchronized (EchoServer.this) {
                        if (!EchoServer.this.isReadBellRunning) {
                            EchoServer.this.isReadBellRunning = true;
                            new Thread(readBell).start();
                        }
                    }
                }
            } else if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();

                int count = sc.read(buffer);    // 写数据到buffer
                if (count < 0) {    // 客户端已经断开连接.
                    key.cancel();
                    sc.close();
                    return;
                }

                // 切换buffer到读状态,内部指针归位.
                buffer.flip();
                String msg = Charset.forName("UTF-8").decode(buffer).toString();
                System.out.println("Server received ["+msg+"] from client address:" + sc.getRemoteAddress());

                Thread.sleep(1000);

                sc.write(ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8"))));  // echo back.

                buffer.clear();
            }
        }

    }

}
