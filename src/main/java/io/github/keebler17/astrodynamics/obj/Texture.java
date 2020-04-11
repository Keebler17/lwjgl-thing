package io.github.keebler17.astrodynamics.obj;

import java.net.URL;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture {
	
	public final int id;
	
	public final int width;
	public final int height;

	public Texture(URL png, int filter, int wrap) {
		try {
			PNGDecoder decoder = new PNGDecoder(png.openStream());
			
			d
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
