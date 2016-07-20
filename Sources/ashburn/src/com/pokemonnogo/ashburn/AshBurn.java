package com.pokemonnogo.ashburn;



import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by the.Legend on 17/07/2016.
 */
public class AshBurn implements KeyListener {

    private JButton cpGoBtn01;
    private JButton cpGoBtn02;
    private JButton cpGoBtn03;
    private JButton cpGoBtn04;
    private JButton cpGoBtn05;
    private JButton cpGoBtn06;
    private JButton cpGoBtn07;
    private JButton cpGoBtn08;
    private JButton cpGoBtn09;
    private JButton cpGoBtn10;
    private JButton cpSetBtn01;
    private JButton cpSetBtn02;
    private JButton cpSetBtn03;
    private JButton cpSetBtn04;
    private JButton cpSetBtn05;
    private JButton cpSetBtn06;
    private JButton cpSetBtn07;
    private JButton cpSetBtn08;
    private JButton cpSetBtn09;
    private JButton cpSetBtn10;
    private JButton loadSettingsButton;
    private JButton reconnectButton;
    private JButton saveSettingsButton;
    private JButton setPointButton;
    private JLabel cpPos01;
    private JLabel cpPos02;
    private JLabel cpPos03;
    private JLabel cpPos04;
    private JLabel cpPos05;
    private JLabel cpPos06;
    private JLabel cpPos07;
    private JLabel cpPos08;
    private JLabel cpPos09;
    private JLabel cpPos10;
    private JLabel dirLabel;
    private JPanel contentPane;
    private JTextField cpName01;
    private JTextField cpName02;
    private JTextField cpName03;
    private JTextField cpName04;
    private JTextField cpName05;
    private JTextField cpName06;
    private JTextField cpName07;
    private JTextField cpName08;
    private JTextField cpName09;
    private JTextField cpName10;
    private JTextField LatMain;
    private JTextField LonMain;
    private JTextArea keyReadingArea;
    private JTextField messageBoard;
    private JCheckBox checkBox1;

    private ArrayList<JLabel> cpPositionLabels;
    private ArrayList<JTextField> cpNames;

    private ButtonProcessor buttonsProcessor;

    private SignalProcessor signalProcessor;
    private DataManager dataManager;

    private Coordinates position;

    private boolean isOnAutopilot=false;

    private boolean goFast=false;

    private String path;


    public AshBurn() {


        buttonsProcessor = new ButtonProcessor();
        signalProcessor = new SignalProcessor();
        dataManager = new DataManager();

        cpPositionLabels = new ArrayList<>(Arrays.asList(cpPos01, cpPos02, cpPos03, cpPos04, cpPos05, cpPos06, cpPos07, cpPos08, cpPos09, cpPos10));
        cpNames = new ArrayList<>(Arrays.asList(cpName01, cpName02, cpName03, cpName04, cpName05, cpName06, cpName07, cpName08, cpName09, cpName10));

        path=System.getProperty("user.dir")+"\\";


        cpGoBtn01.addActionListener(buttonsProcessor);
        cpGoBtn01.setActionCommand("Go to checkpoint");
        cpGoBtn02.addActionListener(buttonsProcessor);
        cpGoBtn02.setActionCommand("Go to checkpoint");
        cpGoBtn03.addActionListener(buttonsProcessor);
        cpGoBtn03.setActionCommand("Go to checkpoint");
        cpGoBtn04.addActionListener(buttonsProcessor);
        cpGoBtn04.setActionCommand("Go to checkpoint");
        cpGoBtn05.addActionListener(buttonsProcessor);
        cpGoBtn05.setActionCommand("Go to checkpoint");
        cpGoBtn06.addActionListener(buttonsProcessor);
        cpGoBtn06.setActionCommand("Go to checkpoint");
        cpGoBtn07.addActionListener(buttonsProcessor);
        cpGoBtn07.setActionCommand("Go to checkpoint");
        cpGoBtn08.addActionListener(buttonsProcessor);
        cpGoBtn08.setActionCommand("Go to checkpoint");
        cpGoBtn09.addActionListener(buttonsProcessor);
        cpGoBtn09.setActionCommand("Go to checkpoint");
        cpGoBtn10.addActionListener(buttonsProcessor);
        cpGoBtn10.setActionCommand("Go to checkpoint");


        cpSetBtn01.addActionListener(buttonsProcessor);
        cpSetBtn01.setActionCommand("Set checkpoint");
        cpSetBtn02.addActionListener(buttonsProcessor);
        cpSetBtn02.setActionCommand("Set checkpoint");
        cpSetBtn03.addActionListener(buttonsProcessor);
        cpSetBtn03.setActionCommand("Set checkpoint");
        cpSetBtn04.addActionListener(buttonsProcessor);
        cpSetBtn04.setActionCommand("Set checkpoint");
        cpSetBtn05.addActionListener(buttonsProcessor);
        cpSetBtn05.setActionCommand("Set checkpoint");
        cpSetBtn06.addActionListener(buttonsProcessor);
        cpSetBtn06.setActionCommand("Set checkpoint");
        cpSetBtn07.addActionListener(buttonsProcessor);
        cpSetBtn07.setActionCommand("Set checkpoint");
        cpSetBtn08.addActionListener(buttonsProcessor);
        cpSetBtn08.setActionCommand("Set checkpoint");
        cpSetBtn09.addActionListener(buttonsProcessor);
        cpSetBtn09.setActionCommand("Set checkpoint");
        cpSetBtn10.addActionListener(buttonsProcessor);
        cpSetBtn10.setActionCommand("Set checkpoint");


        loadSettingsButton.addActionListener(buttonsProcessor);
        loadSettingsButton.setActionCommand("Load settings");

        reconnectButton.addActionListener(buttonsProcessor);
        reconnectButton.setActionCommand("Reconnect");

        saveSettingsButton.addActionListener(buttonsProcessor);
        saveSettingsButton.setActionCommand("Save settings");

        setPointButton.addActionListener(buttonsProcessor);
        setPointButton.setActionCommand("Set current location");



        keyReadingArea.addKeyListener(this);

        position = new Coordinates();


        updateMainData();


        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                goFast=checkBox1.isSelected();
            }
        });

    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("AshBurn");
        frame.setContentPane(new AshBurn().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("AshBurn v2.0 - Pokemon NoGo project");
        frame.pack();
        frame.setVisible(true);



    }



    private class  ButtonProcessor implements ActionListener{



        public void actionPerformed(ActionEvent actionEvent) {

            JButton sourceButton = (JButton)actionEvent.getSource();
            String sourceButtonName = sourceButton.getName();

            String command = actionEvent.getActionCommand();
            actionEvent.paramString();



            switch(command){
                case "Load settings":
                    loadSettings();
                    break;
                case "Save settings":
                    saveSettings();
                    break;
                case "Set current location":
                    setCurrentLocation();
                    break;
                case "Reconnect":
                    signalProcessor.reconnect();
                    break;
                case "Go to checkpoint":
                    goToCheckpoint(Integer.parseInt(sourceButtonName));
                    break;
                case "Set checkpoint":
                    setCheckpoint(Integer.parseInt(sourceButtonName));
                    break;

            }


        }

    }



    private void loadSettings(){

        dataManager.loadSettings();
        Coordinates checkPoint;

        for (int i=1; i<=10 ; i++){
            checkPoint=dataManager.loadCheckpoint(i);
            if(checkPoint!=null){

                updateCheckpointView(i, checkPoint);
            }

        }

        checkPoint=dataManager.loadCheckpoint(11);
        if (checkPoint!=null){
            position=new Coordinates(checkPoint);
            updateMainData();
            setCurrentLocation();
        }


        messageBoard.setText("Settings loaded from " + path + "AshBurn_saved_data.bin");

    }



    private void saveSettings(){

        dataManager.saveSettings(position);
        messageBoard.setText("Settings saved to " + path + "AshBurn_saved_data.bin");
    }




    private void setCurrentLocation(){

        Double lon=0.0;
        Double lat=0.0;

        boolean operationFailed=false;

        try {
            lat= Double.parseDouble(LatMain.getText());
            lon= Double.parseDouble(LonMain.getText());
        } catch (NumberFormatException e) {
            messageBoard.setText("Invalid coordinates");
            operationFailed=true;
        }

        if(!operationFailed){

            position.latitude=lat;
            position.longitude=lon;

            signalProcessor.sendData(position);

            if(signalProcessor.isConnected){
                messageBoard.setText("Data sent");
            }else {
                messageBoard.setText("Connection failed, please reconnect");
            }
        }

    }


    private void setCheckpoint(int id){

        JTextField caption=cpNames.get(id-1);

        if (caption.getText().length()<=0){
            caption.setText("Spot #"+id);
        }
        position.name=caption.getText();
        dataManager.saveCheckpoint(id,position);
        updateCheckpointView(id);
    }

    private void updateCheckpointView(int id){

        JLabel cpPosition=cpPositionLabels.get(id-1);

        Coordinates labelText=new Coordinates(position);
        labelText.fixFormat(4);

        cpPosition.setText("Lon: " + labelText.latitude.toString() + " Lat: " + labelText.longitude.toString());

    }


    private void updateCheckpointView(int id,Coordinates labelText){

        JLabel cpPosition=cpPositionLabels.get(id-1);
        JTextField cpName=cpNames.get(id-1);

        labelText.fixFormat(4);

        cpName.setText(labelText.name);
        cpPosition.setText("Lon: " + labelText.latitude.toString() + " Lat: " + labelText.longitude.toString());

    }





    public void goToCheckpoint(int id){
        Coordinates target=dataManager.loadCheckpoint(id);


        isOnAutopilot=true;

        new Thread(new Runnable() {
            @Override
            public void run() {

                int direction=0;

                while (isOnAutopilot){

                    direction=getDirection(target);

                    if(direction!=0) {

                        sendMovement(direction);
                        sleep(30);
                    }else {
                        isOnAutopilot=false;
                    }

                }


            }
        }).start();



    }


    private int getDirection(Coordinates target){
        int direction=0;


        int dirX;
        int dirY;

        Double distX;
        Double distY;

        boolean isBetweenAxis=true;


        target.fixFormat(6);
        position.fixFormat(6);


        if(target.longitude>position.longitude){
            distX=target.longitude-position.longitude;
            dirX=1;
        }else{
            distX=position.longitude-target.longitude;
            if(distX==0){
                dirX=0;
            }else {
                dirX=-1;
            }
        }




        if(target.latitude>position.latitude){
            distY=target.latitude-position.latitude;
            dirY=1;
        }else{
            distY=position.latitude-target.latitude;
            if(distY==0){
                dirY=0;
            }else {
                dirY=-1;
            }
        }


        if(distX>distY){
            if((distX-1.2*distY)>0){
                isBetweenAxis=false;
            }
        }else{
            if((distY-1.2*distX)>0){
                isBetweenAxis=false;
            }
        }


        //1
        if (dirX==1 && dirY==1){

            if(isBetweenAxis){
                direction=9;
            }else{
                if(distX>distY){
                    direction=6;
                }else {
                    direction=8;
                }
            }

        }

        //2
        if (dirX==1 && dirY==-1){

            if(isBetweenAxis){
                direction=3;
            }else{
                if(distX>distY){
                    direction=6;
                }else {
                    direction=2;
                }
            }

        }

        //3
        if (dirX==-1 && dirY==-1){

            if(isBetweenAxis){
                direction=1;
            }else{
                if(distX>distY){
                    direction=4;
                }else {
                    direction=2;
                }
            }

        }

        //4
        if (dirX==-1 && dirY==1){

            if(isBetweenAxis){
                direction=7;
            }else{
                if(distX>distY){
                    direction=4;
                }else {
                    direction=8;
                }
            }

        }


        if(dirX==0 && dirY==1){
            direction=8;
        }

        if(dirX==0 && dirY==-1){
            direction=2;
        }

        if(dirX==1 && dirY==0){
            direction=6;
        }

        if(dirX==-1 && dirY==0){
            direction=4;
        }

        if(dirX==0 && dirY==0){
            direction=0;
        }


        if(distX<0.000015 && distY<0.000015 ){
            goFast=false;
            checkBox1.setSelected(false);
        }


        return direction;
    }



    public void keyReleased(KeyEvent e) {
        keyReadingArea.setText("");
    }
    public void keyTyped(KeyEvent e) {
        keyReadingArea.setText("");
    }

    public void keyPressed(KeyEvent e) {

        isOnAutopilot=false;

        keyReadingArea.setText("");

        switch ( e.getKeyCode()){
            case 105:
            case 33:
                sendMovement(9);
                break;
            case 104:
            case 38:
                sendMovement(8);
                break;
            case 103:
            case 36:
                sendMovement(7);
                break;
            case 102:
            case 39:
                sendMovement(6);
                break;

            case 100:
            case 37:
                sendMovement(4);
                break;
            case 99:
            case 34:
                sendMovement(3);
                break;
            case 98:
            case 40:
                sendMovement(2);
                break;
            case 97:
            case 35:
                sendMovement(1);
                break;
        }
    }




    private void sendMovement(int direction) {


        Double step= 0.000001;
        if (goFast){
            step= 0.00001;
        }

        switch (direction) {
            case 1:
                position.longitude = position.longitude - step;
                position.latitude = position.latitude - step;
                position.direction = "←↓";
                break;
            case 2:
                position.latitude = position.latitude - step;
                position.direction = "↓";
                break;
            case 3:
                position.longitude = position.longitude + step;
                position.latitude = position.latitude - step;
                position.direction = "↓→";
                break;
            case 4:
                position.longitude = position.longitude - step;
                position.direction = "←";
                break;
            case 5:
                break;

            case 6:
                position.longitude = position.longitude + step;
                position.direction = "→";
                break;
            case 7:
                position.longitude = position.longitude - step;
                position.latitude = position.latitude + step;
                position.direction = "←↑";
                break;
            case 8:
                position.latitude = position.latitude + step;
                position.direction = "↑";
                break;
            case 9:
                position.longitude = position.longitude + step;
                position.latitude = position.latitude + step;
                position.direction = "↑→";
                break;

        }

        signalProcessor.sendData(position);

        if(signalProcessor.isConnected){
            messageBoard.setText("Data sent");
        }else {
            messageBoard.setText("Connection failed, please reconnect");
        }

        updateMainData();

    }

    private void updateMainData(){
        Coordinates labelText=new Coordinates(position);
        labelText.fixFormat(6);

        LatMain.setText(labelText.latitude.toString());
        LonMain.setText(labelText.longitude.toString());
        dirLabel.setText(labelText.direction);

    }


    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

}
