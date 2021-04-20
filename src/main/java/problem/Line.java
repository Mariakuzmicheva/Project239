package main.java.problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Line {

	Point point1;
	Point point2;
boolean isSegment=false;
	
	public Line(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public Line() {}
	/**
     * Рисование линии
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl) {
            gl.glPolygonMode(2,1); 
            gl.glBegin(GL.GL_LINE_LOOP);
            
            if(point1!=null && point2!=null) {
            	gl.glVertex2d(point1.x,point1.y);
            	gl.glVertex2d(point2.x,point2.y);
            }	
            gl.glEnd();
            gl.glLineWidth(1);

    }
}
