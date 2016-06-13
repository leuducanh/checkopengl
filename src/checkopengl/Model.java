/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkopengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javafx.beans.binding.FloatBinding;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author l
 */
public class Model {

    private int draw_count;
    private int v_id;

    private int t_id;
    
    private int i_id;

    public Model(float[] vertices, float[] tex_coords, int[] indices) {
        draw_count = indices.length;


        v_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, v_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);

        t_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, t_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords), GL_STATIC_DRAW);

        i_id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
        
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void render() {
        glEnableVertexAttribArray(0); // cho phép render trên graphic card, truy cập vào mảng thuộc tính mã 0 là mảng tọa độ điểm
        glEnableVertexAttribArray(1); //  cho phép render, truy cập vào mảng thuộc tính mã 1 là mảng tọa độ dệt ảnh

        glBindBuffer(GL_ARRAY_BUFFER, v_id);
//       glVertexPointer(3, GL_FLOAT, 0, 0); // vẽ điểm 3: số tọa độ xác định 1 đỉnh, Float: kiểu dữ liệu, hàm định vị điểm [chỉ ra địa điểm và cách tổ chức của mảng điểm]
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, t_id);
//        glTexCoordPointer(2, GL_FLOAT, 0, 0); // mỗi đỉnh là 2 phần tử thuộc mảng, [ hàm định vị hệ tọa độ dệt ảnh ]
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
        glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0); // thực hiện chuỗi hành động vẽ [draw_count: số điểm muốn vẽ]
        
//        glDrawArrays(GL_TRIANGLES, 0, draw_count); // vẽ tam giác mỗi số tam giác là draw count
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        
    }

    private FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public int getV_id() {
        return v_id;
    }

    public int getT_id() {
        return t_id;
    }

    public int getI_id() {
        return i_id;
    }
    
}
