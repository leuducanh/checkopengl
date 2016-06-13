#version 120

attribute vec3 vertices;
attribute vec2 textures;

varying vec2 tex_coords;

uniform mat4 transportationMatrix;

void main(){
    tex_coords = textures;
    gl_Position = transportationMatrix * vec4(vertices,1); 




    //gl_Position = vec4(vertices,1); // gl_position là vật thể vec4 mà vectices lại là vec3 nên thêm biến 1 cho nó ko đổi
}