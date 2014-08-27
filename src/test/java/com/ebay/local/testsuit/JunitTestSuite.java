package com.ebay.local.testsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebay.local.scrappers.TestMacysScrapper;
import com.ebay.local.scrappers.TestToysrusScrapper;
import com.ebay.local.services.TestAvailabilityService;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestAvailabilityService.class,
   TestMacysScrapper.class,
   TestToysrusScrapper.class
   
})
public class JunitTestSuite {   
}
