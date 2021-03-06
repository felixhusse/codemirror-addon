# CodeMirror Add-on for Vaadin 7

CodeMirror is an UI component add-on for Vaadin 7 based on the CodeMirror Java Script library ([CodeMirror](http://codemirror.net)). CodeMirror version in use is 5.11.

# Features/Highlights
- setCode & getCode (wow)
- nearly all CodeMirror Themes included
- F11 while focus on CodeMirror will expand to fullscreen (Fullscreen Addon)
- Search/Replace Support
- TernJS integration


## Building and running demo

git clone <url of the CodeMirror repository>
mvn clean install
cd demo
mvn jetty:run-war

To see the demo, navigate to http://localhost:8080/

## Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

### Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine.

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for NetworkGraph-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your NetworkGraph-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the NetworkGraph-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/NetworkGraph-demo/ to see the application.

### Debugging client-side

The most common way of debugging and making changes to the client-side code is dev-mode. To create debug configuration for it, open NetworkGraph-demo project properties and click "Create Development Mode Launch" button on the Vaadin tab. Right-click newly added "GWT development mode for NetworkGraph-demo.launch" and choose Debug As > Debug Configurations... Open up Classpath tab for the development mode configuration and choose User Entries. Click Advanced... and select Add Folders. Choose Java and Resources under NetworkGraph/src/main and click ok. Now you are ready to start debugging the client-side code by clicking debug. Click Launch Default Browser button in the GWT Development Mode in the launched application. Now you can modify and breakpoints to client-side classes and see changes by reloading the web page.

Another way of debugging client-side is superdev mode. To enable it, uncomment devModeRedirectEnabled line from the end of DemoWidgetSet.gwt.xml located under NetworkGraph-demo resources folder and compile the widgetset once by running vaadin:compile Maven target for NetworkGraph-demo. Refresh NetworkGraph-demo project resources by right clicking the project and choosing Refresh. Click "Create SuperDevMode Launch" button on the Vaadin tab of the NetworkGraph-demo project properties panel to create superder mode code server launch configuration and modify the class path as instructed above. After starting the code server by running SuperDevMode launch as Java application, you can navigate to http://localhost:8080/NetworkGraph-demo/?superdevmode. Now all code changes you do to your client side will get compiled as soon as you reload the web page. You can also access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings.


## Release notes

### Version 1.2.1
- update of Codemirror 4.9 to 5.11
- update of Vaadin 7.1 to 7.6

### Version 1.1.0
- TernJS integrated (#4)
- Search/Replace integrate (#5)
- CodeMirror updated to version 4.8 (#6)

### Version 1.0.0
- Height Issue fixed (#2 & #3)
- switched to version 1.0.0 as it is now in production use in our company

### Version 0.9.2
- refactored project layout -> removed codemirror-addon from parent, had trouble adding to Vaadin Directory

### Version 0.9.1
- onBlur event added

## Roadmap
- integrating [LINT addon](http://codemirror.net/doc/manual.html#addon_lint)

## Issue tracking

The issues for this add-on are tracked on [github.com](https://github.com/felixhusse/codemirror-addon/issues). All bug reports and feature requests are appreciated.

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author
([CodeMirror](http://codemirror.net)) is distributed under MIT License by Marijn Haverbeke <marijnh@gmail.com> and others. For license terms, see CODEMIRROR-LICENSE
Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.



