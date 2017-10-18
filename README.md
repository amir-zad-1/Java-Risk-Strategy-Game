# 6441-project

##SOEN 6441 Project conding standards

* `tmp` is name given to the local variables which are used for temporary purposes 6
* view package has view classes like WelcomeView, MapEditorView and GameView every view decoupled.
* Views are also decoupled from controllers by using dependecy injection.
* Class whose name starts with I is interface.
* `bootstrap.StartGame` is the class which bootstraps loads the entire program.
* variables always follows lowerCamelCase.
* controllers, views and models are decopuled, `bootstrap.StartGame` injects dependencies. 
