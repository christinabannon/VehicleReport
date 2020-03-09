If you are getting this error when trying to run any JavaFX part of the application:

	"Error: JavaFX runtime components are missing, and are required to run this application"

you will need to pass this argument to the VM:

	--module-path "\path\to\javafx-sdk-13\lib" --add-modules javafx.controls,javafx.fxml

You can do that by 
	1. clicking Run -> Run Configurations, 
	2. Making a new configuration for the troublesome class, 
	3. Adding the above argument to "VM Arguments"
	4. Make sure the option "Use the -XstartOnFirstThread argument when launching with SWT" is not selected
	
	