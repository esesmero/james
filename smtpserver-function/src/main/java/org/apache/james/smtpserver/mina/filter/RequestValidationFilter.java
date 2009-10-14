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
package org.apache.james.smtpserver.mina.filter;

import org.apache.commons.logging.Log;
import org.apache.james.smtpserver.SMTPRequest;
import org.apache.james.smtpserver.SMTPResponse;
import org.apache.james.smtpserver.SMTPRetCode;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;

/**
 * Filter which check if valid Object are used
 */
public class RequestValidationFilter extends AbstractValidationFilter {

    public RequestValidationFilter(Log logger) {
        super(logger);
    }

    /**
     * @see org.apache.mina.core.filterchain.IoFilterAdapter#messageReceived(org.apache.mina.core.filterchain.IoFilter.NextFilter,
     *      org.apache.mina.core.session.IoSession, java.lang.Object)
     */
    public void messageReceived(NextFilter nextFilter, IoSession session,
            Object message) throws Exception {
        if (message instanceof SMTPRequest) {
            super.messageReceived(nextFilter, session, message);
        } else {
            getLogger().error("The Received object is not an instance of SMTPRequestImpl");
            WriteRequest req = new DefaultWriteRequest(new SMTPResponse(
                    SMTPRetCode.TRANSACTION_FAILED,
                    "Cannot handle Request of type " + (message != null ? message.getClass() : "NULL")));
            nextFilter.filterWrite(session, req);
        }
    }

}