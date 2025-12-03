#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Selenium Proficiency Questions
 
  @ClickingOnRemovedElement
  Scenario: Remove DOM elements and test with clicks
    Given I am on the cutomized Page
    And I Click on the button after being removed from DOM with apporach "<SCENARIO_TYPE>"
    
    Examples:
      | SCENARIO_TYPE                                                    |
      | WITH REFERENCE VARIABLE REMOVE CHILD NODE ALONE                  |
      | WITH REFERENCE VARIABLE REMOVE PARENT NODE ALONE                 |
      | WITHOUT REFERENCE VARIABLE REMOVE CHILD NODE ALONE               |
      | WITHOUT REFERENCE VARIABLE PARENT NODE ALONE                     |
      | WITH REFERENCE VARIABLE POINT TO PARENT REMOVE PARENT NODE ALONE |
      
    @ClickingOnRemovedAttribute
  Scenario: Remove DOM elements and test with clicks
    Given I am on the cutomized Page with DisabledElement
    And I remove the disbaled attribute and fetch value
    And I highlight the element using Javascript Executor
    
 	@SetInnertTextOnDivElement
  Scenario: Remove DOM elements and test with clicks
    Given I am on the spiceJet site
    And I enter date depart and return
    And I enter the destination and choose date of travel before clicking search
    And I click on the search button wrapped in try and catch block
    And I await for the sort element after clinking search using Try and catch block with recursive function
    #And I want the page to load using jsexecutor page ready state
    And I wait after the search is sorted to verify visually
    
    @RemoveReadOnlyAndEnterText
  Scenario: Remove readonly attribute and enter required text
	  Given I am on the cutomized Page with ReadOnly Element
	  And I remove the readonly attribute and enter date in to and from
	  
	@AcceptInsecureCerts
  Scenario: Remove readonly attribute and enter required text
	  Given I write snippet for Accepting Insecure Certificates for various browser via option and desired Capabilities
	  
	  
	@AuthenticationPopUp
	Scenario: Switch to Autentication Alert
		Given I visit the site and provide the authentication credentials
		
	 @ProgressBarYoutube
  Scenario: Clicking on progress bar of Youtube
  Given I visit the youtube for a video and click at "75" % length
      
  @PositionOfElementInLinuxGUIAbsent
  Scenario: Finding the position of Video Container on YouTube
  Given I visit the youtube to find position of video container from left and top
      
  @DraggingTheScrubber
  Scenario: Dragging the Scrubber along the progress bar
  Given I visit the youtube and Drag the scubber along the progress bar

	@AcceptingInsecureCertificates
	Scenario: Accept the insecure certificate
	Given I write snippet for Accepting Insecure Certificates for various browser via option and desired Capabilities
	
	@XMLhttpRequest
	Scenario: Build an XMLHttp request for making post or get call
	Given I write xmlHttp request to make post or get call and check page loading status

 
