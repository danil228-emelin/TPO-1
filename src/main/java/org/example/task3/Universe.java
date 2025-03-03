package org.example.task3;



import java.util.List;


record Universe(String name, List<Creature> beings) {
    public void addBeing(Creature being) {
        beings.add(being);
    }

}
