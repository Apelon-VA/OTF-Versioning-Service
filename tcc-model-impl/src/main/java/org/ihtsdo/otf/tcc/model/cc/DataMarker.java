/*
 * Copyright 2013 International Health Terminology Standards Development Organisation.
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
package org.ihtsdo.otf.tcc.chronicle.cc;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * DataMarker is used to indicate the type of object that follows in the stream. 
 * A case statement can then call the proper constructor. 
 * @author kec
 */
public enum DataMarker {

    CONCEPT((byte) 0);
    byte id;

    private DataMarker(byte id) {
        this.id = id;
    }

    public void writeMarker(DataOutput out) throws IOException {
        out.writeByte(this.id);
    }

    public static DataMarker readMarker(DataInput in) throws IOException {
        byte b = in.readByte();
        switch (b) {
            case 0:
                return CONCEPT;

            default:
                throw new UnsupportedOperationException("Can't handle: " + b);
        }
    }
}
