package org.test;

import org.connection.TCPConn;

public class ServerTest {
    public static void main(String[] args) throws Exception {
        TCPConn conn = new TCPConn();
        conn.TCPServer();
    }
}
