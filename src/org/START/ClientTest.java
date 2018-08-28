package org.START;

import org.connection.TCPConn_Client;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        TCPConn_Client conn = new TCPConn_Client();
        conn.TCPServer();
    }
}
