package com.ld.practice.nio.echoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lidong9144@163.com 17-6-28.
 */
public class Client implements Runnable {

    private static int idleCounter = 0;  // 空闲计数器,如果空闲超过10次,将检测server是否中断连接.
    private Selector selector;
    private SocketChannel socketChannel;
    private ByteBuffer temp = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        Client client= new Client();
        new Thread(client).start();
    }

    public Client() throws IOException {
        this.selector = Selector.open();
        this.socketChannel = SocketChannel.open();

        // 如果快速的建立了连接,返回true.如果没有建立,则返回false,并在连接后出发Connect事件.
        Boolean isConnected = socketChannel.connect(new InetSocketAddress("localhost", 7878));
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);

        if (isConnected) {
            this.sendFirstMsg();
        } else {
            key.interestOps(SelectionKey.OP_CONNECT);
        }
    }

    public void sendFirstMsg() throws IOException {
        String msg = "Hello NIO.";
        socketChannel.write(ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8"))));
    }

    @Override
    public void run() {
        for (;;) {
            try {
                int num = this.selector.select(1000);   // 阻塞,等待事件发生,或者1秒超时. num为发生事件的数量.
                if (num == 0) {
                    idleCounter ++;
                    if(idleCounter >10) {
                        // 如果server断开了连接,发送消息将失败.
                        try {
                            this.sendFirstMsg();
                        } catch(ClosedChannelException e) {
                            e.printStackTrace();
                            this.socketChannel.close();
                            return;
                        }
                    }
                    continue;
                } else {
                    idleCounter = 0;
                }

                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (sc.isConnectionPending()) {
                            sc.finishConnect();
                        }
                        this.sendFirstMsg();
                    } else if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();

                        if (sc.read(temp) < 0) {
                            sc.close();
                            continue;
                        }

                        temp.flip();
                        String msg = Charset.forName("UTF-8").decode(temp).toString();
                        System.out.println("Client received [" + msg + "] from server address:" + sc.getRemoteAddress());

                        Thread.sleep(1000);

                        sc.write(ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8"))));

                        temp.clear();
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
