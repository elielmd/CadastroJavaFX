package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	// ... AP�S AS OUTRAS VARI�VEIS ...

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
		personData.add(new Person("Darth", "Vader"));
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

	    // Set the application icon.
	    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

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

	        // D� ao controlador acesso � the main app.
	        PersonOverviewController controller = loader.getController();
	        controller.setMainApp(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada. Se o usu�rio clicar
	 * OK, as mudan�as s�o salvasno objeto pessoa fornecido e retorna true.
	 * 
	 * @param person O objeto pessoa a ser editado
	 * @return true Se o usu�rio clicou OK,  caso contr�rio false.
	 */
	public boolean showPersonEditDialog(Person person) {
	    try {
	        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Cria o palco dialogStage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Define a pessoa no controller.
	        PersonEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPerson(person);

	        // Mostra a janela e espera at� o usu�rio fechar.
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
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