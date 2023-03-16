package com.example.ecomm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    // Login login=new Login();
    private final int width=700,height=400,headerLine=50;
    ProductList productList=new ProductList();
    Customer loggedInCustomer=null;
    ObservableList<Product> cartItemList= FXCollections.observableArrayList();

    Pane bodyPane;
    Button signInButton=new Button("Sign In");
    Button placeOrderButton=new Button("Place Order");
    Button signUpButton=new Button("Sign Up");
    Label welcomeLabel=new Label("Welcome Customer");
    private void addItemsToCart(Product product){
        if(cartItemList.contains(product)) return ;
        cartItemList.add(product);
//        System.out.println("Products in cart"+cartItemList.stream().count());
    }
    private GridPane headerBar(){
        GridPane header=new GridPane();

        TextField searchBar=new TextField();
        Button viewButton=new Button("View Products");
        Button cartButton=new Button("Cart");
        Button searchButton=new Button("Search");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchedWord=searchBar.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getSearched(searchedWord));
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.productsInCart(cartItemList));
            }
        });

        viewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getAllProducts());
            }
        });
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signupPage());
            }
        });
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        header.setHgap(10);
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(viewButton,3,0);
        header.add(signInButton,4,0);
        header.add(signUpButton,5,0);
        header.add(welcomeLabel,2,1);
        header.add(cartButton,6,0);
        return header;
    }

    private GridPane signupPage(){
        Label userLabel=new Label("UserName");
        Label passLabel=new Label("Password");
        Label emLabel=new Label("Email");
        Label phLabel=new Label("Phone Number");
        Label adLabel=new Label("Address");

        TextField userName=new TextField();
        userName.setPromptText("Enter User Name");

        PasswordField password= new PasswordField();
        password.setPromptText("Enter Password");

        TextField email=new TextField();
        email.setPromptText("Enter Email");

        TextField phno=new TextField();
        phno.setPromptText("Enter Your no");

        TextField address=new TextField();
        address.setPromptText("Enter Address");

        Button signButton=new Button("Sign Up");
        Label messageLabel=new Label("Sign Up");

        signButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user=userName.getText();
                String pass=password.getText();
                String em=email.getText();
                String phn=phno.getText();
                int ph= Integer.parseInt(phn);
                String ad=address.getText();
                boolean checkSuccess=SignUp.customerSignUp(user,pass,em,ph,ad);
                if(checkSuccess){
                    showDialogue("Successfully Created");
                }
                else{
                    showDialogue("Error");
                }
            }
        });
        GridPane signinPane=new GridPane();
        signinPane.setTranslateY(150);
        signinPane.setTranslateX(125);
        signinPane.setVgap(10);
        signinPane.setHgap(10);
        signinPane.add(userLabel,0,0);
        signinPane.add(userName,1,0);
        signinPane.add(passLabel,0,1);
        signinPane.add(password,1,1);
        signinPane.add(emLabel,0,2);
        signinPane.add(email,1,2);
        signinPane.add(phLabel,0,3);
        signinPane.add(phno,1,3);
        signinPane.add(adLabel,0,4);
        signinPane.add(address,1,4);
        signinPane.add(signButton,1,5);
        signinPane.add(messageLabel,0,5);

        return signinPane;
    }
    private GridPane loginPage(){

        Label userLabel=new Label("UserName");
        Label passLabel=new Label("Password");

        TextField userName=new TextField();
        userName.setPromptText("Enter User Name");

        PasswordField password= new PasswordField();
        password.setPromptText("Enter Password");

        Button loginButton=new Button("Login");
        Label messageLabel=new Label("Login-Message");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user= userName.getText();
                String pass=password.getText();
                loggedInCustomer=Login.customerLogin(user,pass);
                if(loggedInCustomer!=null){
                    messageLabel.setText("Login Successfull!!!");
                    welcomeLabel.setText("Welcome  "+loggedInCustomer.getName());
                }
                else{
                    messageLabel.setText("Login Failed!!!");
                }
            }
        });

        GridPane loginPane=new GridPane();
        loginPane.setTranslateY(150);
        loginPane.setTranslateX(125);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(userLabel,0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLabel,1,2);

        return loginPane;
    }

    public void showDialogue(String message){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }


    private GridPane footerBar(){
        Button buyNow=new Button("Buy Now");
        Button addToCart=new Button("Add To Cart");

        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                boolean orderStatus=false;
                if(product!=null && loggedInCustomer!=null){
                    orderStatus=Order.placeOrder(loggedInCustomer,product);
                }
                if(orderStatus==true){
                    showDialogue("Order Successfull");
                }
                else{
                    showDialogue("Order Failed");
                }
            }
        });

        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProduct();
                addItemsToCart(product);
            }
        });
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int orderCount=0;
                if(!cartItemList.isEmpty() && loggedInCustomer!=null){
                    orderCount=Order.placeOrderMultipleProducts(cartItemList,loggedInCustomer);
                }
                if(orderCount>0){
                    showDialogue("Orders for "+orderCount+" products Placed Successfully");
                }
                else{
                    showDialogue("Order Failed");
                }
            }
        });

        GridPane footer=new GridPane();
        footer.setHgap(10);
        footer.setTranslateY(headerLine+height);
        footer.add(buyNow,0,0);
        footer.add(addToCart,1,0);
        footer.add(placeOrderButton,2,0);

        return footer;
    }
    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width,height+2*headerLine);

        bodyPane =new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);

        bodyPane.getChildren().add(loginPage());
        root.getChildren().addAll(headerBar()
          //      , loginPage()
        //        , productList.getAllProducts()
                , bodyPane
                , footerBar()
        );
        return root;
    }

    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}