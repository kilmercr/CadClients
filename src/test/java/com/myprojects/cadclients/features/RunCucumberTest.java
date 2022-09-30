package com.myprojects.cadclients.features;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, monochrome = true, features = {"classpath:com/myprojects/cadclients/features"})
public class RunCucumberTest {

}
