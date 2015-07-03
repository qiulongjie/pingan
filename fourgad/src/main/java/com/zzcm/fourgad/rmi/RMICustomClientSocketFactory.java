package com.zzcm.fourgad.rmi;

import java.io.IOException;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

/**
 * RMI客户端工厂
 * 
 * @author Administrator
 *
 */
public class RMICustomClientSocketFactory implements RMIClientSocketFactory {

	/**
	 * 超时时间
	 */
	private int timeout;

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Socket createSocket(String host, int port) throws IOException {
		Socket socket = new Socket(host, port);
		socket.setSoTimeout(timeout);
		return socket;
	}

}
