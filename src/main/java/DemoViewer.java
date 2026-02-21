import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DemoViewer {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        String[] options = {"Darcula", "Dark", "Light", "Swing"};

        JComboBox<String> comboBox = new JComboBox<>(options);

        int result = JOptionPane.showConfirmDialog(
                null,
                comboBox,
                "Select a Theme",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        String selection = (String) comboBox.getSelectedItem();

        if (selection != "Swing") {
            if (selection == "Dark") {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            }
            if (selection == "Light") {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            if (selection == "Darcula") {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
            }
        }

        Color newColor = null;
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\preferences.txt";
        try {
            String line;
            int pos = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split("=");
                List<String> rowData = Arrays.asList(values);
                System.out.println(rowData);

                if (Objects.equals(rowData.getFirst(), "bgcolor")) {
                    newColor = Color.getHSBColor(Float.parseFloat(rowData.getLast().split(",")[0]),
                            Float.parseFloat(rowData.getLast().split(",")[1]),
                            Float.parseFloat(rowData.getLast().split(",")[2]));
                }

                pos++;
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        }
        final Color[][] bgColor = {{newColor}};
        final boolean[] doShade = {true};
        final boolean[] multiLoad = {false};

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            Container pane = frame.getContentPane();
            pane.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JMenuBar menuBar = new JMenuBar();
            JMenu Models = new JMenu("Model");
            JMenu View = new JMenu("View");
            JMenu Info = new JMenu("Info");
            JMenuItem Cube = new JMenuItem("Cube");
            JMenuItem Clear = new JMenuItem("Clear");
            JMenuItem Tetrahedron = new JMenuItem("Tetrahedron");
            JMenuItem Load = new JMenuItem("Load");
            JMenuItem Reset = new JMenuItem("Reset");
            JMenuItem Background = new JMenuItem("Background");
            JMenuItem Shade = new JMenuItem("Hide/Show");
            JMenuItem Model_Info = new JMenuItem("Model Info");
            JMenuItem About = new JMenuItem("About");
            JMenuItem Help = new JMenuItem("Help");
            JMenuItem Custom = new JMenuItem("Custom");
            JMenuItem Scale = new JMenuItem("Scale");
            JMenuItem Export = new JMenuItem("Export");

            frame.setJMenuBar(menuBar);
            menuBar.add(Models);
            menuBar.add(View);
            menuBar.add(Info);
            Models.add(Cube);
            Models.add(Clear);
            Models.add(Tetrahedron);
            Models.add(Load);
            Models.add(Custom);
            Models.add(Export);
            //Models.add(Scale);
            View.add(Background);
            View.add(Reset);
            View.add(Shade);
            Info.add(Model_Info);
            Info.add(About);
            Info.add(Help);

            List<Triangle> cube = new ArrayList<>();
            cube.add(new Triangle(
                    new Vertex(-100, -100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(100, 100, -100),
                    Color.RED));
            cube.add(new Triangle(
                    new Vertex(-100, -100, -100),
                    new Vertex(-100, 100, -100),
                    new Vertex(100, 100, -100),
                    Color.RED));
            cube.add(new Triangle(
                    new Vertex(-100, -100, 100),
                    new Vertex(100, -100, 100),
                    new Vertex(100, 100, 100),
                    Color.BLUE));
            cube.add(new Triangle(
                    new Vertex(-100, -100, 100),
                    new Vertex(-100, 100, 100),
                    new Vertex(100, 100, 100),
                    Color.BLUE));
            cube.add(new Triangle(
                    new Vertex(-100, -100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(100, -100, 100),
                    Color.YELLOW));
            cube.add(new Triangle(
                    new Vertex(-100, -100, -100),
                    new Vertex(-100, -100, 100),
                    new Vertex(100, -100, 100),
                    Color.YELLOW));
            cube.add(new Triangle(
                    new Vertex(-100, 100, -100),
                    new Vertex(100, 100, -100),
                    new Vertex(100, 100, 100),
                    Color.WHITE));
            cube.add(new Triangle(
                    new Vertex(-100, 100, -100),
                    new Vertex(-100, 100, 100),
                    new Vertex(100, 100, 100),
                    Color.WHITE));
            cube.add(new Triangle(
                    new Vertex(-100, 100, -100),
                    new Vertex(-100, -100, -100),
                    new Vertex(-100, -100, 100),
                    Color.GREEN));
            cube.add(new Triangle(
                    new Vertex(-100, 100, -100),
                    new Vertex(-100, 100, 100),
                    new Vertex(-100, -100, 100),
                    Color.GREEN));
            cube.add(new Triangle(
                    new Vertex(100, 100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(100, -100, 100),
                    Color.CYAN));
            cube.add(new Triangle(
                    new Vertex(100, 100, -100),
                    new Vertex(100, 100, 100),
                    new Vertex(100, -100, 100),
                    Color.CYAN));

            List<Triangle> tetrahedron = new ArrayList<>();
            tetrahedron.add(new Triangle(new Vertex(100, 100, 100),
                    new Vertex(-100, -100, 100),
                    new Vertex(-100, 100, -100),
                    Color.YELLOW));
            tetrahedron.add(new Triangle(new Vertex(100, 100, 100),
                    new Vertex(-100, -100, 100),
                    new Vertex(100, -100, -100),
                    Color.RED));
            tetrahedron.add(new Triangle(new Vertex(-100, 100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(100, 100, 100),
                    Color.GREEN));
            tetrahedron.add(new Triangle(new Vertex(-100, 100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(-100, -100, 100),
                    Color.BLUE));


            List<Triangle> toLoad = new ArrayList<>(tetrahedron);

            final double[][] rotation = {{0, 0}};

            JPanel renderPanel = new JPanel() {
                public void paintComponent(Graphics g) {
                    int numTris = 0;

                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(bgColor[0][0]);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    double heading = Math.toRadians(rotation[0][0]);
                    double pitch = Math.toRadians(rotation[0][1]);

                    Matrix3 headingTransform = new Matrix3(new double[]{
                            Math.cos(heading), 0, -Math.sin(heading),
                            0, 1, 0,
                            Math.sin(heading), 0, Math.cos(heading)
                    });

                    Matrix3 pitchTransform = new Matrix3(new double[]{
                            1, 0, 0,
                            0, Math.cos(pitch), Math.sin(pitch),
                            0, -Math.sin(pitch), Math.cos(pitch)
                    });

                    Matrix3 transform = headingTransform.multiply(pitchTransform);

                    BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                    double[] zBuffer = new double[img.getWidth() * img.getHeight()];

                    for (int i = 0; i < zBuffer.length; i++) {
                        zBuffer[i] = Double.NEGATIVE_INFINITY;
                    }


                    for (Triangle t : toLoad) {
                        numTris++;

                        Vertex v1 = transform.transform(t.v1);
                        v1.x += getWidth() / 2;
                        v1.y += getHeight() / 2;

                        Vertex v2 = transform.transform(t.v2);
                        v2.x += getWidth() / 2;
                        v2.y += getHeight() / 2;

                        Vertex v3 = transform.transform(t.v3);
                        v3.x += getWidth() / 2;
                        v3.y += getHeight() / 2;

                        Vertex ab = new Vertex(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
                        Vertex ac = new Vertex(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z);
                        Vertex norm = new Vertex(
                                ab.y * ac.z - ab.z * ac.y,
                                ab.z * ac.x - ab.x * ac.z,
                                ab.x * ac.y - ab.y * ac.x
                        );
                        double normalLength = Math.sqrt(norm.x * norm.x + norm.y * norm.y + norm.z * norm.z);
                        norm.x /= normalLength;
                        norm.y /= normalLength;
                        norm.z /= normalLength;

                        double angleCos = Math.abs(norm.z);

                        int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
                        int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
                        int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
                        int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

                        double triangleArea = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

                        for (int y = minY; y <= maxY; y++) {
                            for (int x = minX; x <= maxX; x++) {
                                double b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
                                double b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
                                double b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
                                if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
                                    double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
                                    int zIndex = y * img.getWidth() + x;
                                    if (zBuffer[zIndex] < depth && doShade[0]) {
                                        img.setRGB(x, y, getShade(t.color, angleCos).getRGB());
                                        zBuffer[zIndex] = depth;
                                    }
                                }
                            }
                        }
                    }

                    g2.drawImage(img, 0, 0, null);
                }
            };
            pane.add(renderPanel, BorderLayout.CENTER);

            Reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rotation[0] = new double[]{0, 0};
                    bgColor[0] = new Color[]{Color.DARK_GRAY};
                    renderPanel.repaint();
                }
            });

            Background.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        bgColor[0] = new Color[]{JColorChooser.showDialog(null, "Choose Background Color", renderPanel.getBackground())};
                        String path = System.getProperty("user.dir") + "\\src\\main\\java\\preferences.txt";

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

                            bw.write("bgcolor=" + Arrays.toString(Color.RGBtoHSB(Integer.parseInt(Arrays.toString(bgColor[0])
                                            .replace("java.awt.Color", "")
                                            .replace("[[", "")
                                            .replace("]]", "")
                                            .replace("r=", "")
                                            .replace("b=", "")
                                            .replace("g=", "").split(",")[0]),
                                    Integer.parseInt(Arrays.toString(bgColor[0])
                                            .replace("java.awt.Color", "")
                                            .replace("[[", "")
                                            .replace("]]", "")
                                            .replace("r=", "")
                                            .replace("b=", "")
                                            .replace("g=", "").split(",")[1]),
                                    Integer.parseInt(Arrays.toString(bgColor[0])
                                            .replace("java.awt.Color", "")
                                            .replace("[[", "")
                                            .replace("]]", "")
                                            .replace("r=", "")
                                            .replace("b=", "")
                                            .replace("g=", "").split(",")[2]),
                                    null)).replace("[", "").replace("]", ""));
                            bw.close();
                        } catch (IOException ignored) {

                        }
                        renderPanel.repaint();
                    } catch (SecurityException | NullPointerException | IllegalArgumentException ignored) {

                    }
                }

            });

            Cube.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toLoad.clear();
                    toLoad.addAll(cube);
                    renderPanel.repaint();
                }
            });

            Clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toLoad.clear();
                    renderPanel.repaint();
                }
            });

            Tetrahedron.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toLoad.clear();
                    toLoad.addAll(tetrahedron);
                    renderPanel.repaint();
                }
            });

            Load.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                    String defaultPath = System.getProperty("user.dir");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(Path.of(defaultPath).toFile());
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    fileChooser.addChoosableFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return f.getName().endsWith(".obj") | f.isDirectory();
                        }

                        @Override
                        public String getDescription() {
                            return "Wavefront Object File (.obj)";
                        }
                    });
                    fileChooser.addChoosableFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return f.getName().endsWith(".3djm") | f.isDirectory();
                        }

                        @Override
                        public String getDescription() {
                            return "Java 3D Model (.3djm)";
                        }
                    });


                    fileChooser.setDialogTitle("Select a file to read mesh data");

                    int userSelection = fileChooser.showOpenDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();

                        String absolutePath = selectedFile.getAbsolutePath();

                        System.out.println("Selected file: " + absolutePath);

                        if (selectedFile.getName().endsWith(".3djm")) {
                            try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
                                String line;
                                int pos = 0;
                                Vertex v1 = null, v2 = null, v3 = null;
                                Color color = null;
                                List<Triangle> newLoad = new ArrayList<>();
                                while ((line = br.readLine()) != null) {
                                    String[] values = line.split(",");
                                    List<String> rowData = Arrays.asList(values);
                                    if (pos == 0) {
                                    } else if ((pos % 4) == 1) {
                                        v1 = new Vertex(Double.parseDouble(rowData.get(0)), Double.parseDouble(rowData.get(1)), Double.parseDouble(rowData.get(2)));
                                    } else if ((pos % 4) == 2) {
                                        v2 = new Vertex(Double.parseDouble(rowData.get(0)), Double.parseDouble(rowData.get(1)), Double.parseDouble(rowData.get(2)));
                                    } else if ((pos % 4) == 3) {
                                        v3 = new Vertex(Double.parseDouble(rowData.get(0)), Double.parseDouble(rowData.get(1)), Double.parseDouble(rowData.get(2)));
                                    } else if ((pos % 4) == 0) {
                                        color = new Color(Integer.valueOf(rowData.getFirst()));
                                        newLoad.add(new Triangle(v1, v2, v3, color));
                                    }
                                    pos++;
                                }
                                if (line == null) {
                                    String nullTest1 = v1.toString(), nullTest2 = v2.toString(), nullTest3 = v2.toString(), nullTest4 = color.toString();

                                    toLoad.clear();
                                    toLoad.addAll(newLoad);
                                    renderPanel.repaint();
                                }
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "File not found!");
                            } catch (NullPointerException | NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "File not valid!");
                            }
                        }
                        if (selectedFile.getName().endsWith(".obj")) {
                            try {
                                List<Vertex> vertices = new ArrayList<>();
                                List<Triangle> triangles = new ArrayList<>();
                                double scaleFactor = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter scale factor (New auto-scale coming soon):"));

                                try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        line = line.trim();
                                        if (line.startsWith("v ")) {
                                            String[] vertexData = line.split("\\s+");
                                            double x = Double.parseDouble(vertexData[1]) * scaleFactor;
                                            double y = Double.parseDouble(vertexData[2]) * scaleFactor;
                                            double z = Double.parseDouble(vertexData[3]) * scaleFactor;
                                            vertices.add(new Vertex(x, y, z));
                                        } else if (line.startsWith("f ")) {
                                            String[] faceData = line.split("\\s+");
                                            int v1Index = Integer.parseInt(faceData[1].split("/")[0]) - 1;
                                            int v2Index = Integer.parseInt(faceData[2].split("/")[0]) - 1;
                                            int v3Index = Integer.parseInt(faceData[3].split("/")[0]) - 1;

                                            Vertex v1 = vertices.get(v1Index);
                                            Vertex v2 = vertices.get(v2Index);
                                            Vertex v3 = vertices.get(v3Index);

                                            triangles.add(new Triangle(v1, v2, v3, new Color(16777215)));
                                        }
                                    }

                                    toLoad.clear();
                                    toLoad.addAll(triangles);
                                    renderPanel.repaint();
                                } catch (IOException | NullPointerException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "File not found or valid");
                                }

                            } catch (NullPointerException | NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "File not valid!");
                            }
                        }
                    } else {
                        System.out.println("No file selected.");
                    }


                }
            });

            Custom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option = JOptionPane.showOptionDialog(null, "Do you want to add mesh(y) or replace it(n)?", "Mesh", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    List<Triangle> triangles = new ArrayList<>();
                    List<Double> component = new ArrayList<>();
                    Color color = new Color(255,255,255);
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 1 x", "Vertex 1 x")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 1 y", "Vertex 1 y")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 1 z", "Vertex 1 z")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 2 x", "Vertex 2 x")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 2 y", "Vertex 2 y")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 2 z", "Vertex 2 z")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 3 x", "Vertex 3 x")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 3 y", "Vertex 3 y")));
                    component.add(Double.valueOf(JOptionPane.showInputDialog(null, "Vertex 3 z", "Vertex 3 z")));
                    color = JColorChooser.showDialog(null, "Color", color);
                    triangles.add(new Triangle(
                            new Vertex(
                                    component.get(0),
                                    component.get(1),
                                    component.get(2)),
                            new Vertex(
                                    component.get(3),
                                    component.get(4),
                                    component.get(5)),
                            new Vertex(
                                    component.get(6),
                                    component.get(7),
                                    component.get(8)),
                            color));

                    if (option == 1) {
                        toLoad.clear();
                    }
                    toLoad.addAll(triangles);
                    renderPanel.repaint();
                }
            });

            Export.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            Model_Info.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long triCount = 0;

                    for (Triangle t : toLoad) {
                        triCount++;
                    }
                    JOptionPane.showMessageDialog(null, "Triangles: " + triCount + System.lineSeparator() + "Vertexes: " + (triCount * 3), "Model Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            About.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                    JOptionPane.showMessageDialog(null, "Made by: @michaelrex2012" + System.lineSeparator() + "Java3D Version: 0.5" + System.lineSeparator() + "Java: Temurin JRE 21.0.7+6", "About", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            Help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create("https://github.com/michaelrex2012/Java3D/wiki"));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "You may not have set a default browser!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            renderPanel.addMouseMotionListener(new MouseMotionAdapter() {
                private int prevX, prevY;

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getX() - prevX;
                    int deltaY = e.getY() - prevY;

                    rotation[0][0] += deltaX;
                    rotation[0][1] -= deltaY;

                    prevX = e.getX();
                    prevY = e.getY();

                    renderPanel.repaint();
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            });
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame.setLocation(new Point(screenSize.width / 2, screenSize.height / 2));
            frame.setSize(500, 500);
            frame.setVisible(true);
        });
    }

    public static Color getShade(Color color, double shade) {
        double redLinear = Math.pow(color.getRed(), 2.4) * shade;
        double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
        double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;

        int red = (int) Math.pow(redLinear, 1 / 2.4);
        int green = (int) Math.pow(greenLinear, 1 / 2.4);
        int blue = (int) Math.pow(blueLinear, 1 / 2.4);

        return new Color(red, green, blue);
    }

}

class Vertex {
    double x;
    double y;
    double z;

    Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }
}

class Triangle {
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Color color;

    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
}

class Matrix3 {
    double[] values;

    Matrix3(double[] values) {
        this.values = values;
    }

    Matrix3 multiply(Matrix3 other) {
        double[] result = new double[9];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int i = 0; i < 3; i++) {
                    result[row * 3 + col] +=
                            this.values[row * 3 + i] * other.values[i * 3 + col];
                }
            }
        }
        return new Matrix3(result);
    }

    Vertex transform(Vertex in) {
        return new Vertex(
                in.x * values[0] + in.y * values[3] + in.z * values[6],
                in.x * values[1] + in.y * values[4] + in.z * values[7],
                in.x * values[2] + in.y * values[5] + in.z * values[8]
        );
    }
}
