package videoPoker;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

class ImageLayer{
  public ArrayList<sprite> spritelist=new ArrayList<sprite>(); 
  
  public ArrayList<sprite> getList(){return spritelist;}
  
  public void addsprite(sprite s){
    spritelist.add(s);
  }
  
  public void addsprite(int x, int y, Image img, boolean visible){
    sprite sp=new sprite();
    sp.x=x;
    sp.y=y;
    sp.img=img;
    sp.visible=visible;
  }

}