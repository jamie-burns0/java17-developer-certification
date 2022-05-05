package me.jamieburns.record;

public record ChildRecord(String name, int age) implements ChildInterface {

    private static final String DEFAULT_LIST_OF_DEMANDS = "TV all day";

    private enum DemandsEnum {
        UNDER_3_DEMANDS("PEPPA PIG!"),
        UNDER_8_DEMANDS("ELANOR OF AVALAR!"),
        DEFAULT_DEMANDS("TV! TV!");

        private final String demands;

        DemandsEnum(String demands) {
            this.demands = demands;
            // do nothing statement to remove unused variable warning
            demands = DEFAULT_LIST_OF_DEMANDS;
            demands = this.demands;
        }

        public String demands() {
            return demands;
        }
    }

    @Override
    public String listDemands() {
        return switch(listDemandsImpl()) {
            case UNDER_3_DEMANDS -> DemandsEnum.UNDER_3_DEMANDS.demands();
            case UNDER_8_DEMANDS -> DemandsEnum.UNDER_8_DEMANDS.demands();
            case DEFAULT_DEMANDS -> DemandsEnum.DEFAULT_DEMANDS.demands();
        };       
    }

    private DemandsEnum listDemandsImpl() {
        if (age < 3) 
            return DemandsEnum.UNDER_3_DEMANDS;
        if (age < 8)
            return DemandsEnum.UNDER_8_DEMANDS;
        return DemandsEnum.DEFAULT_DEMANDS;
    }
}
