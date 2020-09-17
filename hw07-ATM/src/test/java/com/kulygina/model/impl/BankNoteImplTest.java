package com.kulygina.model.impl;

import com.kulygina.model.BankNote;
import com.kulygina.model.Denomination;
import com.kulygina.model.enums.DenominationEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankNoteImplTest {
    @Test
    @DisplayName("Should create object Banknote with denomination")
    public void shouldCreateBankNoteImpl() {
        BankNoteImpl result = new BankNoteImpl(10);

        assertThat(result).isNotNull().isInstanceOf(BankNoteImpl.class)
                .isInstanceOf(Denomination.class).isInstanceOf(BankNote.class);
        assertThat(result.getDenomination()).isEqualTo(DenominationEnum.DENOMINATION_10);
    }
}