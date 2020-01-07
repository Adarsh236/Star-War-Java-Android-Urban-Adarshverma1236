package com.example.star_war_java_android_urban_adarshverma1236.model;

public class CharacterComparator extends Character {

    // comparator for name
    public static final java.util.Comparator<Character> BY_NAME_ALPHABETICAL = new java.util.Comparator<Character>() {
        @Override
        public int compare(Character character, Character t1) {

            return character.getName().compareToIgnoreCase(t1.getName());
        }
    };
}