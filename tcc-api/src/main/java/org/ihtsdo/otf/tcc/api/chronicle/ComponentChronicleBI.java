package org.ihtsdo.otf.tcc.api.chronicle;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.coordinate.Position;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.coordinate.EditCoordinate;
import org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate;

public interface ComponentChronicleBI<T extends ComponentVersionBI>
        extends ComponentBI {

    T getVersion(ViewCoordinate c) throws ContradictionException;

    Collection<? extends T> getVersions(ViewCoordinate c);

    Collection<? extends T> getVersions();

    boolean isUncommitted();
    
    
    /**
     * 
     * @return the stamps for all versions of this chronicle. 
     * @throws IOException 
     */

    Set<Integer> getAllStamps() throws IOException;
    
    Set<Position> getPositions() throws IOException;
    
    T getPrimordialVersion();
    
    boolean makeAdjudicationAnalogs(EditCoordinate ec, ViewCoordinate vc) throws Exception;
    
    ConceptChronicleBI getEnclosingConcept();

}
