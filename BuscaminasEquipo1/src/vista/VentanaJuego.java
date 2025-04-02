package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Dificultad;
import modelo.Tablero;

public class VentanaJuego extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel numeroMinas;
    private JLabel dificultad;
    private JLabel temporizador;
    private JButton[][] tableroInterfaz;

    public VentanaJuego(Tablero tablero) {
        setTitle("Juego Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;

        int filas = tablero.getTablero().length;
        int columnas = tablero.getTablero()[0].length;
        tableroInterfaz = new JButton[filas][columnas];


        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tableroInterfaz[i][j] = new JButton();
                tableroInterfaz[i][j] = new JButton();
                tableroInterfaz[i][j].setFocusPainted(false);
                tableroInterfaz[i][j].setBorder(BorderFactory.createEmptyBorder()); 
                tableroInterfaz[i][j].setContentAreaFilled(false);
                ImageIcon icono = tableroInicial("src/imagenes/blank.gif");
                tableroInterfaz[i][j].setIcon(icono);
                
                final int x = i;
                final int y = j;
                
                tableroInterfaz[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarCeldas("src/open0.gif", x, y);
                    }
                });
                
                gbc.gridx = j;
                gbc.gridy = i + 1;
                
                contentPane.add(tableroInterfaz[i][j], gbc);
            }
        }

        
        numeroMinas = new JLabel("Minas: 0");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = columnas / 3;
        contentPane.add(numeroMinas, gbc);


        dificultad = new JLabel("Dificultad: ");
        gbc.gridx = columnas / 3;
        contentPane.add(dificultad, gbc);


        temporizador = new JLabel("Tiempo: 0s");
        gbc.gridx = 2 * (columnas / 3);
        contentPane.add(temporizador, gbc);
        pack();
        setVisible(true);
    }

    public ImageIcon tableroInicial(String ruta) {
    	ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }
    
    public void actualizarCeldas(String fotoSrc, int i, int j) {
        ImageIcon iconLogo = new ImageIcon("src/" + fotoSrc);
        Image image = iconLogo.getImage();
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(scaledImage);
        tableroInterfaz[i][j].setIcon(iconLogo);
    }
}