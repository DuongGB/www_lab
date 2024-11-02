package vn.edu.iuh.fit.enums;

public enum EmployeeStatus {
    TERMINATED(-1), ACTIVE(1), IN_ACTIVE(0);

    private int value;

    EmployeeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  value == 1 ? "ACTIVE" : value == 0 ? "IN_ACTIVE" : "TERMINATED";
    }
}
