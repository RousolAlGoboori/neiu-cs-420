package data.models;

public enum RestaurantZipEnum {

    FIVE(60605),
    ELEVEN(60611),
    FOURTEEN(60614),
    SEVEN(60607),
    EIGHTEEN(60618),
    TEN(60610),
    TWENTY_TWO(60622),
    FORTY_ONE(60641),
    FORTY_SEVEN(60647),
    FIFTY_FOUR(60654);

    private final int zip;
    RestaurantZipEnum(int zip){
        this.zip= zip;
    }
    public int getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return String.valueOf(zip);
    }
}
