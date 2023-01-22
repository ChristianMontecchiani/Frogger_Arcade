package myGame.View;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

import static myGame.View.MenuScene.IMAGE_PATH;


public class RankingTable {


    static String fileName = IMAGE_PATH+"RankingTable.csv";
    static String charset = "UTF-8";
    static PlayerData playerData=new PlayerData(); //lista dei punteggi salvati e da salvare
    static int numScores=0;//indice utile per capire quanti punteggi sono presenti
    static int numClick=0;//permette l'inizializzazione della tabella una sola volta
    public static boolean enableAddButton; //serve per impedire che si aggiungano risultati nei momenti sbagliati

    static List<Integer> scoreRecords= new ArrayList<>();//copia dei punteggi dei player


    private static TextField nameInput;
    private static TableView<Player> table;


    public static void scoreRecord() throws IOException {

        Stage scoreStage = new Stage();
        scoreStage.setResizable(false);
        scoreStage.setTitle("Ranking");
        scoreStage.initModality(Modality.APPLICATION_MODAL);

        Text score = new Text("\t \t TOP 10:\n");
        nameInput = new TextField();
        nameInput.setPromptText("Player NAME");

        //caricamento ranking
        if(numClick<1) {
            LinkedList<String[]> lstRows = FileManagment.read(fileName, charset);
            for (String[] sArr : lstRows) {
                playerData.add(new Player(sArr[0], Integer.parseInt(sArr[1])));
                scoreRecords.add(Integer.parseInt(sArr[1]));
            }
        }
        numScores = scoreRecords.size();
        //riordina i punteggi dal più alto al più basso
        scoreRecords.sort(Collections.reverseOrder());



        Button addButton = new Button("Add");
        addButton.setDisable(!enableAddButton);
        nameInput.setDisable(!enableAddButton);

        if (numScores >9 && scoreRecords.get(9) > GameScene.points) {
            addButton.setDisable(true);
            nameInput.setDisable(true);
        }

        addButton.setOnAction(e -> {
            try {
                addButtonClicked(addButton);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button resumeButton = new Button("Resume");


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, addButton, resumeButton);

        //Button


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        //Colonna name
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Colonna score
        TableColumn<Player, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        //inizializzazione tabella
        if (numClick < 1) {
            table = new TableView<>();
            table.setItems(getPlayer(playerData));

            //noinspection unchecked
            table.getColumns().addAll(nameColumn, scoreColumn);
        }

        gridPane.add(hBox, 0, 2);

        gridPane.add(score, 0, 0);
        gridPane.add(table, 0, 1);


        Scene scene = new Scene(gridPane, 500, 500);
        scoreStage.setScene(scene);
        scoreStage.show();

        resumeButton.setOnAction(e -> {
            numClick++;
            MenuScene.autoPlay = true;
            if(GameScene.FROGGER_LIVES==0 || GameScene.burrowCounter==5) {//reset del game
                MenuScene.mediaPlayer.pause();
                ViewVariables.sceneManager=0;
                MenuScene.mediaPlayer.play();

            }else {
                MenuScene.mediaPlayer.play();
            }
            scoreStage.close();
        });


        scoreStage.setOnCloseRequest(we ->  {
            numClick++;
            MenuScene.autoPlay = true;
            if(GameScene.FROGGER_LIVES==0 || GameScene.burrowCounter==5) {//reset del game
                MenuScene.mediaPlayer.pause();
                ViewVariables.sceneManager=0;
                MenuScene.mediaPlayer.play();

            }else {
                MenuScene.mediaPlayer.play();
            }
            scoreStage.close();
        });

    }

    public static void addButtonClicked(Button button) throws IOException {

        Player player = new Player();
        String name=nameInput.getText();
        if(!name.equals("") && !name.contains(";")) {
            player.setName(name);
            player.setScore(GameScene.points);
            scoreRecords.add(GameScene.points);
            playerData.add(player);
            Collections.sort(scoreRecords, Collections.reverseOrder());
            playerData = sortPlayers(playerData);
            if (scoreRecords.size() > 10) {
                playerData.remove(playerData.size()-1);
                scoreRecords.remove(scoreRecords.size()-1);
            }
            table.getItems().clear();
            table.setItems(getPlayer(playerData));
            FileManagment.write(fileName, charset, playerData.asListOfStringArray());
            nameInput.clear();

            enableAddButton = false;
            button.setDisable(true);
            nameInput.setDisable(true);
        }else
            System.out.println("non valido");


    }


    public static ObservableList<Player> getPlayer(PlayerData playerLst){

        PlayerData sortedPlayers=sortPlayers(playerLst);
        ObservableList<Player> players = FXCollections.observableArrayList();
        LinkedList<Player>  allPlayers= sortedPlayers.getListOfPlayers();
        players.addAll(allPlayers);

        return players;

    }
    public static PlayerData sortPlayers(PlayerData allplayers){
        Player player;
        for(int i=0;i<scoreRecords.size();i++)
            for(int j=0;j<scoreRecords.size();j++)
                if(scoreRecords.get(i)==allplayers.get(j).getScore()) {
                    player = allplayers.get(j);
                    allplayers.set(j, allplayers.get(i));
                    allplayers.set(i, player);
                }
        return allplayers;
    }



}

