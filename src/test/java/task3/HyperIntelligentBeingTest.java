package task3;

import org.example.task3.HyperIntelligentBeing;
import org.example.task3.MeaningOfLifeDebate;
import org.example.task3.Universe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestLoggingAndTimingExtension.class)
class HyperIntelligentBeingTest {

    private HyperIntelligentBeing being;

    @BeforeEach
    void setUp() {
        being = HyperIntelligentBeing.builder()
                .name("Zaphod")
                .intelligence(80)
                .energy(100)
                .debateSkill(70)
                .cricketSkill(50)
                .build();
    }

    @Nested
    @DisplayName("Debate Tests")
    class DebateTests {

        @Test
        @DisplayName("Test successful debate with enough energy")
        void testSuccessfulDebate() {
            MeaningOfLifeDebate debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
            assertTrue(being.debateMeaningOfLife(debate));
            assertEquals(90, being.getEnergy());
        }

        @Test
        @DisplayName("Test debate failure due to low energy")
        void testDebateFailureDueToLowEnergy() {
            being.setEnergy(5);
            MeaningOfLifeDebate debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
            assertFalse(being.debateMeaningOfLife(debate));
            assertEquals(5, being.getEnergy());
        }

        @Test
        @DisplayName("Test debate score calculation")
        void testDebateScoreCalculation() {
            MeaningOfLifeDebate debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
            debate.addParticipant(being);
            debate.startDebate();
            assertEquals(100, debate.calculateDebateScore(being));
        }

        @Test
        @DisplayName("Test ability to print history of debates")
        void testDebatePrintingHistory() {
            MeaningOfLifeDebate debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
            debate.addParticipant(being);
            debate.startDebate();
            assertEquals("Debate History for: Life, the Universe, and Everything\nZaphod - Won (Score: 100)\n", debate.displayDebateHistory());
        }

    }

    @Nested
    @DisplayName("Cricket Tests")
    class CricketTests {

        private HyperIntelligentBeing opponent;

        @BeforeEach
        void setUp() {
            opponent = HyperIntelligentBeing.builder()
                    .name("Ford")
                    .intelligence(60)
                    .energy(100)
                    .debateSkill(50)
                    .cricketSkill(40)
                    .build();
        }

        @Test
        @DisplayName("Test successful cricket game")
        void testSuccessfulCricketGame() {
            assertTrue(being.playBrokianUltraCricket(opponent));
            assertEquals(80, being.getEnergy());
        }

        @Test
        @DisplayName("Test cricket game failure due to low energy")
        void testCricketGameFailureDueToLowEnergy() {
            being.setEnergy(10);
            assertFalse(being.playBrokianUltraCricket(opponent));
            assertEquals(10, being.getEnergy());
        }
    }

    @Nested
    @DisplayName("Rest and Energy Tests")
    class RestAndEnergyTests {

        @Test
        @DisplayName("Test rest to regain energy")
        void testRestToRegainEnergy() {
            being.setEnergy(50);
            being.rest();
            assertEquals(80, being.getEnergy());
        }

        @Test
        @DisplayName("Test rest does not exceed max energy")
        void testRestDoesNotExceedMaxEnergy() {
            being.setEnergy(90);
            being.rest();
            assertEquals(100, being.getEnergy());
        }

        @Test
        @DisplayName("Test being is exhausted")
        void testBeingIsExhausted() {
            being.setEnergy(0);
            assertTrue(being.isExhausted());
        }
    }
}

class MeaningOfLifeDebateTest {

    private MeaningOfLifeDebate debate;
    private HyperIntelligentBeing being;

    @BeforeEach
    void setUp() {
        debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
        being = HyperIntelligentBeing.builder()
                .name("Zaphod")
                .intelligence(80)
                .energy(100)
                .debateSkill(70)
                .cricketSkill(50)
                .build();
    }

    @Test
    @DisplayName("Test adding a participant to the debate")
    void testAddParticipant() {
        debate.addParticipant(being);
        assertEquals(1, debate.getParticipants().size());
        assertTrue(debate.getParticipants().contains(being));
    }

    @Test
    @DisplayName("Test starting a debate with participants")
    void testStartDebateWithParticipants() {
        debate.addParticipant(being);
        debate.startDebate();
        assertEquals(1, debate.getHistory().size());
    }

    @Test
    @DisplayName("Test starting a debate without participants")
    void testStartDebateWithoutParticipants() {
        debate.startDebate();
        assertEquals(0, debate.getHistory().size());
    }
}

class UniverseTest {

    private Universe universe;
    private HyperIntelligentBeing being1;
    private HyperIntelligentBeing being2;

    @BeforeEach
    void setUp() {
        universe = new Universe("The Milky Way");
        being1 = HyperIntelligentBeing.builder()
                .name("Zaphod")
                .intelligence(80)
                .energy(100)
                .debateSkill(70)
                .cricketSkill(50)
                .build();
        being2 = HyperIntelligentBeing.builder()
                .name("Ford")
                .intelligence(60)
                .energy(100)
                .debateSkill(50)
                .cricketSkill(40)
                .build();
    }

    @Test
    @DisplayName("Test adding a being to the universe")
    void testAddBeing() {
        universe.addBeing(being1);
        assertEquals(1, universe.getBeings().size());
        assertTrue(universe.getBeingNames().contains(being1.getName()));
    }

    @Test
    @DisplayName("Test displaying cricket skill leaderboard")
    void testDisplayCricketSkillLeaderboard() {
        universe.addBeing(being1);
        universe.addBeing(being2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        universe.displayCricketSkillLeaderboard();

        String expectedOutput = "Cricket Skill Leaderboard:\n" +
                "Zaphod: 50\n" +
                "Ford: 40\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Test displaying debate skill leaderboard")
    void testDisplayDebateSkillLeaderboard() {
        universe.addBeing(being1);
        universe.addBeing(being2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        universe.displayDebateSkillLeaderboard();

        String expectedOutput = "Debate Skill Leaderboard:\n" +
                "Zaphod: 70\n" +
                "Ford: 50\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Test displaying intelligence leaderboard")
    void testDisplayIntelligenceLeaderboard() {
        universe.addBeing(being1);
        universe.addBeing(being2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        universe.displayIntelligenceLeaderboard();

        String expectedOutput = "Intelligence Leaderboard:\n" +
                "Zaphod: 80\n" +
                "Ford: 60\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Test getting total energy of all beings")
    void testGetTotalEnergy() {
        universe.addBeing(being1);
        universe.addBeing(being2);

        assertEquals(200, universe.getTotalEnergy());

        being1.setEnergy(50);
        assertEquals(150, universe.getTotalEnergy());
    }

    @Test
    @DisplayName("Test adding a being with duplicate name")
    void testAddBeingWithDuplicateName() {
        universe.addBeing(being1);
        assertThrows(IllegalArgumentException.class, () -> universe.addBeing(being1));
    }

    @Test
    @DisplayName("Test organizing a debate tournament")
    void testOrganizeDebateTournament() {
        universe.addBeing(being1);
        universe.addBeing(being2);
        MeaningOfLifeDebate debate = new MeaningOfLifeDebate("Life, the Universe, and Everything", 5, 30);
        HyperIntelligentBeing winner = universe.organizeDebateTournament(debate);
        assertEquals(being1, winner);
    }

    @Test
    @DisplayName("Test organizing a cricket tournament")
    void testOrganizeCricketTournament() {
        universe.addBeing(being1);
        universe.addBeing(being2);
        HyperIntelligentBeing winner = universe.organizeCricketTournament();
        assertEquals(being1, winner);
    }

    @Test
    @DisplayName("Test recharging energy for all beings")
    void testRechargeEnergyForAll() {
        being1.setEnergy(50);
        being2.setEnergy(50);
        universe.addBeing(being1);
        universe.addBeing(being2);
        universe.rechargeEnergyForAll();
        assertEquals(80, being1.getEnergy());
        assertEquals(80, being2.getEnergy());
    }
}
