/**
 * Copyright Notice
 *
 * This is a work of the U.S. Government and is not subject to copyright
 * protection in the United States. Foreign copyrights may apply.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ihtsdo.otf.tcc.model.cc.refex2;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.apache.mahout.math.list.IntArrayList;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.InvalidCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexDirective;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.coordinate.Status;
import org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate;
import org.ihtsdo.otf.tcc.api.refex.RefexType;
import org.ihtsdo.otf.tcc.api.refex2.RefexAnalogBI;
import org.ihtsdo.otf.tcc.api.refex2.RefexUsageDescriptionBI;
import org.ihtsdo.otf.tcc.api.refex2.RefexVersionBI;
import org.ihtsdo.otf.tcc.api.refex2.data.RefexDataBI;
import org.ihtsdo.otf.tcc.dto.component.TtkRevision;
import org.ihtsdo.otf.tcc.model.cc.P;
import org.ihtsdo.otf.tcc.model.cc.component.Revision;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

/**
 * {@link RefexRevision}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
public class RefexRevision extends Revision<RefexRevision, RefexMember> implements RefexAnalogBI<RefexRevision>
{

    public RefexRevision() {
        super();
    }

    public RefexRevision(int statusAtPositionNid, RefexMember primordialComponent) {
        super(statusAtPositionNid, primordialComponent);
    }

    public RefexRevision(TtkRevision eVersion, RefexMember member)  throws IOException{
        super(eVersion.getStatus(), eVersion.getTime(), P.s.getNidForUuids(eVersion.getAuthorUuid()),
                 P.s.getNidForUuids(eVersion.getModuleUuid()), P.s.getNidForUuids(eVersion.getPathUuid()),  member);
    }

    public RefexRevision(TupleInput input, RefexMember primordialComponent) {
        super(input, primordialComponent);
    }

    public RefexRevision(Status status, long time, int authorNid, int moduleNid, int pathNid, RefexMember primordialComponent) {
        super(status, time, authorNid, moduleNid, pathNid, primordialComponent);
    }

    //~--- methods -------------------------------------------------------------
    @Override
    protected void addComponentNids(Set<Integer> allNids) {
        allNids.add(primordialComponent.referencedComponentNid);
        allNids.add(primordialComponent.assemblageNid);
        addRefsetTypeNids(allNids);
    }

    @Override
    public RefexType getRefexType() {
        return getTkRefsetType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (RefexRevision.class.isAssignableFrom(obj.getClass())) {
            RefexRevision another = (RefexRevision) obj;

            if (this.stamp == another.stamp) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean refexFieldsEqual(RefexVersionBI another) {
        return primordialComponent.refexFieldsEqual(another);
    }

    @Override
    public final boolean readyToWriteRevision() {
        assert readyToWriteRefsetRevision() : assertionString();

        return true;
    }

    /*
     *  (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append(super.toString());

        return buf.toString();
    }

    @Override
    public String toUserString() {
        return toString();
    }

    //~--- get methods ---------------------------------------------------------
    @Override
    public int getAssemblageNid() {
        return primordialComponent.assemblageNid;
    }

    @Override
    @Deprecated
    public int getRefexExtensionNid() {
        return getAssemblageNid();
    }

    @Override
    public RefexMember getPrimordialVersion() {
        return primordialComponent;
    }

    @Override
    public int getReferencedComponentNid() {
        return primordialComponent.getReferencedComponentNid();
    }

    @Override
    public RefexCAB makeBlueprint(ViewCoordinate vc, 
            IdDirective idDirective, RefexDirective refexDirective) throws IOException,
            InvalidCAB, ContradictionException {
//        RefexCAB rcs = new RefexCAB(
//                getTkRefsetType(),
//                P.s.getUuidPrimordialForNid(getReferencedComponentNid()),
//                getAssemblageNid(),
//                getVersion(vc), 
//                vc, 
//                idDirective, 
//                refexDirective);

//        addSpecProperties(rcs);

        return null;//rcs;
    }

    //~--- set methods ---------------------------------------------------------
    @Override
    public void setAssemblageNid(int collectionNid) throws PropertyVetoException, IOException {
        primordialComponent.setAssemblageNid(collectionNid);
    }

    @Override
    @Deprecated
    public void setRefexExtensionNid(int collectionNid) throws PropertyVetoException, IOException {
        setAssemblageNid(collectionNid);
    }

    @Override
    public void setReferencedComponentNid(int componentNid) throws PropertyVetoException, IOException {
        primordialComponent.setReferencedComponentNid(componentNid);
    }
    
    /**
	 * @see org.ihtsdo.otf.tcc.api.chronicle.ComponentChronicleBI#getVersion(org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate)
	 */
	@Override
	public RefexVersionBI<RefexRevision> getVersion(ViewCoordinate c) throws ContradictionException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.chronicle.ComponentChronicleBI#getVersions(org.ihtsdo.otf.tcc.api.coordinate.ViewCoordinate)
	 */
	@Override
	public Collection<? extends RefexVersionBI<RefexRevision>> getVersions(ViewCoordinate c)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.chronicle.ComponentChronicleBI#getVersions()
	 */
	@Override
	public Collection<? extends RefexVersionBI<RefexRevision>> getVersions()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberVersionBI#getRefexUsageDescriptorNid()
	 */
	@Override
	public int getRefexUsageDescriptorNid()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberVersionBI#getRefexUsageDescription()
	 */
	@Override
	public RefexUsageDescriptionBI getRefexUsageDescription()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberVersionBI#getData()
	 */
	@Override
	public RefexDataBI[] getData()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberVersionBI#getData(int)
	 */
	@Override
	public RefexDataBI getData(int columnNumber) throws IndexOutOfBoundsException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberAnalogBI#setRefexUsageDescriptorNid(int)
	 */
	@Override
	public void setRefexUsageDescriptorNid(int refexUsageDescriptorNid)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberAnalogBI#setData(org.ihtsdo.otf.tcc.api.refex2.data.RefexDataBI[])
	 */
	@Override
	public void setData(RefexDataBI[] data) throws PropertyVetoException
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see org.ihtsdo.otf.tcc.api.refex2.RefexMemberAnalogBI#setData(int, org.ihtsdo.otf.tcc.api.refex2.data.RefexDataBI)
	 */
	@Override
	public void setData(int columnNumber, RefexDataBI data) throws IndexOutOfBoundsException, PropertyVetoException
	{
		// TODO Auto-generated method stub
		
	}

	protected void addRefsetTypeNids(Set<Integer> allNids)
	{
		// TODO Auto-generated method stub
		
	}

	protected void addSpecProperties(RefexCAB rcs)
	{
		// TODO Auto-generated method stub
		
	}

	public RefexRevision makeAnalog()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean readyToWriteRefsetRevision()
	{
		// TODO Auto-generated method stub
		return false;
	}

	protected RefexType getTkRefsetType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.model.cc.component.Revision#makeAnalog(org.ihtsdo.otf.tcc.api.coordinate.Status, long, int, int, int)
	 */
	@Override
	public RefexRevision makeAnalog(Status status, long time, int authorNid, int moduleNid, int pathNid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.ihtsdo.otf.tcc.model.cc.component.Revision#writeFieldsToBdb(com.sleepycat.bind.tuple.TupleOutput)
	 */
	@Override
	protected void writeFieldsToBdb(TupleOutput output)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see org.ihtsdo.otf.tcc.model.cc.component.Revision#getVariableVersionNids()
	 */
	@Override
	public IntArrayList getVariableVersionNids()
	{
		// TODO Auto-generated method stub
		return null;
	}


}