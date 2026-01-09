package com.gnssmonitor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.gnssmonitor.tests.*;

import static com.gnssmonitor.util.Helpers.*;

/**
 * Test Suite encompassing all tests for Location API calls
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApiMethodsTest.class,
        LastLocationTest.class,
        LastKnownLocationTest.class,
        GetCurrentLocationTest.class,
        RequestSingleUpdateTest.class,
        RequestLocationUpdatesTest.class,
})
public class LocationApiTestSuite {
    @BeforeClass
    public static void setUp() {
        grantLocationPermissions();
    }

    @AfterClass
    public static void tearDown() {
        revokeLocationPermissions();
    }
}
