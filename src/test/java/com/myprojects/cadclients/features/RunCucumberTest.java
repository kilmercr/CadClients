package com.myprojects.cadclients.features;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:com/myprojects/cadclients/features/"}, plugin = {"pretty"}, monochrome = true, tags = "@client_tests")
public class RunCucumberTest {

}
