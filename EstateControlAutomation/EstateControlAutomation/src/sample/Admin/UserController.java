package sample.Admin;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class UserController {
    public ArrayList<House> houseList;
    public Alert alert;

    public ComboBox<String> citySelectorCombo;
    public TextField villageText;
    public TextArea adressTextArea;
    public TextField volumeText;
    public TextField roomCountText;
    public ComboBox<String> yearCombo;
    public ComboBox<String> typeCombo;
    public TextField priceText;
    public ComboBox<String> heatingCombo;
    public Button saveButton;
    public Button imageButton;
    public ImageView postImage;
    public Image image;
    public String imagePath;



    public TableColumn<House,String> yearColumn;
    public TableColumn<House,String> heatupColumn;
    public TableColumn<House,String> roomColumn;
    public TableColumn<House,String> volumeColumn;
    public TableColumn<House,String> typeColumn;
    public TableColumn<House,String> villageColumn;
    public TableColumn<House,String> cityColumn;
    public TableColumn<House,String> priceColumn;
    public TableColumn<House,Image> imageColumn;
    public ComboBox<String> city2Combo;
    public Button queryButton;



    public TableColumn<House,String> year2Column;
    public TableColumn<House,String> heat2Column;
    public TableColumn<House,String> room2Column;
    public TableColumn<House,String> volume2Column;
    public TableColumn<House,String> type2Column;
    public TableColumn<House,String> village2Column;
    public TableColumn<House,String> city2Column;
    public TableColumn<House,String> price2Column;
    public TableColumn<House,Image> image2Column;
    public Button deleteButton;
    public TableView<House> queryTable;
    public TableView<House> listTable;
    public Button clearFilterButton;


    Connection myConn;
    Statement myStat;
    ResultSet myRs;
    PreparedStatement myPStat;
    //İlanımızı kayddetmek için oluşturduğumuz savePost fonksiyonu fieldlardan veriyi alıp
    //house nesnesi oluşturuyor ve nesneyi houseSaver fonksiyonu ile veritabanına ekliyor
    @FXML
    public void savePost() throws SQLException, IOException {
        String year = yearCombo.getSelectionModel().getSelectedItem();
        String heat = heatingCombo.getSelectionModel().getSelectedItem();
        String room = roomCountText.getText();
        String volume = volumeText.getText();
        String type = typeCombo.getSelectionModel().getSelectedItem();
        String village = villageText.getText();
        String city = citySelectorCombo.getSelectionModel().getSelectedItem();
        String price = priceText.getText();
        House house = new House(year,heat,room,volume,type,village,city,price,image);
        house.setImagePath(imagePath);
        houseSaver(house);
        houseList.add(house);
        clearFields();
    }
    //kodumuzu temiz tutabilmek adına resim ekleme işlemini imageButton fonksiyonu ile yapıyoruz
    @FXML
    public void imageButtonAction() {
        FileChooser fileChooser = new FileChooser();

        // Sadece resim dosyalarını göster
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Resim Dosyaları", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Dosya seçme penceresini aç
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Seçilen dosyayı ImageView'e yükle
            imagePath=selectedFile.getPath();
            image = new Image(selectedFile.toURI().toString());
            postImage.setImage(image);
        }
    }
    //filtreleme işlemini yaptığımız fonksiyonumuz ilanları şehirlere göre listeliyor
    @FXML
    public void queryButtonAction(){
        ArrayList<House> filteredList = new ArrayList<>();
        String selectedCity = city2Combo.getSelectionModel().getSelectedItem();
        for(House house:houseList){
            if(house.getCity().equals(selectedCity)){
                filteredList.add(house);
            }
        }
        queryTable.getItems().clear();
        if(filteredList.size()>0){

            for(House house: filteredList){
                queryTable.getItems().add(house);
            }
        }

    }
    //bu fonksiyon filtreleri kaldırıyor
    @FXML
    public void clearButtonAction(){
        queryTable.getItems().clear();
        listTable.getItems().clear();
        for (House house:houseList){
            queryTable.getItems().add(house);
            listTable.getItems().add(house);
        }
    }
    //tableviewdan seçili ilanı silen fonksiyonumuz
    @FXML
    public void deleteButtonAction() throws SQLException {
        if(!houseList.isEmpty() || !listTable.getSelectionModel().getSelectedItems().isEmpty()){
        House deletedHouse = this.deleteSelectedRow();
        try {
            // Establish the database connection
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "estateadmin",       "admin123+");

            // Create the DELETE query
            String query = "DELETE FROM houses WHERE city = ? AND village = ? AND type = ?";

            // Prepare the statement with the query
            myPStat = myConn.prepareStatement(query);

            // Set the parameter values
            myPStat.setString(1, deletedHouse.getCity());
            myPStat.setString(2, deletedHouse.getVillage());
            myPStat.setString(3, deletedHouse.getType());

            // Execute the statement
            int rowsAffected = myPStat.executeUpdate();

            if (rowsAffected > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("İlan Başarı ile silindi!");
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // Close the statement and connection
            if (myPStat != null) myPStat.close();
            if (myConn != null) {
                myConn.close();
            }
        }}
    else{
    alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("İlan yok o yüzden silinemez!!!");
    alert.showAndWait();
        }
    }
    @FXML
    public void initialize() throws SQLException {

        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","estateadmin","admin123+");
        myStat = myConn.createStatement();
        myRs = myStat.executeQuery("SELECT * FROM cities");
        while (myRs.next()){
            citySelectorCombo.getItems().add(myRs.getString("city_name"));
            city2Combo.getItems().add(myRs.getString("city_name"));
        }
        heatingCombo.getItems().add("Kombi");
        heatingCombo.getItems().add("Soba");
        heatingCombo.getItems().add("Kalorifer");

        yearCombo.getItems().add("0-5");
        yearCombo.getItems().add("6-15");
        yearCombo.getItems().add("16-25");
        yearCombo.getItems().add("26+");

        typeCombo.getItems().add("Satılık");
        typeCombo.getItems().add("Kiralık");

        houseList = new ArrayList<>();

        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        heatupColumn.setCellValueFactory(new PropertyValueFactory<>("heat"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        villageColumn.setCellValueFactory(new PropertyValueFactory<>("village"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        imageColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getImage()));
        imageColumn.setCellFactory(column -> {
            return new TableCell<House, Image>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item);
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);
                        setGraphic(imageView);
                    }
                }
            };
        });



        year2Column.setCellValueFactory(new PropertyValueFactory<>("year"));
        heat2Column.setCellValueFactory(new PropertyValueFactory<>("heat"));
        room2Column.setCellValueFactory(new PropertyValueFactory<>("room"));
        volume2Column.setCellValueFactory(new PropertyValueFactory<>("volume"));
        type2Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        village2Column.setCellValueFactory(new PropertyValueFactory<>("village"));
        city2Column.setCellValueFactory(new PropertyValueFactory<>("city"));
        price2Column.setCellValueFactory(new PropertyValueFactory<>("price"));

        image2Column.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getImage()));
        image2Column.setCellFactory(column -> {
            return new TableCell<House, Image>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item);
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);
                        setGraphic(imageView);
                    }
                }
            };
        });




        myRs = myStat.executeQuery("SELECT * FROM houses");
        while (myRs.next()) {
            House house = new House(".",".",".",".",".",".",".",".",null);
            house.setYear(myRs.getString("year"));
            house.setHeat(myRs.getString("heat"));
            house.setRoom(myRs.getString("room"));
            house.setVolume(myRs.getString("volume"));
            house.setType(myRs.getString("type"));
            house.setVillage(myRs.getString("village"));
            house.setCity(myRs.getString("city"));
            house.setPrice(myRs.getString("price"));

            // Image column için ImageView oluştur ve Image nesnesini set et
            Image image = new Image(myRs.getBinaryStream("image"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            house.setImage(imageView.getImage());
            houseList.add(house);
            // House nesnesini TableView'e ekle
            queryTable.getItems().add(house);
            listTable.getItems().add(house);
        }


    }


    //ilanları veritabanına işleyen fonksiyonumuz
    public void houseSaver(House house) throws SQLException {
        String query = "INSERT INTO houses (year, heat, room, volume, type, village, city, price, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "estateadmin", "admin123+");

        try (PreparedStatement statement = myConn.prepareStatement(query)) {
            statement.setString(1, house.getYear());
            statement.setString(2, house.getHeat());
            statement.setString(3, house.getRoom());
            statement.setString(4, house.getVolume());
            statement.setString(5, house.getType());
            statement.setString(6, house.getVillage());
            statement.setString(7, house.getCity());
            statement.setString(8, house.getPrice());

            // Load image from file
            try (InputStream imageStream = new FileInputStream(house.getImagePath())) {

                // Set the image parameter
                statement.setBinaryStream(9, imageStream);

                // Execute the update
                statement.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Ev İlanlar Arasına Eklendi!");
                alert.showAndWait();

            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Boş bırakılan girdi var!");
            alert.showAndWait();
        }
    }
    //bu fonksiyon hem tableviewları hem de elimizde tüm ilanların bulunduğu listeyi temizleyen yani seçtiğimiz
    //ilanı çıkartan fonksiyon bu fonksiyonu deleteButtonAction fonksiyonu içinde kullanıyoruz
    public House deleteSelectedRow(){
        House selectedHouse = listTable.getSelectionModel().getSelectedItem();
        if(houseList.size()>1) {
            int index = houseList.indexOf(selectedHouse);
            houseList.remove(index);
            listTable.getItems().remove(index);
            return selectedHouse;
        }
        else{
            houseList.clear();
            listTable.getItems().clear();
            return selectedHouse;
        }
    }
    //bu fonksiyon ilan girdisi sonrası veri alanlarını temizleyen fonksiyon savePost içinde kullanıyoruz
    public void clearFields(){
        citySelectorCombo.getSelectionModel().select(0);
        villageText.setText("");
        adressTextArea.setText("");
        volumeText.setText("");
        roomCountText.setText("");
        heatingCombo.getSelectionModel().select(0);
        yearCombo.getSelectionModel().select(0);
        typeCombo.getSelectionModel().select(0);
        priceText.setText("");
    }
}