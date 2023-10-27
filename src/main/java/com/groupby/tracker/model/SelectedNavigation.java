package com.groupby.tracker.model;

public class SelectedNavigation {
    private String name;
    private String value;

    public SelectedNavigation() {
    }

    public SelectedNavigation(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SelectedNavigation{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static void main(String[] args) {
        SelectedNavigation navigation = new SelectedNavigation("refined 1", "ukulele");
        System.out.println(navigation);
    }
}
