import MG2D.geometrie.Point;
import MG2D.geometrie.Texture;

public class Interface extends Texture {

 //constructeur Interface
	public Interface(String image,Point a,int x,int y) {
		super("img/"+image,a,x,y);
	}

	public Interface(String image,Point a) {
		super("img/"+image,a);
	}
}
