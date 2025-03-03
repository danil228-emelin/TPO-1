package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Decision {
    private Creature creatureWithAnswer;
    private String answer;

    public void printDecision() {
        System.out.println(  creatureWithAnswer.getName() + " decided what matter of life is " + answer);
    }
}
