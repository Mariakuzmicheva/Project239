package main.java.problem;

import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Triangle {
	
	
	Point apex1;
	Point apex2;
	Point apex3;

	public Triangle(Point apex1, Point apex2, Point apex3) {
		this.apex1 = apex1;
		this.apex2 = apex2;
		this.apex3 = apex3;
	}
	
	public Triangle() {}
	
	public Point getApex1() {
		return apex1;
	}
	
	public Point getApex2() {
		return apex2;
	}
	
	public Point getApex3() {
		return apex3;
	}
	
    /**
     * Получить случайный треугольник
     *
     * @return случайный треугольник
     */
    static Triangle getRandomTriangle() {
        Random r = new Random();
        double apex1x = (double) r.nextInt(50) / 25 - 1; 
        double apex1y = (double) r.nextInt(50) / 25 - 1;
        double apex2x = (double) r.nextInt(50) / 25 - 1; 
        double apex2y = (double) r.nextInt(50) / 25 - 1;
        double apex3x = (double) r.nextInt(50) / 25 - 1; 
        double apex3y = (double) r.nextInt(50) / 25 - 1;
        
        return new Triangle(new Point(apex1x,apex1y), new Point(apex2x,apex2y),new Point(apex3x,apex3y));
    }
	
    /**
     * Рисование треугольника
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl) {
            gl.glColor3d(0.0, 1.0, 0.0);
            
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glColor3d(0.0,1.0,0.0);      // рисуем треугольник
            if(apex1!=null && apex2!=null && apex3!=null) {
            	gl.glVertex2d(apex1.x,apex1.y);
            	gl.glVertex2d(apex2.x,apex2.y);
            	gl.glVertex2d(apex3.x,apex3.y);
            }
            gl.glEnd();
            gl.glLineWidth(1);
    }
}
