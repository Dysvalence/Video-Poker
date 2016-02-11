package videoPoker;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

class keyClass implements KeyListener{

  public boolean[] keys = new boolean[600];

  public void keyPressed(KeyEvent e){
    keys[e.getKeyCode()] = true;
  }
 
  public void keyReleased(KeyEvent e){
    keys[e.getKeyCode()] = false;
  }

  public void keyTyped(KeyEvent e){}

}