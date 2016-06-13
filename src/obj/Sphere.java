/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.File;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Matrix4f;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author l
 */
public class Sphere {
    private float[] vertices;
    private int[] indices;
    private double segments;
    private double radius;
    
    public Sphere(double segments, double radius) {
        this.segments = segments; // the number of division in the sphere
        this.radius = radius; //radius of sphere
        
        Vector<Float> ve = new Vector<Float>();
        Vector<Integer> in = new Vector<Integer>();
        
        double radius1 = 0;
        double radius2 = 0;

        double angle = 0;
        double dAngle = (Math.PI / segments);
        float x = 0;
        float y = 0;
        float z = 0;

            int  k = 0;
            for (int i = 0; i < segments; i++) // lặp vĩ độ
            {
                angle = Math.PI / 2 - i * dAngle;
                radius1 = radius * Math.cos(angle);
                float z1 = (float) (radius * Math.sin(angle));

                angle = Math.PI / 2 - (i + 1) * dAngle;
                radius2 = radius * Math.cos(angle);
                float z2 = (float) (radius * Math.sin(angle));



                for (int j = 0; j <= 2 * segments; j++) // lặp kinh độ
                {
                    double cda = Math.cos(j * dAngle);
                    double sda = Math.sin(j * dAngle);
                    
                    x = (float) (radius1 * cda);
                    y = (float) (radius1 * sda);
                    
                    ve.add(x);
                    in.add(k);
                    k++;
                    ve.add(y);
                    in.add(k);
                    k++;
                    ve.add(z1);
                    in.add(k);
                    k++;
                    
                    x = (float) (radius2 * cda);
                    y = (float) (radius2 * sda);
                    
                    ve.add(x);
                    in.add(k);
                    k++;
                    ve.add(y);
                    in.add(k);
                    k++;
                    ve.add(z1);
                    in.add(k);
                    k++;
                }
                
                vertices = new float[ve.size()];
                indices = new int[in.size()];
                
                for(int m = 0; m < ve.size();m++){
                    vertices[m] = ve.get(m);
                }
                 for(int m = 0; m < in.size();m++){
                    indices[m] = in.get(m);
                }
        }

    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    
    
    public static void main(String[] args) {
    }
}
