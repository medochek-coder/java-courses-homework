package com.kulygina.model.impl;

import com.kulygina.exception.RequestedAmountException;
import com.kulygina.model.ATMCell;
import com.kulygina.model.Denomination;
import com.kulygina.model.enums.DenominationEnum;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ATMCellImplTest {
    @Test
    @DisplayName("Should create object ATMCell with denomination")
    public void shouldCreateATMCellImpl() {
        ATMCellImpl result = new ATMCellImpl(10);

        assertThat(result).isNotNull().isInstanceOf(ATMCellImpl.class)
                .isInstanceOf(Denomination.class).isInstanceOf(ATMCell.class);
        assertThat(result.getDenomination()).isEqualTo(DenominationEnum.DENOMINATION_10);
    }

    @Test
    @DisplayName("Should add one banknote in cell")
    public void shouldAddOneBankNote() {
        ATMCellImpl atmCell = new ATMCellImpl(10);

        int countBankNotesInCell = atmCell.addOneBankNote();
        assertThat(countBankNotesInCell).isEqualTo(1);

        countBankNotesInCell = atmCell.addOneBankNote();
        assertThat(countBankNotesInCell).isEqualTo(2);
    }

    @Test
    @DisplayName("Should get count banknotes in cell")
    public void shouldGetCountBankNotes() {
        ATMCellImpl atmCell = new ATMCellImpl(10);
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();

        assertThat(atmCell.getCountBankNotes()).isEqualTo(3);
    }

    @lombok.SneakyThrows
    @Test
    @DisplayName("Should get some banknotes from cell")
    public void shouldGetBankNotes() {
        ATMCellImpl atmCell = new ATMCellImpl(10);
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();

        int bankNotesInATMCellAfterGet = atmCell.getBankNotes(2);

        assertThat(bankNotesInATMCellAfterGet).isEqualTo(1).isEqualTo(atmCell.getCountBankNotes());
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_TooMuchRequestedSum() {
        ATMCellImpl atmCell = new ATMCellImpl(10);
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();

        RequestedAmountException exception = assertThrows(RequestedAmountException.class, () -> atmCell.getBankNotes(4));

        assertThat(exception.getMessage()).isEqualTo("Unacceptable amount! There are only 3 banknotes in the cell!");
        assertThat(atmCell.getCountBankNotes()).isEqualTo(3);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_LessThan1() {
        ATMCellImpl atmCell = new ATMCellImpl(10);
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();

        RequestedAmountException exception = assertThrows(RequestedAmountException.class,
                () -> atmCell.getBankNotes(0));

        assertThat(exception.getMessage()).isEqualTo("Unacceptable amount! Amount must be more than zero");
        assertThat(atmCell.getCountBankNotes()).isEqualTo(3);
    }

    @SneakyThrows
    @Test
    @DisplayName("Should throw RequestedAmountException")
    public void shouldThrowRequestedAmountException_Negative() {
        ATMCellImpl atmCell = new ATMCellImpl(10);
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();
        atmCell.addOneBankNote();

        RequestedAmountException exception = assertThrows(RequestedAmountException.class,
                () -> atmCell.getBankNotes(-5));

        assertThat(exception.getMessage()).isEqualTo("Unacceptable amount! Amount must be more than zero");
        assertThat(atmCell.getCountBankNotes()).isEqualTo(3);
    }
}