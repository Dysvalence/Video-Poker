package videoPoker;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

class anAction extends AbstractAction{
  public int x;
  public boolean[] input;
  public anAction(int a, boolean[] in){
    x=a; //0<=a<=4
    input=in;
  }
  public void actionPerformed(ActionEvent e) {
    input[x]=false;  
    input[x]=true;
  }
}