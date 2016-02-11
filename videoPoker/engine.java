package videoPoker;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class engine{

public static int state=-1;
public static long time=0;

//Card images are 300x200 pixels, these values include a 5 pixel offset
public final static int CARD_WIDTH = 205; //Image size in pixels

public static int[] hand=new int[5];
public static int[] card=new int[5];
public static int[] suit=new int[5];
public static boolean held1=false;
public static boolean held2=false;
public static boolean held3=false;
public static boolean held4=false;
public static boolean held5=false;
public static boolean trigger=false;
public static boolean trigger1=false;
public static boolean trigger2=false;
public static boolean trigger3=false;
public static boolean trigger4=false;
public static boolean trigger5=false;
public static int cash=50;
public static int bet=0;
public static int b=0;

public static void main(String[] args) throws IOException{
  JFrame frame=new JFrame();
  ImagePanel panel=new ImagePanel();
  frame.getContentPane().add(panel);
  deck Deck=new deck();
  
  for(int layers=0; layers<9; ++layers){
    panel.LayerList.add(new ImageLayer());
  }
  JFrame frame2=new JFrame();

  frame2.setLayout(new FlowLayout());

  frame.setBounds(0,0,1035,340); //adj
  frame2.setBounds(100,370,700,80); //adj
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  keyClass input=new keyClass();
  mouseClass mouse=new mouseClass();
  frame.addKeyListener(input);
  frame.addMouseListener(mouse);
  frame.setResizable(false);
  frame.setVisible(true);
  frame2.setVisible(true);

  JLabel currCash=new JLabel("Cash: "+cash);
  JLabel events = new JLabel("Welcome to VidPoker 0.2");

  anAction act1=new anAction(49, input.keys);
  anAction act2=new anAction(50, input.keys);
  anAction act3=new anAction(51, input.keys);
  anAction act4=new anAction(52, input.keys);
  anAction act5=new anAction(53, input.keys);
  anAction act6=new anAction(10, input.keys);

  JButton hold1=new JButton(act1);
  JButton hold2=new JButton(act2);
  JButton hold3=new JButton(act3);
  JButton hold4=new JButton(act4);
  JButton hold5=new JButton(act5);
  JButton hold6=new JButton(act6);

  hold1.setText("bet  1");
  hold2.setText("bet  2");
  hold3.setText("bet  3");
  hold4.setText("bet  4");
  hold5.setText("bet  5");
  hold6.setText(" Draw ");
  currCash.setText("Cash: "+cash);
  
  frame2.add(currCash);
  frame2.add(hold1);
  frame2.add(hold2);
  frame2.add(hold3);
  frame2.add(hold4);
  frame2.add(hold5);
  frame2.add(hold6);
  frame2.add(events);

  
/*
 * layers:
 * 0:card background
 * 1:suit, top
 * 2:number, top
 * 3:suit, bottom
 * 4:number, bottom
 * 5:cover
 * 6:card buffer
 * 7:cardnumberbuffer
 * 8:suit buffer
*/
  
  //card base
  
  for(int s=0; s<5; ++s){
    panel.LayerList.get(0).spritelist.add(new sprite());
  }
  //suit & number
  
  for(int s=0; s<5; ++s){
    panel.LayerList.get(1).spritelist.add(new sprite());
    panel.LayerList.get(2).spritelist.add(new sprite());
    panel.LayerList.get(3).spritelist.add(new sprite());
    panel.LayerList.get(4).spritelist.add(new sprite());
  }
  
//suitbuffer
  
  for(int s=0; s<4; ++s){
    panel.LayerList.get(8).spritelist.add(new sprite());
  }
  
//cardnumberbuffer
   for(int s=0; s<13; ++s){
     panel.LayerList.get(7).spritelist.add(new sprite());
   }
   
//hold, covered
  for(int s=0; s<5; ++s){
    panel.LayerList.get(5).spritelist.add(new sprite());
  }
  
//cardbuffer
  for(int s=0; s<5; ++s){
    panel.LayerList.get(6).spritelist.add(new sprite());
  }
  
//arranging the graphics

//initial buffer
  panel.LayerList.get(6).spritelist.get(0).img = ImageIO.read(new File("facedown.png"));
  panel.LayerList.get(6).spritelist.get(1).img = ImageIO.read(new File("held.png"));
  panel.LayerList.get(6).spritelist.get(2).img = ImageIO.read(new File("blank.png"));

  for(int x=0;x<13;x++){
    panel.LayerList.get(7).spritelist.get(x).img =ImageIO.read(new File(x+".png")); //Load card numbers
  }

  for(int s=0; s<4; ++s){
    panel.LayerList.get(8).spritelist.get(s).img =ImageIO.read(new File("s"+s+".png"));//Load suits
  }
    


//buffer assignments

  for(int s=0; s<5; ++s){  
    panel.LayerList.get(0).spritelist.get(s).img =panel.LayerList.get(6).spritelist.get(2).img;
    panel.LayerList.get(0).spritelist.get(s).x=5+(s*CARD_WIDTH);
    panel.LayerList.get(0).spritelist.get(s).y=5;
    panel.LayerList.get(0).spritelist.get(s).visible=true;
    
    panel.LayerList.get(5).spritelist.get(s).img =panel.LayerList.get(6).spritelist.get(0).img;
    panel.LayerList.get(5).spritelist.get(s).x=5+(s*CARD_WIDTH);
    panel.LayerList.get(5).spritelist.get(s).y=5;
    panel.LayerList.get(5).spritelist.get(s).visible=true;

    panel.LayerList.get(1).spritelist.get(s).x=65+100+(s*CARD_WIDTH);
    panel.LayerList.get(1).spritelist.get(s).y=15;
    panel.LayerList.get(1).spritelist.get(s).visible=true;
    
    panel.LayerList.get(2).spritelist.get(s).x=65+100+(s*CARD_WIDTH);
    panel.LayerList.get(2).spritelist.get(s).y=55;
    panel.LayerList.get(2).spritelist.get(s).visible=true;

    panel.LayerList.get(3).spritelist.get(s).x=15+(s*CARD_WIDTH);
    panel.LayerList.get(3).spritelist.get(s).y=265;
    panel.LayerList.get(3).spritelist.get(s).visible=true;

    panel.LayerList.get(4).spritelist.get(s).x=15+(s*CARD_WIDTH);
    panel.LayerList.get(4).spritelist.get(s).y=225;
    panel.LayerList.get(4).spritelist.get(s).visible=true;
  }


//END LOADING
System.out.println("Loaded");


while(true){
if((System.currentTimeMillis()-time)>42){
time=System.currentTimeMillis();
//FRAMES PER SEC=~30

if(cash<=0){
events.setText("Friend loans you $50");
cash=50;
currCash.setText("Cash: 50");
}

/*
 * States:
 * -1:Reset
 * 0: Initial betting
 * 1: Randomly Draw and reveal cards
 * 2: Unused
 * 3: Choosing cards to hold
 * 4: Second draw, and determine payout if any
 * 5: Click/key handling
 * 6: Click/key handling
 * 7: Click/key handling
 */

if(state==-1){
 currCash.setText("Cash: "+cash);
 
 trigger=false;
 trigger1=false;
 trigger2=false;
 trigger3=false;
 trigger4=false;
 trigger5=false;
 
 hold1.setEnabled(true);
 hold2.setEnabled(true);
 hold3.setEnabled(true);
 hold4.setEnabled(true);
 hold5.setEnabled(true);
 hold6.setEnabled(false);
 
 state++;
}

if(state==0){
  hold1.setText("Bet  1");
  hold2.setText("Bet  2");
  hold3.setText("Bet  3");
  hold4.setText("Bet  4");
  hold5.setText("Bet  5");
  hold6.setText(" Draw ");

  for(int s=0; s<5; ++s){
    panel.LayerList.get(0).spritelist.get(s).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  
  held1=false;
  held2=false;
  held3=false;
  held4=false;
  held5=false;

  for(int s=0; s<5; ++s){
    panel.LayerList.get(5).spritelist.get(s).visible=true;
  }
  
  if(input.keys[49]){--cash; ++state; bet=1;System.out.println("Bet 1");}
  if(input.keys[50]){cash=cash-2; ++state; bet=2;System.out.println("Bet 2");}
  if(input.keys[51]){cash=cash-3; ++state; bet=3;System.out.println("Bet 3");}
  if(input.keys[52]){cash=cash-4; ++state; bet=4;System.out.println("Bet 4");}
  if(input.keys[53]){cash=cash-5; ++state; bet=5;System.out.println("Bet 5");}

}

if (state==1){
  currCash.setText("Cash: "+cash);
  
  for(int s=0; s<5; ++s){
    panel.LayerList.get(5).spritelist.get(s).visible = false;
  }
  
  for(int x=0;x<5;++x){
    hand[x]=Deck.drawCard();
    panel.LayerList.get(1).spritelist.get(x).img=panel.LayerList.get(8).spritelist.get(Deck.findSuit(hand[x])).img;
    panel.LayerList.get(2).spritelist.get(x).img=panel.LayerList.get(7).spritelist.get(Deck.findCard(hand[x])).img;
    panel.LayerList.get(3).spritelist.get(x).img=panel.LayerList.get(1).spritelist.get(x).img;
    panel.LayerList.get(4).spritelist.get(x).img=panel.LayerList.get(2).spritelist.get(x).img;
  }
  
  state=3;
}

if(state==3){
  
  //mouse
  if(mouse.clicked){
    if((mouse.x<205)&&(mouse.x>5)&&(mouse.y<200)&&(mouse.y>5)){held1=!held1;mouse.clicked=false;}
    if((mouse.x<410)&&(mouse.x>210)&&(mouse.y<200)&&(mouse.y>5)){held2=!held2;mouse.clicked=false;}
    if((mouse.x<615)&&(mouse.x>415)&&(mouse.y<200)&&(mouse.y>5)){held3=!held3;mouse.clicked=false;}
    if((mouse.x<820)&&(mouse.x>620)&&(mouse.y<200)&&(mouse.y>5)){held4=!held4;mouse.clicked=false;}
    if((mouse.x<1030)&&(mouse.x>825)&&(mouse.y<200)&&(mouse.y>5)){held5=!held5;mouse.clicked=false;}
  }
  
  if(!mouse.clicked&&mouse.held){mouse.held=false;}
  //endmouse
  
  hold6.setEnabled(true);

  hold1.setText("Hold 1");
  hold2.setText("Hold 2");
  hold3.setText("Hold 3");
  hold4.setText("Hold 4");
  hold5.setText("Hold 5");

  //Keyboard
  if(!input.keys[10]){trigger=true;}
  if(input.keys[10]&&trigger){++state; trigger=false;}
  
  if(!input.keys[49]){trigger1=true;}
  if(input.keys[49]&&trigger1){held1=!held1;trigger1=false;}
  
  if(!input.keys[50]){trigger2=true;}
  if(input.keys[50]&&trigger2){held2=!held2;trigger2=false;}
  
  if(!input.keys[51]){trigger3=true;}
  if(input.keys[51]&&trigger3){held3=!held3;trigger3=false;}
  
  if(!input.keys[52]){trigger4=true;}
  if(input.keys[52]&&trigger4){held4=!held4;trigger4=false;}
  
  if(!input.keys[53]){trigger5=true;}
  if(input.keys[53]&&trigger5){held5=!held5;trigger5=false;}
  //end Keyboard
  
  if(held1){
    panel.LayerList.get(0).spritelist.get(0).img=panel.LayerList.get(6).spritelist.get(1).img;
  }
  else{
    panel.LayerList.get(0).spritelist.get(0).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  
  if(held2){
    panel.LayerList.get(0).spritelist.get(1).img=panel.LayerList.get(6).spritelist.get(1).img;
  }
  else{
    panel.LayerList.get(0).spritelist.get(1).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  
  if(held3){
    panel.LayerList.get(0).spritelist.get(2).img=panel.LayerList.get(6).spritelist.get(1).img;
  }
  else{
    panel.LayerList.get(0).spritelist.get(2).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  
  if(held4){
    panel.LayerList.get(0).spritelist.get(3).img=panel.LayerList.get(6).spritelist.get(1).img;
  }
  else{
    panel.LayerList.get(0).spritelist.get(3).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  
  if(held5){
    panel.LayerList.get(0).spritelist.get(4).img=panel.LayerList.get(6).spritelist.get(1).img;
  }
  else{
    panel.LayerList.get(0).spritelist.get(4).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
}

if (state==4){
  hold1.setEnabled(false);
  hold2.setEnabled(false);
  hold3.setEnabled(false);
  hold4.setEnabled(false);
  hold5.setEnabled(false);
  
  for(int s=0; s<5; ++s){
    panel.LayerList.get(0).spritelist.get(s).img=panel.LayerList.get(6).spritelist.get(2).img;
  }
  if(!held1){hand[0]=Deck.drawCard();}
  if(!held2){hand[1]=Deck.drawCard();}
  if(!held3){hand[2]=Deck.drawCard();}
  if(!held4){hand[3]=Deck.drawCard();}
  if(!held5){hand[4]=Deck.drawCard();}

  for(int x=0;x<5;++x){
    panel.LayerList.get(1).spritelist.get(x).img=panel.LayerList.get(8).spritelist.get(Deck.findSuit(hand[x])).img;
    panel.LayerList.get(2).spritelist.get(x).img=panel.LayerList.get(7).spritelist.get(Deck.findCard(hand[x])).img;
    panel.LayerList.get(3).spritelist.get(x).img=panel.LayerList.get(1).spritelist.get(x).img;
    panel.LayerList.get(4).spritelist.get(x).img=panel.LayerList.get(2).spritelist.get(x).img;
  }

  Arrays.sort(hand);

  for(int x=0;x<5;++x){
    card[x]=Deck.findCard(hand[x]);
    suit[x]=Deck.findSuit(hand[x]);
  }


  payfunc:{
  System.out.println("");
  b=0; //Keeps track of how much of a particular condition is fulfilled. As win conditions have priorities, only one condition is being checked at any given time, and the variable is reused accordingly.
  while(true){

    for(int x=1;x<5;++x){
      if(suit[0]==suit[x]){++b;}
      if (b==4){//flush
        b=0;///
        for(int d=1;d<5;++d){
          if(card[d-1]==(card[d]-1)){++b;}
          if (b==4){//straight flush

            if (card[4]==13){//royal flush
              events.setText("Royal Flush");
              cash+=bet*250;
              break payfunc;
            }
            
            events.setText("Straight Flush");
            cash+=bet*50;
            break payfunc;

          }
        }

        events.setText("Flush");
        cash+=bet*6;
        break payfunc;
      }
    }
    
    b=0;////
    for(int x=1;x<5;++x){
      if(card[x-1]==(card[x]-1)){++b;}
      if (b==4){//straight
        events.setText("Straight");
        cash+=bet*4;
        break payfunc;
      }
    }
    
    b=0;/////
    for(int x=1;x<5;++x){
  
      if(card[0]==card[x]){++b;}
      if (b==3){//four of a kind
        events.setText("Four of a kind");
        cash+=bet*25;
        break payfunc;
      }
    }
    
    b=0;
    for(int x=0;x<4;++x){
  
      if(card[4]==card[x]){++b;}
      if (b==3){//four of a kind
        events.setText("Four of a kind");
        cash+=bet*25;
        break payfunc;
      }
    }


    if(card[1]==card[2]&&card[2]==card[3]){//center triple
      events.setText("Triple");
      cash+=bet*3;
      break payfunc;
    }

    if(card[0]==card[1]&&card[1]==card[2]){
      if(card[3]==card[4]){//fullhouse
      events.setText("Full House");
      cash+=bet*9;break payfunc;
      }
      else{//left triple
        events.setText("Triple");
        cash+=bet*3;
        break payfunc;
      }
    }

    if(card[2]==card[3]&&card[3]==card[4]){
      if(card[0]==card[1]){//fullhouse
        events.setText("Full House");
        cash+=bet*9;break payfunc;
      }
      else{//right triple
        events.setText("Triple");
        cash+=bet*3;
        break payfunc;}
    }
    
    b=0;
    for(int x=1;x<5;++x){
      //equals paircount here
      if(card[x-1]==card[x]){++b;}
      if (b==2){//two pair
        events.setText("Two Pair");
        cash+=bet*2;
        break payfunc;
      }
    }

    for(int x=1;x<5;++x){// >=pair jacks
      if((card[x-1]==card[x])&&((card[x-1]>=10)||(card[x-1]==0))){
        events.setText("Jacks Or Higher");
        cash+=bet;
        break payfunc;
      }
    }

    events.setText("Sorry, Nothing");
    break payfunc;
  }
  }
  
  state=5;
  if(mouse.held){state=7;}
  }

if(state==5){
  Deck.shuffle();
  state=6;
}

if(state==6){

 if(!input.keys[10]){trigger=true;}
  
 if(input.keys[10]&&trigger){state=-1; trigger=false;}
}

if(state==7){
  if(input.keys[10]&&trigger){state=-1; trigger=false;}
}

input.keys[10]=false;
input.keys[49]=false;
input.keys[50]=false;
input.keys[51]=false;
input.keys[52]=false;
input.keys[53]=false;
input.keys[54]=false;
frame.repaint(1, 0, 0, 1035, 340);
}
}

}



}



