package com.app.rotativeturnsapi.services.businesslogic;

import com.app.rotativeturnsapi.entities.Workday;

public interface BusinessLogicValidator {

    public boolean ValidateWorkdayCreate(Workday newWorkday) throws IllegalArgumentException;
}
