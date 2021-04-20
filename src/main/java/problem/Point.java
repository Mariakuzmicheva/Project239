package main.java.problem;

import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;



public class Point {

    boolean isSolution = false;
    /**
     * x - координата точки
     */
    double x;
    /**
     * y - координата точки
     */
    double y;

    /**
     * Конструктор точки
     *
     * @param x         координата
     * @param y         координата y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Point() {}
    
    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }
    
    /**
     * Получить случайную точку
     *
     * @return случайная точка
     */
    static Point getRandomPoint() {
        Random r = new Random();
        double nx = (double) r.nextInt(50) / 25 - 1; 
        double ny = (double) r.nextInt(50) / 25 - 1;
        return new Point(nx, ny);
    }
    
    /**
     * Рисование точки
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl) {
        if (isSolution) {
        	gl.glPointSize(10);
            gl.glColor3d(1.0, 0.0, 0.0);
        }
        else {
        	gl.glColor3d(0.0, 0.0, 1.1);
        	gl.glPointSize(3);	
        }
        
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }
    
    /**
     * Получить строковое представление точки
     *
     * @return строковое представление точки
     */
    @Override
    public String toString() {
        return "Точка с координатами: {" + x + "," + y + "}";
    }
}
