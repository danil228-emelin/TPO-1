package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
class ArgueAboutLife {
    private Creature initiator;
    private Creature opponent;
    private String argument;


    public void conductArgue() {
        System.out.println(initiator.getName() + " is arguing with " + opponent.getName() + " about " + argument);
    }
}
