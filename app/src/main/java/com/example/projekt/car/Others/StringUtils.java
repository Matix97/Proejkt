package com.example.projekt.car.Others;


/*
    JSPWiki - a JSP-based WikiWiki clone.

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
 */

import java.security.SecureRandom;
import java.util.Random;

public class StringUtils
{


    /**
     *  Generates a hexadecimal string from an array of bytes.  For
     *  example, if the array contains { 0x01, 0x02, 0x3E }, the resulting
     *  string will be "01023E".
     *
     * @param bytes A Byte array
     * @return A String representation
     * @since 2.3.87
     */
    public static String toHexString( byte[] bytes )
    {
        StringBuffer sb = new StringBuffer( bytes.length*2 );
        for( int i = 0; i < bytes.length; i++ )
        {
            sb.append( toHex(bytes[i] >> 4) );
            sb.append( toHex(bytes[i]) );
        }

        return sb.toString();
    }
    private static char toHex(int nibble)
    {
        final char[] hexDigit =
                {
                        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
                };
        return hexDigit[nibble & 0xF];
    }


}