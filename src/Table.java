/* Ross Andreucetti
 * 58432481
 * CASE 4
 */
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;

//class to create a table
class Table
{
	Transform3D tableTransform; //used later to transform table parts
	TransformGroup table;  //a single group for all parts of table
	Appearance app;  //appearance
	Material mat;	//table material, oak
	
	public Table()	//our constructor
	{
		tableTransform = new Transform3D();
		table = new TransformGroup();
		app = new Appearance ();	//apearance of our table
		TextureLoader loader = new TextureLoader (getClass().getResource("img/Oak.jpg"), null);	//loads in our image
		Texture tex = loader.getTexture ();
		TextureAttributes ta = new TextureAttributes ();
		ta.setTextureMode(TextureAttributes.MODULATE);
		mat = new Material();		//sets a material also to allow shadings
		mat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		mat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		mat.setShininess(.2f);
		app.setMaterial(mat);
		app.setTextureAttributes (ta);
		app.setTexture (tex);
	}
	
	public TransformGroup getTable()  //returns the assembled table
	{
		//tabletop
		Cylinder cylinder1 = new Cylinder(1.5f,0.2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);	//applies appearance
		TransformGroup tableTopTG = new TransformGroup();	
		tableTopTG.addChild(cylinder1);
		tableTransform.setTranslation(new Vector3f(5.5f, 0f, -5.5f));  //placing the top of the tble at the origion
		tableTopTG.setTransform(tableTransform);
		table.addChild(tableTopTG);  //add the table top the the table group
		
		//middle shelf
		Cylinder cylinder2 = new Cylinder(1.5f,0.2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);
		TransformGroup middleShelfTG = new TransformGroup();	
		middleShelfTG.addChild(cylinder2);
		tableTransform.setTranslation(new Vector3f(5.5f, -1.5f, -5.5f));  //placing the middle shelf
		middleShelfTG.setTransform(tableTransform);
		table.addChild(middleShelfTG);  //add the middleshelf the the table group
		
		//bottom shelf
		Cylinder cylinder3 = new Cylinder(1.5f,0.2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);
		TransformGroup bottomShelfTG = new TransformGroup();	
		bottomShelfTG.addChild(cylinder3);
		tableTransform.setTranslation(new Vector3f(5.5f, -3f, -5.5f));  //placing the bottomshelf
		bottomShelfTG.setTransform(tableTransform);
		table.addChild(bottomShelfTG);  //add the bottomshelf the the table group
		
		//table leg, back
		Cylinder backLeg = new Cylinder(.13f,3.5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);
		TransformGroup backLegTG = new TransformGroup();
		backLegTG.addChild(backLeg);
		tableTransform.setTranslation(new Vector3f(5.5f,-1.65f,-7f));  //placing the leg in the right place
		backLegTG.setTransform(tableTransform);
		table.addChild(backLegTG);	
		
		//table leg, left
		Cylinder leftLeg = new Cylinder(.13f,3.5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);
		TransformGroup leftLegTG = new TransformGroup();
		leftLegTG.addChild(leftLeg);
		tableTransform.setTranslation(new Vector3f(4f,-1.65f,-5.5f));  //placing the leg in the right place
		leftLegTG.setTransform(tableTransform);
		table.addChild(leftLegTG);	
		
		//table leg, right
		Cylinder rightLeg = new Cylinder(.13f,3.5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,app);
		TransformGroup rightLegTG = new TransformGroup();
		rightLegTG.addChild(rightLeg);
		tableTransform.setTranslation(new Vector3f(7f,-1.65f,-5.5f));  //placing the leg in the right place
		rightLegTG.setTransform(tableTransform);
		table.addChild(rightLegTG);	
	
		return table;
	}
}