
import MG2D.*;
import MG2D.geometrie.*;
import MG2D.Clavier;
import MG2D.geometrie.Couleur;
import java.awt.Font;
import java.lang.String;


import MG2D.audio.*;


class Main{
public static void main(String[]args) {

//attributs pour la mise en place du jeu de golf
Fenetre fenetre = new Fenetre("Mini Golf", 1200, 600);
Clavier clavier = fenetre.getClavier();

boolean faux = false;
boolean vrai = true;



//mise en place de l'image en arrière plan
int backgX = 0;

Interface background1 = new Interface("background.png",new Point(0,0));
Interface background2 = new Interface("background.png",new Point(1600,0));
fenetre.ajouter(background1);
fenetre.ajouter(background2);


//Dans cette partie, le code mets en place les différents texte de description dans l'interface
Texte prem_etape = new Texte(Couleur.NOIR, "Appuyez sur ESPACE pour choisir l'angle de tir.", new Font("Calibri",Font.TYPE1_FONT, 18), new Point(600,350));
fenetre.ajouter(prem_etape);
Texte deux_etape = new Texte(Couleur.NOIR, "Appuyez sur touche HAUT pour choisir l'angle de tir.", new Font("Calibri",Font.TYPE1_FONT, 18), new Point(600,350));
//f.ajouter(deux_etape);
Texte troi_etape = new Texte(Couleur.NOIR, "Hauteur :", new Font("Calibri",Font.TYPE1_FONT, 18), new Point(600,550));
fenetre.ajouter(troi_etape);
Texte text_score = new Texte(Couleur.NOIR, "Score : ", new Font("Calibri",Font.TYPE1_FONT, 18), new Point(1000,550));
fenetre.ajouter(text_score);
Texte text_hauteur = new Texte(Couleur.NOIR, "Hauteur :", new Font("Calibri",Font.TYPE1_FONT, 18), new Point(600,550));
fenetre.ajouter(text_hauteur);


// création du rectangle et du curseur de puissance + attributs qui permettent de gerer le curseur de puissance
int xcurseur = 1;
int accélération_curseur = 1;

Rectangle rectangle_curseur = new Rectangle(Couleur.NOIR, new Point(400,20), 400, 30, vrai);
Rectangle curseur = new Rectangle(Couleur.ROUGE, new Point(400,20), 20, 30, vrai);
fenetre.ajouter(rectangle_curseur);
fenetre.ajouter(curseur);



//Création de l'angle de tire
int xangle = 300;
int yangle = 100;
int direction_angle = 0;

Ligne angle = new Ligne(Couleur.BLANC, new Point(118,100), new Point(xangle,yangle));
fenetre.ajouter(angle);



//mise en place de la balle dans la Fenetre
int balleY = 0;
int balleX = 0;
int hauteurMax;

Cercle balle = new Cercle(Couleur.NOIR,new Point (118,100),4 , vrai);
fenetre.ajouter(balle);


//Mise en place et création de l'mouvement du golfeur, lorsque le jeu sera activé
int xplayer = 0;

Animation mouvement = new Animation("IMG/joueurb", "2", "3","png",new Point(50,90));
fenetre.ajouter(mouvement);
mouvement.setLecture(false);

//Mise en place de la musique
//Musique m = new Musique("test.mp3");

/***********************************************************************************************************************************************/
/***********************************************************************************************************************************************/


while(vrai){
try{
  Thread.sleep(15);
} catch (Exception e) {}


//Mouvement du Joueur de Golf
mouvement.translater(xplayer,0) ;



//Les "si" ci-dessous permettent aux background (1-2) de retourner les uns derrieres les autres pour donner un effet d'infinité
background1.translater(backgX,0);
background2.translater(backgX,0);

if(background1.getA().getX() < -1600)
{
   background1.translater(3200,0);
}

if(background2.getA().getX() < -1600)
{
   background2.translater(3200,0);
}



//Cela crée l'effet de boum rang de la puissance avec changement de couleur.

fenetre.ajouter(curseur) ;
curseur.translater(xcurseur,0) ;

if(curseur.getB().getX() < 410){
   xcurseur = 2;
}

if(curseur.getB().getX() > 200 && 700 > curseur.getB().getX()){
   curseur.setCouleur(Couleur.JAUNE);
   xcurseur = accélération_curseur + xcurseur;
}

if(curseur.getB().getX() > 700){
   xcurseur = accélération_curseur + xcurseur;
   curseur.setCouleur(Couleur.ORANGE);
}

if(curseur.getB().getX() > 800){
   xcurseur = accélération_curseur - xcurseur;
}





//Angle de puissance
angle.setB(new Point(xangle -= direction_angle, yangle+= direction_angle));
angle.setCouleur(Couleur.NOIR);

if(xangle < 600 && yangle > 200)   //condition qui fait en sorte de faire descendre la barre d'angle lorsque celle-ci est a son maximum
{
  direction_angle = direction_angle - 1;
}

if(xangle > 100 && yangle < 100)   //condition qui fait en sorte de faire remonter la barre d'angle. Lorsque celle-ci est au minimum
{
  direction_angle = direction_angle + 1 ;
}


//angle de la balle
if(yangle > 90 && 120 > yangle){
   hauteurMax = 100 ;
}

if(yangle > 120 && 150 > yangle){
   hauteurMax = 500 ;
}

if(yangle > 150 && 195 > yangle){
   hauteurMax = 700 ;
}




//mouvement de la balles lorsque le joueur a choisit la puissance et l'angle
balle.translater(balleX , balleY) ;

if(balle.getO().getY() > 400){
   balleY = balleY - 1;
}

if(balle.getO().getY() < 90){
    balleY = -balleY;
    backgX = backgX + 1;
}

/*******Première étape****************************************************************************************************************************/

if(clavier.getEspace())
{
  fenetre.supprimer(prem_etape) ;
  xcurseur = 0 ; //lorsque le joueur appuie sur la barre d'espace on passe a la deuxiéme étape : choisir l'angle de tire. xcurseur = 0 permet de stopper le curseur de puissance.
  accélération_curseur = 0 ;

  fenetre.ajouter(deux_etape) ;
  fenetre.ajouter(angle);
  direction_angle = 1;

//  m.lecture();
}



/*******Deuxième étape****************************************************************************************************************************/

if(clavier.getHautTape()){

mouvement.setLecture(true);

fenetre.supprimer(deux_etape);
fenetre.supprimer(angle);

direction_angle = 0 ;
balleY = 3 ;



    //Puissance de la balle
    if(curseur.getB().getX() > 400 && 500 > curseur.getB().getX()){
        xplayer = -8;
        backgX = -8 ;
    }
    if(curseur.getB().getX() > 500 && 600 > curseur.getB().getX()){
        xplayer = -16;
        backgX = -16;
    }
    if(curseur.getB().getX() > 600 && 800 > curseur.getB().getX()){
        backgX = -28;
        xplayer = -28;
    }
    if(curseur.getB().getX() > 800){
        backgX = -40 ;
        xplayer = -40;
    }

fenetre.supprimer(deux_etape);
fenetre.supprimer(angle);


          }
       }
    }
 }
