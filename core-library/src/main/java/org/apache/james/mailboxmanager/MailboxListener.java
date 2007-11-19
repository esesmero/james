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

package org.apache.james.mailboxmanager;

import java.util.Iterator;


/**
 * Listens to <code>Mailbox</code> events.
 */

public interface MailboxListener {
    
    //TODO: replace with event
    void mailboxDeleted();
    //TODO: replace with event
    void mailboxRenamed(String origName, String newName);
    
    void event(final Event event);
    
    /**
     * A mailbox event.
     */
    public interface Event {
        /**
         * Gets the id of the session which  the event.
         * @return session id
         */
        public long getSessionId();
    }
    
    /**
     * A mailbox event related to a message.
     */
    public interface MessageEvent extends Event {
        /**
         * Gets the subject of this event.
         * @return <code>MessageResult</code>, not null
         */
        public MessageResult getSubject();
    }
    
    public abstract class Expunged implements MessageEvent {}
    
    public abstract class FlagsUpdated implements MessageEvent {
        
        /**
         * Gets an iterator for the system flags changed.
         * @return <code>Flags.Flag</code> <code>Iterator</code>,
         * not null
         */
        public abstract Iterator flagsIterator();
    }
    
    public abstract class Added implements MessageEvent {}
}
