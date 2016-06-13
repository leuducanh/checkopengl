/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkopengl.org.joml;
import checkopengl.Model;
import checkopengl.Shader;
import checkopengl.Textture;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.ModelSphere;
import obj.Sphere;
import org.joml.Matrix4f;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author l
 */
public class Main {
   
    
    
    public Main(){
         
        if(glfwInit() != 1){
            System.err.println("GLFW failed!!!!");
            System.exit(1);
        }
        
        long win = glfwCreateWindow(1280, 960, "Window", 0, 0);
        
        glfwShowWindow(win);
        
        
        glfwMakeContextCurrent(win); //saying hey i need a context :v set window has context
        
        GL.createCapabilities(); //create context thay the opengl context
        
        

Textture tex = new Textture(new File("res\\Win.png").getAbsolutePath()+"");
   Textture tex0 = new Textture(new File("res\\" + "night.jpg").getAbsolutePath());
        
        
        
        
        float[]vertices0 = {
            -1f, 1f,0,  // top left 1
            1f, 1f,0, // top right 2
            1f, -1f,0, // bottom right 3
              -1f, -1f,0, // bottom left 4
        };
        float[]texture0 = {
            0,0, //đỉnh trái
            1,0,// đỉnh phải
            1,1,// đáy phải
            0,1,// đáy trái
        };
      int[] indices0 = new int[]{
            0,1,2,
            2,3,0,
      };
        glEnable(GL_TEXTURE_2D);
        

                float[] vertices;
        vertices = new float[]{

//            -1f, 0.5f,0,  // top left 1
//            0f, 0.5f,0, // top right 2
//            0f, -0.5f,0, // bottom right 3
//              -1f, -0.5f,0, // bottom left 4
                                -0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
        };
        float[] texture = new float[]{
//            0,0, //đỉnh trái
//            1,0,// đỉnh phải
//            1,1,// đáy phải
//            0,1,// đáy trái
            0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0
        };
        int[] indices = new int[]{
//            0,1,2,
//            2,3,0,
            0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

        };
        Model model = new Model(vertices,texture,indices);
        Model model0 = new Model(vertices0,texture0,indices0);
        
            Shader shader = new Shader("shader");
            
        int red = 1;
        int green = 0;
        int blue = 0;
        int alpha = 0;
            
        int i = 1;
        int flag = 0;
        double angdeg = 0f;
        float increase = -0.5f;
        float scale = 1;
        while(glfwWindowShouldClose(win) != 1){
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();
            if(glfwGetKey(win, GLFW_KEY_ESCAPE) == 1){
                glfwDestroyWindow(win);
                break;
            }

           angdeg += 0.05f;

            if (flag == 0) {
                i++;
                increase += 0.004f;
            } else {
                
                if (flag == 1) {
                    i++;
                    angdeg += 0.008f;
                    increase -= 0.0015f;
                }
                if (flag == 2) {
                    i++;
                    scale += 0.0025;
                }

            }
            if (i == 1000) {
                flag = 1;
            }
            if (i == 2000) {
                flag = 2;
            }
            
            if (i == 2300) {
                flag = 3;
            }
            if(flag == 3){
                i = 400;
            }
            
            if (i % 400 == 0) {
                Random r = new Random();

                red = r.nextInt(2);
                green = r.nextInt(2);
                blue = r.nextInt(2);
                alpha = r.nextInt(2);
                if (red == green && red == blue && red == 1) {
                    red = 0;
                }
            }
            //Ma trận quay theo trục XYZ
            Matrix4f rotateMatrix = new Matrix4f().rotateXYZ(0, (float) Math.toRadians(angdeg), (float) Math.toRadians(angdeg));
            //Ma trận tỉ lệ
            Matrix4f scaleMatrix = new Matrix4f().scale((float) (scale/3));
            //Ma trận tịnh tiến
            Matrix4f translateMatrix = new Matrix4f().translate(increase - 2f, 0, 0);

            Matrix4f transportationMatrix = new Matrix4f();
            rotateMatrix.mul(scaleMatrix, transportationMatrix);
            transportationMatrix.mul(translateMatrix, transportationMatrix);

            shader.bind();// tạo chương trình kết nối với gpu, đọc file vs và fs + truyền đầu vào các Array_buffer cho vs
            shader.setUniform("sampler", 0); // truyền vật thể ảnh số 0 vào biến sampler
            shader.setUniform("red", red);
            shader.setUniform("green", green);
            shader.setUniform("blue", blue);
            shader.setUniform("alpha", alpha);
            shader.setUniform("transportationMatrix", transportationMatrix);
            tex.bind(0); // đọc ảnh và tạo 1 bản sao của vật thể ảnh chính là vật thể ảnh số 0
            model.render();// cho phép các Array_buffer được file vs truy cập và vẽ chúng theo thứ tự cho trước

            if (flag < 2) {
                Matrix4f transportationMatrix2 = new Matrix4f();
                Matrix4f translateMatrix2 = new Matrix4f().translate(2f - increase, 0, 0);
                Matrix4f rotateMatrix2 = new Matrix4f().rotateXYZ(0, (float) Math.toRadians(angdeg), (float) Math.toRadians(angdeg));
                rotateMatrix2.mul(scaleMatrix, transportationMatrix2);
                
                shader.setUniform("sampler", 0);
                transportationMatrix2.mul(translateMatrix2, transportationMatrix2);
                shader.setUniform("transportationMatrix", transportationMatrix2);
                model.render();
            }

            shader.bind();
            shader.setUniform("sampler", 1);
            Matrix4f a = new Matrix4f().translate(0, 0, 0.9f); 
            //opengl dùng vùng không gian lập phương NDC
            //(Normal Device Coordination) được giới hạn từ [-1, -1, -1] tới [1, 1, 1], z = -1 là ra ngoài, z = 1 vào trong
            shader.setUniform("transportationMatrix", a);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex0.bind(1);
            model0.render();

            glfwSwapBuffers(win);// vẽ rồi đưa ra đầu đưa cái ngoài vào trong rồi vẽ , rồi đưa cái trong ra ngoài
        }
        
        glfwTerminate();
    }
    
    
    
    public static void main(String[] args) {
        new Main();
    }
}
