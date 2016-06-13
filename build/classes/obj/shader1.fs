#version 120

uniform int green;
uniform int red;
uniform int blue;
uniform int alpha;



void main(){
    // set màu cho từng pixel

    gl_FragColor =vec4(red, green, blue, alpha);
    
    //  trắng (1,1,1)
    //  đen(0,0,0)
}