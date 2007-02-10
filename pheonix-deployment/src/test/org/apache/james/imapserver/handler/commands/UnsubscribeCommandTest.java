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

package org.apache.james.imapserver.handler.commands;

import org.apache.james.imapserver.ImapSessionState;
import org.apache.james.imapserver.ProtocolException;
import org.jmock.core.Constraint;

public class UnsubscribeCommandTest extends AbstractCommandTest {

    public void testSubscribeNonFq() throws ProtocolException {
        final String fqMailboxName = "#mock.user.Test";

        setSessionState(ImapSessionState.AUTHENTICATED);
        setUpMailboxManager();

        mockSession.expects(once()).method("buildFullName").with(eq("Test"))
                .will(returnValue(fqMailboxName));
        mockSession.expects(once()).method("unsolicitedResponses")
                .withAnyArguments();

        mockMailboxManager.expects(once()).method("setSubscription").with(
                new Constraint[] {eq(fqMailboxName),eq(false)});

        String response = handleRequest("1 UNSUBSCRIBE Test\n");

        assertEquals("1 OK UNSUBSCRIBE completed.\r\n", response);
    }

}
