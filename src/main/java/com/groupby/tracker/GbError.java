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
 * <p>
 * The schema for the response body when there is a 400 error because the beacon data was invalid.additionalProperties being true is intentional here. This allows us to add more data to the HTTP response in the future without it being a major version change of the API.
 *
 */
public class GbError implements Parcelable
{

    /**
     * The errors encountered while validating the beacon according to the request body JSON Schemas.maxItems being omitted here is intentional. This allows us to do more advanced forms of validation in the future where there are many error messages returned, without it being a major version change of the API.
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
     * The errors encountered while validating the beacon according to the request body JSON Schemas.maxItems being omitted here is intentional. This allows us to do more advanced forms of validation in the future where there are many error messages returned, without it being a major version change of the API.
     * (Required)
     *
     */
    public List<String> getJsonSchemaValidationErrors() {
        return jsonSchemaValidationErrors;
    }

    /**
     * The errors encountered while validating the beacon according to the request body JSON Schemas.maxItems being omitted here is intentional. This allows us to do more advanced forms of validation in the future where there are many error messages returned, without it being a major version change of the API.
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
