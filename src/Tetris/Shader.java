package Tetris;

import org.lwjgl.system.CallbackI;

import java.io.*;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {
    public static int load(String vertexShader, String fragmentShader){
        int VertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        int FragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);

        CharSequence VertexShaderCode = readFile(vertexShader);
        CharSequence FragmentShaderCode = readFile(fragmentShader);

        glShaderSource(VertexShaderID, VertexShaderCode);
        glShaderSource(FragmentShaderID, FragmentShaderCode);

        glCompileShader(VertexShaderID);
        glCompileShader(FragmentShaderID);

        if(glGetShaderi(VertexShaderID, GL_COMPILE_STATUS) != GL_TRUE)
            throw new RuntimeException(glGetShaderInfoLog(VertexShaderID));
        if(glGetShaderi(FragmentShaderID, GL_COMPILE_STATUS) != GL_TRUE)
            throw new RuntimeException(glGetShaderInfoLog(FragmentShaderID));

        int ProgramID = glCreateProgram();
        glAttachShader(ProgramID, VertexShaderID);
        glAttachShader(ProgramID, FragmentShaderID);
        glLinkProgram(ProgramID);

        if(glGetProgrami(ProgramID, GL_LINK_STATUS)!=GL_TRUE){
            throw new RuntimeException(glGetProgramInfoLog(ProgramID));
        }

        glDetachShader(ProgramID, VertexShaderID);
        glDetachShader(ProgramID, FragmentShaderID);

        glDeleteShader(VertexShaderID);
        glDeleteShader(FragmentShaderID);

        return ProgramID;
    }

    public static CharSequence readFile(String path) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                    + System.lineSeparator() + ex.getMessage());
        }
        return builder.toString();
    }
}
