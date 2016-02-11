package videoPoker;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class ImagePanel extends JPanel{
  public Color transparent=new Color(254,254,254);
  public ArrayList<ImageLayer> LayerList=new ArrayList<ImageLayer>(); 

  public ImagePanel(){}

  //override paint method of panel
  public void paint(Graphics g){
  //draw the image

    for(ImageLayer il : LayerList){
      for(sprite sp : il.spritelist){
        if(sp.visible)g.drawImage(sp.img,sp.x,sp.y,transparent,this);
      }
    }
  }
}


