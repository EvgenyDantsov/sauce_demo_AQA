package enums;

public enum SortProducts {
    NAME_A_TO_Z("Name (A to Z)"),
    NAME_Z_TO_A("Name (Z to A)"),
    PRICE_LOW_TO_HIGH("Price (low to high)"),
    PRICE_HIGH_TO_LOW("Price (high to low)");

    private final String visibleText;

    SortProducts(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return visibleText;
    }

    @Override
    public String toString() {
        return visibleText;
    }
}
