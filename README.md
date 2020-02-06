# equal-experts-tech-test

# Automated Checks
To run the UI tests create a JUnit configuration for the below classes
* CreateUIBooking
* DeleteUIBooking

Please Note: that you will need to provide one of the two environment variables to determine the browser you wish to 
execute the tests in:    
* browserName=firefox
* browserName=chrome

To run the API tests create a JUnit configuration for the below classes
* CreateAPIBooking
* DeleteAPIBooking

# Manual Testing

In the "src/test/resources/Manual Test Documentation" directory you will find the manual testing documentation.
This consists of 2 types of documents:
* Mind map.xmind
* Test session.txt

The mind maps require the xmind application to view (https://www.xmind.net/download/xmind8) and contain the charters and
missions that make up the test coverage of each area of the application. Each charter consists of one or more missions 
and each mission consists of one or more test sessions.

The test sessions detail the actions performed to test the application around a specific mission. In addition it
documents any potential defects, questions and items to discuss with the team.

In the mind maps I have marked the test ideas and missions that are covered during the completed test sessions as 
follows:
* A tick for passed.
* A cross for failed.
* An attention symbol for things that need further discussion.

This will enable the team to get a clear view of the testing that has been conducted and its status.     


