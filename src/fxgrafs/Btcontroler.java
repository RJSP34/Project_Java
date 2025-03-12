package fxgrafs;

import Classimports.DirectedEdge_copy;
import Classimports.EdgeWeightedDigraph_copy;
import Classimports.RedBlackBST_copy;
import Project.Cache;
import Project.Geo_Cache;
import edu.princeton.cs.algs4.ST;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static Project.Start.*;

public class Btcontroler extends Application {

    private static final String path_CAche="src/datapacks/Cache";
    private static final String path_pos="src/datapacks/Position.txt";
    private static final RedBlackBST_copy<Integer, Cache> st=new RedBlackBST_copy<>();
    private static  RedBlackBST_copy<Integer, Geo_Cache> Geo=new RedBlackBST_copy<>();
    private  EdgeWeightedDigraph_copy speed;
  private static ST<Integer, Integer> Symbel=new ST<>();

    public TableView<Geo_Cache> GeoCacheTable=new TableView<>();
    public TableColumn<Geo_Cache,String> IdCol= new TableColumn<Geo_Cache,String>("id");
    public TableColumn<Geo_Cache,String> TypeCol= new TableColumn<Geo_Cache,String>("type");
    public TableColumn<Geo_Cache,String> LatCol= new TableColumn<Geo_Cache,String>("latitude");
    public TableColumn<Geo_Cache,String> LonCol= new TableColumn<Geo_Cache,String>("longitude");
    public TableColumn <Geo_Cache,String>RegionCol=new TableColumn<Geo_Cache,String>("regiao");
    public TextField IDField;
    public TextField TypeField;
    public TextField LatField;
    public TextField LongField;
    public TextField RegiaoField1;
    protected static final int GROUP_MARGIN = 20;

    public Group graphGroup;
    String path_OBJ_BIN="src/datapacks/PositionBin_OBJ";
    String path_OBJ_BIN_SAVE="src/datapacks/Save_Geo+_Cache+_speed";
    String path_TEXT_ITI="src/datapacks/Position.txt";
    private double radius = 20;
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxgra.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Graph Creator");
        primaryStage.setScene(scene);
        primaryStage.show();
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LatCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        LatCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LonCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        LonCol.setCellFactory(TextFieldTableCell.forTableColumn());
        RegionCol.setCellValueFactory(new PropertyValueFactory<>("regiao"));
        RegionCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    public static void main(String[] args) {
        Read_Cache(path_CAche,st);
        Convert_cache_geo_with_ST(st,Geo);
        launch(args);
    }


    public void handleReadFileAction(ActionEvent actionEvent) {
        if (GeoCacheTable.getItems().isEmpty()){
            GeoCacheTable.getItems().clear();
        }
        speed=new EdgeWeightedDigraph_copy(st.size());
        Geo=new RedBlackBST_copy<>();
        Symbel=Read_conce_tx(path_pos,st,speed);
        Convert_cache_geo_with_ST(st,Geo);
        ArrayList<Geo_Cache> aux=new ArrayList<>();
        for (int key:Geo.keys()) {
            aux.add(Geo.get(key));

        }
      GeoCacheTable.getItems().addAll(aux);

    }

    public int Check( ST<Integer, Integer> Symbel,int key){

        for (int key2:Symbel.keys()) {


        }
        return -1;
    }

    public void handleReadBinFileAction(ActionEvent actionEvent) {
        if (GeoCacheTable.getItems().isEmpty()){
            GeoCacheTable.getItems().clear();
        }
        speed=new EdgeWeightedDigraph_copy(st.size());
        Geo=new RedBlackBST_copy<>();
        Symbel= Read_conce_Bin(path_OBJ_BIN,  Geo,speed);
        ArrayList<Geo_Cache> aux=new ArrayList<>();
        for (int key:Geo.keys()) {
            aux.add(Geo.get(key));
            GeoCacheTable.getItems().add(Geo.get(key));
        }

    }
    public void handleSaveFileAction(ActionEvent actionEvent) {






    }

    public void handleSaveBinFileAction(ActionEvent actionEvent) {
        SAVE_conce_Bin(path_OBJ_BIN_SAVE,Geo,speed);
        System.out.println("Save Complete\n");

    }

    public void handleExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void handleEditIDAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void handleEditTypeAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void handleEditLatAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void handleEditLONGAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void handleEditRegionAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void handleAddGEOCACHEAction(ActionEvent actionEvent) {
        if (Geo.get(Integer.parseInt(IDField.getText()))==null||Symbel.get(Integer.parseInt(IDField.getText()))!=null){
            System.out.println("DOesnt existe or already existes");
            return;
        }

    }
    public int Find_id(ST<Integer, Integer> sy,int vertix) {
        for (int key:sy.keys()) {
            if (sy.get(key)==vertix){
                return key;
            }

        }
return -1;
    }
    public double[] MAX_MIN() {
        double [] aux=new double[4];
        double max=-1000000;
        double max2=-100000;
        double min=Double.MAX_VALUE;
        double min2=Double.MAX_VALUE;
        for (int key:Geo.keys()) {
            Geo_Cache a=Geo.get(key);
            if (Geo.get(key).getGps().getLatitude()>max){
                max=Geo.get(key).getGps().getLatitude();
            }
            double j=Geo.get(key).getGps().getLongitude();
            if (j>max2){
                max2=Geo.get(key).getGps().getLongitude();
            }
            if (Geo.get(key).getGps().getLatitude()<min){
                min=Geo.get(key).getGps().getLatitude();
            }
            if (Geo.get(key).getGps().getLongitude()<min2){
                min2=Geo.get(key).getGps().getLongitude();
            }
        }
        aux[0]=max;
        aux[1]=max2;
        aux[2]=min;
        aux[3]=min2;
        return aux;
    }

    public void handleEdgesButtonAction(ActionEvent actionEvent) {
        int[][] aux =new int[speed.V()][2] ;
        double []aux2=MAX_MIN();
        for (int i = 0; i <speed.V(); i++ ) {

            Random r = new Random();
aux[i][0]= (int) (GROUP_MARGIN + r.nextDouble() * (600-GROUP_MARGIN*2));
aux[i][1]= (int) (GROUP_MARGIN + r.nextDouble() * (371-GROUP_MARGIN*2));
            //aux[i][0]=(int) ((((Math.abs(aux2[0])-Geo.get(Find_id(Symbel,i)).getGps().getLatitude()))*600)/(Math.abs(aux2[0])-Math.abs(aux2[2])));
            //aux[i][1]=(int) (((Math.abs(aux2[1])-Geo.get(Find_id(Symbel,i)).getGps().getLongitude())*341)/(Math.abs(aux2[1])+Math.abs(aux2[3])));
        }
        for(int i=0; i <speed.V(); i++){
            Random r = new Random();
            Circle c = new Circle((int) (GROUP_MARGIN + r.nextDouble() * (600-GROUP_MARGIN*2)),(int) (GROUP_MARGIN + r.nextDouble() * (600-GROUP_MARGIN*2)),radius );
            c.setFill(Color.WHITE);

            StackPane stack = new StackPane();
            stack.setLayoutX(aux[i][0]-radius);
            stack.setLayoutY(aux[i][1]-radius);
            stack.getChildren().addAll(c, new Text(i + ""));

            graphGroup.getChildren().add(stack);


            if(speed.E() > 0){

                for(DirectedEdge_copy adj: speed.adj(i)) {

                    Line line = new Line(aux[adj.to()][0],aux[adj.to()][1], aux[adj.from()][0], aux[adj.from()][1]);
                    graphGroup.getChildren().add(line);
                }
            }
    }



    }
}
