package com.noveria.common;

import com.noveria.common.groups.UnitTest;
import org.junit.experimental.categories.Category;
import org.mockito.MockitoAnnotations;

@Category(UnitTest.class)
public abstract class BaseUnitTest {

    protected BaseUnitTest() {
        MockitoAnnotations.initMocks(this);
    }
}
