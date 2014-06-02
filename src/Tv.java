/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import java.io.File;

import javax.media.j3d.*;
import javax.sound.midi.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;


public class Tv //class for our tv
{
	Transform3D tvTransform;
	TransformGroup tv;
	Sequencer sequencer;	//for midi
	Texture screenTex;	//for turning on/off tv
	Appearance screenApp;
	
	public Tv()
	{
		tvTransform = new Transform3D();
		tv = new TransformGroup();
	}
	
	@SuppressWarnings("deprecation")
	public TransformGroup getTv()
	{
		Appearance tvApp = new Appearance();
		Material tvMat = new Material();		//sets a material also to allow shadings
		tvMat.setDiffuseColor(new Color3f(.1f,.1f,.1f));
		tvMat.setSpecularColor(new Color3f(0f,0f,0f));
		tvMat.setShininess(.2f);
		tvApp.setMaterial(tvMat);
		
		Box back = new Box(1.4f,1f,.1f,tvApp);		//back of telly
		TransformGroup backTg = new TransformGroup();
		backTg.addChild(back);
		tvTransform.rotY(1.56);
		tvTransform.setTranslation(new Vector3f(7.4f,3f,0f));
		backTg.setTransform(tvTransform);
		tv.addChild(backTg);
		
		Box front = new Box(1.8f,1.3f,.1f,tvApp);	//front of telly
		TransformGroup frontTg = new TransformGroup();
		frontTg.addChild(front);
		tvTransform.rotY(1.56);
		tvTransform.setTranslation(new Vector3f(7.2f,3f,0f));
		frontTg.setTransform(tvTransform);
		tv.addChild(frontTg);
		
		QuadArray screen = new QuadArray(4,QuadArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);	//screen where image will be displayed
		screen.setCoordinate(0,new Point3f(0f,-2.3f,0f));
		screen.setCoordinate(1,new Point3f(3.3f,-2.3f,0f));
		screen.setCoordinate(2,new Point3f(3.3f,0f,0f));
		screen.setCoordinate(3,new Point3f(0f,0f,0f));
		screen.setTextureCoordinate(0,new Point2f(0f,0f));
		screen.setTextureCoordinate(1,new Point2f(1f,0f));
		screen.setTextureCoordinate(2,new Point2f(1f,1f));
		screen.setTextureCoordinate(3,new Point2f(0f,1f));
		
		GeometryInfo screenGI = new GeometryInfo(screen);	//normals need to be calculated for shading
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(screenGI);
		GeometryArray screenResult = screenGI.getGeometryArray();
		
		screenApp = new Appearance();
		screenApp.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		TextureLoader screenLoader = new TextureLoader (getClass().getResource("img/screen.jpg"), null);	//loads in our image
		screenTex = screenLoader.getTexture ();
		TextureAttributes screenTa = new TextureAttributes ();
		screenTa.setTextureMode(TextureAttributes.MODULATE);
		screenTa.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
		Material screenMat = new Material();		//sets a material also to allow shadings
		screenMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		screenMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		screenMat.setShininess(.2f);
		screenApp.setMaterial(screenMat);
				
		TransformGroup screenTg = new TransformGroup();
		screenTg.addChild(new Shape3D(screenResult,screenApp));
		tvTransform.rotY(-1.59);
		tvTransform.setTranslation(new Vector3f(7.1f,4.15f,-1.65f));
		screenTg.setTransform(tvTransform);
		tv.addChild(screenTg);
		
		return tv;
	}
	public void activate()	//tv on
	{	
		screenApp.setTexture(screenTex);
		try {
	        // From file
	        Sequence sequence = MidiSystem.getSequence(new File("img/sound.mid"));	//deadly tunes
	    
	    
	        // Create a sequencer for the sequence
	        sequencer = MidiSystem.getSequencer();
	        sequencer.open();
	        sequencer.setSequence(sequence);
	    
	        // Start playing
	        sequencer.start();
	    } catch (Exception e){}
	}
	public void deActivate()//tv off
	{
		screenApp.setTexture(null);
		sequencer.stop();
	}
}
