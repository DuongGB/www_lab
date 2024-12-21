package vn.edu.iuh.fit.backend.enums;

public enum AccountStatus {
    CHECKING("Checking"), CHECKED("Checked");
    private String value;

    AccountStatus(String value) {
        this.value = value;
    }
//    AccountStatus(Byte value) {
//        this.value = value;
//    }
//
//    public Byte getValue() {
//        return value;
//    }
}
