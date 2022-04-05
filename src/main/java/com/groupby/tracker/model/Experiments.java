
package com.groupby.tracker.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * An experiment item.
 * 
 */
public class Experiments implements Parcelable
{

    /**
     * The ID (aka name) for the experiment. There should be one name shared by two or more variants.
     * (Required)
     * 
     */
    @SerializedName("experimentId")
    @Expose
    private String experimentId;
    /**
     * The variant (aka bucket) of the experiment active for this event. There should be at least two experiment variants for each experiment ID across all beacon data (otherwise there would be no comparison to make) and there should be only one experiment variant for each experiment ID in a single beacon (because it is invalid for an event to belong to more than one experiment bucket at a time).
     * (Required)
     * 
     */
    @SerializedName("experimentVariant")
    @Expose
    private String experimentVariant;
    public final static Creator<Experiments> CREATOR = new Creator<Experiments>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Experiments createFromParcel(android.os.Parcel in) {
            return new Experiments(in);
        }

        public Experiments[] newArray(int size) {
            return (new Experiments[size]);
        }

    }
    ;

    protected Experiments(android.os.Parcel in) {
        this.experimentId = ((String) in.readValue((String.class.getClassLoader())));
        this.experimentVariant = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Experiments() {
    }

    /**
     * 
     * @param experimentVariant
     * @param experimentId
     */
    public Experiments(String experimentId, String experimentVariant) {
        super();
        this.experimentId = experimentId;
        this.experimentVariant = experimentVariant;
    }

    /**
     * The ID (aka name) for the experiment. There should be one name shared by two or more variants.
     * (Required)
     * 
     */
    public String getExperimentId() {
        return experimentId;
    }

    /**
     * The ID (aka name) for the experiment. There should be one name shared by two or more variants.
     * (Required)
     * 
     */
    public void setExperimentId(String experimentId) {
        this.experimentId = experimentId;
    }

    /**
     * The variant (aka bucket) of the experiment active for this event. There should be at least two experiment variants for each experiment ID across all beacon data (otherwise there would be no comparison to make) and there should be only one experiment variant for each experiment ID in a single beacon (because it is invalid for an event to belong to more than one experiment bucket at a time).
     * (Required)
     * 
     */
    public String getExperimentVariant() {
        return experimentVariant;
    }

    /**
     * The variant (aka bucket) of the experiment active for this event. There should be at least two experiment variants for each experiment ID across all beacon data (otherwise there would be no comparison to make) and there should be only one experiment variant for each experiment ID in a single beacon (because it is invalid for an event to belong to more than one experiment bucket at a time).
     * (Required)
     * 
     */
    public void setExperimentVariant(String experimentVariant) {
        this.experimentVariant = experimentVariant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Experiments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("experimentId");
        sb.append('=');
        sb.append(((this.experimentId == null)?"<null>":this.experimentId));
        sb.append(',');
        sb.append("experimentVariant");
        sb.append('=');
        sb.append(((this.experimentVariant == null)?"<null>":this.experimentVariant));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.experimentVariant == null)? 0 :this.experimentVariant.hashCode()));
        result = ((result* 31)+((this.experimentId == null)? 0 :this.experimentId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Experiments) == false) {
            return false;
        }
        Experiments rhs = ((Experiments) other);
        return (((this.experimentVariant == rhs.experimentVariant)||((this.experimentVariant!= null)&&this.experimentVariant.equals(rhs.experimentVariant)))&&((this.experimentId == rhs.experimentId)||((this.experimentId!= null)&&this.experimentId.equals(rhs.experimentId))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(experimentId);
        dest.writeValue(experimentVariant);
    }

    public int describeContents() {
        return  0;
    }

}
