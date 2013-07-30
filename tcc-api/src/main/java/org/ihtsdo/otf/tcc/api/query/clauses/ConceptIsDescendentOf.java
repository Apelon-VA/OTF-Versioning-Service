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
package org.ihtsdo.otf.tcc.api.query.clauses;

import java.io.IOException;
import java.util.EnumSet;
import org.ihtsdo.otf.tcc.api.query.ClauseComputeType;
import org.ihtsdo.otf.tcc.api.query.LeafClause;
import org.ihtsdo.otf.tcc.api.nid.NativeIdSetBI;
import org.ihtsdo.otf.tcc.api.query.Query;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.nid.NativeIdSetItrBI;
import org.ihtsdo.otf.tcc.api.store.Ts;
import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate;
import org.ihtsdo.otf.tcc.api.query.Clause;
import org.ihtsdo.otf.tcc.api.query.Where;
import org.ihtsdo.otf.tcc.api.spec.ConceptSpec;
import org.ihtsdo.otf.tcc.api.spec.ValidationException;

/**
 *
 * @author dylangrald
 */
public class ConceptIsDescendentOf extends LeafClause {

    ConceptSpec kindOfSpec;
    String kindOfSpecKey;

    public ConceptIsDescendentOf(Query enclosingQuery, String kindOfSpecKey) {
        super(enclosingQuery);
        this.kindOfSpecKey = kindOfSpecKey;
        this.kindOfSpec = (ConceptSpec) enclosingQuery.getLetDeclarations().get(kindOfSpecKey);
    }

    @Override
    public NativeIdSetBI computePossibleComponents(NativeIdSetBI incomingPossibleComponents) 
            throws ValidationException, IOException, ContradictionException {
        ViewCoordinate viewCoordinate = getEnclosingQuery().getViewCoordinate();
        int parentNid = kindOfSpec.getNid(viewCoordinate);
        NativeIdSetItrBI itr = incomingPossibleComponents.getIterator();
        while (itr.next()) {
            if (itr.nid() != parentNid && Ts.get().isKindOf(itr.nid(), parentNid, viewCoordinate)) {
                getResultsCache().setMember(itr.nid());
            }
        }
        return getResultsCache();
    }

    @Override
    public EnumSet<ClauseComputeType> getComputePhases() {
        return PRE_AND_POST_ITERATION;
    }

    @Override
    public void getQueryMatches(ConceptVersionBI conceptVersion) {
        // Nothing to do...
    }
    
    @Override
    public Where.WhereClause getWhereClause() {
        Where.WhereClause whereClause = new Where.WhereClause();
        whereClause.setSemantic(Where.ClauseSemantic.CONCEPT_IS_DESCENDENT_OF);
        for(Clause clause : getChildren()){
            whereClause.getChildren().add(clause.getWhereClause());
        }
        whereClause.getLetKeys().add(kindOfSpecKey);
        return whereClause;
    }
}
