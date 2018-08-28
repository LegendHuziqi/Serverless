package org.START;

import org.connection.TCPConn_Server;

public class ServerTest {
    public static void main(String[] args) throws Exception {
        TCPConn_Server conn = new TCPConn_Server();
        conn.TCPServer();
    }
}
