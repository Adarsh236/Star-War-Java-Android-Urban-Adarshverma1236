package com.example.star_war_java_android_urban_adarshverma1236.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Character implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("hair_color")
    private String haircolor;
    @SerializedName("skin_color")
    private String skincolor;
    @SerializedName("eye_color")
    private String eyecolor;
    @SerializedName("birth_year")
    private String birthyear;
    @SerializedName("gender")
    private String gender;
    @SerializedName("homeworld")
    private String homeworld;
    @SerializedName("films")
    private List<String> films = new ArrayList<>();
    @SerializedName("species")
    private List<String> species = new ArrayList<>();
    @SerializedName("vehicles")
    private List<String> vehicles = new ArrayList<>();
    @SerializedName("starships")
    private List<String> starships = new ArrayList<>();
    @SerializedName("created")
    private String created;
    @SerializedName("edited")
    private String edited;
    @SerializedName("url")
    private String url;

    public Character(String name, String height, String mass, String haircolor,
                     String skincolor, String eyecolor, String birthyear, String gender,
                     String homeworld, List<String> films, List<String> species,
                     List<String> vehicles, List<String> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.haircolor = haircolor;
        this.skincolor = skincolor;
        this.eyecolor = eyecolor;
        this.birthyear = birthyear;
        this.gender = gender;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public String getSkincolor() {
        return skincolor;
    }

    public void setSkincolor(String skincolor) {
        this.skincolor = skincolor;
    }

    public String getEyecolor() {
        return eyecolor;
    }

    public void setEyecolor(String eyecolor) {
        this.eyecolor = eyecolor;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.height);
        dest.writeString(this.mass);
        dest.writeString(this.haircolor);
        dest.writeString(this.skincolor);
        dest.writeString(this.eyecolor);
        dest.writeString(this.birthyear);
        dest.writeString(this.gender);
        dest.writeString(this.homeworld);
        dest.writeList(this.films);
        dest.writeList(this.species);
        dest.writeList(this.vehicles);
        dest.writeList(this.starships);
        dest.writeString(this.created);
        dest.writeString(this.edited);
        dest.writeString(this.url);
    }

    private Character(Parcel in) {
        this.name = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.haircolor = in.readString();
        this.skincolor = in.readString();
        this.eyecolor = in.readString();
        this.birthyear = in.readString();
        this.gender = in.readString();
        this.homeworld = in.readString();
        this.films = new ArrayList<>();
        in.readList(this.films, String.class.getClassLoader());

        this.species = new ArrayList<>();
        in.readList(this.species, String.class.getClassLoader());

        this.vehicles = new ArrayList<>();
        in.readList(this.vehicles, String.class.getClassLoader());

        this.starships = new ArrayList<>();
        in.readList(this.starships, String.class.getClassLoader());

        this.created = in.readString();
        this.edited = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
