
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

public class RestaurantView extends JFrame implements Runnable, ActionListener {

    // Atributos
    private RestaurantController restaurantController;
    private ArrayList<JLabel> areabuffet;
    private JLabel[][] chefs;
    private JLabel[][] comensals;
    private ArrayList<JTable> estadisticas;
    private ArrayList<JButton> botones;
    private JSlider multiplicador;

    // Constructor
    public RestaurantView(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
        this.areabuffet = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
        this.botones = new ArrayList<>();
        this.multiplicador = new JSlider();
        this.chefs = new JLabel[3][9];
        this.comensals = new JLabel[3][36];
        // Configurar el layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        crearStatisticControlPanel();
        crearViewPanel();

        this.setDefaultCloseOperation(3);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Buffet");
        this.setVisible(true);
    }

    // Getters y setters
    public RestaurantController getRestaurantController() {
        return restaurantController;
    }

    public void setRestaurantController(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < comensals[0].length; j++) {
                comensals[i][j].setBackground(Color.white);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < chefs[0].length; j++) {
                chefs[i][j].setBackground(Color.white);
            }
        }

        while (!this.restaurantController.getRestaurantModel().getStatus().equals("stop")) {
            // Actualizar el color de comensal
            restaurantController.getRestaurantModel().getStatus();
            actualizarComensalColor();
            // Actualizar el color de chef
            restaurantController.getRestaurantModel().getStatus();
            actualizarChefColor();
            // Actualizar el estadistica
            restaurantController.getRestaurantModel().getStatus();
            actualizarbufferEstadistica();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void actualizarChefColor() {
        int tempsDescansChef = Chef.getEstadistiquesChefs().getTempsDescansChef();
        int tempsCuinatsChef = Chef.getEstadistiquesChefs().getTempsCuinatsChef();
        int platsCuinats = Chef.getEstadistiquesChefs().getPlatsCuinats();
        int cuinant = 0;
        int descansant = 0;
        int entregant = 0;
        for (int i = 0; i < restaurantController.getRestaurantModel().getChefs().size(); i++) {
            switch (restaurantController.getRestaurantModel().getChefs().get(i).getStatus()) {
                case "cuinant":
                    this.chefs[0][i].setBackground(Color.green);
                    this.chefs[1][i].setBackground(Color.white);
                    this.chefs[2][i].setBackground(Color.white);
                    cuinant += 1;
                    break;
                case "descansant":
                    this.chefs[0][i].setBackground(Color.white);
                    this.chefs[1][i].setBackground(Color.gray);
                    this.chefs[2][i].setBackground(Color.white);
                    descansant += 1;
                    break;
                case "entregant":
                    this.chefs[0][i].setBackground(Color.white);
                    this.chefs[1][i].setBackground(Color.white);
                    this.chefs[2][i].setBackground(Color.red);
                    entregant += 1;
                    break;
            }
        }
        DefaultTableModel chefStatisticModel = (DefaultTableModel) estadisticas.get(0).getModel();
        chefStatisticModel.removeRow(1);
        String[] estatString = { String.valueOf(tempsDescansChef), String.valueOf(tempsCuinatsChef),
                String.valueOf(platsCuinats), String.valueOf(cuinant), String.valueOf(descansant),
                String.valueOf(entregant) };
        chefStatisticModel.addRow(estatString);
    }

    public void actualizarComensalColor() {
        int tempsMenjantComensals = Comensal.getEstadistiques().getTempsMenjantComensals();
        int tempsTertuliaComensals = Comensal.getEstadistiques().getTempsTertuliaComensals();
        int tempsEsperantComensals = Comensal.getEstadistiques().getTempsEsperantComensals();
        int menjant = 0;
        int xerrant = 0;
        int agafantPlat = 0;
        for (int i = 0; i < restaurantController.getRestaurantModel().getComensals().size(); i++) {
            switch (restaurantController.getRestaurantModel().getComensals().get(i).getStatus()) {
                case "menjant":
                    this.comensals[1][i].setBackground(Color.red);
                    this.comensals[2][i].setBackground(Color.white);
                    this.comensals[0][i].setBackground(Color.white);
                    menjant += 1;
                    break;
                case "xerrant":
                    this.comensals[1][i].setBackground(Color.white);
                    this.comensals[2][i].setBackground(Color.gray);
                    this.comensals[0][i].setBackground(Color.white);
                    xerrant += 1;
                    break;
                case "agafantPlat":
                    this.comensals[1][i].setBackground(Color.white);
                    this.comensals[2][i].setBackground(Color.white);
                    this.comensals[0][i].setBackground(Color.green);
                    agafantPlat += 1;
                    break;
            }
        }
        DefaultTableModel comensalStatisticModel = (DefaultTableModel) estadisticas.get(1).getModel();
        comensalStatisticModel.removeRow(1);
        String[] estatString = { String.valueOf(tempsMenjantComensals), String.valueOf(tempsTertuliaComensals),
                String.valueOf(tempsEsperantComensals), String.valueOf(menjant), String.valueOf(xerrant),
                String.valueOf(agafantPlat) };
        comensalStatisticModel.addRow(estatString);
    }

    public void actualizarbufferEstadistica() {
        int buffet1 = restaurantController.getRestaurantModel().getAreaBuffets().get(0).getQuantitatActual();
        int buffet2 = restaurantController.getRestaurantModel().getAreaBuffets().get(1).getQuantitatActual();
        int buffet3 = restaurantController.getRestaurantModel().getAreaBuffets().get(2).getQuantitatActual();
        int cola1 = restaurantController.getRestaurantModel().getAreaBuffets().get(0).getColaPlatsCuinats()
                .getQuantitatActual();
        int cola2 = restaurantController.getRestaurantModel().getAreaBuffets().get(1).getColaPlatsCuinats()
                .getQuantitatActual();
        int cola3 = restaurantController.getRestaurantModel().getAreaBuffets().get(2).getColaPlatsCuinats()
                .getQuantitatActual();
        DefaultTableModel aerabuffetmodel = (DefaultTableModel) estadisticas.get(2).getModel();
        aerabuffetmodel.removeRow(1);
        String[] estatString = { String.valueOf(buffet1), String.valueOf(buffet2), String.valueOf(buffet3),
                String.valueOf(cola1), String.valueOf(cola2), String.valueOf(cola3) };
        aerabuffetmodel.addRow(estatString);
    }

    public void crearStatisticControlPanel() {

        // Crear el panel izquierda de Jframe
        JPanel statisticContorl = new JPanel();

        GridBagLayout gridBagLayout = new GridBagLayout();
        statisticContorl.setLayout(gridBagLayout);
        GridBagConstraints c;

        // Estadisitca chef
        JTable chefStatistic = new JTable(
                new DefaultTableModel(
                        new Object[] { "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6" }, 0));
        DefaultTableModel chefStatisticModel = (DefaultTableModel) chefStatistic.getModel();
        String[] columnNamesChef = { "tempsDescansant",
                "tempsCuinant",
                "platsCuinats",
                "cuinant",
                "descansant",
                "entregant" };
        String[] dataChef = { "0", "0", "0", "0", "0", "0" };

        chefStatisticModel.addRow(columnNamesChef);
        chefStatisticModel.addRow(dataChef);

        // Estadisitca comensal
        JTable comensalStatistic = new JTable(
                new DefaultTableModel(
                        new Object[] { "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6" }, 0));

        DefaultTableModel comensalStatisticModel = (DefaultTableModel) comensalStatistic.getModel();
        String[] columnNamesComensal = { "tempsMenjantComensals",
                "tempsTertuliaComensals",
                "tempsEsperantComensals",
                "menjant",
                "xerrant",
                "agafantPlat" };
        String[] dataComensal = { "0", "0", "0", "0", "0", "0" };

        comensalStatisticModel.addRow(columnNamesComensal);
        comensalStatisticModel.addRow(dataComensal);

        // Estadisitca Areabuffet
        JTable areabuffetStatistic = new JTable(
                new DefaultTableModel(
                        new Object[] { "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6" }, 0));

        DefaultTableModel areabuffetStatisticModel = (DefaultTableModel) areabuffetStatistic.getModel();
        String[] columnNamesareabuffe = { "1areabuffet",
                "2areabuffet",
                "3areabuffet",
                "1ColaPlat",
                "2ColaPlat",
                "3ColaPlat" };
        String[] dataareabuffe = { "0", "0", "0", "0", "0", "0" };

        areabuffetStatisticModel.addRow(columnNamesareabuffe);
        areabuffetStatisticModel.addRow(dataareabuffe);

        // Añadir estadistica al panel
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        statisticContorl.add(chefStatistic, c);
        estadisticas.add(chefStatistic);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        statisticContorl.add(comensalStatistic, c);
        estadisticas.add(comensalStatistic);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        statisticContorl.add(areabuffetStatistic, c);
        estadisticas.add(areabuffetStatistic);

        // Añadir boton al panel
        JButton start = new JButton("Start");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        statisticContorl.add(start, c);
        start.addActionListener(this);
        botones.add(start);

        JButton stop = new JButton("Stop");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        statisticContorl.add(stop, c);
        stop.addActionListener(this);
        botones.add(stop);

        JButton pause = new JButton("Pause");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        statisticContorl.add(pause, c);
        pause.addActionListener(this);
        botones.add(pause);

        // Añadir Jslider al panel
        JSlider jSlider = new JSlider(1, 10, 1);
        jSlider.setPaintTicks(true);
        jSlider.setMajorTickSpacing(1);
        jSlider.setPaintLabels(true);
        jSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("changed to " + jSlider.getValue());
                Rellotge.getInstance().setMultiplicadorTemps(jSlider.getValue());
            }

        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 6;
        statisticContorl.add(jSlider, c);
        this.multiplicador = jSlider;

        // Añadir el panel al Jframe
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx=500;
        c.weightx = 0;
        c.weighty = 1;
        this.add(statisticContorl, c);
    }

    public void crearViewPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(gridBagLayout);
        GridBagConstraints c;

        // Crear panel Chef
        for (int j = 0; j < 3; j++) {
            JPanel p1 = new JPanel();
            p1.setLayout(gridBagLayout);
            for (int i = 0; i < this.restaurantController.getRestaurantModel().getParametresSimulacio().getNumChefPerGrill().getMax()*3; i++) {
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = 0;
                c.weightx = 1;
                c.weighty = 1;

                JLabel chef = new JLabel("chef: " + (i + 1), SwingConstants.CENTER);
                chef.setOpaque(true);
                chef.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                p1.add(chef, c);
                this.chefs[j][i] = chef;
            }
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = j;
            c.weightx = 1;
            c.weighty = 1;
            viewPanel.add(p1, c);
        }

        // Crear panel Areabuffet
        JPanel p2 = new JPanel();
        p2.setLayout(gridBagLayout);
        for (int i = 0; i < restaurantController.getRestaurantModel().getAreaBuffets().size(); i++) {

            c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridx = i;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;

            JLabel arabuffet = new JLabel("Areabuffet", SwingConstants.CENTER);
            arabuffet.setOpaque(true);
            arabuffet.setBackground(Color.magenta);
            arabuffet.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            p2.add(arabuffet, c);
            this.areabuffet.add(arabuffet);

        }
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        viewPanel.add(p2, c);

        // Crear panel comensal
        for (int j = 0; j < 3; j++) {
            JPanel p3 = new JPanel();
            p3.setLayout(gridBagLayout);
            for (int i = 0; i <this.restaurantController.getRestaurantModel().getParametresSimulacio().getNumComensal().getMax(); i++) {
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = 0;
                c.weightx = 1;
                c.weighty = 1;

                JLabel comensal = new JLabel("Comensal", SwingConstants.CENTER);
                comensal.setOpaque(true);
                comensal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                p3.add(comensal, c);
                this.comensals[j][i]=comensal;

            }
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = j+4;
            c.weightx = 1;
            c.weighty = 1;
            viewPanel.add(p3, c);
        }

        // Añadir el panel de view al Jframes
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        this.add(viewPanel, c);

    }

    // Getter y Setters
    public ArrayList<JLabel> getAreabuffet() {
        return areabuffet;
    }

    public void setAreabuffet(ArrayList<JLabel> areabuffet) {
        this.areabuffet = areabuffet;
    }

    public ArrayList<JTable> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(ArrayList<JTable> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public ArrayList<JButton> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<JButton> botones) {
        this.botones = botones;
    }

    public JSlider getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(JSlider multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String butname = e.getActionCommand();
        if (butname.equals("Start")) {
            restaurantController.play();
            System.out.println("presionaste al Start");
        }
        if (butname.equals("Pause")) {
            restaurantController.pause();
            System.out.println("presionaste al Pause");
        }
        if (butname.equals("Stop")) {
            restaurantController.stop();
            System.out.println("presionaste al Stop");
        }

    }
}
