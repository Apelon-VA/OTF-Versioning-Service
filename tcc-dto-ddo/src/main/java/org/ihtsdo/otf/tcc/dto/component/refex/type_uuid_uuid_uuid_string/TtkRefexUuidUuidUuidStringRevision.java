package org.ihtsdo.otf.tcc.dto.component.refex.type_uuid_uuid_uuid_string;

//~--- non-JDK imports --------------------------------------------------------

import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;
import org.ihtsdo.otf.tcc.api.store.Ts;
import org.ihtsdo.otf.tcc.api.refex.type_nid_nid_nid_string
   .RefexNidNidNidStringVersionBI;
import org.ihtsdo.otf.tcc.dto.UtfHelper;
import org.ihtsdo.otf.tcc.dto.component.TtkRevision;
import org.ihtsdo.otf.tcc.dto.component.transformer.ComponentFields;
import org.ihtsdo.otf.tcc.dto.component.transformer.ComponentTransformerBI;

import static org.ihtsdo.otf.tcc.dto.component.TtkRevision.informAboutUuid;

//~--- JDK imports ------------------------------------------------------------

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;

public class TtkRefexUuidUuidUuidStringRevision extends TtkRevision {
   public static final long serialVersionUID = 1;
   @XmlAttribute
   public UUID              uuid1;
   @XmlAttribute
   public UUID              uuid2;
   @XmlAttribute
   public UUID              uuid3;
   @XmlAttribute
   public String            string1;

   public TtkRefexUuidUuidUuidStringRevision() {
      super();
   }

   public TtkRefexUuidUuidUuidStringRevision(
           RefexNidNidNidStringVersionBI another)
           throws IOException {
      super(another);

      TerminologyStoreDI ts = Ts.get();

      this.uuid1   = ts.getUuidPrimordialForNid(another.getNid1());
      this.uuid2   = ts.getUuidPrimordialForNid(another.getNid2());
      this.uuid3   = ts.getUuidPrimordialForNid(another.getNid3());
      this.string1 = another.getString1();
   }

   public TtkRefexUuidUuidUuidStringRevision(DataInput in, int dataVersion)
           throws IOException, ClassNotFoundException {
      super();
      readExternal(in, dataVersion);
   }

   public TtkRefexUuidUuidUuidStringRevision(
           TtkRefexUuidUuidUuidStringRevision another,
           ComponentTransformerBI transformer) {
      super(another, transformer);
      this.uuid1 =
         transformer.transform(another.uuid1, another,
                               ComponentFields.REFEX_COMPONENT_1_UUID);
      this.uuid2 =
         transformer.transform(another.uuid2, another,
                               ComponentFields.REFEX_COMPONENT_2_UUID);
      this.uuid3 =
         transformer.transform(another.uuid3, another,
                               ComponentFields.REFEX_COMPONENT_3_UUID);
      this.string1 = transformer.transform(another.string1, another,
              ComponentFields.REFEX_STRING1);
   }

   /**
    * Compares this object to the specified object. The result is <tt>true</tt>
    * if and only if the argument is not <tt>null</tt>, is a
    * <tt>ERefsetCidCidStrVersion</tt> object, and contains the same values,
    * field by field, as this <tt>ERefsetCidCidStrVersion</tt>.
    *
    * @param obj the object to compare with.
    * @return <code>true</code> if the objects are the same; <code>false</code>
    * otherwise.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }

      if (TtkRefexUuidUuidUuidStringRevision.class.isAssignableFrom(
              obj.getClass())) {
         TtkRefexUuidUuidUuidStringRevision another =
            (TtkRefexUuidUuidUuidStringRevision) obj;

         // =========================================================
         // Compare properties of 'this' class to the 'another' class
         // =========================================================
         // Compare c1Uuid
         if (!this.uuid1.equals(another.uuid1)) {
            return false;
         }

         // Compare c2Uuid
         if (!this.uuid2.equals(another.uuid2)) {
            return false;
         }

         // Compare c2Uuid
         if (!this.uuid3.equals(another.uuid3)) {
            return false;
         }

         // Compare stringValue
         if (!this.string1.equals(another.string1)) {
            return false;
         }

         // Compare their parents
         return super.equals(obj);
      }

      return false;
   }

   @Override
   public TtkRefexUuidUuidUuidStringRevision makeTransform(
           ComponentTransformerBI transformer) {
      return new TtkRefexUuidUuidUuidStringRevision(this, transformer);
   }

   @Override
   public final void readExternal(DataInput in, int dataVersion)
           throws IOException, ClassNotFoundException {
      super.readExternal(in, dataVersion);
      uuid1   = new UUID(in.readLong(), in.readLong());
      uuid2   = new UUID(in.readLong(), in.readLong());
      uuid3   = new UUID(in.readLong(), in.readLong());
      string1 = UtfHelper.readUtfV7(in, dataVersion);
   }

   /**
    * Returns a string representation of the object.
    */
   @Override
   public String toString() {
      StringBuilder buff = new StringBuilder();

      buff.append(this.getClass().getSimpleName()).append(": ");
      buff.append(" c1:");
      buff.append(informAboutUuid(this.uuid1));
      buff.append(" c2:");
      buff.append(informAboutUuid(this.uuid2));
      buff.append(" c3:");
      buff.append(informAboutUuid(this.uuid3));
      buff.append(" str:");
      buff.append("'").append(this.string1).append("' ");
      buff.append(super.toString());

      return buff.toString();
   }

   @Override
   public void writeExternal(DataOutput out) throws IOException {
      super.writeExternal(out);
      out.writeLong(uuid1.getMostSignificantBits());
      out.writeLong(uuid1.getLeastSignificantBits());
      out.writeLong(uuid2.getMostSignificantBits());
      out.writeLong(uuid2.getLeastSignificantBits());
      out.writeLong(uuid3.getMostSignificantBits());
      out.writeLong(uuid3.getLeastSignificantBits());
      UtfHelper.writeUtf(out, string1);
   }

   public String getString1() {
      return string1;
   }

   public UUID getUuid1() {
      return uuid1;
   }

   public UUID getUuid2() {
      return uuid2;
   }

   public UUID getUuid3() {
      return uuid3;
   }

   public void setString1(String string1) {
      this.string1 = string1;
   }

   public void setUuid1(UUID uuid1) {
      this.uuid1 = uuid1;
   }

   public void setUuid2(UUID uuid2) {
      this.uuid2 = uuid2;
   }

   public void setUuid3(UUID uuid3) {
      this.uuid3 = uuid3;
   }
}
