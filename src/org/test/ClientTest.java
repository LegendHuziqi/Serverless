package org.test;

import org.connection.TCPConn_Server;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        TCPConn_Server conn = new TCPConn_Server();
        conn.TCPClient("127.0.0.1",8888);
    }
}
