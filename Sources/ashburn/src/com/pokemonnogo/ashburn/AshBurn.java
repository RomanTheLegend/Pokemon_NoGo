package com.pokemonnogo.ashburn;



import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.pokemonmapswrapper.PokemonData;
import com.pokemonmapswrapper.RequestProcessor;


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
    private JButton loadGameDataButton;
    private JButton reconnectButton;
    private JButton saveGameDataButton;
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
    private JButton walkToButton;
    private JButton loopThroughCheckpointsButton;
    private JButton clearRouteButton;
    private JTextField textRouteSequence;
    private JButton cpAddRoutePointButton01;
    private JButton cpAddRoutePointButton03;
    private JButton cpAddRoutePointButton02;
    private JButton cpAddRoutePointButton05;
    private JButton cpAddRoutePointButton07;
    private JButton cpAddRoutePointButton09;
    private JButton cpAddRoutePointButton04;
    private JButton cpAddRoutePointButton06;
    private JButton cpAddRoutePointButton08;
    private JButton cpAddRoutePointButton10;
    private JLabel currentLoopTarget;
    private JButton locatorSwitchButton;

    private JTable detectorTable;
    private JScrollPane scrollPane;
    private JTextField detectorMessages;
    private static JMenuBar menuBar = new JMenuBar();
    private DefaultTableModel tableModel = new DefaultTableModel();

    private ArrayList<JLabel> cpPositionLabels;
    private ArrayList<JTextField> cpNames;


    private UserActionsProcessor userActionsProcessor;
    private UserClickProcessor userCickProcessor;

    private SignalProcessor signalProcessor;
    private DataManager dataManager;

    private Coordinates position;
    private ServiceAccount serviceAccountPTC;

    private boolean isOnAutopilot=false;
    private boolean isLoopEnabled=false;
    private boolean isRefreshEnabled=true;
    private boolean goFast=false;
    private boolean allwaysOnTop=false;

    private int loopTargetID=0;

    private String path;

    private List<Integer> routePoints;

    private RequestProcessor requestProcessor;



    private final long REFRESH_TIME_SECONDS=5;

    private Semaphore semaphore = new Semaphore();

    private List<Coordinates> detectedPokemons= new ArrayList<>();

    public AshBurn() {


        userActionsProcessor = new UserActionsProcessor();
        userCickProcessor = new UserClickProcessor();
        signalProcessor = new SignalProcessor();
        dataManager = new DataManager();
        routePoints = new ArrayList<>();

        serviceAccountPTC = new ServiceAccount();


        cpPositionLabels = new ArrayList<>(Arrays.asList(cpPos01, cpPos02, cpPos03, cpPos04, cpPos05, cpPos06, cpPos07, cpPos08, cpPos09, cpPos10));
        cpNames = new ArrayList<>(Arrays.asList(cpName01, cpName02, cpName03, cpName04, cpName05, cpName06, cpName07, cpName08, cpName09, cpName10));

        if(System.getProperty("os.name").indexOf("Windows")>=0) {
            path = System.getProperty("user.dir") + "\\";
        }else{
            path = System.getProperty("user.dir") + "/";
        }


        cpGoBtn01.addActionListener(userActionsProcessor);
        cpGoBtn01.setActionCommand("Go to checkpoint");
        cpGoBtn02.addActionListener(userActionsProcessor);
        cpGoBtn02.setActionCommand("Go to checkpoint");
        cpGoBtn03.addActionListener(userActionsProcessor);
        cpGoBtn03.setActionCommand("Go to checkpoint");
        cpGoBtn04.addActionListener(userActionsProcessor);
        cpGoBtn04.setActionCommand("Go to checkpoint");
        cpGoBtn05.addActionListener(userActionsProcessor);
        cpGoBtn05.setActionCommand("Go to checkpoint");
        cpGoBtn06.addActionListener(userActionsProcessor);
        cpGoBtn06.setActionCommand("Go to checkpoint");
        cpGoBtn07.addActionListener(userActionsProcessor);
        cpGoBtn07.setActionCommand("Go to checkpoint");
        cpGoBtn08.addActionListener(userActionsProcessor);
        cpGoBtn08.setActionCommand("Go to checkpoint");
        cpGoBtn09.addActionListener(userActionsProcessor);
        cpGoBtn09.setActionCommand("Go to checkpoint");
        cpGoBtn10.addActionListener(userActionsProcessor);
        cpGoBtn10.setActionCommand("Go to checkpoint");


        cpSetBtn01.addActionListener(userActionsProcessor);
        cpSetBtn01.setActionCommand("Set checkpoint");
        cpSetBtn02.addActionListener(userActionsProcessor);
        cpSetBtn02.setActionCommand("Set checkpoint");
        cpSetBtn03.addActionListener(userActionsProcessor);
        cpSetBtn03.setActionCommand("Set checkpoint");
        cpSetBtn04.addActionListener(userActionsProcessor);
        cpSetBtn04.setActionCommand("Set checkpoint");
        cpSetBtn05.addActionListener(userActionsProcessor);
        cpSetBtn05.setActionCommand("Set checkpoint");
        cpSetBtn06.addActionListener(userActionsProcessor);
        cpSetBtn06.setActionCommand("Set checkpoint");
        cpSetBtn07.addActionListener(userActionsProcessor);
        cpSetBtn07.setActionCommand("Set checkpoint");
        cpSetBtn08.addActionListener(userActionsProcessor);
        cpSetBtn08.setActionCommand("Set checkpoint");
        cpSetBtn09.addActionListener(userActionsProcessor);
        cpSetBtn09.setActionCommand("Set checkpoint");
        cpSetBtn10.addActionListener(userActionsProcessor);
        cpSetBtn10.setActionCommand("Set checkpoint");



        cpAddRoutePointButton01.addActionListener(userActionsProcessor);
        cpAddRoutePointButton01.setActionCommand("Add route point");
        cpAddRoutePointButton02.addActionListener(userActionsProcessor);
        cpAddRoutePointButton02.setActionCommand("Add route point");
        cpAddRoutePointButton03.addActionListener(userActionsProcessor);
        cpAddRoutePointButton03.setActionCommand("Add route point");
        cpAddRoutePointButton04.addActionListener(userActionsProcessor);
        cpAddRoutePointButton04.setActionCommand("Add route point");
        cpAddRoutePointButton05.addActionListener(userActionsProcessor);
        cpAddRoutePointButton05.setActionCommand("Add route point");
        cpAddRoutePointButton06.addActionListener(userActionsProcessor);
        cpAddRoutePointButton06.setActionCommand("Add route point");
        cpAddRoutePointButton07.addActionListener(userActionsProcessor);
        cpAddRoutePointButton07.setActionCommand("Add route point");
        cpAddRoutePointButton08.addActionListener(userActionsProcessor);
        cpAddRoutePointButton08.setActionCommand("Add route point");
        cpAddRoutePointButton09.addActionListener(userActionsProcessor);
        cpAddRoutePointButton09.setActionCommand("Add route point");
        cpAddRoutePointButton10.addActionListener(userActionsProcessor);
        cpAddRoutePointButton10.setActionCommand("Add route point");


        loadGameDataButton.addActionListener(userActionsProcessor);
        loadGameDataButton.setActionCommand("Load game data");

        reconnectButton.addActionListener(userActionsProcessor);
        reconnectButton.setActionCommand("Reconnect");

        saveGameDataButton.addActionListener(userActionsProcessor);
        saveGameDataButton.setActionCommand("Save game data");

        setPointButton.addActionListener(userActionsProcessor);
        setPointButton.setActionCommand("Set current location");

        walkToButton.addActionListener(userActionsProcessor);
        walkToButton.setActionCommand("Walk to location");

        loopThroughCheckpointsButton.addActionListener(userActionsProcessor);
        loopThroughCheckpointsButton.setActionCommand("Loop through checkpoints");

        clearRouteButton.addActionListener(userActionsProcessor);
        clearRouteButton.setActionCommand("Clear route");


        locatorSwitchButton.addActionListener(userActionsProcessor);
        locatorSwitchButton.setActionCommand("Start/stop locator");


        detectorTable.setModel(tableModel);
        detectorTable.addMouseListener(userCickProcessor);



        keyReadingArea.addKeyListener(this);


        setDetectorTableStyle();


        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                goFast=checkBox1.isSelected();
            }
        });




        position = new Coordinates();



        connectToPTC();

        createMenu();

        updateMainData();


        refreshPokemons();

    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("AshBurn");
        frame.setContentPane(new AshBurn().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("AshBurn v4.3 Alpha - Pokemon NoGo project");




        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);



    }

    private void createMenu(){


        JMenu settingsMenu = new JMenu("Settings");
        final JMenu aboutMenu = new JMenu("About");

        JMenuItem settingsMenuItem = new JMenuItem("Edit");
        settingsMenuItem.addActionListener(userActionsProcessor);
        settingsMenuItem.setActionCommand("Edit settings");

        JCheckBoxMenuItem alwaysOnTopMenuItem = new JCheckBoxMenuItem("Always on top");
        alwaysOnTopMenuItem.addActionListener(userActionsProcessor);
        alwaysOnTopMenuItem.setActionCommand("Switch always on top");

        settingsMenu.add(settingsMenuItem);
        settingsMenu.add(alwaysOnTopMenuItem);
        menuBar.add(settingsMenu);
        menuBar.add(aboutMenu);


    }

    private void setDetectorTableStyle(){

        tableModel.addColumn("Pokemon");
        tableModel.addColumn("Direction");
        tableModel.addColumn("Action");

        TableColumnModel columnModel = detectorTable.getColumnModel();

        columnModel.getColumn(2).setCellRenderer(
                new DefaultTableCellRenderer(){
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        setText("Go!");
                        setHorizontalAlignment(SwingConstants.CENTER);
                        setBackground(new Color(81,154,249));
                        addMouseListener(userCickProcessor);
                        return this;
                    }

                }
        );


         scrollPane.getViewport().setBackground(new Color(86,154,5));
    }

    private void switchAlwaysOnTop(){


        JFrame frame = (JFrame)SwingUtilities.getRoot(contentPane);

        allwaysOnTop=!allwaysOnTop;

        frame.setAlwaysOnTop(allwaysOnTop);





    }



    private class UserClickProcessor implements MouseListener{

        public void mouseEntered(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseExited(MouseEvent e){}

        public void mouseClicked(MouseEvent mouseEvent){
            Point clickPoint=mouseEvent.getPoint();
            JTable target = (JTable) mouseEvent.getSource();
            int pokemonID = target.rowAtPoint(clickPoint);
            int column = target.columnAtPoint(clickPoint);

            if(column==2){
                try{
                    Coordinates selectedPokemon=detectedPokemons.get(pokemonID);
                    autopilotToLocation(selectedPokemon);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }


        }


    }



    private class UserActionsProcessor implements ActionListener{




        public void actionPerformed(ActionEvent actionEvent) {

            String sourceButtonName=null;

            switch (actionEvent.getSource().getClass().toString()){
                case "class javax.swing.JMenuItem":
                    break;
                case "class javax.swing.JButton":
                    JButton sourceButton = (JButton)actionEvent.getSource();
                    sourceButtonName = sourceButton.getName();
                    break;
            }





            String command = actionEvent.getActionCommand();
            actionEvent.paramString();



            switch(command){
                case "Load game data":
                    loadGameData();
                    break;
                case "Save game data":
                    saveGameData();
                    break;
                case "Set current location":
                    setCurrentLocation();
                    break;
                case "Walk to location":
                    walkToLocation();
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
                case "Add route point":
                    addRoutePoint(Integer.parseInt(sourceButtonName));
                    break;
                case "Loop through checkpoints":
                    loopThroughCheckpoints();
                    break;
                case "Clear route":
                    clearRoute();
                    break;
                case "Start/stop locator":
                    startStopLocator();
                    break;
                case "Edit settings":
                    showSettingsDialog();
                    break;
                case "Switch always on top":
                    switchAlwaysOnTop();
                    break;

            }


        }

    }



    private void connectToPTC(){

        serviceAccountPTC=dataManager.loadUserData();

        if(serviceAccountPTC!=null) {
            try {
                requestProcessor = new RequestProcessor(serviceAccountPTC.LOGIN, serviceAccountPTC.PASSWORD);
            } catch (Exception e) {
                detectorMessages.setText("Failed to login. Retry in " + REFRESH_TIME_SECONDS+ " seconds");
                requestProcessor = null;
                isRefreshEnabled=true;
            }
        }else {
            detectorMessages.setText("Please check PTC login");
            isRefreshEnabled=false;
            locatorSwitchButton.setText("Start");
        }


    }




    private void showSettingsDialog(){
        Settings settingsDialog=new Settings();
        settingsDialog.setLocationRelativeTo( null );
        settingsDialog.pack();
        settingsDialog.setVisible(true);

    }


    private void startStopLocator(){

        if(isRefreshEnabled){
            locatorSwitchButton.setText("Start");
        }else {
            locatorSwitchButton.setText("Stop");
        }

            isRefreshEnabled=!isRefreshEnabled;

    }


private void refreshPokemons(){



    new Thread(new Runnable() {
        @Override
        public void run() {

            ArrayList<PokemonData> catchablePokemons;


            int direction=0;

            String compassDirection=null;

            while (true) {



                if(isRefreshEnabled) {
/*

                    tableModel.addRow(new Object[] { "pokemon.name" , "compassDirection", "GO!" });
                    tableModel.addRow(new Object[] { "pokemon.name" , "compassDirection", "GO!" });
                    tableModel.addRow(new Object[] { "pokemon.name" , "compassDirection", "GO!" });
*/

                    if(requestProcessor==null){
                        detectorMessages.setText("Reconnecting");
                        connectToPTC();
                    }else {

                        try {
                            requestProcessor.setLocation(position.latitude, position.longitude);
                            catchablePokemons = requestProcessor.findCatchablePokemons();
                            detectorMessages.setText("");

                            int tableSize = tableModel.getRowCount();

                            for (int i = 0; i < tableSize; i++) {
                                tableModel.removeRow(0);
                            }

                            detectedPokemons.clear();

                            for (PokemonData pokemon : catchablePokemons) {

                                Coordinates pokemonLocation = new Coordinates();

                                pokemonLocation.latitude = pokemon.latitude;
                                pokemonLocation.longitude = pokemon.longitude;
                                pokemonLocation.name = pokemon.name;

                                direction = getDirection(pokemonLocation);

                                switch (direction) {
                                    case 1:
                                        compassDirection = "South-West";
                                        break;
                                    case 2:
                                        compassDirection = "South";
                                        break;
                                    case 3:
                                        compassDirection = "South-East";
                                        break;
                                    case 4:
                                        compassDirection = "West";
                                        break;
                                    case 0:
                                        compassDirection = "Here";
                                        break;

                                    case 6:
                                        compassDirection = "East";
                                        break;
                                    case 7:
                                        compassDirection = "North-West";
                                        break;
                                    case 8:
                                        compassDirection = "North";
                                        break;
                                    case 9:
                                        compassDirection = "North-East";
                                        break;

                                }


                                //textArea1.append(pokemon.name + "\n" + pokemon.longitude.toString() + "\n" + pokemon.latitude.toString() + "\n\n");


                                detectedPokemons.add(pokemonLocation);


                                tableModel.addRow(new Object[]{pokemon.name, compassDirection, "GO!"});


                            }

                        } catch (Exception e) {
                            detectorMessages.setText("Connection failed, retrying");
                            try {
                                sleep(2000);

                                if (serviceAccountPTC.LOGIN != null && serviceAccountPTC.PASSWORD != null) {
                                    requestProcessor.reconnect();
                                } else {
                                    connectToPTC();
                                }


                            } catch (Exception ex) {
                                detectorMessages.setText("Login failed. Retry in " + REFRESH_TIME_SECONDS + " seconds");
                            }


                        }
                    }

                }

                for(long i = REFRESH_TIME_SECONDS ; i>0; i--){

                    if(isRefreshEnabled) {
                        detectorMessages.setText("Refreshing data in " + i);
                    }else {
                        detectorMessages.setText("Scan on pause");
                    }
                    sleep(1000);

                }
                detectorMessages.setText("Looking for nearby pokemons");


            }

        }
    }).start();








}


    private void loadGameData(){

        dataManager.loadGameData();
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



    private void saveGameData(){

        dataManager.saveGameData(position);
        messageBoard.setText("Settings saved to " + path + "AshBurn_saved_data.bin");
    }




    private void setCurrentLocation(){

        Double lon=0.0;
        Double lat=0.0;

        isLoopEnabled=false;
        isOnAutopilot=false;

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





    private void goToCheckpoint(int id) {

        isLoopEnabled=false;
        isOnAutopilot=false;

        sleep(100);

        Coordinates target = dataManager.loadCheckpoint(id);

        autopilotToLocation(target);
    }



    private void addRoutePoint(int id){


        int size = routePoints.size();

        String pointName=cpNames.get(id-1).getText();



        if (size>0){
            if (id != routePoints.get(size-1)) {
                routePoints.add(id);
                textRouteSequence.setText(textRouteSequence.getText() + " -> " + pointName);
            }
        }else {
            textRouteSequence.setText(pointName+"");
            routePoints.add(id);
        }


    }


    private void loopThroughCheckpoints(){

        if (routePoints.size()>0) {

            isLoopEnabled = false;

            sleep(300);

            isLoopEnabled = true;

            if (loopTargetID != 0) {
                loopTargetID--;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {

                    Coordinates target;


                    while (isLoopEnabled) {


                        if (loopTargetID >= routePoints.size()) {
                            loopTargetID = 0;
                        }


                        while (loopTargetID < routePoints.size() && !isOnAutopilot) {
                            target = dataManager.loadCheckpoint(routePoints.get(loopTargetID));

                            autopilotToLocation(target);
                            loopTargetID++;

                        }

                        sleep(1000);


                    }


                }
            }).start();


        }
        else {
            currentLoopTarget.setText("No points added to the route");
        }

    }

    private void clearRoute(){
        isLoopEnabled=false;
        isOnAutopilot=false;
        loopTargetID=0;
        textRouteSequence.setText("");
        routePoints.clear();
        currentLoopTarget.setText("");
    }

    private void walkToLocation(){

        isLoopEnabled=false;
        isOnAutopilot=false;

        sleep(100);

        Coordinates target=new Coordinates();

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

            target.latitude=lat;
            target.longitude=lon;

            autopilotToLocation(target);

        }


    }



    private void autopilotToLocation(Coordinates target){

        isOnAutopilot=true;

        if(target.name.length()>0){
            currentLoopTarget.setText("Going to " + target.name);
        }else {
            currentLoopTarget.setText("Going somewhere on autopilot");
        }


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

        isLoopEnabled=false;
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


    public class Semaphore {
        private boolean signal = false;

        public synchronized void take() {
            this.signal = true;
            this.notify();
        }

        public synchronized void release() throws InterruptedException{
            while(!this.signal) wait();
            this.signal = false;
        }

    }

}
