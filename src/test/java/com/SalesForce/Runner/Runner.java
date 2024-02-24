package com.SalesForce.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = {"src/test/resources/features/SalesForceLogin.feature"},
glue = "com.SalesForce.StepDef",


dryRun = false


)
public class Runner extends AbstractTestNGCucumberTests {

}
