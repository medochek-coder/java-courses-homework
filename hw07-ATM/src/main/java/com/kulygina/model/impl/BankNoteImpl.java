package com.kulygina.model.impl;

import com.kulygina.model.BankNote;
import com.kulygina.model.enums.DenominationEnum;

public class BankNoteImpl implements BankNote {
    private DenominationEnum denomination;

    public BankNoteImpl(int value) {
        this.denomination = DenominationEnum.fromValue(value);
    }

    @Override
    public DenominationEnum getDenomination() {
        return denomination;
    }
}
