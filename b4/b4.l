%{
	#include<stdio.h>
	#include "y.tab.h"
%}

digit [0-9]
real {digit}+[.]{digit}*
letter [a-zA-z]

%%

"int" {printf("\n%s = Int Datatype",yytext);return intType;}
"float" {printf("\n%s = Float Datatype",yytext);return floatType;}
"char" {printf("\n%s = Char Datatype",yytext);return charType;}
"String" {printf("\n%s = String Dataype",yytext);return stringType;}
"boolean" {printf("\n%s = Boolean Datatype",yytext);return boolType;}
"=" {printf("\n%s = Assignment",yytext);return assignment;}
"," {printf("\n%s = Comma",yytext);return comma;}
{digit}+ {printf("\n%s = Int Value",yytext);return intVal;}
{real} {printf("\n%s = Float Value",yytext);return floatVal;}
"true"|"false" {printf("\n%s = Boolean Value",yytext);return boolVal;}
['][^\t\n]['] {printf("\n%s = Char Value",yytext);return charVal;}
["][^\t\n]+["] {printf("\n%s = String Value",yytext);return stringVal;}
({letter}|"_")({letter}|{digit}|"_")* {printf("\n%s = Variable",yytext);return variable;}
[\n] {printf("\n%s = NewLine",yytext);return nl;}
[;] {printf("\n%s = Semicoln",yytext);return sc;}

%%

int yywrap()
{
	return 1;
}
