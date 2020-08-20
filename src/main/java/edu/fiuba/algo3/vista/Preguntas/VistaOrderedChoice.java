package edu.fiuba.algo3.vista.Preguntas;

import edu.fiuba.algo3.controlador.ControladorReset;
import edu.fiuba.algo3.modelo.Entidades.Jugador;
import edu.fiuba.algo3.modelo.Entidades.Opciones.Opcion;
import edu.fiuba.algo3.modelo.Entidades.Opciones.OpcionOrdenada;
import edu.fiuba.algo3.modelo.Entidades.Preguntas.MultipleChoice;
import edu.fiuba.algo3.modelo.Entidades.Preguntas.OrderedChoice;
import edu.fiuba.algo3.modelo.Entidades.Respuestas.Respuesta;
import edu.fiuba.algo3.modelo.Entidades.Respuestas.RespuestaOrdenada;
import edu.fiuba.algo3.vista.Opciones.VistaOpcionData;
import edu.fiuba.algo3.vista.Opciones.VistaOpcionOrdenada;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class VistaOrderedChoice extends VistaPregunta{

    private HashMap<Integer, VistaOpcionData> opcionesInfo = new HashMap<Integer,VistaOpcionData>();
    private ArrayList<OpcionOrdenada> opcionesElegidas = new ArrayList<>();
    private ArrayList<VistaOpcionOrdenada> vistasOpcionesOrdenadas = new ArrayList<>();

    RespuestaOrdenada respuesta;
    public VistaOrderedChoice(OrderedChoice pregunta, Jugador jugador){
        super(pregunta);

        respuesta = new RespuestaOrdenada(jugador,pregunta);

        opcionesInfo.put(0,new VistaOpcionData(1,0, "File:src\\resources\\imagenes\\IMG_Botones\\IMG_BotonAzul.png"));
        opcionesInfo.put(1,new VistaOpcionData(1,1, "File:src\\resources\\imagenes\\IMG_Botones\\IMG_BotonRojo.png"));
        opcionesInfo.put(2,new VistaOpcionData(2,0, "File:src\\resources\\imagenes\\IMG_Botones\\IMG_BotonAmarillo.png"));
        opcionesInfo.put(3,new VistaOpcionData(2,1, "File:src\\resources\\imagenes\\IMG_Botones\\IMG_BotonVerde.png"));
        opcionesInfo.put(4,new VistaOpcionData(3,0, "File:src\\resources\\imagenes\\IMG_Botones\\IMG_BotonVioleta.png"));

        inicializarBotonReset();

        inicializarOpciones(pregunta,opcionesElegidas);
    }

    private void inicializarBotonReset(){
        Button botonReset = new Button("Reiniciar selección");
        botonReset.setStyle("    -fx-background-radius: 5px;"
                + "    -fx-padding: 5px;\n"
                + "    -fx-background-color: rgb(51, 51, 51);"
                + "    -fx-text-fill: rgb(255, 255, 255);"
                + "    -fx-font-family: Arial;"
                + "    -fx-font-weight: bold;"
                + "    -fx-font-size: 18 ;");
        botonReset.setOnAction(new ControladorReset(opcionesElegidas, vistasOpcionesOrdenadas));

        HBox seccionA = new HBox(botonReset);
        seccionA.setAlignment(Pos.CENTER_RIGHT);
        seccionA.setPadding(new Insets(0, -93, 0, 10));
        this.addRow(0,seccionA);
    }

    private void inicializarOpciones(OrderedChoice pregunta, ArrayList<OpcionOrdenada> opcionesElegidas){
        if (pregunta.cantidadOpciones() > opcionesInfo.size()) // lanzar exepcion, cantidad de opciones incorrectas
            return;

        for(int i = 0; i < pregunta.cantidadOpciones(); i++){
            OpcionOrdenada opcionActual = pregunta.obtenerOpcion(i);
            VistaOpcionData dataActual = opcionesInfo.get(i);
            VistaOpcionOrdenada vistaActual = new VistaOpcionOrdenada(opcionActual,dataActual.getImagen(),opcionesElegidas,pregunta.cantidadOpciones() );

            vistasOpcionesOrdenadas.add(vistaActual);

            this.add(vistaActual,dataActual.getColumna(),dataActual.getFila());
            vistaActual.prefWidthProperty().bind(this.widthProperty());
            vistaActual.prefHeightProperty().bind(this.heightProperty());
        }
    }


    public Respuesta getRespuesta(){
        return respuesta;
    }

    @Override
    public Respuesta completarRespuesta() {
        opcionesElegidas.forEach(opcion -> respuesta.agregarOpcion(opcion));
        return respuesta;
    }
}
