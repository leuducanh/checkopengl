/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkopengl;

import obj.Sphere;
import obj.ShaderSphere;
import obj.ModelSphere;
import java.io.File;
import java.util.Random;
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
public class Main4 {

    public Main4() {
        if (glfwInit() != 1) {
            System.err.println("GLFW failed!!!!");
            System.exit(1);
        }

        long win = glfwCreateWindow(1280, 960, "Window", 0, 0);

        glfwShowWindow(win);

        glfwMakeContextCurrent(win); //saying hey i need a context :v set window has context

        GL.createCapabilities(); //create context thay the opengl context

        Textture tex = new Textture(new File("res\\Win.png").getAbsolutePath() + "");
        Textture tex0 = new Textture(new File("res\\" + "night.jpg").getAbsolutePath());

        float[] vertices0 = {
            -1f, 1f, 0, // top left 1
            1f, 1f, 0, // top right 2
            1f, -1f, 0, // bottom right 3
            -1f, -1f, 0, // bottom left 4
        };

        float[] vertices1 = { //cho hinh vuong
            -0.5f, 0.5f, 0, // top left 1
            0.5f, 0.5f, 0, // top right 2
            0.5f, -0.5f, 0, // bottom right 3
            -0.5f, -0.5f, 0, // bottom left 4
        };

        float[] texture0 = {
            0, 0, //đỉnh trái
            1, 0,// đỉnh phải
            1, 1,// đáy phải
            0, 1,// đáy trái
        };
        int[] indices0 = new int[]{
            0, 1, 2,
            2, 3, 0,};
        glEnable(GL_TEXTURE_2D);

        float[] vertices;
        vertices = new float[]{
            //            -1f, 0.5f,0,  // top left 1
            //            0f, 0.5f,0, // top right 2
            //            0f, -0.5f,0, // bottom right 3
            //              -1f, -0.5f,0, // bottom left 4
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            
            -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            
            -0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, 0.5f,
            
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f
        };
        float[] texture = new float[]{
            //            0,0, //đỉnh trái
            //            1,0,// đỉnh phải
            //            1,1,// đáy phải
            //            0,1,// đáy trái
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };

        int[] indices = new int[]{
            //            0,1,2,
            //            2,3,0,
            0, 1, 3,
            3, 1, 2,
            4, 5, 7,
            7, 5, 6,
            8, 9, 11,
            11, 9, 10,
            12, 13, 15,
            15, 13, 14,
            16, 17, 19,
            19, 17, 18,
            20, 21, 23,
            23, 21, 22

        };
        Sphere sphere = new Sphere(50, 0.5f);
        
        ModelSphere modelSphere = new ModelSphere(sphere.getVertices(), sphere.getIndices());
        Model model = new Model(vertices, texture, indices);    // khối vuông
        Model model0 = new Model(vertices0, texture0, indices0); // cho ảnh nền
        Model model1 = new Model(vertices1, texture0, indices0); // dành cho ảnh vuông

        Shader shader = new Shader("shader");
        ShaderSphere shaderSphere = new ShaderSphere("shader1");

       
        while (glfwWindowShouldClose(win) != 1) {
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();
            if (glfwGetKey(win, GLFW_KEY_ESCAPE) == 1) {
                glfwDestroyWindow(win);
                break;
            }

            ovuongquay(tex, model1, shader, win, model0, tex0);
            khoivuongquay(tex, model, shader, win, model0, tex0);
            hien4khoivuong(tex, model, shader, win, model0, tex0);
            hienkhoitron(tex,model,shader,win,model0,tex0);
            
            glfwSwapBuffers(win);// vẽ rồi đưa ra đầu đưa cái ngoài vào trong rồi vẽ , rồi đưa cái trong ra ngoài
        }

        glfwTerminate();
    }

    public void ovuongquay(Textture tex, Model model, Shader shader, long win, Model model0, Textture tex0) {

        float i = 0;
        float t = 0;
        float dichuyen1 = 0;
        float dichuyen2 = 0;
        float dichuyen3 = 0;
        float goc1 = 0;
        float goc2 = 0;
        float goc3 = 0;
        int flag = 0;
        int a = 1;
        while (i > -1) {
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();

            if (i <= 1f / 0.01) {
                dichuyen1 += 0.01;
                dichuyen2 += 0.01;
                dichuyen3 += 0.01;
                if(i == 1f/0.01){
                    flag = 1;
                }
            }
            if (i <= 2.5f / 0.01 && flag == 1) {
                dichuyen2 += 0.01;
                dichuyen3 += 0.01;
                if(i == 2.5f/0.01){
                    flag = 2;
                }
            }
            if (i <= 4f / 0.01 && flag == 2) {
                dichuyen3 += 0.01;
                if (i == 4f / 0.01) {
                    flag = 3;
                }
            }
            
            if(flag >= 3){
                if(goc1 != 180f){
                    goc1 += 1f;
                }
                if(goc1 == 180f && goc2 != 180f){
                    goc2 += 1f;
                }
                if(goc1 == 180f && goc2 == 180f && goc3 != 180f){
                    goc3 += 1f;
                }
                if(goc1 == 180f && goc2 == 180f && goc3 == 180f){
                    flag = 4;
                }
            }
            
            if(flag == 4){
                if(t <= 1.5f/0.01){
                    dichuyen1 += 0.01;
                    dichuyen3 -= 0.01;
                    t++;
                }else if(t <= 2.5f/0.005){
                    goc1 += 1f; // goc1 đã khác 180f, chạy câu lệnh if trên
                    goc2 = goc1;
                    goc3 = goc2;
                    t++;
                }else{
                    flag = 5;
                }
            }
            
            if(goc1 == goc2 && goc2 == goc3 && goc2 > 180f){
        }

            Matrix4f scalematrix = new Matrix4f().scale((float)(.5f));
            Matrix4f translate1 = new Matrix4f().translate(-2.5f + dichuyen1, 0, 0);
            Matrix4f translate2 = new Matrix4f().translate(-2.5f + dichuyen2, 0, 0);
            Matrix4f translate3 = new Matrix4f().translate(-2.5f + dichuyen3, 0, 0);

            Matrix4f transportation1 = new Matrix4f();
            Matrix4f transportation2 = new Matrix4f();
            Matrix4f transportation3 = new Matrix4f();
            
            scalematrix.mul(translate1,transportation1);
            scalematrix.mul(translate2,transportation2);
            scalematrix.mul(translate3,transportation3);

            shader.bind();
            shader.setUniform("sampler", 0); // truyền vật thể ảnh số 0 vào biến sampler
            tex.bind(0);
            
            if(flag >= 3){
                Matrix4f rotateMatrix1 = new Matrix4f().rotateXYZ((float)Math.toRadians(goc1),(float)Math.toRadians(goc1),0);
                transportation1.mul(rotateMatrix1,transportation1);
                
                Matrix4f rotateMatrix2 = new Matrix4f().rotateXYZ((float)Math.toRadians(goc2),(float)Math.toRadians(goc2),0);
                transportation2.mul(rotateMatrix2,transportation2);
                
                Matrix4f rotateMatrix3 = new Matrix4f().rotateXYZ((float)Math.toRadians(goc3),(float)Math.toRadians(goc3),0);
                transportation3.mul(rotateMatrix3,transportation3);
            }
            
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            if(goc1 >= 180f){
                shader.setUniform("red", 1);
            }
            shader.setUniform("transportationMatrix", transportation1);
            model.render();

            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            if(goc2 >= 180f){
                shader.setUniform("green", 1);
            }
            shader.setUniform("transportationMatrix", transportation2);
            model.render();
            
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            if(goc3 >= 180f){
                shader.setUniform("blue", 1);
            }
            shader.setUniform("transportationMatrix", transportation3);
            model.render();

           
            
            shader.bind();
            shader.setUniform("sampler", 1);
            Matrix4f matrixAnhnen = new Matrix4f().translate(0, 0, 0.9f); 
            //opengl dùng vùng không gian lập phương NDC
            //(Normal Device Coordination) được giới hạn từ [-1, -1, -1] tới [1, 1, 1], z = -1 là ra ngoài, z = 1 vào trong
            shader.setUniform("transportationMatrix", matrixAnhnen);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex0.bind(1);
            model0.render();
            
             i++;
            glfwSwapBuffers(win);
            if(flag == 5){
                return;
            }

        }
    }

    public void khoivuongquay(Textture tex, Model model, Shader shader, long win, Model model0, Textture tex0) {

        float i = 0;
        float dichuyen1 = 0;
        float goc1 = 0;
        int flag = 0;
        
        int red = 0;
        int blue = 0;
        int green = 0;
        int alpha = 0;
        
        while (i > -1) {
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();
            
            goc1 += .5f;
            if (i % 7 == 0) {
                Random r = new Random();

                red = r.nextInt(2);
                green = r.nextInt(2);
                blue = r.nextInt(2);
                alpha = r.nextInt(2);
                if (red == green && red == blue && red == 1) {
                    red = 0;
                }
            }
            
            if(i >= 350){
                goc1 = 0;
                if(dichuyen1 <= 1.5f){
                    dichuyen1 += 0.01;
                }else{
                    return;
                }
            }
            
          
            Matrix4f transportationMatrix = new Matrix4f();
             Matrix4f rotateMatrix1 = new Matrix4f().rotateXYZ((float)Math.toRadians(goc1),(float)Math.toRadians(goc1),0);
             Matrix4f scaleMatrix1 = new Matrix4f().scale((float)0.5f);
             Matrix4f translateMatrix1 = new Matrix4f().translate(0 - dichuyen1,0,0);
             
             scaleMatrix1.mul(translateMatrix1,transportationMatrix);
             transportationMatrix.mul(rotateMatrix1,transportationMatrix);
             
            shader.bind();
            shader.setUniform("sampler", 0); // truyền vật thể ảnh số 0 vào biến sampler
            tex.bind(0);
            
            shader.setUniform("red", red);
            shader.setUniform("green", green);
            shader.setUniform("blue", blue);
            if(goc1 == 180f){
                shader.setUniform("red", 1);
            }
            shader.setUniform("transportationMatrix", transportationMatrix);
            model.render();
            
            i++;
            
            shader.bind();
            shader.setUniform("sampler", 1);
            Matrix4f matrixAnhnen = new Matrix4f().translate(0, 0, 0.9f); 
            //opengl dùng vùng không gian lập phương NDC
            //(Normal Device Coordination) được giới hạn từ [-1, -1, -1] tới [1, 1, 1], z = -1 là ra ngoài, z = 1 vào trong
            shader.setUniform("transportationMatrix", matrixAnhnen);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex0.bind(1);
            model0.render();

            glfwSwapBuffers(win);
            

        }        
    }
    
    public void hien4khoivuong(Textture tex, Model model, Shader shader, long win, Model model0, Textture tex0) {
        
        float i = 0;
        float dichuyenX = 0;
        float dichuyenY = 0;
        float dichuyenZ = 0;
        float scale = 0;
        float goc1 = 0;
        int flag = 0;
        
        int red = 0;
        int blue = 0;
        int green = 0;
        int alpha = 0;
        while (i > -1) {
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();
            
            if (i % 7 == 0) {
                Random r = new Random();

                red = r.nextInt(2);
                green = r.nextInt(2);
                blue = r.nextInt(2);
                alpha = r.nextInt(2);
                if (red == green && red == blue && red == 1) {
                    red = 0;
                }
            }
            
            dichuyenX += 0.001f;
            dichuyenY += 0.001f;
            
            goc1 += 0.5f; 
            scale += 0.0005f;
            if(scale >= 0.25f){
                return;
            }

            Matrix4f transportationMatrix1 = new Matrix4f();
            Matrix4f transportationMatrix2 = new Matrix4f();
            Matrix4f transportationMatrix3 = new Matrix4f();
            Matrix4f transportationMatrix4 = new Matrix4f();
            
             Matrix4f rotateMatrix = new Matrix4f().rotateXYZ((float)Math.toRadians(goc1),(float)Math.toRadians(goc1),0);
             Matrix4f scaleMatrix1 = new Matrix4f().scale((float)(0.25f + scale)); // phóng to 2 cái trên
             Matrix4f scaleMatrix2 = new Matrix4f().scale((float)(0.25f - scale)); // thu nhỏ 2 cái dưới
             
             Matrix4f translateMatrix1 = new Matrix4f().translate(-2.5f-dichuyenX,.5f+dichuyenY,0); // trên
             Matrix4f translateMatrix2 = new Matrix4f().translate(-3.5f-dichuyenX,.5f+dichuyenY,0); // trên
             Matrix4f translateMatrix3 = new Matrix4f().translate(-2.5f+dichuyenX,-.5f-dichuyenY,0);// dưới 
             Matrix4f translateMatrix4 = new Matrix4f().translate(-3.5f+dichuyenX,-.5f+dichuyenY,0);// dưới
             
            scaleMatrix1.mul(translateMatrix1,transportationMatrix1);
            transportationMatrix1.mul(rotateMatrix,transportationMatrix1);
            
            scaleMatrix1.mul(translateMatrix2,transportationMatrix2);
            transportationMatrix2.mul(rotateMatrix,transportationMatrix2);
            
            scaleMatrix2.mul(translateMatrix3,transportationMatrix3);
            transportationMatrix3.mul(rotateMatrix,transportationMatrix3);
            
            scaleMatrix2.mul(translateMatrix4,transportationMatrix4);
            transportationMatrix4.mul(rotateMatrix,transportationMatrix4);
            
            shader.bind();
            shader.setUniform("sampler", 0); // truyền vật thể ảnh số 0 vào biến sampler
            tex.bind(0);
            
            shader.setUniform("red", red);
            shader.setUniform("green", green);
            shader.setUniform("blue", blue);
            
            
            shader.setUniform("transportationMatrix", transportationMatrix1);
            model.render();
            
            shader.setUniform("transportationMatrix", transportationMatrix2);
            model.render();
            
            shader.setUniform("transportationMatrix", transportationMatrix3);
            model.render();
            
            shader.setUniform("transportationMatrix", transportationMatrix4);
            model.render();
            i++;
            
            shader.bind();
            shader.setUniform("sampler", 1);
            Matrix4f matrixAnhnen = new Matrix4f().translate(0, 0, 0.9f); 
            //opengl dùng vùng không gian lập phương NDC
            //(Normal Device Coordination) được giới hạn từ [-1, -1, -1] tới [1, 1, 1], z = -1 là ra ngoài, z = 1 vào trong
            shader.setUniform("transportationMatrix", matrixAnhnen);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex0.bind(1);
            model0.render();

            glfwSwapBuffers(win);
            

        }      
    }

    public void hienkhoitron(Textture tex, Model model, Shader shader, long win, Model model0, Textture tex0) {
        
        float i = 0;
        float dichuyen1 = 0;
        float dichuyen2 = 0;
        float goc1 = 0;
        int flag = 0;
        
        int red = 0;
        int blue = 0;
        int green = 0;
        int alpha = 0;
        float scale = 0;
        while (i > -1) {
            glEnable(GL11.GL_DEPTH_TEST); // để open gl nhận biết cái nào vẽ trước
            glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// đưa toàn bộ hình về đen
            glfwPollEvents();
            
            goc1 += .5f;
            if (i % 7 == 0) {
                Random r = new Random();

                red = r.nextInt(2);
                green = r.nextInt(2);
                blue = r.nextInt(2);
                alpha = r.nextInt(2);
                if (red == green && red == blue && red == 1) {
                    red = 0;
                }
            }
            
            if(dichuyen1 <= 2.5f){
                dichuyen1 += 0.01f;
            }
            
            float n = (float) (2*Math.sqrt(2f*2f*2));
            
            if(dichuyen2 <= n){
                dichuyen2 += 0.01f;
                if(dichuyen2 < n/2){
                    scale += 0.01;
                }else{
                    scale -= 0.01;
                }
            }else{
                dichuyen2 = 0;
                scale = 0;
            }
            
            Matrix4f transportationMatrixSphere = new Matrix4f();
            Matrix4f rotateMatrixSphere = new Matrix4f().rotateXYZ(0,(float)Math.toRadians(-goc1),0);
            Matrix4f scaleMatrixSphere = new Matrix4f().scale((float)0.5f);
            Matrix4f translateMatrixSphere = new Matrix4f().translate(-2.5f + dichuyen1,0,.8f);
             
             scaleMatrixSphere.mul(translateMatrixSphere,transportationMatrixSphere);
             transportationMatrixSphere.mul(rotateMatrixSphere,transportationMatrixSphere);
           // hình vuông quay
            shader.bind();
            shader.setUniform("sampler", 0); // truyền vật thể ảnh số 0 vào biến sampler
            shader.setUniform("transportationMatrix", transportationMatrixSphere);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex.bind(0);
            model.render();
             
            Matrix4f transportationMatrix = new Matrix4f();
            Matrix4f scaleMatrixCoDinh = new Matrix4f().scale((float)0.25f);
            Matrix4f rotateMatrixCoDinh = new Matrix4f().rotateZ((float)Math.toRadians(-30f));
            Matrix4f translateMatrix = new Matrix4f().translate(dichuyen2,0,0);
            Matrix4f translateMatrixCoDinh = new Matrix4f().translate(-2f,2f,0);
            Matrix4f rotateMatrix = new Matrix4f().rotateX((float)Math.toRadians(goc1));
            Matrix4f scaleMatrix = new Matrix4f().scale(scale);
            
            scaleMatrixCoDinh.mul(translateMatrixCoDinh,transportationMatrix);
            transportationMatrix.mul(rotateMatrixCoDinh,transportationMatrix);
            transportationMatrix.mul(translateMatrix);
            transportationMatrix.mul(rotateMatrix);
            transportationMatrix.mul(scaleMatrix);
            
            shader.setUniform("red", red);
            shader.setUniform("green", green);
            shader.setUniform("blue", blue);
            if(goc1 == 180f){
                shader.setUniform("red", 1);
            }
            shader.setUniform("transportationMatrix", transportationMatrix);
            model.render();
            
            i++;
            
            shader.bind();
            shader.setUniform("sampler", 1);
            Matrix4f matrixAnhnen = new Matrix4f().translate(0, 0, 0.9f); 
            //opengl dùng vùng không gian lập phương NDC
            //(Normal Device Coordination) được giới hạn từ [-1, -1, -1] tới [1, 1, 1], z = -1 là ra ngoài, z = 1 vào trong
            shader.setUniform("transportationMatrix", matrixAnhnen);
            shader.setUniform("red", 0);
            shader.setUniform("green", 0);
            shader.setUniform("blue", 0);
            shader.setUniform("alpha", 0);
            tex0.bind(1);
            model0.render();

            if(glfwGetKey(win, GLFW_KEY_ESCAPE) == 1){
                return;
            }
            
            glfwSwapBuffers(win);
        }
    }        
    
    public static void main(String[] args) {
        new Main4();
    }

    
}
