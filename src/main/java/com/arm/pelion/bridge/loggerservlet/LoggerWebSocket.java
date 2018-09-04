/**
 * @file LoggerWebSocket.java
 * @brief pelion-bridge Logging WebSocket
 * @author Brian Daniels
 * @version 1.0
 * @see
 *
 * Copyright 2018. ARM Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.arm.pelion.bridge.loggerservlet;

import com.arm.pelion.bridge.core.BaseClass;
import com.arm.pelion.bridge.core.ErrorLogger;
import com.arm.pelion.bridge.preferences.PreferenceManager;
import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


/**
 * Logger Web Socket implementation
 * 
 * @author Brian Daniels
 */
@WebSocket
public class LoggerWebSocket {
        public Session session;

	@OnWebSocketConnect
	public void onConnect(Session session) throws IOException {
            this.session = session;
            LoggerTracker.getInstance().join(this);
	}

	@OnWebSocketClose
	public void onClose(Session session, int status, String reason) {
            LoggerTracker.getInstance().leave(this);
	}

}