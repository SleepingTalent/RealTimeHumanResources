package com.noveria.realtimehumanresources.model.address.helper;

import com.noveria.common.BaseUnitTest;
import com.noveria.realtimehumanresources.model.address.entities.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressHelperTest extends BaseUnitTest {

    AddressHelper addressHelper;

    @Before
    public void setUp() {
        addressHelper = new AddressHelper();
    }

    @Test
    public void findPrimaryAddress_returnsNull_ifNoPrimaryAddressInList() {
        Address address = new Address();
        address.setPrimaryAddress(false);

        List<Address> addressList = addressHelper.convertToList(address);

        Assert.assertNull("Expected Address Returned to be null!",addressHelper.findPrimaryAddress(addressList));
    }


    @Test
    public void findPrimaryAddress_returnsExpectedPrimaryAddress() {
        Address nonPrimaryAddress = new Address();
        nonPrimaryAddress.setPrimaryAddress(false);
        nonPrimaryAddress.setId(1234l);

        Address primaryAddress = new Address();
        primaryAddress.setPrimaryAddress(true);
        primaryAddress.setId(4567l);

        List<Address> addressList = addressHelper.convertToList(nonPrimaryAddress, primaryAddress);

        Address actual = addressHelper.findPrimaryAddress(addressList);
        Assert.assertEquals(primaryAddress.getId(), actual.getId());
    }
}
