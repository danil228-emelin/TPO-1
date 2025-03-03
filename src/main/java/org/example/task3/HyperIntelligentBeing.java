package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class HyperIntelligentBeing {
    private String name;
    private int intelligence;
    private int energy;
    private int debateSkill;
    private int cricketSkill;

    // Constants for energy costs and gains
    private static final int DEBATE_ENERGY_COST = 10;
    private static final int CRICKET_ENERGY_COST = 20;
    private static final int REST_ENERGY_GAIN = 30;
    private static final int MAX_ENERGY = 100;

    /**
     * Participate in a debate about the meaning of life.
     *
     * @param debate The debate to participate in.
     * @return True if the being wins the debate, false otherwise.
     */
    public boolean debateMeaningOfLife(MeaningOfLifeDebate debate) {
        if (energy < DEBATE_ENERGY_COST) {
            System.out.println(name + " is too tired to debate!");
            return false;
        }

        energy -= DEBATE_ENERGY_COST;
        System.out.println(name + " is taking part into debate " + debate.getTopic());

        // Simulate debate outcome based on intelligence and debate skill
        int debateScore = intelligence + debateSkill;
        boolean isWin = debateScore > 50; // Arbitrary threshold for winning
        if (isWin) {
            System.out.println(name + " wins the debate!");
        } else {
            System.out.println(name + " loses the debate.");
        }

        return isWin;
    }

    /**
     * Play Brokian Ultra Cricket with another being.
     *
     * @param target The being to play against.
     * @return True if the being wins the game, false otherwise.
     */
    public boolean playBrokianUltraCricket(HyperIntelligentBeing target) {
        if (energy < CRICKET_ENERGY_COST) {
            System.out.println(name + " is too tired to play!");
            return false;
        }

        energy -= CRICKET_ENERGY_COST;
        System.out.println(name + " is playing to Brokian Ultra Cricket and kicking" + target.getName() + " without reason");

        // Simulate game outcome based on cricket skill
        int cricketScore = cricketSkill;
        boolean isWin = cricketScore > target.getCricketSkill();
        if (isWin) {
            System.out.println(name + " wins the game!");
        } else {
            System.out.println(name + " loses the game.");
        }

        return isWin;
    }

    /**
     * Rest to regain energy.
     */
    public void rest() {
        energy = Math.min(energy + REST_ENERGY_GAIN, MAX_ENERGY);
        System.out.println(name + " rests and regains energy. Current energy: " + energy);
    }

    /**
     * Check if the being is exhausted.
     *
     * @return True if the being has no energy left, false otherwise.
     */
    public boolean isExhausted() {
        return energy <= 0;
    }

}