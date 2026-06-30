@ProductSearch
Feature: Title of your feature
  I want to use this template for my feature file

  @Testing
  Scenario: Title of your scenario
    Given I am the amazon site
    And I enter the searhbox with required product

  @Testing2
  Scenario: Switch the window and visit new url
    Given I am at Google Site
    And I open new Tab and open a new url

  @DB_Testing
  Scenario: Fetch the value from DB table
    Given I am at Google Site
    ## need a dependency mysql-connector-j from maven repository
    Given I fetch column values from the DB and filter the required value

  @XMLValueFetching_Testing @UHG_Reference
  Scenario: Fetch the value from DB table
    Given I am at Google Site
    Given I fetch value from the given XML based on parameters

  @ExcelEnumValueFetching_Testing  @UHG_Reference
  Scenario: Fetch the value from DB table
    Given I am at Google Site
    Given I fetch value from excel where paths are stored a enum

  @WhileLoopLookingForAnElementORStatusCompleted @UHG_Reference
  Scenario: Fetch the value from DB table
    Given I am at Google Site
    Given I look whether execution of batch is completed and completion status has appeared for a batch "Single Step Batch - ESI Check File Creation"

  @GeneratingEmployeeViaNewHire @UHG_Reference
  Scenario: Fetch the value from the Excel and Print it out
    Given I am at Google Site
    #Given I Generate Employee via New Hire "Vijeth"
    #Given I Generate Employee via New Hire "GregoryHouse"
    #Given I Generate Employee via New Hire "JamesWilson"
    Given I Generate Employee via New Hire "LisaCuddy"

  @WritignInExcelTrial
  Scenario: Fetch the value from the Excel and Print it out
    Given I am at Google Site
    Given I Write in the Excel Sheet the required output in the trial attempt

  
 

    
 	
	  
 
	
