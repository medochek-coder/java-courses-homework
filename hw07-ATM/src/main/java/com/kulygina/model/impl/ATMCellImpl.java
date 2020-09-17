package com.kulygina.model.impl;

import com.kulygina.exception.RequestedAmountException;
import com.kulygina.model.ATMCell;
import com.kulygina.model.enums.DenominationEnum;

public class ATMCellImpl implements ATMCell {
    private DenominationEnum denomination;
    private int countBankNotes;

    public ATMCellImpl(int value) {
        this.denomination = DenominationEnum.fromValue(value);
    }

    @Override
    public DenominationEnum getDenomination() {
        return denomination;
    }

    @Override
    public int getCountBankNotes() {
        return countBankNotes;
    }

    @Override
    public int addOneBankNote() {
        return ++countBankNotes;
    }

    @Override
    public int getBankNotes(int count) throws RequestedAmountException {
        if (countBankNotes < count) {
            throw new RequestedAmountException("Unacceptable amount! There are only " + getCountBankNotes()
                    + " banknotes in the cell!");
        }
        if (count <= 0) {
            throw new RequestedAmountException("Unacceptable amount! Amount must be more than zero");
        }
        countBankNotes = countBankNotes - count;
        return countBankNotes;
    }
}
