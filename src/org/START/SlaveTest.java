package org.START;

import org.connection.TCPConn_Slave;

public class SlaveTest {
    public static void main(String[] args) throws Exception {
        TCPConn_Slave conn = new TCPConn_Slave();
        conn.TCPServer();
    }
}
