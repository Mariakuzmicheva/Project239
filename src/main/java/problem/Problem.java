package main.java.problem;

import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.media.opengl.GL2;


public class Problem {

	/**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задан треугольник и еще множество точек.\n" +
            "Найти такие две точки множества, что прямая, проходящая через эти две\r\n" + 
            "точки, пересекает треугольник, и при этом отрезок этой прямой\n"+
            "треугольника, оказывается наибольшей длины.";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика ??????";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME_POINTS = "points.txt";
    private static final String FILE_NAME_TRIANGLE = "triangle.txt";

    /**
     * список точек
     */
    private ArrayList<Point> points;
    private  Triangle triangle;
    private  Line line;
    private  Segment segment;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        triangle = new Triangle();
        line= new Line();
        segment= new Segment();
    }
    
    public ArrayList<Point> getPointList(){
    	return points;
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     */
    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }
    
    /**
     * Добавить треугольник
     *
     * @param apex1      первая вершина
     * @param apex2      вторая вершина
     * @param apex3      третья вершина
     */
    public void addTriangle(Point apex1, Point apex2, Point apex3) {
        triangle = new Triangle(apex1, apex2, apex3);
    }
    
    /**
     * Решить задачу
     */
    public void solve() {
    	
    	for(int i=0; i<points.size(); i++) {
    		points.get(i).isSolution=false;
    	}
    	
    	
    	double max=0;
        Point intersectionPoint1 = new Point();
        Point intersectionPoint2 = new Point();
        Point totalPoint1=new Point();
        Point totalPoint2=new Point();
    	if(triangle.getApex1()!=null)
    	  for(int i = 0;i<points.size();i++)
        	for(int j=0; j<=i; j++ ) {
        		if(intersectionExists(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2())!=0
        				&& intersectionExists(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3())!=0) {
        			
        			
        			if(insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()),triangle.getApex1(), triangle.getApex2(), triangle.getApex3())
        					&& insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()), triangle.getApex1(), triangle.getApex2(),triangle.getApex3())){
        				
        				if(getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()),intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()))>max) {
        					
        					max=getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()),intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()));
        					intersectionPoint1=intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2());
        					intersectionPoint2=intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3());
        					totalPoint1=points.get(i);
        					totalPoint2=points.get(j);
        				}
        			}
        		}	
        		if(intersectionExists(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2())!=0
        				&& intersectionExists(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3())!=0) { 
        			
        			
        			if(insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()), triangle.getApex1(),triangle.getApex2(), triangle.getApex3())
        					&& insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3()), triangle.getApex1(), triangle.getApex2(), triangle.getApex3())){
        				
        				if(getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()),intersectionPoint(points.get(i), points.get(j),triangle.getApex2(), triangle.getApex3()))>max) {
        					
        					max=getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2()),intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3()));
        					intersectionPoint1=intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex2());
        					intersectionPoint2=intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3());
        					totalPoint1=points.get(i);
        					totalPoint2=points.get(j);
        				}
        				
        			}
        		}
        		if(intersectionExists(points.get(i), points.get(j),triangle.getApex2(), triangle.getApex3())!=0
        				&& intersectionExists(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3())!=0) { 
        			
        			
        			if(insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3()), triangle.getApex1(), triangle.getApex2(), triangle.getApex3())
        					&& insideTriangle(intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()), triangle.getApex1(), triangle.getApex2(), triangle.getApex3())){
        				
        				if(getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3()),intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()))>max) {
        					
        					max=getDistance(intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3()),intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3()));
        					intersectionPoint1=intersectionPoint(points.get(i), points.get(j), triangle.getApex2(), triangle.getApex3());
        					intersectionPoint2=intersectionPoint(points.get(i), points.get(j), triangle.getApex1(), triangle.getApex3());
        					totalPoint1=points.get(i);
        					totalPoint2=points.get(j);
        				}
        			}
        		}
        	}
    	
    	//System.out.println(totalPoint1+"  "+totalPoint2);
    	
    	totalPoint1.isSolution=true;
    	totalPoint2.isSolution=true;
    	
    	segment = new Segment(intersectionPoint1,intersectionPoint2);
    	
    	
    	line.isSegment=false;
    	Point firstBorderPoint= new Point(getFirstBorderPoint(totalPoint1,totalPoint2).getX(),getFirstBorderPoint(totalPoint1,totalPoint2).getY());
    	Point secondBorderPoint= new Point(getSecondBorderPoint(totalPoint1,totalPoint2).getX(),getSecondBorderPoint(totalPoint1,totalPoint2).getY());
    	line = new Line(firstBorderPoint, secondBorderPoint);
    	
    }
    
    public Point getFirstBorderPoint(Point point1, Point point2) {
    	if((point2.y-point1.y)==0 && point2.y!=0)
    		return new Point(-1,point2.y);
    	
    	return  new Point(point1.x + (point2.x-point1.x)*(1-point1.y)/(point2.y-point1.y),1);
    			 
    }
    
    public Point getSecondBorderPoint(Point point1, Point point2) {
    	if((point2.y-point1.y)==0 && point2.y!=0)
    		return new Point(1,point2.y);
    	return new Point(point1.x + (point2.x-point1.x)*(-1-point1.y)/(point2.y-point1.y),-1);
    }
    
    public double getDistance(Point point1, Point point2) {
		   
		   return Math.sqrt((point1.getX()-point2.getX())*(point1.getX()-point2.getX())
				                +(point1.getY()-point2.getY())*(point1.getY()-point2.getY()));
	   }
    
    public boolean insideTriangle(Point intersectionPoint, Point A1, Point A2,Point A3) {
    	
    	/*double minX=A1.x;
    	if(A2.x<minX) minX=A2.x;
    	if(A3.x<minX) minX=A3.x;
    	
    	double minY=A1.y;
    	if(A2.y<minY) minY=A2.y;
    	if(A3.y<minY) minY=A3.y;
    	
    	double maxX=A1.x;
    	if(A2.x>maxX) maxX=A2.x;
    	if(A3.x>maxX) maxX=A3.x;
    	
    	double maxY=A1.y;
    	if(A2.y>maxY) maxY=A2.y;
    	if(A3.y>maxY) maxY=A3.y;
    	
    	if(intersectionPoint.getX()<minX || intersectionPoint.getX()>maxX) return false;
    	
    	if(intersectionPoint.getY()<minY || intersectionPoint.getY()>maxY) return false;*/
    	
    	Path2D path = new Path2D.Double();
    	double[] xx= {A1.getX(),A2.getX(),A3.getX()};
    	double[] yy= {A1.getY(),A2.getY(),A3.getY()};
    	path.moveTo(xx[0], yy[0]);
    	for(int i = 1; i < xx.length; ++i) {
    	   path.lineTo(xx[i], yy[i]);
    	}
    	
    	path.closePath();
    	
    	if(!path.contains(intersectionPoint.getX(), intersectionPoint.getY())) return false;
    	
    	return true;
    }
    
    public double intersectionExists(Point point1, Point point2, Point apex1, Point apex2) {
    	
        double a1 = point1.getY() - point2.getY();//Y11 - Y12;
        double b1 = point2.getX() - point1.getX();// X12 - X11;
        double a2 = apex1.getY() - apex2.getY();// Y21 - Y22;
        double b2 =  apex2.getX() - apex1.getX();//X22 - X21;
    	
    	return a1 * b2 - a2 * b1; //если равно нулю, то нет пересечения. 
    }
    
    public Point intersectionPoint(Point point1, Point point2, Point apex1, Point apex2) {
    	
    	double a1 = point1.getY() - point2.getY();//Y11 - Y12;
        double b1 = point2.getX() - point1.getX();// X12 - X11;
        double a2 = apex1.getY() - apex2.getY();// Y21 - Y22;
        double b2 =  apex2.getX() - apex1.getX();//X22 - X21;
        double c1=0;
        double c2=0;
    	double	d = a1 * b2 - a2 * b1;
    	if ( d != 0 ) { 
    		c1 = point2.getY()*point1.getX()-point2.getX()*point1.getY();//Y12 * X11 - X12 * Y11;
    	    c2 = apex2.getY()*apex1.getX()-apex2.getX()*apex1.getY();// Y22 * X21 - X22 * Y21;
    	} 
    	double x1=(b1 * c2 - b2 * c1) / d;
    	double y1=(a2 * c1 - a1 * c2) / d;
    	
    	return new Point(x1,y1);
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME_POINTS);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                //int setVal = sc.nextInt();
                if(sc.hasNextLine())
                	sc.nextLine();
                Point point = new Point(x, y);
                points.add(point);
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
        
        try {
            File file = new File(FILE_NAME_TRIANGLE);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                double x3 = sc.nextDouble();
                double y3 = sc.nextDouble();
                if(sc.hasNextLine())
                	sc.nextLine();
               // Point point = new Point(x, y);
                //points.add(point);
                triangle = new Triangle(new Point(x1,y1), new Point(x2,y2),new Point(x3,y3));
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME_POINTS));
            for (Point point : points) {
                out.printf("%.2f %.2f\n", point.x, point.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
        
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME_TRIANGLE));
                out.printf("%.2f %.2f %.2f %.2f %.2f %.2f\n", triangle.getApex1().getX(), triangle.getApex1().getY(),
                		                  triangle.getApex2().getX(), triangle.getApex2().getY(),
                		                  triangle.getApex3().getX(), triangle.getApex3().getY());
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
        }
    }
    
    /**
     * Добавить случайный треугольник
     *
     */
    public void addRandomTriangle() {
    	
        triangle= Triangle.getRandomTriangle();
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        triangle= new Triangle();
        line= new Line();
        segment= new Segment();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Point point : points) {
            point.render(gl);
        }
        triangle.render(gl);
        line.render(gl);
        segment.render(gl);
    }
}
