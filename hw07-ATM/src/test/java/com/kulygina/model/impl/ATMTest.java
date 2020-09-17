package com.kulygina.model.impl;

import com.kulygina.exception.RequestedAmountException;
import com.kulygina.model.BankNote;
import com.kulygina.model.enums.DenominationEnum;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ATMTest {
    @Test
    @DisplayName("Should create object ATM")
    public void shouldCreateATM() {
        ATM atm = new ATM();

        assertThat(atm).isNotNull();
    }

    @Test
    @DisplayName("Should take banknotes")
    public void shouldTakeCash() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(100));
        inputBanknotes.add(new BankNoteImpl(100));
        inputBanknotes.add(new BankNoteImpl(50));

        int balance = atm.takeCash(inputBanknotes);

        assertThat(balance).isEqualTo(250);
    }

    @Test
    @DisplayName("Should get balance")
    public void shouldGetBalance() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(500));
        inputBanknotes.add(new BankNoteImpl(100));
        inputBanknotes.add(new BankNoteImpl(10));
        atm.takeCash(inputBanknotes);

        int balance = atm.getBalance();

        assertThat(balance).isEqualTo(610);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should give cash")
    public void shouldGiveCash() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        BankNoteImpl bankNote1 = new BankNoteImpl(100);
        BankNoteImpl bankNote2 = new BankNoteImpl(10);
        BankNoteImpl bankNote3 = new BankNoteImpl(50);
        inputBanknotes.add(bankNote1);
        inputBanknotes.add(bankNote2);
        inputBanknotes.add(bankNote3);
        atm.takeCash(inputBanknotes);

        List<BankNote> bankNotes = atm.giveCash(60);

        assertThat(bankNotes).hasSize(2);
        assertThat(bankNotes.get(0).getDenomination()).isEqualTo(DenominationEnum.DENOMINATION_50);
        assertThat(bankNotes.get(1).getDenomination()).isEqualTo(DenominationEnum.DENOMINATION_10);
        assertThat(atm.getBalance()).isEqualTo(100);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_LessThan1() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(20));
        inputBanknotes.add(new BankNoteImpl(10));
        atm.takeCash(inputBanknotes);

        RequestedAmountException exception = assertThrows(RequestedAmountException.class,
                () -> atm.giveCash(-60));

        assertThat(exception.getMessage()).isEqualTo("Amount must be more than zero");
        assertThat(atm.getBalance()).isEqualTo(30);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_CanNotGiveRequestedSum() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(50));
        inputBanknotes.add(new BankNoteImpl(100));
        atm.takeCash(inputBanknotes);

        RequestedAmountException exception = assertThrows(RequestedAmountException.class,
                () -> atm.giveCash(20));

        assertThat(exception.getMessage()).isEqualTo("Can't give requested sum.");
        assertThat(atm.getBalance()).isEqualTo(150);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_TooMuchSum() {
        ATM atm = new ATM();
        List<BankNote> inputBanknotes = new ArrayList<>();
        inputBanknotes.add(new BankNoteImpl(20));
        inputBanknotes.add(new BankNoteImpl(10));
        atm.takeCash(inputBanknotes);

        RequestedAmountException exception = assertThrows(RequestedAmountException.class,
                () -> atm.giveCash(250));

        assertThat(exception.getMessage()).isEqualTo("Can't give requested sum.");
        assertThat(atm.getBalance()).isEqualTo(30);
    }
}