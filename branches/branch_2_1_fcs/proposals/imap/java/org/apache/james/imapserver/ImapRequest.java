/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE file.
 */
package org.apache.james.imapserver;

import java.util.StringTokenizer;

public interface ImapRequest
{
    String getCommand();
    
    void setCommand( String command );
    
    StringTokenizer getCommandLine();
    
    int arguments();

    SingleThreadedConnectionHandler getCaller();

    boolean useUIDs();

    ACLMailbox getCurrentMailbox();

    String getCommandRaw();

    String getTag();

    String getCurrentFolder();
}
