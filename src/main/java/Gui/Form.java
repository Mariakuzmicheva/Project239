package main.java.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import main.java.problem.Point;
import main.java.problem.Problem;

public class Form extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
     * таймер
     */
    private final Timer timer;
    
	private JPanel root2;
    private JPanel root;
    private int widthFrame=1200;
    private int heightFrame=600;
    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;
    private JLabel problemText;
    private JButton addPoint;
    private JButton addTriangle;
    private JTextField xPointField;
    private JTextField yPointField;
    private JButton randomPointsBtn;
    private JButton randomTriangleBtn;
    private JTextField pointCntField;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel countPoints;
    private JLabel x1Label;
    private JLabel y1Label;
    private JLabel x2Label;
    private JLabel y2Label;
    private JLabel x3Label;
    private JLabel y3Label;
    
    private JTextField apex1X;
    private JTextField apex1Y;
    private JTextField apex2X;
    private JTextField apex2Y;
    private JTextField apex3X;
    private JTextField apex3Y;
    /**
     * рисовалка OpenGL
     */
    private final RendererGL renderer;

    
	private Form() {
		
		renderer = new RendererGL();
		
		loadFromFileBtn = new JButton("Загрузить из файла");
		saveToFileBtn = new JButton("Сохранить в файл");
		clearBtn = new JButton("Очистить");
		addPoint = new JButton("Добавить точку");
		addTriangle = new JButton("Добавить треугольник");
		randomPointsBtn = new JButton("Добавить случайные точки");
		randomTriangleBtn = new JButton("Добавить случайный треугольник");
		solveBtn = new JButton("Решить");
		pointCntField = new JTextField(10);
		xPointField = new JTextField(19);
		xPointField.setText("0.0");
		yPointField = new JTextField(19);
		yPointField.setText("0.0");
		apex1X = new JTextField(5);
		apex1X.setText("-0.6");
		apex1Y = new JTextField(5);
		apex1Y.setText("0.0");
		apex2X = new JTextField(5);
		apex2X.setText("0.0");
		apex2Y = new JTextField(5);
		apex2Y.setText("0.8");
		apex3X = new JTextField(5);
		apex3X.setText("0.6");
		apex3Y = new JTextField(5);
		apex3Y.setText("0.0");
		
		root2 =new JPanel();
		
		JFrame  frame = new JFrame(Problem.PROBLEM_CAPTION);
		frame.setLayout(new GridLayout(1,1));
		JPanel problemTextPanel = new JPanel();
		JPanel addPointsPanel = new JPanel(new FlowLayout());
		JPanel randomPoints = new JPanel(new FlowLayout());
		JPanel actions = new JPanel();
		
		problemText = new JLabel("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));
        problemText.setText("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));
        
        problemTextPanel.add(problemText);
        
        
        xLabel = new JLabel("X");
        addPointsPanel.add(xLabel);
        addPointsPanel.add(xPointField);
        
        yLabel = new JLabel("Y");
        addPointsPanel.add(yLabel);
        addPointsPanel.add(yPointField);
        
        addPointsPanel.add(addPoint);
       
        x1Label = new JLabel("X1");
        addPointsPanel.add(x1Label);
        addPointsPanel.add(apex1X);
        
        y1Label = new JLabel("Y1");
        addPointsPanel.add(y1Label);
        addPointsPanel.add(apex1Y);
        
        x2Label = new JLabel("X2");
        addPointsPanel.add(x2Label);
        addPointsPanel.add(apex2X);
        
        y2Label = new JLabel("Y2");
        addPointsPanel.add(y2Label);
        addPointsPanel.add(apex2Y);
        
        x3Label = new JLabel("X3");
        addPointsPanel.add(x3Label);
        addPointsPanel.add(apex3X);
        
        y3Label = new JLabel("Y3");
        addPointsPanel.add(y3Label);
        addPointsPanel.add(apex3Y);
        
        addPointsPanel.add(addTriangle);

        countPoints = new JLabel("Кол-во");
        pointCntField.setText("10");
        randomPoints.add(countPoints);
        randomPoints.add(pointCntField);
        randomPoints.add(randomPointsBtn);
        randomPoints.add(randomTriangleBtn);
        
        
		actions.add(loadFromFileBtn);
		root2.setLayout(new GridLayout(4,0));
		actions.add(clearBtn);
		actions.add(saveToFileBtn);
		actions.add(clearBtn);
		actions.add(solveBtn);
		
		root2.add(problemTextPanel);
		root2.add(addPointsPanel);
		root2.add(randomPoints);
		root2.add(actions);
		
		root2.setBackground(Color.white);
		root2.setSize(400, 600);
		
		renderer.problem.addPoint(0.0, 0.0);
		renderer.problem.addPoint(0.1, 0.5);
		renderer.problem.addPoint(0.6, -0.8);
		renderer.problem.addPoint(1.0, 0.0);
		
		Point apex1= new Point(-0.6,0);
		Point apex2= new Point(0.0,0.8);
		Point apex3= new Point(0.6,0.0);
		renderer.problem.addTriangle(apex1, apex2,apex3);
		
		root = new JPanel();
		root.setLayout(new BorderLayout());
		root.add(renderer.getCanvas());
		
		frame.getContentPane().add(root);
		frame.getContentPane().add(root2);

		frame.setSize(getPreferredSize());
	      
        // показываем форму
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTime();
            }
        });
        timer.start();
        initWidgets();
    }

	  public Dimension getPreferredSize() {
          return new Dimension(widthFrame, heightFrame);
      }
	
	  /**
	     * Инициализация виджетов
	     */
	    private void initWidgets() {

	        addPoint.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(xPointField.getText().isEmpty() || yPointField.getText().isEmpty() ) {
	                	JOptionPane.showMessageDialog(null, " Не все поля заполнены.");
	                }
	            	else {
	                double x = Double.parseDouble(xPointField.getText());
	                double y = Double.parseDouble(yPointField.getText());
	                renderer.problem.addPoint(x, y);
	            }
	            }
	        });
	        
	        addTriangle.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(apex1X.getText().isEmpty() || apex1Y.getText().isEmpty() || apex2Y.getText().isEmpty() 
	            			|| apex2Y.getText().isEmpty() || apex3X.getText().isEmpty() || apex3Y.getText().isEmpty()) {
	                	JOptionPane.showMessageDialog(null, " Не все поля заполнены.");
	                }
	            	else {
	                double x1 = Double.parseDouble(apex1X.getText());
	                double y1 = Double.parseDouble(apex1Y.getText());
	                double x2 = Double.parseDouble(apex2X.getText());
	                double y2 = Double.parseDouble(apex2Y.getText());
	                double x3 = Double.parseDouble(apex3X.getText());
	                double y3 = Double.parseDouble(apex3Y.getText());
	                Point apex1= new Point(x1,y1);
	        		Point apex2= new Point(x2,y2);
	        		Point apex3= new Point(x3,y3);
	                renderer.problem.addTriangle(apex1, apex2, apex3);
	            }
	            }
	        });
	        randomPointsBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(pointCntField.getText().isEmpty() || Integer.parseInt(pointCntField.getText())<10) {
	                	JOptionPane.showMessageDialog(null, " Введите количество точек большее 9.");
	                }
	            	else {
	                renderer.problem.addRandomPoints(Integer.parseInt(pointCntField.getText()));
	            }
	            }
	        });
	        randomTriangleBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                renderer.problem.addRandomTriangle();
	            }
	        });
	        loadFromFileBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                renderer.problem.loadFromFile();
	            }
	        });
	        saveToFileBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                renderer.problem.saveToFile();
	            }
	        });
	        clearBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                renderer.problem.clear();
	            }
	        });
	        solveBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                renderer.problem.solve();
	            }
	        });
	    }
	    
	    /**
	     * Событие таймера
	     */
	    private void onTime() {
	        // события по таймеру
	    }
	
	public static void main(String[] args) {
		new Form();
	}
}