#version 120

uniform sampler2D sampler;
uniform int green;
uniform int red;
uniform int blue;
uniform int alpha;

varying vec2 tex_coords;

void main(){
    // set màu cho từng pixel

    vec4 tex = texture2D(sampler, tex_coords);
    gl_FragColor = tex + vec4(red, green, blue, alpha);
    
    //  trắng (1,1,1)
    //  đen(0,0,0)
}