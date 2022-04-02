package model;

public class Exercise{
    private int id;
    private String name;
    private String info;
    private Trainer trainer;

    public Exercise(int id, String name, String information){
        this.id = id;
        this.name = name;
        this.info = information;
    }

    public Exercise(int id, String name, String information, Trainer trainer){
        this.id = id;
        this.name = name;
        this.info = information;
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public Trainer getTrainer() {
        return trainer;
    }
}
