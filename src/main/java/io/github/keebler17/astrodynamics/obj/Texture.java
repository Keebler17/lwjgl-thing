package io.github.keebler17.astrodynamics.obj;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	public final int id;
	
	public final int width;
	public final int height;

	public Texture(URL png, int filter, int wrap) {
		InputStream pngStream = null;
		
		try {
			pngStream = png.openStream();
			
			PNGDecoder decoder = new PNGDecoder(pngStream);
			
			width = decoder.getWidth();
			height = decoder.getHeight();
			
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(4 * width * height);
			decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
			
			buffer.flip();
			
			
			glEnable(GL_TEXTURE_2D);
			id = glGenTextures();
			
			this.bind();
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pngStream != null) {
				try {
					pngStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
}
