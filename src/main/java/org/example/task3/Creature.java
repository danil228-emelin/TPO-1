package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
abstract class Creature implements Consciousness {
    private String name;
    private int intelligence;
    private String favoriteActivity;


    //all clever creatures can think about life
    public abstract String thinkAboutLife();

    @Override
    public int calculateIntelligence() {
        return this.intelligence;
    }
}