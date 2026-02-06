//*****************************************************************                                               *
//* File: UserInfoApp.java                                          *
//* Author: Lucy Miller                                           *
//* Last Update Date: 6/25/25                                     *
//* Version 1                                                     *
//*****************************************************************

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UserInfoApp extends Application {
	
	private TextField nameField;
	private ComboBox<String> majorBox;
	private ToggleGroup genderGroup;
	private RadioButton gender1;
	private RadioButton gender2;
	private RadioButton gender3;
	private RadioButton gender4;
	private CheckBox confirm;
	private Label finalResult = new Label();
	
	
	// *****************************************************************
	// * Function Name: start                                          *
	// * Description: Sets up GUI nodes, scene, and stage to display   *
	// *               the full application                            *
	// * Parameter Description: Stage primaryStage - stage upon which  *
	// *                          the scenes and nodes are placed      *
	// * Date: 6/25/25                                                 *
	// * Author: Lucy Miller                                           *
	// * References: JavaFX1 and JavaFX2 videos on Canvas              *
	// *****************************************************************
	@Override
	public void start(Stage primaryStage) {
		//Set pop-up title
		primaryStage.setTitle("FBI Application");
		
		//Set up VBox for full form layout
		VBox mainFormat = new VBox(20);
		
		//Adding padding to left and top side for aesthetics
		mainFormat.setPadding(new Insets(10, 0, 0, 20));
		Label nameAndMajor = new Label("Enter your name and select a major: ");
		initControls();
		
		//Set up HBox for name and major layout
		HBox inputPart1 = new HBox();
		inputPart1.getChildren().addAll(new Label("Name: "), nameField, new Label("Major: "), majorBox);
		//Add extra spacing between the name and major fields
		inputPart1.setSpacing(10);
		
		//Set up HBox for gender chooser layout
		Label genderPick = new Label("Select your gender: ");
		HBox inputPart2 = new HBox();
		inputPart2.getChildren().addAll(gender1, gender2, gender3, gender4);
		//Add extra spacing between gender radio buttons
		inputPart2.setSpacing(5);
		
		//Creating submit button with CSS-style properties
		Button submitButton = new Button("Submit");
		submitButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
		
		//Setting up Button to pop up resulting Label on press
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			// *****************************************************************
			// * Function Name: handle (EventHandler)                          *
			// * Description: Sets up submit button to show a finalResult      *
			// *                label when clicked                             *
			// * Parameter Description: ActionEvent actionEvent - event that   *
			// *                          occurs (mouse click)                 *
			// * Date: 6/25/25                                                 *
			// * Author: Lucy Miller                                           *
			// * References: JavaFX1 and JavaFX2 canvas videos                 *
			// *****************************************************************
			public void handle(ActionEvent actionEvent) {
				String name = nameField.getText().trim();
				String major = majorBox.getValue();
				
				//Getting the gender from the Radio buttons
				if (genderGroup.getSelectedToggle() == null) {
					finalResult.setText("Please enter all information");
				}
				else {
					//Receive entered gender radio button
					Toggle selected = genderGroup.getSelectedToggle();
					RadioButton selectedButton = (RadioButton) selected;
					String gender = selectedButton.getText();
				
					//Checks that information is fully filled out
					if(name.isEmpty() || majorBox == null) {
						finalResult.setText("Please enter all information");
					}
					//Checks if the confirm check box has been ticked
					else if(!confirm.isSelected()) {
						finalResult.setText("User agreement checkbox must be checked");
					}
					else {
						finalResult.setText("You are " + name + ", majoring in " + major +
								             ". You identify as " + gender + ".");
					}
				}
			}
		});
		
		//Putting all children elements in main layout VBox
		mainFormat.getChildren().addAll(nameAndMajor, inputPart1, genderPick, inputPart2, confirm, submitButton, finalResult);
		
		//Setting the scene and stage
		Scene scene = new Scene(mainFormat, 450, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// *****************************************************************
	// * Function Name: initControls                                   *
	// * Description: Initializes control nodes set up at start of the *
	// *                class.                                         *
	// * Parameter Description: none                                   *
	// * Date: 6/25/25                                                 *
	// * Author: Lucy Miller                                           *
	// * References: None                                              *
	// *****************************************************************
	private void initControls() {
		nameField = new TextField();
		nameField.setPromptText("Name");
		
		majorBox = new ComboBox<>();
		majorBox.getItems().addAll("Arts and Sciences", "Business", "Engineering", "Humanities", "Zoology", "Other");
		
		//Radio Button group setup
		genderGroup = new ToggleGroup();
		gender1 = new RadioButton("Male");
		gender2 = new RadioButton("Female");
		gender3 = new RadioButton("Non-Binary");
		gender4 = new RadioButton("Other");
		
		gender1.setToggleGroup(genderGroup);
		gender2.setToggleGroup(genderGroup);
		gender3.setToggleGroup(genderGroup);
		gender4.setToggleGroup(genderGroup);
		
		//Create check box and remove indeterminate case
		confirm = new CheckBox("I confirm all information is accurate");
		confirm.setIndeterminate(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
