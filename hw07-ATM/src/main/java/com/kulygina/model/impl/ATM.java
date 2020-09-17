package com.kulygina.model.impl;

import com.kulygina.exception.RequestedAmountException;
import com.kulygina.model.ATMCell;
import com.kulygina.model.BankNote;
import com.kulygina.model.enums.DenominationEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ATM {
    private static final List<ATMCell> cells = new ArrayList<>();

    static {
        crateATMCells();
    }

    private static void crateATMCells() {
        for (DenominationEnum value : DenominationEnum.values()) {
            cells.add(new ATMCellImpl(value.getValue()));
        }
    }

    public int getBalance() {
        return cells.stream().mapToInt(cell -> cell.getCountBankNotes() * cell.getDenomination().getValue()).sum();
    }

    public int takeCash(List<BankNote> bankNotes) {
        for (BankNote bankNote : bankNotes) {
            for (ATMCell atmCell : cells) {
                if (atmCell.getDenomination() == bankNote.getDenomination()) {
                    atmCell.addOneBankNote();
                }
            }
        }
        return this.getBalance();
    }

    public List<BankNote> giveCash(int requestedSum) throws RequestedAmountException {

        if (requestedSum <= 0) {
            throw new RequestedAmountException("Amount must be more than zero");
        }

        int amountOfBanknotesFromCurrentCell;
        List<BankNote> result = new ArrayList<>();
        List<ATMCell> sortedCells = cells.stream().sorted(Comparator.comparing(ATMCell::getDenomination).reversed()).collect(Collectors.toList());
        for (ATMCell cell : sortedCells) {
            if (isCellUsefulForRequestedSum(requestedSum, cell)) {
                amountOfBanknotesFromCurrentCell = getAmountOfBanknotesFromCurrentCell(requestedSum, cell);
                if (isEnoughAmountOfBanknotesInCell(amountOfBanknotesFromCurrentCell, cell)) {
                    requestedSum = getRequiredAmountBanknotesFromCell(amountOfBanknotesFromCurrentCell, requestedSum, result, cell);
                } else {
                    requestedSum = getRequiredAmountBanknotesFromCell(cell.getCountBankNotes(), requestedSum, result, cell);
                }
            }
        }
        if (requestedSum > 0) {
            takeCash(result);
            throw new RequestedAmountException("Can't give requested sum.");
        }
        return result;
    }

    private boolean isCellUsefulForRequestedSum(int currentRequestedSum, ATMCell cell) {
        return (currentRequestedSum >= cell.getDenomination().getValue() && cell.getCountBankNotes() > 0);
    }

    private int getAmountOfBanknotesFromCurrentCell(int currentRequestedSum, ATMCell cell) {
        return (currentRequestedSum / cell.getDenomination().getValue());
    }

    private boolean isEnoughAmountOfBanknotesInCell(int neededAmount, ATMCell cell) {
        return (cell.getCountBankNotes() >= neededAmount);
    }

    private int getRequiredAmountBanknotesFromCell(int amountBanknotes, int currentRequestedSum, List<BankNote> result,
                                                   ATMCell cell) throws RequestedAmountException {
        cell.getBankNotes(amountBanknotes);
        for (int i = 0; i < amountBanknotes; i++) {
            result.add(new BankNoteImpl(cell.getDenomination().getValue()));
        }
        currentRequestedSum = currentRequestedSum - cell.getDenomination().getValue() * amountBanknotes;
        return currentRequestedSum;
    }
}
