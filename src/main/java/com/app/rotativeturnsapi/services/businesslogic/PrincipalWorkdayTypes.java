package com.app.rotativeturnsapi.services.businesslogic;

public enum PrincipalWorkdayTypes {
    NORMAL(1), EXTRA_HOURS(2), VACATIONS(3), FREE_DAY(4);

    private Integer id;

    private PrincipalWorkdayTypes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
