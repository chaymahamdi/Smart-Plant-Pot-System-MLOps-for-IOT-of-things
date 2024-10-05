package smart.plantpot.cot.controllers;

public enum PlantState {
    Healthy(0),
    Unhealthy(1);

    private final long stateplant;
    PlantState(long stateplant) {
        this.stateplant=stateplant;
    }

    public long getStateplant() {
        return stateplant;
    }
}
