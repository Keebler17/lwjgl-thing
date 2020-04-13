package io.github.keebler17.astrodynamics.obj;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Sprite {

	Texture texture;
	
	public final int width;
	public final int height;
	
	public Sprite(Texture texture) {
		this.texture = texture;
		this.width = texture.width;
		this.height = texture.height;
	}
	
	public void draw(float x, float y) {
		glPushMatrix();
		texture.bind();
		glTranslatef(x, y, 0);
		
		glBegin(GL_QUADS);
		
		glTexCoord2f(0f, 0f);	
		glVertex2f(0f, 0f);
		
		glTexCoord2f(0f, 1f);
		glVertex2f(0f, this.height);
		
		glTexCoord2f(1f, 0f);
		glVertex2f(this.width, 0f);
		
		glTexCoord2f(1f, 1f);
		glVertex2f(this.width, this.height);
		
		glEnd();
		
		glPopMatrix();	
	}
}
