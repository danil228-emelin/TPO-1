package org.example.task3;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Universe {
    private String name;
    private final List<HyperIntelligentBeing> beings = new ArrayList<>();
    private final Set<String> beingNames = new HashSet<>(); // To ensure unique names


    /**
     * Add a being to the universe.
     *
     * @param being The being to add.
     * @throws IllegalArgumentException If a being with the same name already exists.
     */
    public void addBeing(HyperIntelligentBeing being) {
        if (beingNames.contains(being.getName())) {
            throw new IllegalArgumentException("A being with the name " + being.getName() + " already exists in the universe.");
        }
        beings.add(being);
        beingNames.add(being.getName());
    }

    /**
     * Get the total energy of all beings in the universe.
     *
     * @return The total energy.
     */
    public int getTotalEnergy() {
        return beings.stream().mapToInt(HyperIntelligentBeing::getEnergy).sum();
    }

    /**
     * Organize a debate tournament among all beings.
     *
     * @param debate The debate topic.
     * @return The winner of the tournament.
     */
    public HyperIntelligentBeing organizeDebateTournament(MeaningOfLifeDebate debate) {
        if (beings.isEmpty()) {
            throw new IllegalStateException("No beings in the universe to organize a debate.");
        }

        HyperIntelligentBeing winner = null;
        int highestScore = -1;

        for (HyperIntelligentBeing being : beings) {
            boolean isWin = being.debateMeaningOfLife(debate);
            if (isWin && being.getIntelligence() > highestScore) {
                highestScore = being.getIntelligence();
                winner = being;
            }
        }

        if (winner != null) {
            System.out.println(winner.getName() + " wins the debate tournament!");
        } else {
            System.out.println("No clear winner in the debate tournament.");
        }

        return winner;
    }

    /**
     * Organize a Brokian Ultra Cricket tournament among all beings.
     *
     * @return The winner of the tournament.
     */
    public HyperIntelligentBeing organizeCricketTournament() {
        if (beings.isEmpty()) {
            throw new IllegalStateException("No beings in the universe to organize a cricket tournament.");
        }

        HyperIntelligentBeing winner = null;
        int highestScore = -1;

        for (HyperIntelligentBeing being : beings) {
            for (HyperIntelligentBeing opponent : beings) {
                if (being != opponent) {
                    boolean isWin = being.playBrokianUltraCricket(opponent);
                    if (isWin && being.getCricketSkill() > highestScore) {
                        highestScore = being.getCricketSkill();
                        winner = being;
                    }
                }
            }
        }

        if (winner != null) {
            System.out.println(winner.getName() + " wins the cricket tournament!");
        } else {
            System.out.println("No clear winner in the cricket tournament.");
        }

        return winner;
    }

    /**
     * Display a leaderboard of beings sorted by intelligence.
     */
    public void displayIntelligenceLeaderboard() {
        System.out.println("Intelligence Leaderboard:");
        beings.stream()
                .sorted(Comparator.comparingInt(HyperIntelligentBeing::getIntelligence).reversed())
                .forEach(being -> System.out.println(being.getName() + ": " + being.getIntelligence()));
    }

    /**
     * Display a leaderboard of beings sorted by debate skill.
     */
    public void displayDebateSkillLeaderboard() {
        System.out.println("Debate Skill Leaderboard:");
        beings.stream()
                .sorted(Comparator.comparingInt(HyperIntelligentBeing::getDebateSkill).reversed())
                .forEach(being -> System.out.println(being.getName() + ": " + being.getDebateSkill()));
    }

    /**
     * Display a leaderboard of beings sorted by cricket skill.
     */
    public void displayCricketSkillLeaderboard() {
        System.out.println("Cricket Skill Leaderboard:");
        beings.stream()
                .sorted(Comparator.comparingInt(HyperIntelligentBeing::getCricketSkill).reversed())
                .forEach(being -> System.out.println(being.getName() + ": " + being.getCricketSkill()));
    }

    /**
     * Recharge energy for all beings in the universe.
     */
    public void rechargeEnergyForAll() {
        beings.forEach(HyperIntelligentBeing::rest);
        System.out.println("All beings in the universe have recharged their energy.");
    }

}