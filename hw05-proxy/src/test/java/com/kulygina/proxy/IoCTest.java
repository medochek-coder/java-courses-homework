package com.kulygina.proxy;

import com.kulygina.logging.IClassForLogging;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IoCTest {
    @Test
    @DisplayName("Should create object of IClassForLogging")
    public void createMyClassForLogging() {
        IClassForLogging myClassForLogging = IoC.createMyClassForLogging();

        assertThat(myClassForLogging).isNotNull().isInstanceOf(IClassForLogging.class);
    }
}