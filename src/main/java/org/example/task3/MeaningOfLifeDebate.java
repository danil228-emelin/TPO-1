package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MeaningOfLifeDebate {
    private String topic;
    private int complexity; // Complexity level of the debate (1 = easy, 10 = hard)
    private int timeLimit; // Time limit in minutes
    private final List<HyperIntelligentBeing> participants = new ArrayList<>();
    private final List<DebateOutcome> history = new ArrayList<>();




    /**
     * Add a participant to the debate.
     *
     * @param being The being to add.
     */
    public void addParticipant(HyperIntelligentBeing being) {
        participants.add(being);
        System.out.println(being.getName() + " has joined the debate: " + topic);
    }

    /**
     * Start the debate and determine the outcome.
     */
    public void startDebate() {
        if (participants.isEmpty()) {
            System.out.println("No participants in the debate.");
            return;
        }

        System.out.println("Debate started: " + topic + " (Complexity: " + complexity + ", Time Limit: " + timeLimit + " minutes)");

        // Simulate debate outcomes for each participant
        for (HyperIntelligentBeing being : participants) {
            boolean isWin = being.debateMeaningOfLife(this);
            int score = calculateDebateScore(being);
            DebateOutcome outcome = new DebateOutcome(being, isWin, score);
            history.add(outcome);
            System.out.println(outcome);
        }

        System.out.println("Debate ended.");
    }

    /**
     * Calculate the debate score for a being.
     *
     * @param being The being to calculate the score for.
     * @return The debate score.
     */
    public int calculateDebateScore(HyperIntelligentBeing being) {
        // Score is based on intelligence, debate skill, and debate complexity
        int baseScore = being.getIntelligence() + being.getDebateSkill();
        int adjustedScore = baseScore - complexity * 10; // Higher complexity reduces the score
        return Math.max(adjustedScore, 0); // Ensure score is not negative
    }

    /**
     * Display the history of past debates.
     */
    public void displayDebateHistory() {
        System.out.println("Debate History for: " + topic);
        for (DebateOutcome outcome : history) {
            System.out.println(outcome);
        }
    }

    /**
     * Inner class to represent the outcome of a debate for a being.
     */
    private static class DebateOutcome {
        private HyperIntelligentBeing being;
        private boolean isWin;
        private int score;

        public DebateOutcome(HyperIntelligentBeing being, boolean isWin, int score) {
            this.being = being;
            this.isWin = isWin;
            this.score = score;
        }

        @Override
        public String toString() {
            return being.getName() + " - " + (isWin ? "Won" : "Lost") + " (Score: " + score + ")";
        }
    }
}