package enums;

import java.util.Comparator;

public enum SortOrder {
    ASCENDING(Comparator.naturalOrder()),
    DESCENDING(Comparator.reverseOrder());

    private final Comparator<String> comparator;

    SortOrder(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    public Comparator<String> getComparator() {
        return comparator;
    }
}
