package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controlador.ControladorAcercaDe;
import edu.fiuba.algo3.controlador.ControladorJugar;
import edu.fiuba.algo3.controlador.ControladorSalir;
import edu.fiuba.algo3.modelo.Entidades.Juego;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class VistaCargaJugadores extends StackPane {

    public VistaCargaJugadores(Stage stagePrincipal, Juego juego){
        this.getStylesheets().add("File:src/resources/estilo/cargaJugadores.css");
        this.getStyleClass().add("fondoLogin");

        AudioClip sonido = new AudioClip("File:src/resources/sonidos/SND_KahootLobby.mp3");
        sonido.play();

        ImageView logoView = new ImageView(new Image("File:src/resources/imagenes/IMG_algohoot_logo.png"));
        logoView.setFitHeight(100);
        logoView.setFitWidth(350);
        this.getChildren().addAll(logoView);

        Label notificacionMensaje = new Label();
        notificacionMensaje.getStyleClass().add("notificacionMensaje");

        TextField NombreJugador = new TextField("");
        NombreJugador.setPromptText("Nombre jugador 1");
        NombreJugador.getStyleClass().add("ingresoNombreJugadores");

        TextField NombreJugador2 = new TextField("");
        NombreJugador2.setPromptText("Nombre jugador 2");
        NombreJugador2.getStyleClass().add("ingresoNombreJugadores");

        Button botonEntrar = new Button("Comenzar");
        botonEntrar.getStyleClass().addAll("botonesLogin");
        botonEntrar.setOnAction(new ControladorJugar(stagePrincipal,NombreJugador, NombreJugador2, juego, notificacionMensaje, sonido));

        Button botonSalir = new Button("Salir");
        botonSalir.getStyleClass().add("botonesLogin");
        botonSalir.setOnAction(new ControladorSalir());

        VBox opcionesMenu = new VBox(10, NombreJugador, NombreJugador2, botonEntrar, botonSalir);
        opcionesMenu.setMaxSize(300, 300);
        opcionesMenu.setAlignment(Pos.CENTER);

        Button botonAcercaDe = new Button("Acerca de");
        botonAcercaDe.getStyleClass().add("botonesInferiores");
        botonAcercaDe.setOnAction(new ControladorAcercaDe());

        VBox menuLogoYOpciones = new VBox(50, logoView, opcionesMenu);
        menuLogoYOpciones.setAlignment(Pos.CENTER);

        VBox menuSuperiorConInferior = new VBox(10, menuLogoYOpciones, botonAcercaDe,notificacionMensaje);
        menuSuperiorConInferior.setAlignment(Pos.CENTER);

        this.getChildren().addAll(menuSuperiorConInferior);
    }
}
