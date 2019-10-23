%{
#include<stdio.h>
FILE *yyin;
void yyerror(char *);
int yylex();
%}

%token VERB adverb adjective preposition pronoun conjunction noun

%%

sentence : simple {printf("\nSimple Sentence!");}
	| compound {printf("\nCompount Sentence!");}
	;

simple : subject verb object 
	| subject verb object prepo 
	;

compound : simple conjunction simple
	| compound conjunction simple
	;

subject : noun
	| pronoun
	| pronoun subject
	;

object : noun
	| adjective object
	;

verb : VERB
	| adverb VERB
	| verb VERB
	;

prepo : preposition noun;

%%

void yyerror(char *s)
{
	fprintf(stderr,"Error : %s\n",s);
}

int main()
{
	yyin=fopen("in","r");
	yyparse();
	fclose(yyin);
}
