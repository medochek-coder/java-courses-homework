package com.kulygina;

import com.kulygina.model.BankNote;
import com.kulygina.model.impl.ATM;
import com.kulygina.model.impl.BankNoteImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(100));
        inputBanknotes.add(new BankNoteImpl(100));
        inputBanknotes.add(new BankNoteImpl(50));
        inputBanknotes.add(new BankNoteImpl(50));
        inputBanknotes.add(new BankNoteImpl(10));
        inputBanknotes.add(new BankNoteImpl(500));
        inputBanknotes.add(new BankNoteImpl(10));
        inputBanknotes.add(new BankNoteImpl(10));

        ATM atm = new ATM();
        atm.takeCash(inputBanknotes);
        System.out.println("Balance: " + atm.getBalance());
        try {
            List<BankNote> outputBanknotes = atm.giveCash(270);
            for (BankNote banknote : outputBanknotes) {
                System.out.println(banknote.getDenomination().getValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Balance: " + atm.getBalance());
    }
}
