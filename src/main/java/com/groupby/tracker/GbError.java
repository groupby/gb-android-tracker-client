package com.groupby.tracker;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * v3 beacon 400 HTTP response body
 *
 */
public class GbError implements Parcelable
{

    /**
     * Schema Validation errors
     * (Required)
     *
     */
    @SerializedName("jsonSchemaValidationErrors")
    @Expose
    private List<String> jsonSchemaValidationErrors = new ArrayList<String>();
    public final static Creator<GbError> CREATOR = new Creator<GbError>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GbError createFromParcel(android.os.Parcel in) {
            return new GbError(in);
        }

        public GbError[] newArray(int size) {
            return (new GbError[size]);
        }

    }
            ;

    protected GbError(android.os.Parcel in) {
        in.readList(this.jsonSchemaValidationErrors, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public GbError() {
    }

    /**
     *
     * @param jsonSchemaValidationErrors
     */
    public GbError(List<String> jsonSchemaValidationErrors) {
        super();
        this.jsonSchemaValidationErrors = jsonSchemaValidationErrors;
    }

    /**
     * Schema Validation errors
     * (Required)
     *
     */
    public List<String> getJsonSchemaValidationErrors() {
        return jsonSchemaValidationErrors;
    }

    /**
     * Schema Validation errors
     * (Required)
     *
     */
    public void setJsonSchemaValidationErrors(List<String> jsonSchemaValidationErrors) {
        this.jsonSchemaValidationErrors = jsonSchemaValidationErrors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GbError.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("jsonSchemaValidationErrors");
        sb.append('=');
        sb.append(((this.jsonSchemaValidationErrors == null)?"<null>":this.jsonSchemaValidationErrors));
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
        result = ((result* 31)+((this.jsonSchemaValidationErrors == null)? 0 :this.jsonSchemaValidationErrors.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GbError) == false) {
            return false;
        }
        GbError rhs = ((GbError) other);
        return ((this.jsonSchemaValidationErrors == rhs.jsonSchemaValidationErrors)||((this.jsonSchemaValidationErrors!= null)&&this.jsonSchemaValidationErrors.equals(rhs.jsonSchemaValidationErrors)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(jsonSchemaValidationErrors);
    }

    public int describeContents() {
        return  0;
    }

}
