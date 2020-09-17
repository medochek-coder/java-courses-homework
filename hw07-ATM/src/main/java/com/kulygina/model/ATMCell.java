package com.kulygina.model;

import com.kulygina.exception.RequestedAmountException;

public interface ATMCell extends Denomination {
    int getCountBankNotes();

    int getBankNotes(int count) throws RequestedAmountException;

    int addOneBankNote();
}
