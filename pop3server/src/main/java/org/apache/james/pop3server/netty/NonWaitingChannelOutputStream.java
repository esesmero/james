/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package org.apache.james.pop3server.netty;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.james.server.netty.ChannelOutputStream;
import org.jboss.netty.channel.Channel;

/**
 * Allow to write via an {@link OutputStream} to the underlying {@link Channel}. All writes are just passed to the {@link Channel} without waiting for 
 * acknowledge. 
 *
 */
public class NonWaitingChannelOutputStream extends ChannelOutputStream{

    public NonWaitingChannelOutputStream(Channel channel) {
        super(channel);
    }

    /**
     * This will NOT close the underlying Channel
     * 
     */
    @Override
    public void close() throws IOException {
        
    }
    

    /**
     * Every write is flushed so this will not do anything
     */
    @Override
    public void flush() throws IOException {
    }

}
