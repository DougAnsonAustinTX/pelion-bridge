/*
 * Copyright 2018 bridan01.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arm.pelion.bridge.loggerservlet;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


/**
 *
 * @author bridan01
 */
@WebSocket
public class LoggerWebSocket {
	@OnWebSocketMessage
	public void onText(Session session, String message) throws IOException {
		System.out.println("Message received:" + message);
		if (session.isOpen()) {
			String response = message.toUpperCase();
			session.getRemote().sendString(response);
		}
	}

	@OnWebSocketConnect
	public void onConnect(Session session) throws IOException {
		System.out.println(session.getRemoteAddress().getHostString() + " connected!");
	}

	@OnWebSocketClose
	public void onClose(Session session, int status, String reason) {
		System.out.println(session.getRemoteAddress().getHostString() + " closed!");
	}

}