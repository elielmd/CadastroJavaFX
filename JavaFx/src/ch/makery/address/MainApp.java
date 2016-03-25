package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	// ... APÓS AS OUTRAS VARIÁVEIS ...

	/**
	 * Os dados como uma observable list de Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Construtor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Eliel", "Marques"));
		personData.add(new Person("Darth", "Vader:"));
		personData.add(new Person("Anakin", "Skywalker"));
		personData.add(new Person("Harry", "Potter"));
		personData.add(new Person("Rocky", "Balboa"));
		personData.add(new Person("Hannibal", "Lecter"));
		personData.add(new Person("Jack", "Sparrow"));
		personData.add(new Person("Indiana", "Jones"));
		personData.add(new Person("Emmett", "Brown"));
	
		/**
		 * Mostra a person overview dentro do root layout.
		 */
	}

	/**
	 * Retorna os dados como uma observable list de Persons.
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	// ... O RESTANTE DA CLASSE ...

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();

		showPersonOverview();
	}

	/**
	 * Inicializa o root layout (layout base).
	 */
	public void initRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostra a scene (cena) contendo oroot layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o person overview dentro do root layout.
	 */
	/**
	 * Mostra a person overview dentro do root layout.
	 */
	public void showPersonOverview() {
	    try {
	        // Carrega a person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
	        AnchorPane personOverview = (AnchorPane) loader.load();

	        // Define a person overview no centro do root layout.
	        rootLayout.setCenter(personOverview);

	        // Dá ao controlador acesso à the main app.
	        PersonOverviewController controller = loader.getController();
	        controller.setMainApp(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Retorna o palco principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Mostra a person overview dentro do root layout.
	 */
}