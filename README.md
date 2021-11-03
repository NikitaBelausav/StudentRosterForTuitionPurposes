# StudentRosterForTuitionPurposes

This project was created by a team of two people I particularly worked on

# Back-End

-Set up the classes in an OOP mentality, meaning there is a general student class which is extended by residents and non-residents. Non-Residents are further extended by internationals and triState. This allows for inheritence, overriding, and polymorphism. This also helps not rewriting code and is more time-efficient.
-The student roster only accepts student objects, but you can add any type of student due to it being the super-class.
-Particularly inside of roster I worked on the add, remove, print, print in name order, print by last payment date, and calculate tuition methods. Also helped debug the rest of the roster class functions.
-Did the entirety of the Date class other than the initial testing as it's usually better for your partner to come up with tests for the code you wrote. I later helped with the testing as well to make sure nothing was missing in our black-box testing method.

# Front-End

-Created the main-view using scenebuilder in javaFX, mostly focusing on the roster functionalities in the first tab like add,remove,setting study abroad status, and print methods in the fourth tab and calculating tuition. My partner primarily worked on setting financial aid, along with payments. 
-Carefully programmed the controller to make sure that the input would never allow for an exception, and to print to the outputfield what the issue with their input is so the user can fix it. Only functionalities related to the choices made by the user are ever allowed to be used, for example to set a student for being able to study abroad the buttons are hidden until you select the international student residency button. 
