%{
	#include<stdio.h>
	FILE *yyin;
	int yylex();
	void yyerror(char*);
%}

%token intType floatType charType stringType boolType variable assignment comma intVal floatVal charVal stringVal boolVal nl sc

%%

S :	floatDec
	| intDec
	| charDec
	| stringDec

intVar : variable 
	| variable assignment intVal 
	| variable comma intVar 
	| variable assignment intVal comma intVar

intDec: intType intVar sc nl {printf("\nInt type valid declaration!\n");return 0;}

floatVar : variable 
	| variable assignment floatVal 
	| variable comma floatVar 
	| variable assignment floatVal comma floatVar

floatDec: floatType floatVar sc nl {printf("\nFloat type valid declaration!\n");return 0;}

charVar : variable 
	| variable assignment charVal 
	| variable comma charVar 
	| variable assignment charVal comma charVar

charDec: charType charVar sc nl {printf("\nChar type valid declaration!\n");return 0;}

stringVar : variable 
	| variable assignment stringVal 
	| variable comma stringVar 
	| variable assignment stringVal comma stringVar

stringDec: stringType stringVar sc nl {printf("\nString type valid declaration!\n");return 0;}

%%
void yyerror(char *s )
{ 
	fprintf(stderr, "ERROR: %s\n",s);
}
int main()
{
	yyin=fopen("inp","r");
	yyparse();
	fclose(yyin);
	return 0;
}

