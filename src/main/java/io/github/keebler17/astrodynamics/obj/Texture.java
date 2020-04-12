package io.github.keebler17.astrodynamics.obj;

import java.net.URL;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture {
	
	public final int id;
	
	public final int width;
	public final int height;

	public Texture(URL png, int filter, int wrap) {
		try {
			PNGDecoder decoder = new PNGDecoder(png.openStream());
			
			width = decoder.getWidth();
			height = decoder.getHeight();
			
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(4 * width * height);
			decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
			
			buffer.flip();
			
			glEnable(GL_TEXTURE_2D);
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
