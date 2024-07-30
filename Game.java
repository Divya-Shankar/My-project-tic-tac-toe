import java.util.*;

class Board{
static char board[][];

public Board()
{
    board=new char[3][3];
    for(int i=0; i<3; i++)
    for(int j=0; j<3; j++)
     board[i][j]= ' ';
}

static void placevalue(int row,int col,char ch)
{
    if(row>=0 && row<=2 && col>=0 && col<=2)
    {
            board[row][col]=ch;  
    } 
}

public boolean checkwinr(){
    for(int i=0; i<3; i++)
    {
    if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
    return true ;
    }

    return false;
}
public boolean checkwinc(){
    for(int i=0; i<3; i++)
    {
    if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i])
    return true;
    }
    return false;
}

public boolean checkwind(){
    
    if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2])
    return true;

    else
    return false;
}

public boolean checkdraw(){
    for(int i=0; i<3; i++)
    {
        for(int j=0; j<3; j++)
        {
        if(board[i][j]==' ') return false;
        }
    }

    return true;
}

public void displayboard(){
    System.out.println("---------------");
   for(int i=0; i<3; i++)
   {
    System.out.print(" | ");
    for(int j=0; j<3; j++)
    {
     System.out.print(board[i][j]+" | ");
     
    }
    System.out.println("");
    System.out.println("---------------");
   }     
}
}
abstract class Player
{
    String name;
    char mark;

    abstract void move();
    public boolean isvalid(int r,int c)
    {
      if(r>=0 && r<3 && c>=0 && c<3 && Board.board[r][c]==' ')
      return true;
      else
      return false;
  
    }
}

class Human extends Player{

    Human(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
  public void move()
  {
    Scanner sc=new Scanner(System.in);
    int r;
    int c;
    do{

    System.out.println("enter the row and col");
         r=sc.nextInt();
         c=sc.nextInt();
    }while(!isvalid(r,c));

    Board.placevalue(r,c,this.mark);
  }

}

class AI extends Player{

    AI(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
  public void move()
  {
    
    int r;
    int c;
    do{
        Random ran=new Random();
        r=ran.nextInt(3);
        c=ran.nextInt(3);

    }while(!isvalid(r,c));

    Board.placevalue(r,c,this.mark);
  }

}

class Game{
    public static void main(String args[])
    {
       Board nboard=new Board();
       Human h1=new Human("div",'o');
       Human h2=new Human("ram",'x');
        AI ai=new AI("ai",'x');
       
       Player ch;
       ch=h1;
         
       while(true)
       {
       System.out.println(ch.name+ " turn");
       ch.move();
       nboard.displayboard();
       if(nboard.checkwinr() ||nboard.checkwinc() || nboard.checkwind() )
       {
       System.out.println(ch.name+" won");
       break;
    }
    else if(nboard.checkdraw())
      {
        System.out.println("match draw");
       break;
      }
    else
    {
        if(ch==h1) ch=ai;
        else ch=h1;
    }


       }
       
    }

}