%{
#include<stdio.h>
#include"y.tab.h"
%}

%%

"am"|"is"|"are"|"were"|"being"|"do"|"should"|"could"|"sing"|"write"|"did"|"hear"|"have" {printf("\n%s = Verb",yytext);return VERB;}
"gently"|"sweetly"|"easily"|"very" {printf("\n%s = Adverb",yytext);return adverb;}
"best"|"very"|"small"|"large"|"good" {printf("\n%s = Adjective",yytext);return adjective;}
"on"|"above"|"below"|"in" {printf("\n%s = Preposition",yytext);return preposition;}
"i"|"he"|"she"|"they"|"my"|"his"|"her"|"their" {printf("\n%s = Pronoun",yytext);return pronoun;}
"and"|"but"|"yet"|"thus" {printf("\n%s = Conjunction",yytext);return conjunction;}
[a-zA-z]+ {printf("\n%s = Noun",yytext);return noun;} 

%%

int yywrap()
{
	return 1;
}
