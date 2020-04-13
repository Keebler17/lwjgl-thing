package io.github.keebler17.astrodynamics.obj;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import de.matthiasmann.twl.utils.PNGDecoder;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

	public int id;

	public int width;
	public int height;

	public Texture(URL png, int filter, int wrap) {
		InputStream pngStream = null;

		try {
			pngStream = png.openStream();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		PNGDecoder decoder = null;
		
		try {
			decoder = new PNGDecoder(pngStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		width = decoder.getWidth();
		height = decoder.getHeight();

		ByteBuffer buffer = BufferUtils.createByteBuffer(4 * width * height);
		try {
			decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
		} catch (IOException e) {
			e.printStackTrace();
		}

		buffer.flip();

		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		id = glGenTextures();

		this.bind();

		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		try {
			pngStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}


}
