package com.example.functionplotter;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController {


    @FXML 
    TextField function;

    @FXML
    TextField minValue;

    @FXML
    TextField maxValue;


    @FXML
    protected void onPlotButtonClick(){
       String func = function.getText();
       int i = Function.checkFunction(func);

       if(i == -3){
           showError("the function must have only one variable");
           return;
       }

       if(i == -2){
           showError("Please Enter a function to plot");
           return;
       }

       if(i >=0){
           showError("not expecting this "+func.charAt(i)+" here: "+func.substring(0, i) + " (" + func.charAt(i) +") "+ func.substring(i+1, func.length()));
           return;
       }
        
       Double minVal;
       Double maxVal;
       try{
           minVal = Double.valueOf(minValue.getText());
           maxVal = Double.valueOf(maxValue.getText());
       }
       catch(NumberFormatException ex){
           showError("the minimum and maximum values must be valid numbers");
           return;
       }
       
       if(minVal.compareTo(maxVal) >= 0){
           showError("the minimum value must be smaller than the maximum value");
           return;
       }


       Function userFunction = Function.parseFunction(func);

       NumberAxis xAxis = new NumberAxis();
       NumberAxis yAxis = new NumberAxis();

       xAxis.setLabel("X");
       yAxis.setLabel("Y");


       LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

       XYChart.Series series = new XYChart.Series();

       for(double j = minVal; j<maxVal; j+=(maxVal-minVal)/2000){
           if(Double.compare(j, 0.0) == 0)
               continue;
           series.getData().add(new XYChart.Data(j, userFunction.solveFor(j)));
       }

       lineChart.setCreateSymbols(false);
       lineChart.getData().add(series);

       Stage stage = new Stage();
       Scene scene = new Scene(lineChart, 800, 600);
       stage.setScene(scene);
       stage.show();
    }

    private void showError(String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.show();
    }
    
}
