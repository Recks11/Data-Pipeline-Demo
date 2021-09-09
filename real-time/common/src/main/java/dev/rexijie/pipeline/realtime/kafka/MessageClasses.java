package dev.rexijie.pipeline.realtime.kafka;

public enum MessageClasses {
    CA("Berth Step", "CA", "This moves the description from the 'from' berth, in to the 'to' berth, cancelling the " +
            "description in the 'from' berth and overwriting any description in the 'to' berth."),
    CB("Berth Cancel", "CB", "This cancels the description in the 'from' berth."),
    CC("Berth Interpose", "CC", "This inserts the description in to the 'to' berth, overwriting any description in the 'to' berth."),
    CT("Heartbeat", "CT", "A 'heartbeat' message, periodically sent from a train describer."),
    SF("Signalling Update", "SF", "Updates the signalling data for a specified set of signalling elements"),
    SG("Signalling Refresh", "SG", "Part of a set of SG messages which update the whole set of signalling" +
            " data for TD area. Terminated by an SH message."),
    SH("Signalling Refresh Finished", "SH", "Last message in a signalling refresh");

    private final String name;
    private final String code;
    private final String details;

    MessageClasses(String name, String code, String details) {
        this.name = name;
        this.code = code;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "{" +
                " \"code\" =\"" + code + '\"' +
                ", \"name\"=\"" + name + '\"' +
                ", \"details\"=\"" + details + '\"' +
                '}';
    }
}
