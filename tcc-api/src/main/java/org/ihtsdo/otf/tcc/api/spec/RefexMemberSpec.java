package org.ihtsdo.otf.tcc.api.spec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @TODO
 * @author kec
 *
 */
public class RefexMemberSpec implements SpecBI {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private static final int dataVersion = 1;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(dataVersion);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int objDataVersion = in.readInt();
        if (objDataVersion == dataVersion) {
        	//;
        } else {
            throw new IOException("Can't handle dataversion: " + objDataVersion);
        }

    }

}
