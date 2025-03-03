package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
class CreaturePlayer extends Creature {
    private int BrokianskiyUltraCricketSkill;

    public CreaturePlayer(String name, int intelligence, String favoriteActivity) {
        super(name, intelligence, favoriteActivity);
    }
    public void playBrokianskiyUltraCricket(CreaturePlayer target) {
        System.out.println(this.getName() + " kicked " + target.getName() + " and run");
    }

    @Override
    public String thinkAboutLife() {
        return "I am thinking about life";
    }

}