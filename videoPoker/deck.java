package videoPoker;

import java.util.*;

public class deck{
  int cards=52;
  int card=0;
  String[] allDrawn;
  Random r = new Random();
  String drawn="+";

  public void shuffle(){
    cards=52;
    drawn="+";
  }

  public int drawCard(){
    if(cards<10){shuffle();}
    card=r.nextInt(cards);
    allDrawn=drawn.split(" ");
    for(int r=0; r<allDrawn.length; ++r){
      for(int x=0; x<allDrawn.length; ++x){
        if(allDrawn[x].equals("+")){break;};
        if (card==Integer.parseInt(allDrawn[x])){++card;}
      }
    }

    if (card==52){
      card=0;
      for(int x=0; x<allDrawn.length; ++x){
        if(allDrawn[x]=="+"){break;};
        if (card==Integer.parseInt(allDrawn[x])){++card;}
      }
    }
    drawn=(card+" "+drawn);
    --cards;
    return card;
  }

  public int findSuit(int x){
    //0=diamond
    //1=club 
    //2=heart
    //3=spade
    return (x%4);
  }

  public int findCard(int x){
    return ((x-findSuit(x))/4);
  }
}


