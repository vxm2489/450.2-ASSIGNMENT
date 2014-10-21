import java.io.*;

import java.io.IOException;


public class SebestaScanner {
    public static int charClass;
    public static char lexeme[];
    public static char nextChar;
    public static int lexLen;
    public static int token;
    public static int nextToken;
    //public static FileReader inputstream = null;
    public static final int LETTER = 0;
    public static final int DIGIT = 1;
    public static final int UNKNOWN = 99;
    public static final int EOF = -1;
    public static int INT_LIT = 10;
    public static int IDENT = 11;
    public static int ASSIGN_OP = 20;
    public static int ADD_OP = 21;
    public static int SUB_OP = 22;
    public static int MULT_OP = 23;
    public static int DIV_OP = 24;
    public static int LEFT_PAREN = 25;
    public static int RIGHT_PAREN = 26;
    public static BufferedReader buffer=null;
    public static void main(String[] args) throws IOException {
        try {
            buffer=new BufferedReader(new FileReader("C:\\Users\\vineeth\\Documents\\NetBeansProjects\\SebestaScanner\\src\\sebestascanner\\file.txt")); 
            
            
            getChar();
            do {
                lex();
            } while (nextToken != EOF);
        } catch (FileNotFoundException e) {
            System.out.println("File open error");
            System.out.println("Directory:" + System.getProperty("user.in"));
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e){
                    System.out.println("File close error.");
                }
            }
        }
    }
        /*lookup*/
    public static int lookup(char ch) {
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;
    }

    /*addChar - a function to add nextChar to lexeme
     */
    public static void addChar() {
        if (lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } else {
            System.out.println("Error - lexeme is too long \n");
        }
    }

    /*****************************************************/
/* getChar - a function to get the next character of 
 input and determine its character class */
    public static void getChar() throws IOException {
		int r;
		
			if((r = buffer.read()) != -1){
				nextChar = (char)r;
				if(Character.isAlphabetic(nextChar)){
					charClass = LETTER;
				}else if (Character.isDigit(nextChar)){
					charClass = DIGIT;
				}else {
					charClass = UNKNOWN;
				}
				
			}
                        else 
                        {
				/*System.out.println(r);*/
				charClass = EOF;
				nextChar = '\0';
			}
		 	
    }
   public static void getNonBlank() throws IOException {
        while (Character.isSpaceChar(nextChar))
            getChar();
         }

    public boolean isAlpha(String name) {
          return name.matches("[a-zA-Z]+");
    }
    
    public static int lex() throws IOException{
        lexLen=0;
        lexeme = new char[99];
		getNonBlank();
		switch(charClass)
		{
        case LETTER :
            addChar();
            getChar();
            
            int i=0;
            while(charClass == LETTER)
            {
            	
                 addChar();
                 getChar();
                 
            }
            nextToken =IDENT;
            break;
        
        case DIGIT :
            addChar();
           getChar();
           while(charClass == DIGIT)
           {
                addChar();
                getChar();
           }
           nextToken =INT_LIT;
           break;
        
        case UNKNOWN :
            nextToken=lookup(nextChar);
            getChar();
            break;
        case EOF:        	
            nextToken = EOF;
            lexeme[0] ='E';
            lexeme[1] ='O';
            lexeme[2] ='F';
            
            return nextToken;
            
             
   
            
		}
		
	System.out.println("Next token is :"+nextToken+"  Next lexeme is :"+new String(lexeme));	
	return nextToken;
    }

}
