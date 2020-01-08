package com.example.star_war_java_android_urban_adarshverma1236.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersResponse implements Parcelable {
    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Character> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Character> getResults() {
        return results;
    }

    public List<Character> getCharacters() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }

    public void setCharacters(List<Character> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.next);
        dest.writeString(this.previous);
        dest.writeTypedList(this.results);
    }

    public CharactersResponse() {
    }

    protected CharactersResponse(Parcel in) {
        this.count = in.readInt();
        this.next = in.readString();
        this.previous = in.readString();
        this.results = in.createTypedArrayList(Character.CREATOR);
    }

    public static final Parcelable.Creator<CharactersResponse> CREATOR = new Parcelable.Creator<CharactersResponse>() {
        @Override
        public CharactersResponse createFromParcel(Parcel source) {
            return new CharactersResponse(source);
        }

        @Override
        public CharactersResponse[] newArray(int size) {
            return new CharactersResponse[size];
        }
    };
}
